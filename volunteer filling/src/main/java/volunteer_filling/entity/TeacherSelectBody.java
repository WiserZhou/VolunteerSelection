package volunteer_filling.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class TeacherSelectBody {

  private Integer currentPage;
  private Integer pageSize;

  // 院系
  private String major;
  // 职称
  private java.lang.String degree;
  /** 专业 */
  private java.lang.String field;
  // 姓名
  private String name;
}
