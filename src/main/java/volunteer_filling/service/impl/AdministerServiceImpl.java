package volunteer_filling.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import volunteer_filling.entity.Administer;
import volunteer_filling.entity.Match;
import volunteer_filling.entity.Score;
import volunteer_filling.entity.Student;
import volunteer_filling.mapper.AdministerMapper;
import volunteer_filling.mapper.MatchMapper;
import volunteer_filling.service.AdministerService;
import java.util.Date;
import java.util.List;

@Service
public class AdministerServiceImpl extends ServiceImpl<AdministerMapper, Administer>
    implements AdministerService {
  @Autowired AdministerMapper administerMapper;
  @Autowired MatchMapper matchMapper;

  // 判断账号是否存在
  // A-12-周雨凡
  @Override
  public Boolean find_administer_account(String username) {
    Integer temp = administerMapper.find_administer_account(username);
    return temp != null;
  }
  // A-12-周雨凡
  @Override
  public Boolean administer_register(String username, String password) {
    return administerMapper.administer_register(username, password);
  }

  // A-6-周雨凡
  @Override
  public Boolean administer_update_password(String update_password, String username) {
    return administerMapper.administer_update_password(update_password, username);
  }

  // 判断密码是否正确
  // A-13-周雨凡
  @Override
  public Boolean administer_login(String username, String password) {
    return administerMapper.administer_login(username, password);
  }
  // E-36-陈最
  @Override
  public Boolean time_save(Administer timeFlag) {
    timeFlag.setUsername(administerMapper.selectById(1).getUsername());
    timeFlag.setPassword(administerMapper.selectById(1).getPassword());
    return administerMapper.insert(timeFlag) > 0;
  }

  // E-37-陈最
  @Override
  public Boolean time_delete(Integer id) {
    return administerMapper.deleteById(id) > 0;
  }

  // E-38-陈最
  @Override
  public Boolean time_update(Administer timeFlag) {
    return administerMapper.updateById(timeFlag) > 0;
  }

  // E-39-陈最
  @Override
  public List<Administer> time_getAll() {
    return administerMapper.selectList(null);
  }

  // E-40-陈最
  @Override
  public Administer time_getById(Integer id) {
    return administerMapper.selectById(id);
  }

  // E-22-陈最
  @Override
  public Long FindStudentFinishedNumber() {
    return administerMapper.FindFinishedStudentNumber();
  }

  @Override
  public Long FindAllStudentNumber() {
    return administerMapper.FindAllStudentNumber();
  }

  // 关于时间控制
  // 关于时间控制
  @Override
  public Boolean CheckAdvanceTime() {
    Date dateBegin;
    Date dateEnd;
    dateBegin = administerMapper.Advance_begin().getTimeFlag();
    dateEnd = administerMapper.Advance_end().getTimeFlag();
    Date dateNow = new Date(System.currentTimeMillis());
    return dateBegin.compareTo(dateNow) <= 0 && dateEnd.compareTo(dateNow) >= 0;
  }

  /*@Override
  public Boolean CheckAdvanceTime_2() {
    Date dateBegin;
    Date dateEnd;
    dateBegin = administerMapper.Advance_2_begin().getTimeFlag();
    dateEnd = administerMapper.Advance_2_end().getTimeFlag();
    Date dateNow = new Date(System.currentTimeMillis());
    return dateBegin.compareTo(dateNow) <= 0 && dateEnd.compareTo(dateNow) >= 0;
  }*/

  @Override
  public Boolean CheckSecondTime() {
    Date dateBegin;
    Date dateEnd;
    dateBegin = administerMapper.Second_begin().getTimeFlag();
    dateEnd = administerMapper.Second_end().getTimeFlag();
    Date dateNow = new Date(System.currentTimeMillis());
    return dateBegin.compareTo(dateNow) <= 0 && dateEnd.compareTo(dateNow) >= 0;
  }

  /*@Override
  public Boolean CheckSecondTime_2(Integer num) {
    Date dateBegin = new Date();
    Date dateEnd = new Date();
    switch (num) {
      case 1 -> {
        dateBegin = administerMapper.Second_2_1_begin().getTimeFlag();
        dateEnd = administerMapper.Second_2_1_end().getTimeFlag();
      }
      case 2 -> {
        dateBegin = administerMapper.Second_2_2_begin().getTimeFlag();
        dateEnd = administerMapper.Second_2_2_end().getTimeFlag();
      }
      case 3 -> {
        dateBegin = administerMapper.Second_2_3_begin().getTimeFlag();
        dateEnd = administerMapper.Second_2_3_end().getTimeFlag();
      }
      case 4 -> {
        dateBegin = administerMapper.Second_2_4_begin().getTimeFlag();
        dateEnd = administerMapper.Second_2_4_end().getTimeFlag();
      }
      case 5 -> {
        dateBegin = administerMapper.Second_2_5_begin().getTimeFlag();
        dateEnd = administerMapper.Second_2_5_end().getTimeFlag();
      }
      case 6 -> {
        dateBegin = administerMapper.Second_2_6_begin().getTimeFlag();
        dateEnd = administerMapper.Second_2_6_end().getTimeFlag();
      }
    }
    Date dateNow = new Date(System.currentTimeMillis());
    return dateBegin.compareTo(dateNow) <= 0 && dateEnd.compareTo(dateNow) >= 0;
  }*/

  @Override
  public Boolean CheckThirdTime() {
    Date dateBegin;
    dateBegin = administerMapper.Third().getTimeFlag();
    Date dateNow = new Date(System.currentTimeMillis());
    return dateBegin.compareTo(dateNow) <= 0;
  }

  // E-23-陈最
  @Override
  public List<Match> QueryFinishedStudent() {
    return matchMapper.selectList(null);
  }

  @Override
  public List<Student> QueryNotFinishedStudent() {
    return administerMapper.QueryNotFinishedStudent();
  }

  // E-13-郑尊文
  @Override
  public Score calculateTeacherSatisfaction() {
    Score score = new Score();
    if (administerMapper.countTeachers() == 0) {
      score.setScore(0.0);
    } else {
      score.setScore(
          administerMapper.calculateTeacherSatisfaction() / administerMapper.countTeachers());
    }

    score.setTotalNum(administerMapper.totalTeachers());
    score.setSatisNum(administerMapper.countTeachers());
    score.setUnclearNum(administerMapper.countUnclearNum());
    return score;
  }

  @Override
  public Score calculateStudentSatisfaction() {
    Score score = new Score();
    if (administerMapper.countStudents() == 0) {
      score.setScore(0.0);
    } else {
      score.setScore(
          administerMapper.calculateStudentSatisfaction() / administerMapper.countStudents());
    }
    score.setTotalNum(administerMapper.totalStudent());
    score.setSatisNum(administerMapper.countStudents());
    score.setUnclearNum(administerMapper.countUnclearNum1());
    return score;
  }

  @Override
  public Score calculateOverallSatisfaction() {
    double teacherSatisfaction = administerMapper.calculateStudentSatisfaction();
    double studentSatisfaction = administerMapper.calculateTeacherSatisfaction();
    int teacherCount = administerMapper.countTeachers();
    int studentCount = administerMapper.countStudents();
    int totalCount = teacherCount + studentCount;

    Score score = new Score();
    if (totalCount == 0) {
      score.setScore(0.0);
    } else {
      score.setScore((teacherSatisfaction + studentSatisfaction) / totalCount);
    }

    score.setTotalNum(administerMapper.totalStudent() + administerMapper.totalTeachers());
    score.setSatisNum(administerMapper.countStudents() + administerMapper.countTeachers());
    score.setUnclearNum(administerMapper.countUnclearNum1() + administerMapper.countUnclearNum());
    return score;
  }
}
