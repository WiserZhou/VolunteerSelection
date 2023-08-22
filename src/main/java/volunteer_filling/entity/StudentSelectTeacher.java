package volunteer_filling.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("hit_student_select_teacher")
public class StudentSelectTeacher {
  /** 主键 */
  private java.lang.Integer id;

  @TableField("student_id")
  private java.lang.Integer studentId;

  @TableField("student_name")
  private java.lang.String studentName;

  @TableField("select_teacher1_id")
  private java.lang.Integer selectTeacher1Id;

  @TableField("select_teacher1")
  private java.lang.String selectTeacher1;

  @TableField("isaccept1")
  private java.lang.String isAccept1;
  /** 第二志愿填报老师id */
  @TableField("select_teacher2_id")
  private java.lang.Integer selectTeacher2Id;
  /** 第二志愿填报老师 */
  @TableField("select_teacher2")
  private java.lang.String selectTeacher2;
  /** 第二志愿是否成功 */
  @TableField("isaccept2")
  private java.lang.String isAccept2;
  /** 第三志愿填报老师id */
  @TableField("select_teacher3_id")
  private java.lang.Integer selectTeacher3Id;
  /** 第三志愿填报老师 */
  @TableField("select_teacher3")
  private java.lang.String selectTeacher3;
  /** 第三志愿是否成功 */
  @TableField("isaccept3")
  private java.lang.String isAccept3;
  /** 第四志愿填报老师id */
  @TableField("select_teacher4_id")
  private java.lang.Integer selectTeacher4Id;
  /** 第四志愿填报老师 */
  @TableField("select_teacher4")
  private java.lang.String selectTeacher4;
  /** 第四志愿是否成功 */
  @TableField("isaccept4")
  private java.lang.String isAccept4;
  /** 第五志愿填报老师id */
  @TableField("select_teacher5_id")
  private java.lang.Integer selectTeacher5Id;
  /** 第五志愿填报老师 */
  @TableField("select_teacher5")
  private java.lang.String selectTeacher5;
  /** 第五志愿是否成功 */
  @TableField("isaccept5")
  private java.lang.String isAccept5;
  /** 第六志愿填报老师id */
  @TableField("select_teacher6_id")
  private java.lang.Integer selectTeacher6Id;
  /** 第六志愿填报老师 */
  @TableField("select_teacher6")
  private java.lang.String selectTeacher6;
  /** 第六志愿是否成功 */
  @TableField("isaccept6")
  private java.lang.String isAccept6;
}
