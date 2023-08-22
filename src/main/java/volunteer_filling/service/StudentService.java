package volunteer_filling.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import volunteer_filling.entity.Student;

import java.util.List;

public interface StudentService extends IService<Student> {

    List<Student> getAll();

    // 判断账号是否存在
  // A-12-周雨凡
  Boolean find_student_account(String username); // A-12-周雨凡

  Boolean student_register(String username, String password);

  void add_register(String username);

  // 判断密码是否正确
  Boolean student_login(String username, String password);

  Boolean student_update_password(String update_password, String username);

  IPage<Student> getPage(int currentPage, int pageSize);

  // A-9
  Student student_getInfo(String username); // A-10

  Boolean student_update_information(Student student); // C-2-周雨凡

  Boolean is_finish(Integer id);

  // B-6
  Boolean Be_selected(Integer id);

  Boolean StuIsFinished(Integer id);

  IPage<Student> getPageByName(int currentPage, int pageSize, String name);

  Boolean updateStudentSatisfaction(Integer id, float satisfaction);

    Student getInfoById(Integer id);
}
