package volunteer_filling.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("hit_student")
public class Student {

  private java.lang.Integer id;
  /** 用户名 */
  @TableField("username")
  private java.lang.String username;
  /** 密码 */
  @TableField("password")
  private java.lang.String password;
  /** 姓名 */
  @TableField("name")
  private java.lang.String name;
  /** 学号 */
  @TableField("student_id")
  private java.lang.String studentId;


  /** 生涯规划 */
  @TableField("plan")
  private java.lang.String plan;
  /** 专业 */
  @TableField("major")
  private String major;

  /** 联系方式 */
  @TableField("telephone")
  private java.lang.String telephone;
  /** 个人简介 */
  @TableField("text")
  private java.lang.String text;
  // 是否已经和老师完成匹配
  @TableField("isfinish")
  private java.lang.String isfinish;

  @TableField("satisfaction")
  private float satisfaction;
}
