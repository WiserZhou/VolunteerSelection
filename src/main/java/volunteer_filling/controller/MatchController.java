package volunteer_filling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import volunteer_filling.entity.Match;
import volunteer_filling.entity.Student;
import volunteer_filling.entity.Teacher;
import volunteer_filling.entity.TeacherSelectStudent;
import volunteer_filling.entity.StudentSelectTeacher;
import volunteer_filling.entity.utils.R;
import volunteer_filling.service.ExcelExportService;
import volunteer_filling.service.MatchService;
import volunteer_filling.service.StudentService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/hit_match")
public class MatchController {
  @Autowired MatchService matchService;
  @Autowired StudentService studentService;

  @Autowired ExcelExportService excelExportService;

  @GetMapping("/export")
  public ResponseEntity<byte[]> exportToExcel() {
    try {
      List<Match> data = matchService.getAll();
      byte[] excelBytes = excelExportService.exportToExcelOfMatch(data);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
      headers.setContentDispositionFormData("attachment", "matchData.xlsx");

      return new ResponseEntity<>(excelBytes, headers, 200);
    } catch (IOException e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().build();
    }
  }
  // E-32
  @GetMapping("/get_match/{currentPage}/{pageSize}")
  public R getmatch(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
    return new R(true, matchService.getMatch(currentPage, pageSize), "获取成功！");
  }

  // D-1-郑尊文
  @PostMapping("/rand_match")
  public R matchStudents() {
    List<Student> unMatchedStudents = matchService.getUnmatchedStudents();
    List<Teacher> availableTeachers = matchService.getAvailableTeachers();
    if (unMatchedStudents == null || availableTeachers == null)
      return new R(false, null, "查询不到未录取的学生或有剩余名额的教师。");
    for (Student unMatchedStudent : unMatchedStudents) {
      for (Teacher availableTeacher : availableTeachers) {
        if (availableTeacher.getSelNum() > 0) {
          availableTeacher.setSelNum(availableTeacher.getSelNum() - 1);
          matchService.saveMatch(
              availableTeacher.getTeacherId(),
              availableTeacher.getName(),
              unMatchedStudent.getId(),
              unMatchedStudent.getName(),
              31);
          break;
        }
      }
    }
    return new R(true, null, "随机分配成功！");
  }
  // D-3-郑尊文
  @PostMapping("/rand_match_twice")
  public R matchStudentsTwice() {
    //    matchService.deleteByDescription(31);
    List<Student> unMatchedStudents = matchService.getUnmatchedStudents();
    List<Teacher> availableTeachers = matchService.getAvailableTeachers();
    if (unMatchedStudents == null || availableTeachers == null)
      return new R(false, null, "查询不到未录取的学生或有剩余名额的教师。");
    for (int i = unMatchedStudents.size() - 1; i >= 0; i--) {
      for (Teacher availableTeacher : availableTeachers) {
        if (availableTeacher.getSelNum() > 0) {
          availableTeacher.setSelNum(availableTeacher.getSelNum() - 1);
          matchService.saveMatch(
              availableTeacher.getTeacherId(),
              availableTeacher.getName(),
              unMatchedStudents.get(i).getId(),
              unMatchedStudents.get(i).getName(),
              32);
          break;
        }
      }
    }
    return new R(true, null, "第二次随机分配成功！");
  }

  @GetMapping("/stu_get/{id}")
  public R match_stu(@PathVariable Integer id) {
    List<Match> x = matchService.getTeacher(id);
    if (x.isEmpty()) return new R(false, null, "您未匹配到老师，请联系管理员解决！");
    else {
      return new R(true, x, "已返回匹配信息！");
    }
  }

  @GetMapping("/tea_get/{teacherId}")
  public R match_tea(@PathVariable Integer teacherId) {
    List<Match> x = matchService.getStudent(teacherId);

    List<Student> list = new ArrayList<>();
    for (Match match : x) {
      list.add(studentService.getInfoById(match.getStudentId()));
    }

    if (list.isEmpty()) return new R(false, null, "您未匹配到学生！");
    else return new R(true, list, "已返回匹配信息！");
  }

  static boolean flag1 = false;
  static boolean flag2 = false;

