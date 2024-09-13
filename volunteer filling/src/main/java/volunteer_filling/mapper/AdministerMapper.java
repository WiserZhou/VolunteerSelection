package volunteer_filling.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import volunteer_filling.entity.Administer;
import volunteer_filling.entity.Student;
import java.util.List;

@Mapper
public interface AdministerMapper extends BaseMapper<Administer> {

  // A-6
  @Update("UPDATE hit_administer SET password = #{update_password} WHERE username =#{username}")
  Boolean administer_update_password(String update_password, String username);
  // 判断账号是否存在
  // A-12-周雨凡
  @Select("SELECT * FROM hit_administer WHERE username = #{username}")
  Integer find_administer_account(String username);

  // A-13-周雨凡
  @Select("SELECT * FROM hit_administer WHERE username=#{username} AND password = #{password}")
  Boolean administer_login(String username, String password);
  // 学生通过用户名和密码进行注册,将用户名和密码插入表
  // A-12-周雨凡
  @Insert("INSERT INTO hit_administer(username,password) VALUES (#{username},#{password})")
  Boolean administer_register(String username, String password);

  // 关于时间控制
  // 提前批
  @Select("SELECT * FROM hit_administer WHERE timeName = '提前批次-教师选学生-开始'")
  Administer Advance_begin();

  @Select("SELECT * FROM hit_administer WHERE timeName = '提前批次-教师选学生-结束'")
  Administer Advance_end();

  // 第二批次第二志愿
  @Select("SELECT * FROM hit_administer WHERE timeName = '第二批次-学生选教师-开始'")
  Administer Second_begin();

  @Select("SELECT * FROM hit_administer WHERE timeName = '第二批次-学生选教师-结束'")
  Administer Second_end();

  // 第三批次（随机分配的那个）
  @Select("SELECT * FROM hit_administer WHERE timeName = '第三批次'")
  public Administer Third();

  // E-22-陈最
  // 实时导出填报人数信息
  @Select("SELECT COUNT(id) FROM hit_student WHERE isfinish = 'Y'")
  Long FindFinishedStudentNumber();

  @Select("SELECT COUNT(id) FROM hit_student")
  Long FindAllStudentNumber();

  // E-23-陈最
  @Select("SELECT * FROM hit_student WHERE isfinish = 'N'")
  List<Student> QueryNotFinishedStudent();

  // E-13-郑尊文
  @Select("SELECT SUM(satisfaction) FROM hit_teacher")
  double calculateTeacherSatisfaction();

  @Select("SELECT COUNT(*) FROM hit_teacher WHERE satisfaction >= 0")
  int countTeachers();

  @Select("SELECT COUNT(*) FROM hit_teacher WHERE satisfaction IS NULL")
  int countUnclearNum();

  @Select("SELECT COUNT(*) FROM hit_teacher ")
  int totalTeachers();

  @Select("SELECT SUM(satisfaction) FROM hit_student")
  double calculateStudentSatisfaction();

  @Select("SELECT COUNT(*) FROM hit_student WHERE satisfaction IS NULL")
  int countUnclearNum1();

  @Select("SELECT COUNT(*) FROM hit_student ")
  int totalStudent();

  @Select("SELECT COUNT(*) FROM hit_student WHERE satisfaction >= 0")
  int countStudents();
}
