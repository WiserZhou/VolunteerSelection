package volunteer_filling.entity;

import lombok.Data;

@Data
public class Score {
  //总人数
  private Integer totalNum;
  //打分的人数
  private Integer satisNum;
  //没打分的人数
  private Integer unclearNum;
  //平均分
  private Double score;
}
