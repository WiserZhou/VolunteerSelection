package volunteer_filling.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("hit_teacher")
public class Teacher {

  private java.lang.Integer id;
  /** 用户名 */
  @TableField("username")
  private java.lang.String username;
  /** 密码 */
  @TableField("password")
  private java.lang.String password;

  /** 姓名 */
  @TableField("teacherId")
  private String teacherId;

  @TableField("name")
  private java.lang.String name;
  /** 电话 */
  @TableField("telephone")
  private java.lang.String telephone;

  /** 职称 */
  @TableField("degree")
  private java.lang.String degree;
  /** 专业 */
  @TableField("major")
  private java.lang.String major;
  /** 是否接受外专业学生 */
  @TableField("isaccout")
  private java.lang.String isaccout;
  /** 研究方向 */
  @TableField("field")
  private java.lang.String field;
  /** 可选毕设题目 */
  @TableField("titles")
  private java.lang.String titles;
  /** 个人简介 */
  @TableField("info")
  private java.lang.String info;
  /** 备注 */
  @TableField("memo")
  private java.lang.String memo;

  @TableField("selNum")
  private java.lang.Integer selNum;

  @TableField("totalNum")
  private Integer totalNum;
  /** 邮箱 */
  @TableField("mail")
  private java.lang.String mail;

  @TableField("satisfaction")
  private float satisfaction;

  @TableField("mjor")
  private String mjor;
}
