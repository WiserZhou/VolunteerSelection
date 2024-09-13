package volunteer_filling.service;

import com.baomidou.mybatisplus.extension.service.IService;
import volunteer_filling.entity.Teacher;
import volunteer_filling.entity.TeacherSelectStudent;
import java.util.List;

public interface TeacherSelectStudentService extends IService<TeacherSelectStudent> {

  // B-5
  Boolean update_isaccept(String isaccept, Integer student_id, Integer teacher_id); // B-4

  Boolean teacher_select(Integer student_id, Integer teacher_id);

  Boolean isRepeat(Integer student_id, Integer teacher_id);

  // B-7
  List<Teacher> Stu_getInfo(Integer studentId);
}
