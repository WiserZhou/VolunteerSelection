package volunteer_filling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VolunteerFillingApplication {

  public static void main(String[] args) {
      //关闭args的参数传递，防止设置发生改变
    SpringApplication.run(VolunteerFillingApplication.class);
  }
}
