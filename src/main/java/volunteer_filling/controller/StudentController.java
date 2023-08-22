package volunteer_filling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import volunteer_filling.entity.SelectTeacher;
import volunteer_filling.entity.Student;
import volunteer_filling.entity.StudentSelectTeacher;
import volunteer_filling.entity.utils.R;
import volunteer_filling.service.ExcelExportService;
import volunteer_filling.service.StudentSelectTeacherSerivce;
import volunteer_filling.service.StudentService;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/hit_student")
public class StudentController {
  // 输入用户名和密码进行注册，用户名重复则返回false
  @Autowired StudentService studentService;
  @Autowired StudentSelectTeacherSerivce studentSelectTeacherSerivce;
  @Autowired ExcelExportService excelExportService;

  @GetMapping("/export")
  public ResponseEntity<byte[]> exportToExcel() {
    try {
      List<Student> data = studentService.getAll();
      byte[] excelBytes = excelExportService.exportToExcelOfStudent(data);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
      headers.setContentDispositionFormData("attachment", "studentData.xlsx");

      return new ResponseEntity<>(excelBytes, headers, 200);
    } catch (IOException e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping("/exportUnDir")
  public ResponseEntity<byte[]> exportToExcelUn() {
    try {
      List<Student> data = studentSelectTeacherSerivce.getUnDirStu();
      byte[] excelBytes = excelExportService.exportToExcelOfStudent(data);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
      headers.setContentDispositionFormData("attachment", "studentData.xlsx");

      return new ResponseEntity<>(excelBytes, headers, 200);
    } catch (IOException e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping("/getCount")
  public R getCount() {
    List<Student> data = studentSelectTeacherSerivce.getUnDirStu();
    return new R(true, data.size(), "获取未进行志愿填报人数成功！");
  }
  // C-23
  @GetMapping("/getAllData/{studentId}")
  public R getAll(@PathVariable Integer studentId) {

    List<SelectTeacher> msgList = studentSelectTeacherSerivce.getIsAccept(studentId);
    return new R(true, msgList, "获取志愿信息成功！");
  }

  // A-9
  @GetMapping("/getInfo/{username}")
  public R getInfo(@PathVariable String username) {
    Student studentx = studentService.student_getInfo(username);
    if (studentx != null) return new R(true, studentx, "信息获取成功！");
    else return new R(false, null, "信息获取失败！");
  }

  // A-10
  @PutMapping("/update_student_information")
  public R updateStuInformation(@RequestBody Student student) {
    return new R(studentService.student_update_information(student), null, "修改成功！");
  }

  // B-1-周雨凡
  @GetMapping("/page_select_all_student/{currentPage}/{pageSize}")
  public R getPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
    return new R(true, studentService.getPage(currentPage, pageSize), "获取所有的学生信息成功！");
  }

  // C-2-周雨凡
  @GetMapping("/is_finish/{id}")
  public R get_isfinish(@PathVariable Integer id) {
    if (studentService.is_finish(id)) {
      return new R(true, null, "已经匹配完成！");
    } else return new R(false, null, "未匹配！");
  }

  // B-2
  // get请求，传输入的字符串名字
  @GetMapping("/page_select_by_name/{currentPage}/{pageSize}/{queryInfo_String}")
  public R getPageByName(
      @PathVariable Integer currentPage,
      @PathVariable Integer pageSize,
      @PathVariable String queryInfo_String) {
    if (queryInfo_String == null)
      return new R(true, studentService.getPage(currentPage, pageSize), "获取所有学生数据！");
    else
      return new R(
          true, studentService.getPageByName(currentPage, pageSize, queryInfo_String), "搜索结果如下！");
  }

  @GetMapping("/page_select_by_name/{currentPage}/{pageSize}/")
  public R getPageByName1(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
    return new R(true, studentService.getPage(currentPage, pageSize), "获取所有学生数据！");
  }

  // D-2-郑尊文
  @PostMapping("/updateStudentSatisfaction")
  public R updateStudentSatisfaction(@RequestBody Student student) {
    if (student.getSatisfaction() > 100 || student.getSatisfaction() < 0)
      return new R(false, null, "满意度需在0-100之间！");
    Boolean flag =
        studentService.updateStudentSatisfaction(student.getId(), student.getSatisfaction());
    if (flag) {
      return new R(true, null, "满意度打分成功！");
    } else {
      return new R(false, null, "更新满意度失败！");
    }
  }
}
