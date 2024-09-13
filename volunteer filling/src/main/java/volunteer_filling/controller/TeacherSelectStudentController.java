package volunteer_filling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import volunteer_filling.entity.PreSelection;
import volunteer_filling.entity.Teacher;
import volunteer_filling.entity.TeacherSelectStudent;
import volunteer_filling.entity.utils.R;
import volunteer_filling.mapper.TeacherSelectStudentMapper;
import volunteer_filling.service.ExcelExportService;
import volunteer_filling.service.StudentService;
import volunteer_filling.service.TeacherSelectStudentService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/hit_teacher_select_student")
public class TeacherSelectStudentController {

  @Autowired TeacherSelectStudentService teacherSelectStudentService;
  @Autowired StudentService studentService;
  @Autowired TeacherSelectStudentMapper teacherSelectStudentMapper;
  @Autowired ExcelExportService excelExportService;

  // B-4
  @PostMapping("/tea_select_stu")
  public R teach(@RequestBody TeacherSelectStudent teacherSelectStudent) {
    Boolean y =
        teacherSelectStudentService.isRepeat(
            teacherSelectStudent.getStudentId(), teacherSelectStudent.getTeacherId());
    if (y) return new R(false, null, "不可重复选择！");
    else {
      Boolean x =
          teacherSelectStudentService.teacher_select(
              teacherSelectStudent.getStudentId(), teacherSelectStudent.getTeacherId());
      if (x) return new R(true, null, "选择成功！");
      else return new R(false, null, "选择失败！");
    }
  }

  // B-5
  @PutMapping("/isaccept")
  public R is_accept(@RequestBody TeacherSelectStudent teacherSelectStudent) {
    System.out.println(
        teacherSelectStudent.getTeacherId()
            + "   "
            + teacherSelectStudent.getStudentId()
            + "$$$$$$$$$$$");
    if (!Objects.equals(teacherSelectStudent.getIsaccept(), "N")
        && !Objects.equals(teacherSelectStudent.getIsaccept(), "Y"))
      return new R(false, null, "操作失败！");
    else {
      if (Objects.equals(teacherSelectStudent.getIsaccept(), "N")) {
        Boolean x =
            teacherSelectStudentService.update_isaccept(
                teacherSelectStudent.getIsaccept(),
                teacherSelectStudent.getStudentId(),
                teacherSelectStudent.getTeacherId());
        if (x) return new R(true, null, "拒绝成功！");
        else return new R(false, null, "选择失败！");
      } else if (Objects.equals(teacherSelectStudent.getIsaccept(), "Y")) {
        Boolean x =
            teacherSelectStudentService.update_isaccept(
                teacherSelectStudent.getIsaccept(),
                teacherSelectStudent.getStudentId(),
                teacherSelectStudent.getTeacherId());
        if (x) {
          return new R(
              studentService.Be_selected(teacherSelectStudent.getStudentId()), null, "同意成功！");
        } else return new R(false, null, "选择失败！");
      }
    }
    return new R(false, null, "ERROR！");
  }

  // B-7
  @GetMapping("/stu_getTeachersel/{studentId}")
  public R getteacher(@PathVariable Integer studentId) {
    return new R(true, teacherSelectStudentService.Stu_getInfo(studentId), "获取所有选择自己的老师！");
  }

  @GetMapping("/export")
  public ResponseEntity<byte[]> exportToExcel() {
    try {
      List<TeacherSelectStudent> data = teacherSelectStudentMapper.getAccInfo();
      byte[] excelBytes = excelExportService.exportToExcelOfPre(data);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
      headers.setContentDispositionFormData("attachment", "PreSelectData.xlsx");

      return new ResponseEntity<>(excelBytes, headers, 200);
    } catch (IOException e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping("/getCount")
  public R getCount() {
    List<TeacherSelectStudent> data = teacherSelectStudentMapper.getAccInfo();
    return new R(true, data.size(), "获取提前批匹配人数成功！");
  }
}
