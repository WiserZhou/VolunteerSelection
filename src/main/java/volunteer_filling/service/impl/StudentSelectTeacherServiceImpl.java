package volunteer_filling.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import volunteer_filling.entity.*;
import volunteer_filling.mapper.MatchMapper;
import volunteer_filling.mapper.StudentMapper;
import volunteer_filling.mapper.StudentSelectTeacherMapper;
import volunteer_filling.mapper.TeacherSelectStudentMapper;
import volunteer_filling.service.StudentSelectTeacherSerivce;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class StudentSelectTeacherServiceImpl
    extends ServiceImpl<StudentSelectTeacherMapper, StudentSelectTeacher>
    implements StudentSelectTeacherSerivce {

  @Autowired StudentSelectTeacherMapper studentSelectTeacherMapper;
  @Autowired StudentMapper studentMapper;
  @Autowired MatchMapper matchMapper;
  @Autowired TeacherSelectStudentMapper teacherSelectStudentMapper;

  @Override
  public List<Student> getUnDirStu() {
    List<Student> list1 = studentMapper.getStu();
    List<Student> list = new ArrayList<>();
    List<StudentSelectTeacher> studentSelectTeacherList =
        studentSelectTeacherMapper.getStuSelTeaCount();
    List<TeacherSelectStudent> teacherSelectStudentList = teacherSelectStudentMapper.getAccInfo();
    List<Integer> IdList = new ArrayList<>();
    for (StudentSelectTeacher studentSelectTeacher : studentSelectTeacherList) {
      IdList.add(studentSelectTeacher.getStudentId());
    }
    for (TeacherSelectStudent teacherSelectStudent : teacherSelectStudentList) {
      IdList.add(teacherSelectStudent.getStudentId());
    }
    for (Integer integer : IdList) {
      list.add(studentMapper.getByID(integer));
    }
    list1.removeAll(list);
    return list1;
  }

  // C-4-周雨凡
  @Override
  public Boolean select_teacher1(StudentSelectTeacher studentSelectTeacher) {
    return studentSelectTeacherMapper.select_teacher1(
        studentSelectTeacher.getStudentId(),
        studentSelectTeacher.getSelectTeacher1(),
        studentSelectTeacher.getSelectTeacher1Id());
  }

  // C-5-周雨凡
  @Override
  public Boolean select_teacher2(StudentSelectTeacher studentSelectTeacher) {
    return studentSelectTeacherMapper.select_teacher2(
        studentSelectTeacher.getStudentId(),
        studentSelectTeacher.getSelectTeacher2(),
        studentSelectTeacher.getSelectTeacher2Id());
  }
  // C-6-周雨凡
  @Override
  public Boolean select_teacher3(StudentSelectTeacher studentSelectTeacher) {
    return studentSelectTeacherMapper.select_teacher3(
        studentSelectTeacher.getStudentId(),
        studentSelectTeacher.getSelectTeacher3(),
        studentSelectTeacher.getSelectTeacher3Id());
  }
  // C-7-周雨凡
  @Override
  public Boolean select_teacher4(StudentSelectTeacher studentSelectTeacher) {
    return studentSelectTeacherMapper.select_teacher4(
        studentSelectTeacher.getStudentId(),
        studentSelectTeacher.getSelectTeacher4(),
        studentSelectTeacher.getSelectTeacher4Id());
  }
  // C-8-周雨凡
  @Override
  public Boolean select_teacher5(StudentSelectTeacher studentSelectTeacher) {
    return studentSelectTeacherMapper.select_teacher5(
        studentSelectTeacher.getStudentId(),
        studentSelectTeacher.getSelectTeacher5(),
        studentSelectTeacher.getSelectTeacher5Id());
  }
  // C-9-周雨凡
  @Override
  public Boolean select_teacher6(StudentSelectTeacher studentSelectTeacher) {
    return studentSelectTeacherMapper.select_teacher6(
        studentSelectTeacher.getStudentId(),
        studentSelectTeacher.getSelectTeacher6(),
        studentSelectTeacher.getSelectTeacher6Id());
  }

  // C-11
  @Override
  public List<Student> get_selectStu1(Integer teacherId) {
    List<Integer> x = studentSelectTeacherMapper.get_selected_student1(teacherId);
    return getStudents(x);
  }

  private List<Student> getStudents(List<Integer> x) {
    if (x.isEmpty()) {
      return Collections.emptyList();
    }

    List<Student> y = new ArrayList<>();
    for (Integer integer : x) {
      Student student = studentMapper.getByID(integer);
      if (student != null && Objects.equals(studentMapper.is_finish(integer), "N")) {
        y.add(student);
      }
    }
    return y;
  }

  // C-12
  @Override
  public Boolean accept_teacher1(Integer studentId, Integer teacherId) {
    return studentSelectTeacherMapper.accept_teacher1(studentId, teacherId);
  }
  // C-13
  @Override
  public List<Student> get_selectStu2(Integer teacherId) {
    List<Integer> x = studentSelectTeacherMapper.get_selected_student2(teacherId);
    return getStudents(x);
  }
  // C-14
  @Override
  public Boolean accept_teacher2(Integer studentId, Integer teacherId) {
    return studentSelectTeacherMapper.accept_teacher2(studentId, teacherId);
  }
  // C-15
  @Override
  public List<Student> get_selectStu3(Integer teacherId) {
    List<Integer> x = studentSelectTeacherMapper.get_selected_student3(teacherId);
    return getStudents(x);
  }
  // C-16
  @Override
  public Boolean accept_teacher3(Integer studentId, Integer teacherId) {
    return studentSelectTeacherMapper.accept_teacher3(studentId, teacherId);
  }
  // C-17
  @Override
  public List<Student> get_selectStu4(Integer teacherId) {
    List<Integer> x = studentSelectTeacherMapper.get_selected_student4(teacherId);
    return getStudents(x);
  }
  // C-18
  @Override
  public Boolean accept_teacher4(Integer studentId, Integer teacherId) {
    return studentSelectTeacherMapper.accept_teacher4(studentId, teacherId);
  }
  // C-19
  @Override
  public List<Student> get_selectStu5(Integer teacherId) {
    List<Integer> x = studentSelectTeacherMapper.get_selected_student5(teacherId);
    return getStudents(x);
  }
  // C-20
  @Override
  public Boolean accept_teacher5(Integer studentId, Integer teacherId) {
    return studentSelectTeacherMapper.accept_teacher5(studentId, teacherId);
  }
  // C-21
  @Override
  public List<Student> get_selectStu6(Integer teacherId) {
    List<Integer> x = studentSelectTeacherMapper.get_selected_student6(teacherId);
    return getStudents(x);
  }
  // C-22
  @Override
  public Boolean accept_teacher6(Integer studentId, Integer teacherId) {
    return studentSelectTeacherMapper.accept_teacher6(studentId, teacherId);
  }

  @Override
  public Boolean isRepeat1(Integer studentId, Integer teacherId) {
    return studentSelectTeacherMapper.isRepeat1(studentId, teacherId) != null;
  }

  @Override
  public Boolean isRepeat2(Integer studentId, Integer teacherId) {
    return studentSelectTeacherMapper.isRepeat2(studentId, teacherId) != null;
  }

  @Override
  public Boolean isRepeat3(Integer studentId, Integer teacherId) {
    return studentSelectTeacherMapper.isRepeat3(studentId, teacherId) != null;
  }

  @Override
  public Boolean isRepeat4(Integer studentId, Integer teacherId) {
    return studentSelectTeacherMapper.isRepeat4(studentId, teacherId) != null;
  }

  @Override
  public Boolean isRepeat5(Integer studentId, Integer teacherId) {
    return studentSelectTeacherMapper.isRepeat5(studentId, teacherId) != null;
  }

  @Override
  public Boolean isRepeat6(Integer studentId, Integer teacherId) {
    return studentSelectTeacherMapper.isRepeat6(studentId, teacherId) != null;
  }

  @Override
  public List<SelectTeacher> getIsAccept(Integer studentId) {

    StudentSelectTeacher studentSelectTeacher = studentSelectTeacherMapper.getInfo(studentId);
    if (studentSelectTeacher.getSelectTeacher1Id() == null
        && Objects.equals(
            studentMapper.getByID(studentSelectTeacher.getStudentId()).getIsfinish(), "N")) {
      String msg = "您还未进行志愿填报！";
      List<SelectTeacher> msgList1 = new ArrayList<>();

      SelectTeacher selectTeacher1 = new SelectTeacher();
      selectTeacher1.setTeacherName(null);
      selectTeacher1.setMsg(msg);
      msgList1.add(selectTeacher1);

      SelectTeacher selectTeacher2 = new SelectTeacher();
      selectTeacher2.setTeacherName(null);
      selectTeacher2.setMsg(msg);
      msgList1.add(selectTeacher2);

      SelectTeacher selectTeacher3 = new SelectTeacher();
      selectTeacher3.setTeacherName(null);
      selectTeacher3.setMsg(msg);
      msgList1.add(selectTeacher3);

      SelectTeacher selectTeacher4 = new SelectTeacher();
      selectTeacher4.setTeacherName(null);
      selectTeacher4.setMsg(msg);
      msgList1.add(selectTeacher4);

      SelectTeacher selectTeacher5 = new SelectTeacher();
      selectTeacher5.setTeacherName(null);
      selectTeacher5.setMsg(msg);
      msgList1.add(selectTeacher5);

      SelectTeacher selectTeacher6 = new SelectTeacher();
      selectTeacher6.setTeacherName(null);
      selectTeacher6.setMsg(msg);
      msgList1.add(selectTeacher6);

      SelectTeacher selectTeacher7 = new SelectTeacher();
      selectTeacher7.setTeacherName(null);
      selectTeacher7.setMsg(msg);
      msgList1.add(selectTeacher7);

      SelectTeacher selectTeacher8 = new SelectTeacher();
      selectTeacher8.setTeacherName(null);
      selectTeacher8.setMsg(msg);
      msgList1.add(selectTeacher8);

      return msgList1;
    }
    String isAccept1 = studentSelectTeacher.getIsAccept1();
    String isAccept2 = studentSelectTeacher.getIsAccept2();
    String isAccept3 = studentSelectTeacher.getIsAccept3();
    String isAccept4 = studentSelectTeacher.getIsAccept4();
    String isAccept5 = studentSelectTeacher.getIsAccept5();
    String isAccept6 = studentSelectTeacher.getIsAccept6();

    String teacherName1 = studentSelectTeacher.getSelectTeacher1();
    String teacherName2 = studentSelectTeacher.getSelectTeacher2();
    String teacherName3 = studentSelectTeacher.getSelectTeacher3();
    String teacherName4 = studentSelectTeacher.getSelectTeacher4();
    String teacherName5 = studentSelectTeacher.getSelectTeacher5();
    String teacherName6 = studentSelectTeacher.getSelectTeacher6();

    List<SelectTeacher> msgList = new ArrayList<>();

    SelectTeacher selectTeacher1 = new SelectTeacher();
    selectTeacher1.setTeacherName(teacherName1);
    selectTeacher1.setMsg(null);
    msgList.add(selectTeacher1);

    SelectTeacher selectTeacher2 = new SelectTeacher();
    selectTeacher2.setTeacherName(teacherName2);
    msgList.add(selectTeacher2);

    SelectTeacher selectTeacher3 = new SelectTeacher();
    selectTeacher3.setTeacherName(teacherName3);
    msgList.add(selectTeacher3);

    SelectTeacher selectTeacher4 = new SelectTeacher();
    selectTeacher4.setTeacherName(teacherName4);
    msgList.add(selectTeacher4);

    SelectTeacher selectTeacher5 = new SelectTeacher();
    selectTeacher5.setTeacherName(teacherName5);
    msgList.add(selectTeacher5);

    System.out.println(msgList);

    SelectTeacher selectTeacher6 = new SelectTeacher();
    selectTeacher6.setTeacherName(teacherName6);
    msgList.add(selectTeacher6);

    List<String> teacherName = new ArrayList<>();
    teacherName.add(teacherName1);
    teacherName.add(teacherName2);
    teacherName.add(teacherName3);
    teacherName.add(teacherName4);
    teacherName.add(teacherName5);
    teacherName.add(teacherName6);

    boolean flag = false;

    if (Objects.equals(isAccept1, "Y")) {
      SelectTeacher selectTeacher = new SelectTeacher();
      selectTeacher.setTeacherName(teacherName1);
      selectTeacher.setMsg("老师同意了您的第一志愿！");
      msgList.set(0, selectTeacher);
      flag = true;

      for (int i = 1; i < 6; i++) {
        SelectTeacher selectTeacher7 = new SelectTeacher();
        selectTeacher7.setTeacherName(teacherName.get(i));
        selectTeacher7.setMsg("您的志愿填报已经终止！");

        msgList.set(i, selectTeacher7);
      }
    } else if (Objects.equals(isAccept1, "N")) {
      SelectTeacher selectTeacher8 = new SelectTeacher();
      selectTeacher8.setTeacherName(teacherName1);
      selectTeacher8.setMsg("老师拒绝了您的第一志愿！");
      msgList.set(0, selectTeacher8);

      if (Objects.equals(isAccept2, "Y")) {
        SelectTeacher selectTeacher9 = new SelectTeacher();
        selectTeacher9.setTeacherName(teacherName2);
        selectTeacher9.setMsg("老师同意了您的第二志愿！");
        flag = true;
        msgList.set(1, selectTeacher9);
        for (int i = 2; i < 6; i++) {
          SelectTeacher selectTeacher10 = new SelectTeacher();
          selectTeacher10.setMsg("您的志愿填报已经终止！");
          selectTeacher10.setTeacherName(teacherName.get(i));
          msgList.set(i, selectTeacher10);
        }
      } else if (Objects.equals(isAccept2, "N")) {
        SelectTeacher selectTeacher11 = new SelectTeacher();
        selectTeacher11.setTeacherName(teacherName2);
        selectTeacher11.setMsg("老师拒绝了您的第二志愿申请！");
        msgList.set(1, selectTeacher11);
        if (Objects.equals(isAccept3, "Y")) {
          SelectTeacher selectTeacher12 = new SelectTeacher();
          selectTeacher12.setMsg("老师同意了您的第三志愿！");
          flag = true;
          selectTeacher12.setTeacherName(teacherName3);
          msgList.set(2, selectTeacher12);
          for (int i = 3; i < 6; i++) {
            SelectTeacher selectTeacher13 = new SelectTeacher();
            selectTeacher13.setTeacherName(teacherName.get(i));
            selectTeacher13.setMsg("您的志愿填报已经终止！");
            msgList.set(i, selectTeacher13);
          }
        } else if (Objects.equals(isAccept3, "N")) {
          SelectTeacher selectTeacher14 = new SelectTeacher();
          selectTeacher14.setTeacherName(teacherName3);
          selectTeacher14.setMsg("老师拒绝了您的第三志愿申请！");
          msgList.set(2, selectTeacher14);
          if (Objects.equals(isAccept4, "Y")) {
            SelectTeacher selectTeacher15 = new SelectTeacher();
            selectTeacher15.setTeacherName(teacherName4);
            selectTeacher15.setMsg("老师同意了您的第四志愿！");
            flag = true;
            msgList.set(3, selectTeacher15);
            for (int i = 4; i < 6; i++) {
              SelectTeacher selectTeacher16 = new SelectTeacher();
              selectTeacher16.setTeacherName(teacherName.get(i));
              selectTeacher16.setMsg("您的志愿填报已经终止！");
              msgList.set(i, selectTeacher16);
            }
          } else if (Objects.equals(isAccept4, "N")) {
            SelectTeacher selectTeacher17 = new SelectTeacher();
            selectTeacher17.setTeacherName(teacherName4);
            selectTeacher17.setMsg("老师拒绝了您的第四志愿申请！");
            msgList.set(3, selectTeacher17);
            if (Objects.equals(isAccept5, "Y")) {
              SelectTeacher selectTeacher18 = new SelectTeacher();
              selectTeacher18.setMsg("老师同意了您的第五志愿！");
              flag = true;
              selectTeacher18.setTeacherName(teacherName5);
              msgList.set(4, selectTeacher18);
              for (int i = 5; i < 6; i++) {
                SelectTeacher selectTeacher19 = new SelectTeacher();
                selectTeacher19.setTeacherName(teacherName.get(i));
                selectTeacher19.setMsg("您的志愿填报已经终止！");
                msgList.set(i, selectTeacher19);
              }
            } else if (Objects.equals(isAccept5, "N")) {
              SelectTeacher selectTeacher20 = new SelectTeacher();
              selectTeacher20.setMsg("老师拒绝了您的第五志愿申请！");
              selectTeacher20.setTeacherName(teacherName5);
              msgList.set(4, selectTeacher20);
              if (Objects.equals(isAccept6, "Y")) {
                SelectTeacher selectTeacher21 = new SelectTeacher();
                selectTeacher21.setTeacherName(teacherName6);
                selectTeacher21.setMsg("老师同意了您的第六志愿！");
                flag = true;
                msgList.set(5, selectTeacher21);

              } else if (Objects.equals(isAccept6, "N")) {
                SelectTeacher selectTeacher22 = new SelectTeacher();
                selectTeacher22.setMsg("老师拒绝了您的第六志愿申请！");
                selectTeacher22.setTeacherName(teacherName6);
                msgList.set(5, selectTeacher22);
              }
            }
          }
        }
      }
    }
    SelectTeacher selectTeacher24 = new SelectTeacher();
    SelectTeacher selectTeacher25 = new SelectTeacher();
    List<Match> match = matchMapper.getTeacher(studentId);
    String teacherName11 = null;
    String teacherName12 = null;
    int size = match.size();
    if (size == 0) {
      selectTeacher24.setTeacherName(null);
      selectTeacher24.setMsg("未执行第一次随机分配！");
      selectTeacher25.setTeacherName(null);
      selectTeacher25.setMsg("未执行第二次随机分配！");
    } else if (size == 1) {
      if (match.get(0).getDescription() == 31) {
        selectTeacher24.setTeacherName(match.get(0).getTeacherName());
        selectTeacher24.setMsg("第一次随机分配结果完成！");
        teacherName11 = match.get(0).getTeacherName();
        selectTeacher25.setTeacherName(null);
        selectTeacher25.setMsg("未执行第二次随机分配！");
      }
    } else if (size == 2) {
      Integer des1 = match.get(0).getDescription();
      Integer des2 = match.get(1).getDescription();

      if (des1 == 31 && des2 == 32) {
        selectTeacher24.setTeacherName(match.get(0).getTeacherName());
        selectTeacher24.setMsg("第一次随机分配结果完成！");
        teacherName11 = match.get(0).getTeacherName();
        selectTeacher25.setTeacherName(match.get(1).getTeacherName());
        selectTeacher25.setMsg("第二次随机分配结果完成！");
        teacherName12 = match.get(1).getTeacherName();
      } else if (des2 == 31 && des1 == 32) {
        selectTeacher24.setTeacherName(match.get(1).getTeacherName());
        selectTeacher24.setMsg("第一次随机分配结果完成！");
        teacherName11 = match.get(1).getTeacherName();
        selectTeacher25.setTeacherName(match.get(0).getTeacherName());
        selectTeacher25.setMsg("第二次随机分配结果完成！");
        teacherName12 = match.get(0).getTeacherName();
      }
    }
    if (flag) {
      SelectTeacher selectTeacher23 = new SelectTeacher();
      selectTeacher23.setTeacherName(teacherName11);
      selectTeacher23.setMsg("您的志愿填报已经终止！");
      msgList.add(selectTeacher23);
      SelectTeacher selectTeacher26 = new SelectTeacher();
      selectTeacher26.setTeacherName(teacherName12);
      selectTeacher26.setMsg("您的志愿填报已经终止！");
      msgList.add(selectTeacher26);
    } else {
      msgList.add(selectTeacher24);
      msgList.add(selectTeacher25);
    }

    SelectTeacher replaceSelect = new SelectTeacher();
    for (int i = 0; i < 6; i++) {
      if (Objects.equals(msgList.get(i).getTeacherName(), "")) {
        replaceSelect.setTeacherName(null);
        replaceSelect.setMsg("您未进行志愿填报！");
        msgList.set(i, replaceSelect);
      }
    }
    return msgList;
  }
}
