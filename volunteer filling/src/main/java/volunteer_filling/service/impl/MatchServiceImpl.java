package volunteer_filling.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import volunteer_filling.entity.*;
import volunteer_filling.mapper.MatchMapper;
import volunteer_filling.mapper.StudentMapper;
import volunteer_filling.mapper.TeacherMapper;
import volunteer_filling.service.MatchService;
import java.util.List;

@Service
public class MatchServiceImpl extends ServiceImpl<MatchMapper, Match> implements MatchService {
  @Autowired MatchMapper matchMapper;
  @Autowired TeacherMapper teacherMapper;
  @Autowired StudentMapper studentMapper;
  // E-32
  @Override
  public IPage<Match> getMatch(int currentPage, int pageSize) {
    IPage page = new Page(currentPage, pageSize);
    matchMapper.selectPage(page, null);
    return page;
  }

  @Override
  public List<Match> getAll(){
    return matchMapper.getAll();
  }

  // D-1-郑尊文
  @Override
  public List<Student> getUnmatchedStudents() {
    return matchMapper.findUnmatchedStudents();
  }

  @Override
  public List<Teacher> getAvailableTeachers() {
    return matchMapper.findAvailableTeachers();
  }

  @Override
  public void saveMatch(
      String teacherId,
      String teacherName,
      Integer studentId,
      String studentName,
      Integer description) {
    matchMapper.insertMatch(teacherId, teacherName, studentId, studentName, description);
  }

  @Override
  public void saveMatchInt(
      Integer teacherId,
      String teacherName,
      Integer studentId,
      String studentName,
      Integer description) {
    matchMapper.insertMatchInt(teacherId, teacherName, studentId, studentName, description);
  }

  @Override
  public void deleteByDescription(Integer description) {
    matchMapper.deleteByDescription(description);
  }

  // E-33-34
  @Override
  public List<Match> getTeacher(Integer studentId) {
    return matchMapper.getTeacher(studentId);
  }

  @Override
  public List<Match> getStudent(Integer teacherId) {
    return matchMapper.getStudent(teacherId);
  }

  // D-4-郑尊文
  @Override
  public List<Match> getMatchList() {
    return matchMapper.getMatchList();
  }

  @Override
  public Teacher getTeacherInfo(Integer id) {
    return matchMapper.getTeacherInfo(id);
  }

  @Override
  public Student getStudentInfo(Integer id) {
    return matchMapper.getStudentInfo(id);
  }

  @Override
  public void updateMatch(
      Integer id,
      String teacher_name,
      String teacher_major,
      String student_name,
      String student_major) {
    matchMapper.updateMatch(id, teacher_name, teacher_major, student_name, student_major);
  }

  @Override
  public List<TeacherSelectStudent> getTeacherSelectStudentList() {
    return matchMapper.getTeacherSelectStudentList();
  }

  @Override
  public List<StudentSelectTeacher> getStudentSelectTeacherList() {
    return matchMapper.getStudentSelectTeacherList();
  }
}
