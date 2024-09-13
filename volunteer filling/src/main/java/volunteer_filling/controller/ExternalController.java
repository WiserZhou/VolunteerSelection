package volunteer_filling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import volunteer_filling.entity.User;
import volunteer_filling.entity.utils.R;
import volunteer_filling.service.AdministerService;
import volunteer_filling.service.StudentService;
import volunteer_filling.service.TeacherService;

@CrossOrigin
@RestController
@RequestMapping("/start")
public class ExternalController {

  @Autowired StudentService studentService;
  @Autowired TeacherService teacherService;
  @Autowired AdministerService administerService;
  // A-12-周雨凡
  @PostMapping("/register")
  public R regiser(@RequestBody User user) {
    if (user.getIdentity() == 1) { // student
      if (studentService.find_student_account(user.getUsername()))
        return new R(false, null, "用户名重复！");
      else {
        Boolean x = studentService.student_register(user.getUsername(), user.getPassword());
        studentService.add_register(user.getUsername()); // 在student_select_teacher表里面增加一列
        return new R(x, null, "注册成功！");
      }
    } else if (user.getIdentity() == 2) {
      if (teacherService.find_teacher_account(user.getUsername()))
        return new R(false, null, "用户名重复！");
      else
        return new R(
            teacherService.teacher_register(user.getUsername(), user.getPassword()), null, "注册成功！");
    } else if (user.getIdentity() == 1024) {
      if (administerService.find_administer_account(user.getUsername())) {
        return new R(false, null, "用户名重复！");

      } else
        return new R(
            administerService.administer_register(user.getUsername(), user.getPassword()),
            null,
            "注册成功！");
    } else return new R(false, null, "身份码传输错误！");
  }

  // A-13-周雨凡
  @PostMapping("/login")
  public R loginAdministerAccount(@RequestBody User user) {
    if (user.getIdentity() == 1024) {
      if (!administerService.find_administer_account(user.getUsername())) {
        return new R(false, null, "没有找到管理员账户！");
      } else {
        if (administerService.administer_login(user.getUsername(), user.getPassword()) != null) {
          return new R(true, null, "登陆成功！");
        } else return new R(false, null, "密码错误！");
      }
    } else if (user.getIdentity() == 1) {
      if (!studentService.find_student_account(user.getUsername())) {
        return new R(false, null, "没有找到学生账户！");

      } else {
        if (studentService.student_login(user.getUsername(), user.getPassword()) != null) {
          return new R(true, null, "登陆成功！");

        } else return new R(false, null, "密码错误！");
      }
    } else if (user.getIdentity() == 2) {
      if (!teacherService.find_teacher_account(user.getUsername())) {
        return new R(false, null, "没有找到老师账户！");
      } else {
        if (teacherService.teacher_login(user.getUsername(), user.getPassword()) != null) {
          return new R(true, null, "登陆成功！");

        } else return new R(false, null, "密码错误！");
      }
    } else return new R(false, null, "身份码错误！");
  }
}
