package volunteer_filling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import volunteer_filling.entity.Match;
import volunteer_filling.entity.StudentSelectTeacher;
import volunteer_filling.entity.utils.R;
import volunteer_filling.mapper.StudentSelectTeacherMapper;
import volunteer_filling.service.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/hit_student_select_teacher")
public class StudentSelectTeacherController {
  @Autowired private StudentSelectTeacherSerivce studentSelectTeacherSerivce;
  @Autowired private TeacherService teacherService;
  @Autowired private StudentService studentService;
  @Autowired ExcelExportService excelExportService;
  @Autowired StudentSelectTeacherMapper studentSelectTeacherMapper;

  @GetMapping("/export")
  public ResponseEntity<byte[]> exportToExcel() {
    try {
      List<StudentSelectTeacher> data = studentSelectTeacherMapper.getStuSelTeaCount();
      byte[] excelBytes = excelExportService.exportToExcelOfDirection(data);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
      headers.setContentDispositionFormData("attachment", "directionData.xlsx");

      return new ResponseEntity<>(excelBytes, headers, 200);
    } catch (IOException e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping("/getCount")
  public R getCount() {
    List<StudentSelectTeacher> data = studentSelectTeacherMapper.getStuSelTeaCount();
    return new R(true, data.size(), "获取志愿填报人数成功！");
  }

  // C-23
  @PostMapping("/isSelect")
  public R isSelect(@RequestBody StudentSelectTeacher studentSelectTeacher) {
    String msg = "";

    boolean flag = true;
    String teacherName1 = studentSelectTeacher.getSelectTeacher1();
    Integer Id1 = teacherService.getIdByName(teacherName1);

    if (Id1 == null) {
      msg += teacherName1 + "教师查无此人！（第一志愿）\n";
      flag = false;
    } else {
      Integer y1 = teacherService.is_full(Id1);
      if (y1 == 0) {
        msg += teacherName1 + "教师的名额已满！（第一志愿）\n";
        flag = false;
      } else if (y1 == -1) {
        msg += teacherName1 + "教师未设置名额！（第一志愿）\n";
        flag = false;
      } else msg += teacherName1 + "教师有空余名额！（第一志愿）\n";
    }
    String teacherName2 = studentSelectTeacher.getSelectTeacher2();
    Integer Id2 = teacherService.getIdByName(teacherName2);
    if (Id2 == null) {
      msg += teacherName2 + "教师查无此人！（第二志愿）\n";
      flag = false;
    } else {
      Integer y2 = teacherService.is_full(Id2);
      if (y2 == 0) {
        msg += teacherName2 + "教师的名额已满！（第二志愿）\n";
        flag = false;
      } else if (y2 == -1) {
        flag = false;
        msg += teacherName2 + "教师未设置名额！（第二志愿）\n";
      } else msg += teacherName2 + "教师有空余名额！（第二志愿）\n";
      if (Objects.equals(teacherName2, teacherName1)) {
        msg += "你不可以重复选择" + teacherName2 + "老师！（第二志愿）\n";
        flag = false;
      }
    }

    String teacherName3 = studentSelectTeacher.getSelectTeacher3();
    Integer Id3 = teacherService.getIdByName(teacherName3);
    if (Id3 == null) {
      msg += teacherName3 + "教师查无此人！（第三志愿）\n";
      flag = false;
    } else {
      Integer y3 = teacherService.is_full(Id3);
      if (y3 == 0) {
        msg += teacherName3 + "教师的名额已满！（第三志愿）\n";
        flag = false;
      } else if (y3 == -1) {
        msg += teacherName3 + "教师未设置名额！（第三志愿）\n";
        flag = false;
      } else msg += teacherName3 + "教师有空余名额！（第三志愿）\n";
      if (Objects.equals(teacherName3, teacherName1)
          || Objects.equals(teacherName3, teacherName2)) {
        msg += "你不可以重复选择" + teacherName3 + "老师！（第三志愿）\n";
        flag = false;
      }
    }

    String teacherName4 = studentSelectTeacher.getSelectTeacher4();
    Integer Id4 = teacherService.getIdByName(teacherName4);
    if (Id4 == null) {
      msg += teacherName4 + "教师查无此人！（第四志愿）\n";
      flag = false;
    } else {
      Integer y4 = teacherService.is_full(Id4);
      if (y4 == 0) {
        msg += teacherName4 + "教师的名额已满！（第四志愿）\n";
        flag = false;
      } else if (y4 == -1) {
        msg += teacherName4 + "教师未设置名额！（第四志愿）\n";
        flag = false;
      } else msg += teacherName4 + "教师有空余名额！（第四志愿）\n";
      if (Objects.equals(teacherName4, teacherName1)
          || Objects.equals(teacherName4, teacherName2)
          || Objects.equals(teacherName4, teacherName3)) {
        msg += "你不可以重复选择" + teacherName4 + "老师！（第四志愿）\n";
        flag = false;
      }
    }

    String teacherName5 = studentSelectTeacher.getSelectTeacher5();
    Integer Id5 = teacherService.getIdByName(teacherName5);
    if (Id5 == null) {
      msg += teacherName5 + "教师查无此人！（第五志愿）\n";
      flag = false;
    } else {
      Integer y5 = teacherService.is_full(Id5);
      if (y5 == 0) {
        msg += teacherName5 + "教师的名额已满！（第五志愿）\n";
        flag = false;
      } else if (y5 == -1) {
        msg += teacherName5 + "教师未设置名额！（第五志愿）\n";
        flag = false;
      } else msg += teacherName5 + "教师有空余名额！（第五志愿）\n";
      if (Objects.equals(teacherName5, teacherName1)
          || Objects.equals(teacherName5, teacherName2)
          || Objects.equals(teacherName5, teacherName3)
          || Objects.equals(teacherName5, teacherName4)) {
        msg += "你不可以重复选择" + teacherName5 + "老师！（第五志愿）\n";
        flag = false;
      }
    }

    String teacherName6 = studentSelectTeacher.getSelectTeacher6();
    Integer Id6 = teacherService.getIdByName(teacherName6);
    if (Id6 == null) {
      msg += teacherName6 + "教师查无此人！（第六志愿）\n";
      flag = false;
    } else {
      Integer y6 = teacherService.is_full(Id6);
      if (y6 == 0) {
        msg += teacherName6 + "教师的名额已满！（第六志愿）\n";
        flag = false;
      } else if (y6 == -1) {
        msg += teacherName6 + "教师未设置名额！（第六志愿）\n";
        flag = false;
      } else msg += teacherName6 + "教师有空余名额！（第六志愿）\n";
      if (Objects.equals(teacherName6, teacherName1)
          || Objects.equals(teacherName6, teacherName2)
          || Objects.equals(teacherName6, teacherName3)
          || Objects.equals(teacherName6, teacherName4)
          || Objects.equals(teacherName6, teacherName5)) {
        msg += "你不可以重复选择" + teacherName6 + "老师！（第六志愿）\n";
        flag = false;
      }
    }

    return new R(flag, null, msg);
  }
  // C-4-周雨凡
  @PutMapping("/select_teacher1")
  public R select_teacher1(@RequestBody StudentSelectTeacher studentSelectTeacher) {

    Integer Id = teacherService.getIdByName(studentSelectTeacher.getSelectTeacher1());
    if (Id != null) {
      studentSelectTeacher.setSelectTeacher1Id(Id);
      Integer y = teacherService.is_full(studentSelectTeacher.getSelectTeacher1Id());
      boolean z =
          studentSelectTeacherSerivce.isRepeat1(
              studentSelectTeacher.getStudentId(), studentSelectTeacher.getSelectTeacher1Id());

      if (y == 0) return new R(false, null, "选择失败！老师名额已满！");
      else if (z) {
        return new R(false, null, "选择失败！不可重复选择！");
      } else {
        Boolean x = studentSelectTeacherSerivce.select_teacher1(studentSelectTeacher);
        if (x) {
          teacherService.dec_selNum(studentSelectTeacher.getSelectTeacher1Id());
          return new R(true, null, "选择志愿1老师成功!");
        } else return new R(false, null, "选择出现错误！");
      }
    } else {
      return new R(false, null, studentSelectTeacher.getSelectTeacher1() + "教师查无此人！");
    }
  }
  // C-5-周雨凡
  @PutMapping("/select_teacher2")
  public R select_teacher2(@RequestBody StudentSelectTeacher studentSelectTeacher) {
    Integer Id = teacherService.getIdByName(studentSelectTeacher.getSelectTeacher2());
    if (Id != null) {
      studentSelectTeacher.setSelectTeacher2Id(Id);
      Integer y = teacherService.is_full(studentSelectTeacher.getSelectTeacher2Id());
      boolean z =
          studentSelectTeacherSerivce.isRepeat2(
              studentSelectTeacher.getStudentId(), studentSelectTeacher.getSelectTeacher2Id());

      if (y == 0) return new R(false, null, "选择失败！老师名额已满！");
      else if (z) {
        return new R(false, null, "选择失败！不可重复选择！");
      } else {
        Boolean x = studentSelectTeacherSerivce.select_teacher2(studentSelectTeacher);
        if (x) {
          teacherService.dec_selNum(studentSelectTeacher.getSelectTeacher2Id());
          return new R(true, null, "选择志愿2老师成功!");
        } else return new R(false, null, "选择出现错误！");
      }
    } else {
      return new R(false, null, studentSelectTeacher.getSelectTeacher2() + "教师查无此人！");
    }
  }
  // C-6-周雨凡
  @PutMapping("/select_teacher3")
  public R select_teacher3(@RequestBody StudentSelectTeacher studentSelectTeacher) {
    Integer Id = teacherService.getIdByName(studentSelectTeacher.getSelectTeacher3());
    if (Id != null) {
      studentSelectTeacher.setSelectTeacher3Id(Id);
      boolean y = teacherService.is_full(studentSelectTeacher.getSelectTeacher3Id()) == 0;
      boolean z =
          studentSelectTeacherSerivce.isRepeat3(
              studentSelectTeacher.getStudentId(), studentSelectTeacher.getSelectTeacher3Id());
      if (y) return new R(false, null, "选择失败！老师名额已满！");
      else if (z) {
        return new R(false, null, "选择失败！不可重复选择！");
      } else {
        Boolean x = studentSelectTeacherSerivce.select_teacher3(studentSelectTeacher);
        if (x) {
          teacherService.dec_selNum(studentSelectTeacher.getSelectTeacher3Id());
          return new R(true, null, "选择志愿3老师成功!");
        } else return new R(false, null, "选择出现错误！");
      }
    } else return new R(false, null, studentSelectTeacher.getSelectTeacher3() + "教师查无此人！");
  }
  // C-7-周雨凡
  @PutMapping("/select_teacher4")
  public R select_teacher4(@RequestBody StudentSelectTeacher studentSelectTeacher) {
    Integer Id = teacherService.getIdByName(studentSelectTeacher.getSelectTeacher4());
    if (Id != null) {
      studentSelectTeacher.setSelectTeacher4Id(Id);
      boolean y = teacherService.is_full(studentSelectTeacher.getSelectTeacher4Id()) == 0;
      boolean z =
          studentSelectTeacherSerivce.isRepeat4(
              studentSelectTeacher.getStudentId(), studentSelectTeacher.getSelectTeacher4Id());
      if (y) return new R(false, null, "选择失败！老师名额已满！");
      else if (z) {
        return new R(false, null, "选择失败！不可重复选择！");
      } else {
        Boolean x = studentSelectTeacherSerivce.select_teacher4(studentSelectTeacher);
        if (x) {
          teacherService.dec_selNum(studentSelectTeacher.getSelectTeacher4Id());
          return new R(true, null, "选择志愿4老师成功!");
        } else return new R(false, null, "选择出现错误！");
      }
    } else return new R(false, null, studentSelectTeacher.getSelectTeacher4() + "教师查无此人！");
  }
  // C-8-周雨凡
  @PutMapping("/select_teacher5")
  public R select_teacher5(@RequestBody StudentSelectTeacher studentSelectTeacher) {
    Integer Id = teacherService.getIdByName(studentSelectTeacher.getSelectTeacher5());
    if (Id != null) {
      studentSelectTeacher.setSelectTeacher5Id(Id);
      boolean y = teacherService.is_full(studentSelectTeacher.getSelectTeacher5Id()) == 0;
      boolean z =
          studentSelectTeacherSerivce.isRepeat5(
              studentSelectTeacher.getStudentId(), studentSelectTeacher.getSelectTeacher5Id());
      if (y) return new R(false, null, "选择失败！老师名额已满！");
      else if (z) {
        return new R(false, null, "选择失败！不可重复选择！");
      } else {
        Boolean x = studentSelectTeacherSerivce.select_teacher5(studentSelectTeacher);
        if (x) {
          teacherService.dec_selNum(studentSelectTeacher.getSelectTeacher5Id());
          return new R(true, null, "选择志愿5老师成功!");
        } else return new R(false, null, "选择出现错误！");
      }
    } else return new R(false, null, studentSelectTeacher.getSelectTeacher5() + "教师查无此人！");
  }
  // C-9-周雨凡
  @PutMapping("/select_teacher6")
  public R select_teacher6(@RequestBody StudentSelectTeacher studentSelectTeacher) {
    Integer Id = teacherService.getIdByName(studentSelectTeacher.getSelectTeacher6());
    if (Id != null) {
      studentSelectTeacher.setSelectTeacher6Id(Id);
      boolean y = teacherService.is_full(studentSelectTeacher.getSelectTeacher6Id()) == 0;
      boolean z =
          studentSelectTeacherSerivce.isRepeat6(
              studentSelectTeacher.getStudentId(), studentSelectTeacher.getSelectTeacher6Id());
      if (y) return new R(false, null, "选择失败！老师名额已满！");
      else if (z) {
        return new R(false, null, "选择失败！不可重复选择！");
      } else {
        Boolean x = studentSelectTeacherSerivce.select_teacher6(studentSelectTeacher);
        if (x) {
          teacherService.dec_selNum(studentSelectTeacher.getSelectTeacher6Id());
          return new R(true, null, "选择志愿6老师成功!");
        } else return new R(false, null, "选择出现错误！");
      }
    } else return new R(false, null, studentSelectTeacher.getSelectTeacher6() + "教师差无此人！");
  }
  // C-11
  @GetMapping("/get_student_has_select1/{id}")
  public R get_te1(@PathVariable Integer id) {
    return new R(true, studentSelectTeacherSerivce.get_selectStu1(id), "已获取所有选择的学生！");
  }
  // C-13
  @GetMapping("/get_student_has_select2/{id}")
  public R get_te2(@PathVariable Integer id) {
    return new R(true, studentSelectTeacherSerivce.get_selectStu2(id), "已获取所有选择的学生！");
  }
  // C-12
  @PutMapping("/accept1")
  public R accept1(@RequestBody StudentSelectTeacher studentSelectTeacher) {
    System.out.println(studentSelectTeacher);
    if (Objects.equals(studentSelectTeacher.getIsAccept1(), "Y")) {
      Boolean flag = studentService.StuIsFinished(studentSelectTeacher.getStudentId());
      if (flag != null && flag) {
        teacherService.dec_selNum(studentSelectTeacher.getSelectTeacher1Id());
        studentService.Be_selected(studentSelectTeacher.getStudentId());
        return new R(
            studentSelectTeacherSerivce.accept_teacher1(
                studentSelectTeacher.getStudentId(), studentSelectTeacher.getSelectTeacher1Id()),
            null,
            "接受成功！");
      } else return new R(false, null, "该学生已经被录取！");
    } else if (Objects.equals(studentSelectTeacher.getIsAccept1(), "N")) {
      return new R(true, null, "拒绝成功");
    } else return new R(false, null, "操作错误！");
  }

  // C-14
  @PutMapping("/accept2")
  public R accept2(@RequestBody StudentSelectTeacher studentSelectTeacher) {
    if (Objects.equals(studentSelectTeacher.getIsAccept2(), "Y")) {
      Boolean flag = studentService.StuIsFinished(studentSelectTeacher.getStudentId());
      if (flag != null && flag) {
        teacherService.dec_selNum(studentSelectTeacher.getSelectTeacher2Id());
        studentService.Be_selected(studentSelectTeacher.getStudentId());
        return new R(
            studentSelectTeacherSerivce.accept_teacher2(
                studentSelectTeacher.getStudentId(), studentSelectTeacher.getSelectTeacher2Id()),
            null,
            "接受成功！");
      } else return new R(false, null, "该学生已经被录取！");
    } else if (Objects.equals(studentSelectTeacher.getIsAccept2(), "N")) {
      return new R(true, null, "拒绝成功");
    } else return new R(false, null, "操作错误！");
  }
  // C-15
  @GetMapping("/get_student_has_select3/{id}")
  public R get_te3(@PathVariable Integer id) {
    return new R(true, studentSelectTeacherSerivce.get_selectStu3(id), "已获取所有选择的学生！");
  }
  // C-16
  @PutMapping("/accept3")
  public R accept3(@RequestBody StudentSelectTeacher studentSelectTeacher) {
    if (Objects.equals(studentSelectTeacher.getIsAccept3(), "Y")) {
      Boolean flag = studentService.StuIsFinished(studentSelectTeacher.getStudentId());
      if (flag != null && flag) {
        teacherService.dec_selNum(studentSelectTeacher.getSelectTeacher3Id());
        studentService.Be_selected(studentSelectTeacher.getStudentId());
        return new R(
            studentSelectTeacherSerivce.accept_teacher3(
                studentSelectTeacher.getStudentId(), studentSelectTeacher.getSelectTeacher3Id()),
            null,
            "接受成功！");
      } else return new R(false, null, "该学生已经被录取！");
    } else if (Objects.equals(studentSelectTeacher.getIsAccept3(), "N")) {
      return new R(true, null, "拒绝成功");
    } else return new R(false, null, "操作错误！");
  }
  // C-17
  @GetMapping("/get_student_has_select4/{id}")
  public R get_te4(@PathVariable Integer id) {
    return new R(true, studentSelectTeacherSerivce.get_selectStu4(id), "已获取所有选择的学生！");
  }
  // C-18
  @PutMapping("/accept4")
  public R accept4(@RequestBody StudentSelectTeacher studentSelectTeacher) {
    if (Objects.equals(studentSelectTeacher.getIsAccept4(), "Y")) {
      Boolean flag = studentService.StuIsFinished(studentSelectTeacher.getStudentId());
      if (flag != null && flag) {
        teacherService.dec_selNum(studentSelectTeacher.getSelectTeacher4Id());
        studentService.Be_selected(studentSelectTeacher.getStudentId());
        return new R(
            studentSelectTeacherSerivce.accept_teacher4(
                studentSelectTeacher.getStudentId(), studentSelectTeacher.getSelectTeacher4Id()),
            null,
            "接受成功！");
      } else return new R(false, null, "该学生已经被录取！");
    } else if (Objects.equals(studentSelectTeacher.getIsAccept4(), "N")) {
      return new R(true, null, "拒绝成功");
    } else return new R(false, null, "操作错误！");
  }
  // C-19
  @GetMapping("/get_student_has_select5/{id}")
  public R get_te5(@PathVariable Integer id) {
    return new R(true, studentSelectTeacherSerivce.get_selectStu5(id), "已获取所有选择的学生！");
  }
  // C-20
  @PutMapping("/accept5")
  public R accept5(@RequestBody StudentSelectTeacher studentSelectTeacher) {
    if (Objects.equals(studentSelectTeacher.getIsAccept5(), "Y")) {
      Boolean flag = studentService.StuIsFinished(studentSelectTeacher.getStudentId());
      if (flag != null && flag) {
        teacherService.dec_selNum(studentSelectTeacher.getSelectTeacher5Id());
        studentService.Be_selected(studentSelectTeacher.getStudentId());
        return new R(
            studentSelectTeacherSerivce.accept_teacher5(
                studentSelectTeacher.getStudentId(), studentSelectTeacher.getSelectTeacher5Id()),
            null,
            "接受成功！");
      } else return new R(false, null, "该学生已经被录取！");
    } else if (Objects.equals(studentSelectTeacher.getIsAccept5(), "N")) {
      return new R(true, null, "拒绝成功");
    } else return new R(false, null, "操作错误！");
  }
  // C-21
  @GetMapping("/get_student_has_select6/{id}")
  public R get_te6(@PathVariable Integer id) {
    return new R(true, studentSelectTeacherSerivce.get_selectStu6(id), "已获取所有选择的学生！");
  }
  // C-22
  @PutMapping("/accept6")
  public R accept6(@RequestBody StudentSelectTeacher studentSelectTeacher) {
    if (Objects.equals(studentSelectTeacher.getIsAccept6(), "Y")) {
      Boolean flag = studentService.StuIsFinished(studentSelectTeacher.getStudentId());
      if (flag != null && flag) {
        teacherService.dec_selNum(studentSelectTeacher.getSelectTeacher6Id());
        studentService.Be_selected(studentSelectTeacher.getStudentId());
        return new R(
            studentSelectTeacherSerivce.accept_teacher6(
                studentSelectTeacher.getStudentId(), studentSelectTeacher.getSelectTeacher6Id()),
            null,
            "接受成功！");
      } else return new R(false, null, "该学生已经被录取！");
    } else if (Objects.equals(studentSelectTeacher.getIsAccept6(), "N")) {
      return new R(true, null, "拒绝成功");
    } else return new R(false, null, "操作错误！");
  }
}
