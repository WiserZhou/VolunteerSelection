package volunteer_filling.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("hit_preselect")
public class PreSelection {

    private Integer id;

    @TableField("teacher_id")
    private Integer teacherId;

    @TableField("student_id")
    private Integer student_id;

}
