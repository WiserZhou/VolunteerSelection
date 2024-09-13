package volunteer_filling.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import volunteer_filling.entity.StudentSelectTeacher;
import java.util.List;

@Mapper
public interface StudentSelectTeacherMapper extends BaseMapper<StudentSelectTeacher> {

  @Select("SELECT * FROM hit_student_select_teacher WHERE student_id = #{studentId}")
  StudentSelectTeacher getInfo(Integer studentId);

  // C-4-周雨凡
  @Update(
      "UPDATE hit_student_select_teacher SET select_teacher1 =#{select_teacher1} "
          + ",select_teacher1_id =#{select_teacher1_id} WHERE student_id = #{student_id}")
  Boolean select_teacher1(Integer student_id, String select_teacher1, Integer select_teacher1_id);
  // C-5-周雨凡
  @Update(
      "UPDATE hit_student_select_teacher SET select_teacher2 =#{select_teacher2} "
          + ",select_teacher2_id =#{select_teacher2_id} WHERE student_id = #{student_id}")
  Boolean select_teacher2(Integer student_id, String select_teacher2, Integer select_teacher2_id);
  // C-6-周雨凡
  @Update(
      "UPDATE hit_student_select_teacher SET select_teacher3 =#{select_teacher3} "
          + ",select_teacher3_id =#{select_teacher3_id} WHERE student_id = #{student_id}")
  Boolean select_teacher3(Integer student_id, String select_teacher3, Integer select_teacher3_id);
  // C-7-周雨凡
  @Update(
      "UPDATE hit_student_select_teacher SET select_teacher4 =#{select_teacher4} "
          + ",select_teacher4_id =#{select_teacher4_id} WHERE student_id = #{student_id}")
  Boolean select_teacher4(Integer student_id, String select_teacher4, Integer select_teacher4_id);
  // C-8-周雨凡
  @Update(
      "UPDATE hit_student_select_teacher SET select_teacher5 =#{select_teacher5} "
          + ",select_teacher5_id =#{select_teacher5_id} WHERE student_id = #{student_id}")
  Boolean select_teacher5(Integer student_id, String select_teacher5, Integer select_teacher5_id);
  // C-9-周雨凡
  @Update(
      "UPDATE hit_student_select_teacher SET select_teacher6 =#{select_teacher6} "
          + ",select_teacher6_id =#{select_teacher6_id} WHERE student_id = #{student_id}")
  Boolean select_teacher6(Integer student_id, String select_teacher6, Integer select_teacher6_id);

  // 学生通过用户名和密码进行注册,将用户名和密码插入表
  // A-12-周雨凡
  @Insert("INSERT INTO hit_student_select_teacher(student_id) VALUES (#{student_id})")
  void register_select(Integer student_id);

  // C-11
  @Select(
      "SELECT student_id FROM hit_student_select_teacher WHERE select_teacher1_id = #{teacher_id}")
  List<Integer> get_selected_student1(Integer teacher_id);

  // C-12
  @Update(
      "UPDATE hit_student_select_teacher SET isaccept1 = 'Y' WHERE "
          + "student_id=#{student_id} AND select_teacher1_id=#{teacher_id}")
  Boolean accept_teacher1(Integer student_id, Integer teacher_id);
  // C-13
  @Select(
      "SELECT student_id FROM hit_student_select_teacher WHERE select_teacher2_id = #{teacher_id}")
  List<Integer> get_selected_student2(Integer teacher_id);
  // C-14
  @Update(
      "UPDATE hit_student_select_teacher SET isaccept2 = 'Y' WHERE "
          + "student_id=#{student_id} AND select_teacher2_id=#{teacher_id}")
  Boolean accept_teacher2(Integer student_id, Integer teacher_id);
  // C-15
  @Select(
      "SELECT student_id FROM hit_student_select_teacher WHERE select_teacher3_id = #{teacher_id}")
  List<Integer> get_selected_student3(Integer teacher_id);
  // C-16
  @Update(
      "UPDATE hit_student_select_teacher SET isaccept3 = 'Y' WHERE "
          + "student_id=#{student_id} AND select_teacher3_id=#{teacher_id}")
  Boolean accept_teacher3(Integer student_id, Integer teacher_id);
  // C-17
  @Select(
      "SELECT student_id FROM hit_student_select_teacher WHERE select_teacher4_id = #{teacher_id}")
  List<Integer> get_selected_student4(Integer teacher_id);
  // C-18
  @Update(
      "UPDATE hit_student_select_teacher SET isaccept4 = 'Y' WHERE "
          + "student_id=#{student_id} AND select_teacher4_id=#{teacher_id}")
  Boolean accept_teacher4(Integer student_id, Integer teacher_id);
  // C-19
  @Select(
      "SELECT student_id FROM hit_student_select_teacher WHERE select_teacher5_id = #{teacher_id}")
  List<Integer> get_selected_student5(Integer teacher_id);
  // C-20
  @Update(
      "UPDATE hit_student_select_teacher SET isaccept5 = 'Y' WHERE "
          + "student_id=#{student_id} AND select_teacher5_id=#{teacher_id}")
  Boolean accept_teacher5(Integer student_id, Integer teacher_id);
  // C-21
  @Select(
      "SELECT student_id FROM hit_student_select_teacher WHERE select_teacher6_id = #{teacher_id}")
  List<Integer> get_selected_student6(Integer teacher_id);
  // C-22
  @Update(
      "UPDATE hit_student_select_teacher SET isaccept6 = 'Y' WHERE "
          + "student_id=#{student_id} AND select_teacher6_id=#{teacher_id}")
  Boolean accept_teacher6(Integer student_id, Integer teacher_id);

  @Select(
      "SELECT * FROM hit_student_select_teacher WHERE student_id = #{student_id} AND "
          + "select_teacher1_id = #{select_teacher1_id} ")
  StudentSelectTeacher isRepeat1(Integer student_id, Integer select_teacher1_id);

  @Select(
      "SELECT * FROM hit_student_select_teacher WHERE student_id = #{student_id} AND "
          + "select_teacher2_id = #{select_teacher2_id} ")
  StudentSelectTeacher isRepeat2(Integer student_id, Integer select_teacher2_id);

  @Select(
      "SELECT * FROM hit_student_select_teacher WHERE student_id = #{student_id} AND "
          + "select_teacher3_id = #{select_teacher3_id} ")
  StudentSelectTeacher isRepeat3(Integer student_id, Integer select_teacher3_id);

  @Select(
      "SELECT * FROM hit_student_select_teacher WHERE student_id = #{student_id} AND "
          + "select_teacher4_id = #{select_teacher4_id} ")
  StudentSelectTeacher isRepeat4(Integer student_id, Integer select_teacher4_id);

  @Select(
      "SELECT * FROM hit_student_select_teacher WHERE student_id = #{student_id} AND "
          + "select_teacher5_id = #{select_teacher5_id} ")
  StudentSelectTeacher isRepeat5(Integer student_id, Integer select_teacher5_id);

  @Select(
      "SELECT * FROM hit_student_select_teacher WHERE student_id = #{student_id} AND "
          + "select_teacher6_id = #{select_teacher6_id} ")
  StudentSelectTeacher isRepeat6(Integer student_id, Integer select_teacher6_id);

  @Select("SELECT * FROM  hit_student_select_teacher WHERE select_teacher1_id is not null ")
  List<StudentSelectTeacher> getStuSelTeaCount();

}
