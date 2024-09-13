package volunteer_filling.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import volunteer_filling.entity.Student;

import java.security.Key;
import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {

  // 判断账号是否存在
  // A-12-周雨凡
  @Select("SELECT * FROM hit_student WHERE username = #{username}")
  Boolean find_student_account(String username);

  // 学生通过用户名和密码进行注册,将用户名和密码插入表
  // A-12-周雨凡
  @Insert("INSERT INTO hit_student(username,password) VALUES (#{username},#{password})")
  Boolean student_register(String username, String password);

  // A-12-周雨凡
  @Select("SELECT id FROM hit_student WHERE username = #{username}")
  Integer getId_byusername(String username);
  // 判断密码是否正确
  // A-13-周雨凡
  @Select("SELECT * FROM hit_student WHERE username = #{username} AND password = #{password}")
  Boolean student_login(String username, String password);

  // C-2-周雨凡
  @Select("SELECT isfinish FROM hit_student WHERE id = #{id}")
  String is_finish(Integer id);
  // B-6
  @Update("UPDATE hit_student SET isfinish = 'Y' WHERE id = #{id}")
  Boolean student_be_selected(Integer id);

  @Select("SELECT isfinish FROM hit_student WHERE id = #{id}")
  String studentIsFinished(Integer id);

  // A-6-周雨凡
  @Update("UPDATE hit_student SET password = #{update_password} WHERE username = #{username}")
  Boolean student_update_password(String update_password, String username);

  // A-9
  @Select("SELECT * FROM hit_student WHERE username =#{username}")
  Student getInfoByUser(String username);

  @Select("SELECT  * FROM  hit_student WHERE id = #{id}")
  Student getInfoById(Integer id);

  // C-11
  @Select("SELECT * FROM hit_student WHERE id = #{id}")
  Student getByID(Integer id);

  // D-2-郑尊文
  @Update("UPDATE hit_student SET satisfaction = #{satisfaction} WHERE id = #{id}")
  Boolean updateStudentSatisfaction(Integer id, float satisfaction);

  @Select("SELECT * FROM hit_student")
  List<Student> getAll();

  @Select("SELECT * FROM hit_student WHERE isfinish = 'Y' ")
  List<Student> getFinStu();

  @Select("SELECT * FROM  hit_student WHERE isfinish = 'N'")
  List<Student> getUnFinStu();

  @Select("SELECT * FROM  hit_student")
  List<Student> getStu();
}
