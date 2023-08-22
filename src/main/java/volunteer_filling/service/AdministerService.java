package volunteer_filling.service;

import com.baomidou.mybatisplus.extension.service.IService;
import volunteer_filling.entity.Administer;
import volunteer_filling.entity.Match;
import volunteer_filling.entity.Score;
import volunteer_filling.entity.Student;
import java.util.List;

public interface AdministerService extends IService<Administer> {

  // 判断账号是否存在
  // A-12-周雨凡
  Boolean find_administer_account(String username); // A-12-周雨凡

  Boolean administer_register(String username, String password);

  Boolean administer_update_password(String update_password, String username);

  Boolean administer_login(String username, String password);

  // E-36-陈最
  Boolean time_save(Administer timeFlag); // 关于时间控制
  // E-37-陈最
  Boolean time_delete(Integer id); // E-38-陈最

  Boolean time_update(Administer timeFlag); // E-39-陈最

  List<Administer> time_getAll(); // E-40-陈最

  Administer time_getById(Integer id); // E-21-陈最

  // E-22-陈最
  Long FindStudentFinishedNumber();

  Long FindAllStudentNumber();

  // 关于时间控制
  // 关于时间控制
  Boolean CheckAdvanceTime();

  Boolean CheckSecondTime();


  Boolean CheckThirdTime();
  // E-23-陈最
  List<Match> QueryFinishedStudent();

  List<Student> QueryNotFinishedStudent();

  // E-13-郑尊文
  Score calculateTeacherSatisfaction();

  Score calculateStudentSatisfaction();

  Score calculateOverallSatisfaction();
}
