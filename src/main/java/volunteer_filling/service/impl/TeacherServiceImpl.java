package volunteer_filling.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import volunteer_filling.entity.Student;
import volunteer_filling.entity.Teacher;
import volunteer_filling.entity.TeacherSelectBody;
import volunteer_filling.entity.TeacherSelectStudent;
import volunteer_filling.mapper.PreSelectionMapper;
import volunteer_filling.mapper.StudentMapper;
import volunteer_filling.mapper.TeacherMapper;
import volunteer_filling.service.TeacherService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService {
  @Autowired public TeacherMapper teacherMapper;
  @Autowired PreSelectionMapper preSelectionMapper;
  @Autowired StudentMapper studentMapper;

  // A-7-周雨凡
  @Override
  public Teacher teacher_getInfo(String username) {
    return teacherMapper.getInfo(username);
  }

  // 判断账号是否存在
  // A-12-周雨凡
  @Override
  public Boolean find_teacher_account(String username) {
    Boolean x = teacherMapper.find_teacher_account(username);
    return x != null;
  }
  // A-12-周雨凡
  @Override
  public Boolean teacher_register(String username, String password) {
    return teacherMapper.teacher_register(username, password);
  }

  // A-1-周雨凡

  // 判断密码是否正确
  @Override
  public Boolean teacher_login(String username, String password) {
    return teacherMapper.teacher_login(username, password);
  }

  @Override
  public Boolean teacher_update_password(String update_password, String username) {
    return teacherMapper.teacher_update_password(update_password, username);
  }
  // C-3
  @Override
  public IPage<Teacher> getPageByName(int currentPage, int pageSize, String name) {
    // 创建分页对象
    Page<Teacher> page = new Page<>(currentPage, pageSize); // 查询第1页，每页返回10条数据
    // 构建查询条件
    QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
    wrapper.like("name", "%" + name + "%");
    // 调用 selectPage 方法进行分页查询
    teacherMapper.selectPage(page, wrapper);
    // 获取分页查询结果
    return page;
  }

  // C-3
  @Override
  public IPage<Teacher> getPageByBody(TeacherSelectBody teacherSelectBody) {
    // 创建分页对象
    Page<Teacher> page =
        new Page<>(
            teacherSelectBody.getCurrentPage(), teacherSelectBody.getPageSize()); // 查询第1页，每页返回10条数据
    // 构建查询条件
    QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
    if (!Objects.equals(teacherSelectBody.getName(), ""))
      wrapper.like("name", "%" + teacherSelectBody.getName() + "%");
    if (!Objects.equals(teacherSelectBody.getMajor(), ""))
      wrapper.like("major", "%" + teacherSelectBody.getMajor() + "%");
    if (!Objects.equals(teacherSelectBody.getField(), ""))
      wrapper.like("field", "%" + teacherSelectBody.getField() + "%");
    if (!Objects.equals(teacherSelectBody.getDegree(), ""))
      wrapper.like("degree", "%" + teacherSelectBody.getDegree() + "%");
    // 调用 selectPage 方法进行分页查询
    teacherMapper.selectPage(page, wrapper);
    // 获取分页查询结果
    return page;
  }

  @Override
  public List<Teacher> getAll() {
    return teacherMapper.getAll();
  }

  // A-8
  @Override
  public Boolean Teacher_update_information(Teacher teacher) {
    return teacherMapper.updateById(teacher) > 0;
  }

  // C-1-陈最
  @Override
  public IPage<Teacher> getPage(int currentPage, int pageSize) {
    IPage page = new Page(currentPage, pageSize);
    teacherMapper.selectPage(page, null);
    return page;
  }

  // C-10-周雨凡
  @Override
  public Integer is_full(Integer id) {

    Integer x = teacherMapper.get_selNum(id);
    if (x != null) return x;
    else return -1;
  }

  @Override
  public Integer getTotalNum(Integer id) {
    Integer x = teacherMapper.get_totalNum(id);
    if (x != null) return x;
    else return 0;
  }

  @Override
  public Boolean setNum(Integer id, Integer selNum) {
    return teacherMapper.setNum(selNum, id);
  }
  // A-11-周雨凡
  @Override
  public void dec_selNum(Integer id) {
    teacherMapper.dec_selNum(id);
  }

  // D-2
  @Override
  public Boolean updateTeacherSatisfaction(Integer id, float satisfaction) {
    return teacherMapper.updateTeacherSatisfaction(id, satisfaction);
  }

  @Override
  public Integer getIdByName(String name) {
    return teacherMapper.getIdByName(name);
  }

  @Override
  public Integer getSelNum(Integer id) {
    return teacherMapper.getSelNum(id);
  }

  @Override
  public List<Student> getInstance(Integer teacherId) {
    List<TeacherSelectStudent> list = preSelectionMapper.getInstance(teacherId);
    List<Student> list1 = new ArrayList<>();
    for (TeacherSelectStudent teacherSelectStudent : list) {
      list1.add(studentMapper.getByID(teacherSelectStudent.getStudentId()));
    }
    return list1;
  }
}
