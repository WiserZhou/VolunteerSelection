package volunteer_filling.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import volunteer_filling.entity.*;

import java.util.List;

@Mapper
public interface MatchMapper extends BaseMapper<Match> {

  // D-1-郑尊文
  @Select("SELECT * FROM hit_student WHERE isfinish = 'N'")
  List<Student> findUnmatchedStudents();

  @Select("SELECT * FROM hit_teacher WHERE selNum > 0")
  List<Teacher> findAvailableTeachers();

  @Insert(
      "INSERT INTO hit_match (teacher_id, teacher_name, student_id, student_name,description) "
          + "VALUES (#{teacherId}, #{teacherName}, #{studentId}, #{studentName},#{description})")
  void insertMatch(
      String teacherId,
      String teacherName,
      Integer studentId,
      String studentName,
      Integer description);

  @Insert(
      "INSERT INTO hit_match (teacher_id, teacher_name, student_id, student_name,description) "
          + "VALUES (#{teacherId}, #{teacherName}, #{studentId}, #{studentName},#{description})")
  void insertMatchInt(
      Integer teacherId,
      String teacherName,
      Integer studentId,
      String studentName,
      Integer description);

  @Delete("DELETE FROM hit_match WHERE description = #{description}")
  void deleteByDescription(@Param("description") Integer description);

  // E-33
  @Select(
      "SELECT * FROM hit_match WHERE student_id = #{id} AND  (description = 31 OR description = 32)")
  List<Match> getTeacher(Integer id);
  // E-34
  @Select("SELECT * FROM hit_match WHERE teacher_id =#{id}")
  List<Match> getStudent(Integer id);

  // D-4-郑尊文
  @Select("SELECT * FROM hit_match")
  List<Match> getMatchList();

  @Select("SELECT * FROM hit_teacher WHERE id = #{id}")
  Teacher getTeacherInfo(@Param("id") Integer id);

  @Select("SELECT * FROM hit_student WHERE id = #{id}")
  Student getStudentInfo(@Param("id") Integer id);

  @Update(
      "UPDATE hit_match SET teacher_name = #{teacher_name}, teacher_major "
          + "= #{teacher_major}, student_name = #{student_name}, "
          + "student_major = #{student_major} WHERE student_id = #{id}")
  void updateMatch(
      Integer id,
      String teacher_name,
      String teacher_major,
      String student_name,
      String student_major);

  @Select("SELECT * FROM hit_teacher_select_student WHERE isaccept = 'Y'")
  List<TeacherSelectStudent> getTeacherSelectStudentList();

  @Select("SELECT * FROM hit_student_select_teacher")
  List<StudentSelectTeacher> getStudentSelectTeacherList();

  @Select("SELECT * FROM hit_match")
  List<Match> getAll();

  @Select("SELECT * FROM hit_match WHERE teacher_id = #{teacherId}")
  List<Match> getMatchByTeacherId(Integer teacherId);
}
