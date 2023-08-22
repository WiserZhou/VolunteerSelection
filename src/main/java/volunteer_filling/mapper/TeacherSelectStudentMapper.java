package volunteer_filling.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.poi.ss.formula.functions.T;
import volunteer_filling.entity.TeacherSelectStudent;
import java.util.List;

@Mapper
public interface TeacherSelectStudentMapper extends BaseMapper<TeacherSelectStudent> {

  // B-7
  @Select("SELECT * FROM hit_teacher_select_student WHERE student_id = #{student_id}")
  List<TeacherSelectStudent> Stu_getInfo(Integer student_id);

  // B-5
  @Update(
      "UPDATE hit_teacher_select_student SET isaccept = #{isaccept} WHERE student_id = #{student_id} "
          + "AND teacher_id = #{teacher_id}")
  Boolean choose_isaccept(String isaccept, Integer student_id, Integer teacher_id);

  // B-4
  @Insert(
      "INSERT INTO hit_teacher_select_student(student_id,teacher_id) VALUES (#{student_id},#{teacher_id})")
  Boolean teacher_select(Integer student_id, Integer teacher_id);

  @Select(
      "SELECT * FROM hit_teacher_select_student WHERE student_id = #{student_id} AND "
          + "teacher_id = #{teacher_id}")
  TeacherSelectStudent isRepeat(Integer student_id, Integer teacher_id);

  @Select("SELECT * FROM hit_teacher_select_student WHERE isaccept = 'Y'")
  List<TeacherSelectStudent> getAccInfo();
}
