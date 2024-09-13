package volunteer_filling.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("hit_teacher_select_student")
public class TeacherSelectStudent {
  /** 主键 */
  private Integer id;
  /** 教师id */
  @TableField("teacher_id")
  private Integer teacherId;
  /** 教师姓名 */
  @TableField("teacher_name")
  private String teacherName;
  /** 学生id */
  @TableField("student_id")
  private Integer studentId;
  /** 所选学生姓名 */
  @TableField("student_name")
  private String studentName;
  /** 学生是否接受 */
  @TableField("isaccept")
  private String isaccept;

  @TableField("student_username")
  private String studentUsername;
}
