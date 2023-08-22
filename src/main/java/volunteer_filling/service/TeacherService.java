package volunteer_filling.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import volunteer_filling.entity.Student;
import volunteer_filling.entity.Teacher;
import volunteer_filling.entity.TeacherSelectBody;
import volunteer_filling.entity.TeacherSelectStudent;

import java.util.List;

public interface TeacherService extends IService<Teacher> {
  // A-7-周雨凡
  Teacher teacher_getInfo(String username);

  Boolean find_teacher_account(String username);

  Boolean teacher_register(String username, String password);
  // A-1-周雨凡
  Boolean teacher_login(String username, String password);

  Boolean teacher_update_password(String update_password, String username);

  // C-3
  IPage<Teacher> getPageByName(int currentPage, int pageSize, String name);

  //C-3
  IPage<Teacher> getPageByBody(
          TeacherSelectBody teacherSelectBody);

    List<Teacher> getAll();

    Boolean Teacher_update_information(Teacher teacher);

  IPage<Teacher> getPage(int currentPage, int pageSize);

  Integer is_full(Integer id);

  Integer getTotalNum(Integer id);

  Boolean setNum(Integer id, Integer selNum);

  void dec_selNum(Integer id);

  // D-2
  Boolean updateTeacherSatisfaction(Integer id, float satisfaction);

    Integer getIdByName(String name);

  Integer getSelNum(Integer id);

  List<Student> getInstance(Integer teacherId);
}
