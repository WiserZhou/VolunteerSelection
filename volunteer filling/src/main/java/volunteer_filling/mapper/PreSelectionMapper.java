package volunteer_filling.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import volunteer_filling.entity.PreSelection;
import volunteer_filling.entity.TeacherSelectStudent;

import java.util.List;

@Mapper
public interface PreSelectionMapper extends BaseMapper<PreSelection> {

  @Insert("INSERT INTO  hit_preselect (teacher_id, student_id) VALUES(#{teacherId},#{studentId})")
  Boolean InsertInfo(Integer teacherId, Integer studentId);

  @Select("SELECT * FROM hit_teacher WHERE teacher_id = #{teacherId}")
  List<TeacherSelectStudent> getInstance(Integer teacherId);
}
