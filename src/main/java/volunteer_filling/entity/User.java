package volunteer_filling.entity;

import lombok.Data;

@Data
public class User {
  private String username;
  private String password;
  private Integer identity;
}
