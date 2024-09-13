package volunteer_filling.entity;

import lombok.Data;

@Data
public class UpdatePassword {
  String username;
  String password;
  String updatePassword;
  Integer identity;
}
