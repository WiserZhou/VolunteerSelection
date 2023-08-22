package volunteer_filling.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("hit_match")
public class Match {

  private Integer id;

  @TableField("teacher_id")
  private Integer teacherId;

  @TableField("teacher_name")
  private String teacherName;

  @TableField("teacher_major")
  private String teacherMajor;

  @TableField("student_id")
  private Integer studentId;

  @TableField("student_name")
  private String studentName;

  @TableField("student_major")
  private String studentMajor;

  @TableField("description")
  private Integer description;
}
