package volunteer_filling.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import volunteer_filling.entity.*;

import java.util.List;

public interface MatchService extends IService<Match> {
  // E-32
  IPage<Match> getMatch(int currentPage, int pageSize);

    List<Match> getAll();

    // D-1-郑尊文
  List<Student> getUnmatchedStudents();

  List<Teacher> getAvailableTeachers();



  void saveMatch(
          String teacherId,
          String teacherName,
          Integer studentId,
          String studentName,
          Integer description);

  void saveMatchInt(
          Integer teacherId,
          String teacherName,
          Integer studentId,
          String studentName,
          Integer description);

  void deleteByDescription(Integer description);

  // E-33-34
  List<Match> getTeacher(Integer studentId);

  List<Match> getStudent(Integer teacherId);

  // D-4-郑尊文
  List<Match> getMatchList();

  Teacher getTeacherInfo(Integer id);

  Student getStudentInfo(Integer id);

  void updateMatch(
      Integer id,
      String teacher_name,
      String teacher_major,
      String student_name,
      String student_major);

  List<TeacherSelectStudent> getTeacherSelectStudentList();

  List<StudentSelectTeacher> getStudentSelectTeacherList();
}
