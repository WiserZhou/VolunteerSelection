package volunteer_filling.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import volunteer_filling.entity.Teacher;

import java.util.List;

@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {

  // 判断账号是否存在
  // A-12-周雨凡
  @Select("SELECT * FROM hit_teacher WHERE username = #{username}")
  Boolean find_teacher_account(String username);

  // 学生通过用户名和密码进行注册,将用户名和密码插入表
  // A-12-周雨凡
  @Insert("INSERT INTO hit_teacher(username , password) VALUES (#{username},#{password})")
  Boolean teacher_register(String username, String password);
  // A-13-周雨凡
  @Select("SELECT * FROM hit_teacher WHERE username = #{username} AND password=#{password}")
  Boolean teacher_login(String username, String password);
  // A-11-周雨凡
  @Update("UPDATE hit_teacher SET selNum = selNum-1 WHERE id  = #{id}")
  void dec_selNum(Integer id);
  // C-10-周雨凡
  @Select("SELECT selNum FROM hit_teacher WHERE id= #{id}")
  Integer get_selNum(Integer id);

  @Update("UPDATE  hit_teacher SET totalNum = #{selNum} , selNum = #{selNum}  WHERE id = #{id}")
  Boolean setNum(Integer selNum, Integer id);

  @Select("SELECT totalNum FROM hit_teacher WHERE id = #{id}")
  Integer get_totalNum(Integer id);

  // A-6-周雨凡
  @Update("UPDATE hit_teacher SET password = #{update_password} WHERE username = #{username}")
  Boolean teacher_update_password(String update_password, String username);

  // A-7
  @Select("SELECT * FROM hit_teacher WHERE username =#{username}")
  Teacher getInfo(String username);

  @Select("SELECT * FROM hit_teacher WHERE id = #{id}")
  Teacher getInfoById(Integer id);

  // D-2
  @Update("UPDATE hit_teacher SET satisfaction = #{satisfaction} WHERE id = #{id}")
  Boolean updateTeacherSatisfaction(Integer id, float satisfaction);

  @Select("SELECT id FROM hit_teacher WHERE name =#{teacherName}")
  Integer getIdByName(String teacherName);

  @Select("SELECT * FROM hit_teacher")
  List<Teacher> getAll();

  @Select("SELECT selNum FROM  hit_teacher WHERE id = #{id}")
  Integer getSelNum(Integer id);
}