  // D-4-郑尊文
  @PostMapping("/update")
  public R matchUpdate() {
    if (!flag1) return new R(false, null, "未执行插入操作！");
    if (flag2) return new R(false, null, "已执行过更新操作！");
    StringBuilder msg = new StringBuilder();
    List<Match> matchList = matchService.getMatchList();
    for (Match match : matchList) {
      Teacher teacher = matchService.getTeacherInfo(match.getTeacherId());
      Student student = matchService.getStudentInfo(match.getStudentId());
      if (teacher == null) {
        msg.append("更新失败！查找不到id为").append(match.getTeacherId()).append("的教师！\n");
      } else if (student == null) {
        msg.append("更新失败！查找不到id为").append(match.getStudentId()).append("的学生！\n");
      } else {
        matchService.updateMatch(
            match.getStudentId(),
            teacher.getName(),
            teacher.getMajor(),
            student.getName(),
            student.getMajor());
      }
    }
    flag2 = true;
    return new R(true, null, "更新match表完成！\n" + msg);
  }

  @PostMapping("/insert")
  public R matchInsert() {

    if (flag1) return new R(false, null, "不可重复进行插入操作！");
    StringBuilder msg = new StringBuilder();
    List<TeacherSelectStudent> teacherSelectStudentList =
        matchService.getTeacherSelectStudentList();
    List<StudentSelectTeacher> studentSelectTeacherList =
        matchService.getStudentSelectTeacherList();
    for (TeacherSelectStudent teacherSelectStudent : teacherSelectStudentList) {
      Teacher teacher = matchService.getTeacherInfo(teacherSelectStudent.getTeacherId());
      Student student = matchService.getStudentInfo(teacherSelectStudent.getStudentId());
      if (teacher == null) {
        msg.append("更新失败！查找不到id为").append(teacherSelectStudent.getTeacherId()).append("的教师！\n");
      } else if (student == null) {
        msg.append("更新失败！查找不到id为").append(teacherSelectStudent.getStudentId()).append("的学生！\n");
      } else {
        matchService.saveMatch(
            teacher.getTeacherId(), teacher.getName(), student.getId(), student.getName(), 1);
      }
    }
    for (StudentSelectTeacher studentSelectTeacher : studentSelectTeacherList) {
      if (Objects.equals(studentSelectTeacher.getIsAccept1(), "Y")) {
        matchService.saveMatchInt(
            studentSelectTeacher.getSelectTeacher1Id(),
            studentSelectTeacher.getSelectTeacher1(),
            studentSelectTeacher.getStudentId(),
            studentSelectTeacher.getStudentName(),
            2);
      } else if (Objects.equals(studentSelectTeacher.getIsAccept2(), "Y")) {
        matchService.saveMatchInt(
            studentSelectTeacher.getSelectTeacher2Id(),
            studentSelectTeacher.getSelectTeacher2(),
            studentSelectTeacher.getStudentId(),
            studentSelectTeacher.getStudentName(),
            2);
      } else if (Objects.equals(studentSelectTeacher.getIsAccept3(), "Y")) {
        matchService.saveMatchInt(
            studentSelectTeacher.getSelectTeacher3Id(),
            studentSelectTeacher.getSelectTeacher3(),
            studentSelectTeacher.getStudentId(),
            studentSelectTeacher.getStudentName(),
            2);
      } else if (Objects.equals(studentSelectTeacher.getIsAccept4(), "Y")) {
        matchService.saveMatchInt(
            studentSelectTeacher.getSelectTeacher4Id(),
            studentSelectTeacher.getSelectTeacher4(),
            studentSelectTeacher.getStudentId(),
            studentSelectTeacher.getStudentName(),
            2);
      } else if (Objects.equals(studentSelectTeacher.getIsAccept5(), "Y")) {
        matchService.saveMatchInt(
            studentSelectTeacher.getSelectTeacher5Id(),
            studentSelectTeacher.getSelectTeacher5(),
            studentSelectTeacher.getStudentId(),
            studentSelectTeacher.getStudentName(),
            2);
      } else if (Objects.equals(studentSelectTeacher.getIsAccept6(), "Y")) {
        matchService.saveMatchInt(
            studentSelectTeacher.getSelectTeacher6Id(),
            studentSelectTeacher.getSelectTeacher6(),
            studentSelectTeacher.getStudentId(),
            studentSelectTeacher.getStudentName(),
            2);
      }
    }
    flag1 = true;
    return new R(true, null, "match表更新成功！\n" + msg);
  }
}
