package volunteer_filling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import volunteer_filling.entity.*;
import volunteer_filling.entity.utils.R;
import volunteer_filling.service.AdministerService;
import volunteer_filling.service.StudentService;
import volunteer_filling.service.TeacherService;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/hit_administer")
public class AdministerController {
  @Autowired StudentService studentService;
  @Autowired TeacherService teacherService;
  @Autowired AdministerService administerService;

  // A-6-周雨凡
  @PutMapping("/update_password")
  public R update_password(@RequestBody UpdatePassword updatePassword) {
    if (updatePassword.getIdentity() == 1) {
      if (studentService.find_student_account(updatePassword.getUsername()) == null) {
        return new R(false, null, "用户名输入错误！");
      } else if (studentService.student_login(
              updatePassword.getUsername(), updatePassword.getPassword())
          == null) {
        return new R(false, null, "密码错误！");

      } else
        return new R(
            studentService.student_update_password(
                updatePassword.getUpdatePassword(), updatePassword.getUsername()),
            null,
            "更新密码成功！");
    } else if (updatePassword.getIdentity() == 2) {
      if (teacherService.find_teacher_account(updatePassword.getUsername()) == null) {
        return new R(false, null, "用户名输入错误！");
      } else if (teacherService.teacher_login(
              updatePassword.getUsername(), updatePassword.getPassword())
          == null) {
        return new R(false, null, "密码错误！");

      } else
        return new R(
            teacherService.teacher_update_password(
                updatePassword.getUpdatePassword(), updatePassword.getUsername()),
            null,
            "更新密码成功！");
    } else if (updatePassword.getIdentity() == 1024) {
      if (administerService.find_administer_account(updatePassword.getUsername()) == null) {
        return new R(false, null, "用户名输入错误！");
      } else if (administerService.administer_login(
              updatePassword.getUsername(), updatePassword.getPassword())
          == null) {
        return new R(false, null, "密码错误！");

      } else
        return new R(
            administerService.administer_update_password(
                updatePassword.getUpdatePassword(), updatePassword.getUsername()),
            null,
            "更新密码成功！");
    } else return new R(false, null, "身份码错误！");
  }

  @GetMapping("/get_now_time")
  public R getNowTimeAsString() {
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String nowTime = formatter.format(date);
    return new R(true, nowTime, null);
  }

  // E-36-陈最
  @PostMapping("/set_time_flag")
  public R setTime(@RequestBody Administer timeFlag) {
    return new R(administerService.time_save(timeFlag), null, "时间节点添加成功！");
  }

  // E-37-陈最
  @DeleteMapping("/delete_time_flag")
  public R deleteTime(@RequestBody Administer administer) {
    String tempMessage;
    if (administerService.time_delete(administer.getId())) tempMessage = "时间节点删除成功！";
    else tempMessage = "时间节点删除失败！";
    return new R(administerService.time_delete(administer.getId()), null, tempMessage);
  }

  // E-38-陈最
  @PutMapping("/update_time_flag")
  public R updateTime(@RequestBody Administer timeFlag) {
    String tempMessage;
    if (administerService.time_update(timeFlag)) tempMessage = "时间节点更改成功！";
    else tempMessage = "时间节点更改失败！";
    return new R(administerService.time_update(timeFlag), null, tempMessage);
  }

  // E-39-陈最
  @GetMapping("/find_all_time")
  public R getAllTime() {
    boolean tempFlag;
    String tempMessage;
    if (administerService.time_getAll() == null) {
      tempFlag = false;
      tempMessage = "未设置任何时间节点";
    } else {
      tempFlag = true;
      tempMessage = "这是所有的时间节点";
    }
    return new R(tempFlag, administerService.time_getAll(), tempMessage);
  }

  // E-40-陈最
  @GetMapping("/query_time/{id}")
  public R getTimeById(@PathVariable Integer id) {
    boolean tempFlag;
    String tempMessage;
    if (administerService.time_getById(id) == null) {
      tempFlag = false;
      tempMessage = "未查询到该时间节点。";
    } else {
      tempFlag = true;
      tempMessage = "查询结果如下：";
    }
    return new R(tempFlag, administerService.time_getById(id), tempMessage);
  }

  // E-22-陈最
  public String getNowTimeAsString2() {
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return formatter.format(date);
  }

  @GetMapping("/check_process_of_studentNumber")
  public R checkProcessOfStudentNumber() {
    PeopleNumber peopleNumber = new PeopleNumber();
    peopleNumber.setAllNum(administerService.FindAllStudentNumber());
    peopleNumber.setFinishedNum(administerService.FindStudentFinishedNumber());
    R r = new R();
    r.setFlag(true);
    r.setData(peopleNumber);
    r.setMessage("更新时间:" + getNowTimeAsString2());
    return r;
  }

  // E-23-陈最
  @GetMapping("/check_process_of_student_Finished")
  public R checkProcessOfStudent_Finished() {
    R r = new R();
    r.setFlag(true);
    r.setMessage("已完成师生互选名单如上，更新时间:" + getNowTimeAsString2());
    r.setData(administerService.QueryFinishedStudent());
    return r;
  }

  @GetMapping("/check_process_of_student_NotFinished")
  public R checkProcessOfStudent_NotFinished() {
    R r = new R();
    r.setFlag(true);
    r.setMessage("未完成师生互选名单如上，更新时间:" + getNowTimeAsString2());
    r.setData(administerService.QueryNotFinishedStudent());
    return r;
  }

  // E-13-郑尊文
  @GetMapping("/teacherSatisfaction")
  public R calculateTeacherSatisfaction() {
    return new R(true, administerService.calculateTeacherSatisfaction(), "返回教师满意度情况！");
  }

  @GetMapping("/studentSatisfaction")
  public R calculateStudentSatisfaction() {
    return new R(true, administerService.calculateStudentSatisfaction(), "返回学生满意度情况！");
  }

  @GetMapping("/overallSatisfaction")
  public R calculateOverallSatisfaction() {
    return new R(true, administerService.calculateOverallSatisfaction(), "返回总满意度情况！");
  }
}
