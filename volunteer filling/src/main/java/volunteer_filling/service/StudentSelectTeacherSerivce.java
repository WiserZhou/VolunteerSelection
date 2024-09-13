package volunteer_filling.service;

import com.baomidou.mybatisplus.extension.service.IService;
import volunteer_filling.entity.SelectTeacher;
import volunteer_filling.entity.Student;
import volunteer_filling.entity.StudentSelectTeacher;
import java.util.List;

public interface StudentSelectTeacherSerivce extends IService<StudentSelectTeacher> {

    List<Student> getUnDirStu();

    // C-4-周雨凡
  Boolean select_teacher1(StudentSelectTeacher studentSelectTeacher);
  // C-5-周雨凡
  Boolean select_teacher2(StudentSelectTeacher studentSelectTeacher);

  Boolean select_teacher3(StudentSelectTeacher studentSelectTeacher);

  Boolean select_teacher4(StudentSelectTeacher studentSelectTeacher);

  Boolean select_teacher5(StudentSelectTeacher studentSelectTeacher);

  Boolean select_teacher6(StudentSelectTeacher studentSelectTeacher);
  // C-11

  // C-11
  List<Student> get_selectStu1(Integer teacher_id); // C-13

  List<Student> get_selectStu2(Integer teacher_id);

  Boolean accept_teacher1(Integer student_id, Integer teacher_id); // C-14

  Boolean accept_teacher2(Integer student_id, Integer teacher_id); // C-15

  List<Student> get_selectStu3(Integer teacher_id); // C-16

  Boolean accept_teacher3(Integer student_id, Integer teacher_id); // C-17

  List<Student> get_selectStu4(Integer teacher_id); // C-18

  Boolean accept_teacher4(Integer student_id, Integer teacher_id); // C-19

  List<Student> get_selectStu5(Integer teacher_id); // C-20

  Boolean accept_teacher5(Integer student_id, Integer teacher_id); // C-21

  List<Student> get_selectStu6(Integer teacher_id); // C-22

  Boolean accept_teacher6(Integer student_id, Integer teacher_id);

  Boolean isRepeat1(Integer student_id, Integer teacher_id);

  Boolean isRepeat2(Integer student_id, Integer teacher_id);

  Boolean isRepeat3(Integer student_id, Integer teacher_id);

  Boolean isRepeat4(Integer student_id, Integer teacher_id);

  Boolean isRepeat5(Integer student_id, Integer teacher_id);

  Boolean isRepeat6(Integer student_id, Integer teacher_id);

  List<SelectTeacher> getIsAccept(Integer student_id);
}
