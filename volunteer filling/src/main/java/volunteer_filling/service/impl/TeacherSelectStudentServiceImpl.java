package volunteer_filling.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import volunteer_filling.entity.Teacher;
import volunteer_filling.entity.TeacherSelectStudent;
import volunteer_filling.mapper.TeacherMapper;
import volunteer_filling.mapper.TeacherSelectStudentMapper;
import volunteer_filling.service.TeacherSelectStudentService;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherSelectStudentServiceImpl
    extends ServiceImpl<TeacherSelectStudentMapper, TeacherSelectStudent>
    implements TeacherSelectStudentService {

  @Autowired private TeacherSelectStudentMapper teacherSelectStudentMapper;
  @Autowired TeacherMapper teacherMapper;

  // B-5
  @Override
  public Boolean update_isaccept(String isaccept, Integer student_id, Integer teacher_id) {
    return teacherSelectStudentMapper.choose_isaccept(isaccept, student_id, teacher_id);
  }

  // B-4
  @Override
  public Boolean teacher_select(Integer student_id, Integer teacher_id) {
    return teacherSelectStudentMapper.teacher_select(student_id, teacher_id);
  }

  @Override
  public Boolean isRepeat(Integer student_id, Integer teacher_id) {
    return teacherSelectStudentMapper.isRepeat(student_id, teacher_id) != null;
  }
  // B-7
  @Override
  public List<Teacher> Stu_getInfo(Integer studentId) {
    List<Teacher> list1 = new ArrayList<>();
    List<TeacherSelectStudent> list = teacherSelectStudentMapper.Stu_getInfo(studentId);
    for (TeacherSelectStudent teacherSelectStudent : list) {
      list1.add(teacherMapper.getInfoById(teacherSelectStudent.getTeacherId()));
    }
    return list1;
  }
}
