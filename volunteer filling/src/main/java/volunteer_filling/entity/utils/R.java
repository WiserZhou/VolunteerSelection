package volunteer_filling.entity.utils;

import lombok.Data;

@Data
public class R {
  private boolean flag;
  private Object data;
  private Object message;

  public R() {}

  public R(boolean flag, Object data) {
    this.data = data;
    this.flag = flag;
    this.message = null;
  }

  public R(boolean flag, Object data, Object message) {
    this.data = data;
    this.flag = flag;
    this.message = message;
  }
}
