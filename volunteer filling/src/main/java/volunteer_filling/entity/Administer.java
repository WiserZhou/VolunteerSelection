package volunteer_filling.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

@Data
@TableName("hit_administer")
public class Administer {

  private Integer id;

  @TableField("username")
  private String username;

  @TableField("password")
  private String password;

  @TableField("timeName")
  private String timeName;

  @TableField("timeFlag")
  private Date timeFlag;

  @TableField("description")
  private String description;
}
