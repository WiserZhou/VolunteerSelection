package volunteer_filling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import volunteer_filling.entity.*;
import volunteer_filling.entity.utils.R;
import volunteer_filling.mapper.MatchMapper;
import volunteer_filling.mapper.PreSelectionMapper;
import volunteer_filling.mapper.TeacherMapper;
import volunteer_filling.service.ExcelExportService;
import volunteer_filling.service.TeacherService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/hit_teacher")
public class TeacherController {
  @Autowired private TeacherService teacherService;
  @Autowired ExcelExportService excelExportService;
  @Autowired MatchMapper matchMapper;
  @Autowired TeacherMapper teacherMapper;
  @Autowired PreSelectionMapper preSelectionMapper;

  @GetMapping("/getSelNum/{id}")
  public R getselNum(@PathVariable Integer id) {
    return new R(true, teacherService.getSelNum(id), "获取成功！");
  }

  @GetMapping("/export")
  public ResponseEntity<byte[]> exportToExcel() {
    try {
      List<Teacher> data = teacherService.getAll();
      byte[] excelBytes = excelExportService.exportToExcelOfTeacher(data);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
      headers.setContentDispositionFormData("attachment", "teacherData.xlsx");

      return new ResponseEntity<>(excelBytes, headers, 200);
    } catch (IOException e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping("/getInstance/{id}")
  public R getInstance(@PathVariable Integer id) {
    List<Student> list = teacherService.getInstance(id);
    List<String> list1 = new ArrayList<>();
    List<TeacherSelectStudent> teacherSelectStudentList = preSelectionMapper.getInstance(id);
    for (TeacherSelectStudent teacherSelectStudent : teacherSelectStudentList) {
      String str = teacherSelectStudent.getIsaccept();
      if (Objects.equals(str, "Y")) {
        list1.add("该学生已同意！");
      } else list1.add("该学生还未同意！");
    }

    return new R(true, list, list1);
  }

  @GetMapping("/exportByTeacherId/{teacherId}")
  public ResponseEntity<byte[]> exportToExcelByTeacherId(@PathVariable Integer teacherId) {
    try {
      List<Match> data = matchMapper.getMatchByTeacherId(teacherId);
      byte[] excelBytes = excelExportService.exportToExcelOfTeacherMatch(data);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
      headers.setContentDispositionFormData("attachment", "MatchStudentData.xlsx");

      return new ResponseEntity<>(excelBytes, headers, 200);
    } catch (IOException e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().build();
    }
  }
  // A-8
  @PutMapping("/update_teacher_information")
  public R updateTeaInformation(@RequestBody Teacher teacher) {
    return new R(teacherService.Teacher_update_information(teacher), null, "修改成功！");
  }

  // C-1-陈最
  @GetMapping("/page_select_all_teacher/{currentPage}/{pageSize}")
  public R getPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
    return new R(true, teacherService.getPage(currentPage, pageSize), "获取所有教师信息成功！");
  }

  // C-3
  @PostMapping("/page_select_by_body")
  public R getPageByBody(@RequestBody TeacherSelectBody teacherSelectBody) {
    return new R(true, teacherService.getPageByBody(teacherSelectBody), "获取所查询教师信息成功！");
  }

  // C-10-
  @GetMapping("/is_full/{id}")
  public R getfull(@PathVariable Integer id) {
    Integer x = teacherService.is_full(id);
    if (x == 0) return new R(true, 0, "教师名额已满！");
    else return new R(false, x, "教师名额未满！");
  }
  // A-14
  @GetMapping("/getNum/{id}")
  public R getNum(@PathVariable Integer id) {
    Integer x = teacherService.is_full(id);
    return new R(true, x, "获取教师剩余名额成功！");
  }

  @GetMapping("/setNum/{id}/{selNum}")
  public R setNum(@PathVariable Integer id, @PathVariable Integer selNum) {
    return new R(teacherService.setNum(id, selNum), null, "已更新！");
  }

  @GetMapping("/getTotalNum/{id}")
  public R getTotalNum(@PathVariable Integer id) {
    return new R(true, teacherService.getTotalNum(id), "获取成功！");
  }

  // A-7
  @GetMapping("/getInfo/{username}")
  public R getInfo(@PathVariable String username) {
    Teacher teacherx = teacherService.teacher_getInfo(username);
    if (teacherx != null) return new R(true, teacherx, "信息获取成功！");
    else return new R(false, null, "信息获取失败！");
  }
  // D-2
  @PostMapping("/updateTeacherSatisfaction")
  public R updateTeacherSatisfaction(@RequestBody Teacher teacher) {
    if (teacher.getSatisfaction() > 100 || teacher.getSatisfaction() < 0)
      return new R(false, null, "满意度需在0-100之间！");
    Boolean flag =
        teacherService.updateTeacherSatisfaction(teacher.getId(), teacher.getSatisfaction());
    if (flag) {
      return new R(true, null, "满意度打分成功！");
    } else {
      return new R(false, null, "更新满意度失败！");
    }
  }
}
