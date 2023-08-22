package volunteer_filling.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import volunteer_filling.entity.Student;
import volunteer_filling.mapper.StudentMapper;
import volunteer_filling.mapper.StudentSelectTeacherMapper;
import volunteer_filling.service.StudentService;

import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService {
  @Autowired public StudentMapper studentMapper;
  // 分别是对mapper的实现
  @Autowired public StudentSelectTeacherMapper studentSelectTeacherMapper;

  @Override
  public List<Student> getAll() {
    return studentMapper.getAll();
  }
  // 判断账号是否存在
  // A-12-周雨凡
  @Override
  public Boolean find_student_account(String username) {
    Boolean x = studentMapper.find_student_account(username);
    if (x != null) {
      return x;
    } else return false;
  }
  // A-12-周雨凡
  @Override
  public Boolean student_register(String username, String password) {
    return studentMapper.student_register(username, password);
  }
  // A-12-周雨凡
  @Override
  public void add_register(String username) {
    studentSelectTeacherMapper.register_select(studentMapper.getId_byusername(username));
  }

  // 判断密码是否正确
  // A-13-周雨凡
  @Override
  public Boolean student_login(String username, String password) {
    return studentMapper.student_login(username, password);
  }

  // A-6-周雨凡
  @Override
  public Boolean student_update_password(String update_password, String username) {
    return studentMapper.student_update_password(update_password, username);
  }

  // B-1-周雨凡
  @Override
  public IPage<Student> getPage(int currentPage, int pageSize) {
    IPage page = new Page(currentPage, pageSize);
    studentMapper.selectPage(page, null);
    return page;
  }
  // A-9
  @Override
  public Student student_getInfo(String username) {
    return studentMapper.getInfoByUser(username);
  }
  // A-10
  @Override
  public Boolean student_update_information(Student student) {
    return studentMapper.updateById(student) > 0;
  }
  // C-2-周雨凡
  @Override
  public Boolean is_finish(Integer id) {
    return !Objects.equals(studentMapper.is_finish(id), "N");
  }
  // B-6
  @Override
  public Boolean Be_selected(Integer id) {
    return studentMapper.student_be_selected(id);
  }

  @Override
  public Boolean StuIsFinished(Integer id) {
    String str = studentMapper.studentIsFinished(id);
    if (Objects.equals(str, "Y")) return true;
    else if (Objects.equals(str, "N")) return false;
    else return null;
  }

  // B-2
  @Override
  public IPage<Student> getPageByName(int currentPage, int pageSize, String name) {
    // 创建分页对象
    Page<Student> page = new Page<>(currentPage, pageSize); // 查询第1页，每页返回10条数据
    // 构建查询条件
    QueryWrapper<Student> wrapper = new QueryWrapper<>();
    wrapper.like("name", "%" + name + "%");
    // 调用 selectPage 方法进行分页查询
    studentMapper.selectPage(page, wrapper);
    // 获取分页查询结果
    return page;
  }

  // D-2
  @Override
  public Boolean updateStudentSatisfaction(Integer id, float satisfaction) {
    return studentMapper.updateStudentSatisfaction(id, satisfaction);
  }

  @Override
  public Student getInfoById(Integer id) {
    return studentMapper.getInfoById(id);
  }
}
