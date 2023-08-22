package volunteer_filling.service.impl;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Service;
import volunteer_filling.entity.*;
import volunteer_filling.service.ExcelExportService;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelExportServiceImpl implements ExcelExportService {

  @Override
  public byte[] exportToExcelOfStudent(List<Student> data) throws IOException {
    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("Sheet1");

    // 创建表头
    Row headerRow = sheet.createRow(0);
    // 设置表头列名
    headerRow.createCell(0).setCellValue("ID（id）");
    headerRow.createCell(1).setCellValue("用户名（username）");
    headerRow.createCell(2).setCellValue("密码（password）");
    headerRow.createCell(3).setCellValue("姓名（name）");
    headerRow.createCell(4).setCellValue("学号（student_id）");
    headerRow.createCell(5).setCellValue("生涯规划（plan）");
    headerRow.createCell(6).setCellValue("专业（major）");
    headerRow.createCell(7).setCellValue("联系方式（telephone）");
    headerRow.createCell(8).setCellValue("个人简介（text）");
    headerRow.createCell(9).setCellValue("是否已经匹配完成（isfinish）");
    headerRow.createCell(10).setCellValue("满意度打分（satisfaction）");
    // ...

    // 填充数据
    int rowNum = 1;
    for (Student entity : data) {
      Row row = sheet.createRow(rowNum++);
      // 设置单元格数据
      row.createCell(0).setCellValue(entity.getId());
      row.createCell(1).setCellValue(entity.getUsername());
      row.createCell(2).setCellValue(entity.getPassword());
      row.createCell(3).setCellValue(entity.getName());
      row.createCell(4).setCellValue(entity.getStudentId());
      row.createCell(5).setCellValue(entity.getPlan());
      row.createCell(6).setCellValue(entity.getMajor());
      row.createCell(7).setCellValue(entity.getTelephone());
      row.createCell(8).setCellValue(entity.getText());
      row.createCell(9).setCellValue(entity.getIsfinish());
      row.createCell(10).setCellValue(entity.getSatisfaction());
      // ...
    }

    // 将工作簿转换为字节数组
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    workbook.write(outputStream);
    return outputStream.toByteArray();
  }

  @Override
  public byte[] exportToExcelOfTeacher(List<Teacher> data) throws IOException {
    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("Sheet1");

    // 创建表头
    Row headerRow = sheet.createRow(0);
    // 设置表头列名
    headerRow.createCell(0).setCellValue("ID（id）");
    headerRow.createCell(1).setCellValue("用户名（username）");
    headerRow.createCell(2).setCellValue("密码（password）");
    headerRow.createCell(3).setCellValue("教师工号（teacherId）");
    headerRow.createCell(4).setCellValue("姓名（name）");
    headerRow.createCell(5).setCellValue("联系方式（telephone）");
    headerRow.createCell(6).setCellValue("院系（major）");
    headerRow.createCell(7).setCellValue("职称（degree）");
    headerRow.createCell(8).setCellValue("是否接受外专业学生（isaccout）");
    headerRow.createCell(9).setCellValue("专业（field）");
    headerRow.createCell(10).setCellValue("可选毕设题目（titles）");
    headerRow.createCell(11).setCellValue("个人简介（info）");
    headerRow.createCell(12).setCellValue("备注（memo）");
    headerRow.createCell(13).setCellValue("可选学生名额（selNum）");
    headerRow.createCell(14).setCellValue("总名额（totalNum）");
    headerRow.createCell(15).setCellValue("邮箱（mail）");
    headerRow.createCell(16).setCellValue("满意度分数（satisfaction）");

    // 填充数据
    int rowNum = 1;
    for (Teacher entity : data) {
      Row row = sheet.createRow(rowNum++);
      // 设置单元格数据
      row.createCell(0).setCellValue(entity.getId());
      row.createCell(1).setCellValue(entity.getUsername());
      row.createCell(2).setCellValue(entity.getPassword());
      row.createCell(3).setCellValue(entity.getTeacherId());
      row.createCell(4).setCellValue(entity.getName());
      row.createCell(5).setCellValue(entity.getTelephone());
      row.createCell(6).setCellValue(entity.getMajor());
      row.createCell(7).setCellValue(entity.getDegree());
      row.createCell(8).setCellValue(entity.getIsaccout());
      row.createCell(9).setCellValue(entity.getField());
      row.createCell(10).setCellValue(entity.getTitles());
      row.createCell(11).setCellValue(entity.getInfo());
      row.createCell(12).setCellValue(entity.getMemo());
      row.createCell(13).setCellValue(entity.getSelNum());
      row.createCell(14).setCellValue(entity.getTotalNum());
      row.createCell(15).setCellValue(entity.getMail());
      row.createCell(16).setCellValue(entity.getSatisfaction());
      // ...
    }
    // 将工作簿转换为字节数组
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    workbook.write(outputStream);
    return outputStream.toByteArray();
  }

  @Override
  public byte[] exportToExcelOfMatch(List<Match> data) throws IOException {
    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("Sheet1");

    // 创建表头
    Row headerRow = sheet.createRow(0);
    // 设置表头列名
    headerRow.createCell(0).setCellValue("ID（id）");
    headerRow.createCell(1).setCellValue("教师id（teacher_id）");
    headerRow.createCell(2).setCellValue("教师姓名（teacher_name）");
    headerRow.createCell(3).setCellValue("教师院系（teacher_major）");
    headerRow.createCell(4).setCellValue("学生id（student_id）");
    headerRow.createCell(5).setCellValue("学生姓名（student_name）");
    headerRow.createCell(6).setCellValue("学生专业（student_major）");
    headerRow.createCell(7).setCellValue("批次编号（description）");

    // 填充数据
    int rowNum = 1;
    for (Match entity : data) {
      Row row = sheet.createRow(rowNum++);
      // 设置单元格数据
      row.createCell(0).setCellValue(entity.getId());
      row.createCell(1).setCellValue(entity.getTeacherId());
      row.createCell(2).setCellValue(entity.getTeacherName());
      row.createCell(3).setCellValue(entity.getTeacherMajor());
      row.createCell(4).setCellValue(entity.getStudentId());
      row.createCell(5).setCellValue(entity.getStudentName());
      row.createCell(6).setCellValue(entity.getStudentMajor());
      row.createCell(7).setCellValue(entity.getDescription());
      // ...
    }
    // 将工作簿转换为字节数组
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    workbook.write(outputStream);
    return outputStream.toByteArray();
  }

  @Override
  public byte[] exportToExcelOfTeacherMatch(List<Match> data) throws IOException {
    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("Sheet1");

    // 创建表头
    Row headerRow = sheet.createRow(0);
    // 设置表头列名
    headerRow.createCell(0).setCellValue("ID（id）");
    headerRow.createCell(1).setCellValue("学生id（student_id）");
    headerRow.createCell(2).setCellValue("学生姓名（student_name）");
    headerRow.createCell(3).setCellValue("学生专业（student_major）");
    headerRow.createCell(4).setCellValue("批次编号（description）");

    // 填充数据
    int rowNum = 1;
    for (Match entity : data) {
      Row row = sheet.createRow(rowNum++);
      // 设置单元格数据
      row.createCell(0).setCellValue(entity.getId());
      row.createCell(1).setCellValue(entity.getStudentId());
      row.createCell(2).setCellValue(entity.getStudentName());
      row.createCell(3).setCellValue(entity.getStudentMajor());
      row.createCell(4).setCellValue(entity.getDescription());
      // ...
    }
    // 将工作簿转换为字节数组
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    workbook.write(outputStream);
    return outputStream.toByteArray();
  }

  @Override
  public byte[] exportToExcelOfPre(List<TeacherSelectStudent> data) throws IOException {
    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("Sheet1");

    // 创建表头
    Row headerRow = sheet.createRow(0);
    // 设置表头列名
    headerRow.createCell(0).setCellValue("ID（id）");
    headerRow.createCell(1).setCellValue("学生id（student_id）");
    headerRow.createCell(2).setCellValue("学生姓名（student_name）");
    headerRow.createCell(3).setCellValue("教师id（teacher_id）");
    headerRow.createCell(4).setCellValue("教师姓名（teacher_name）");

    // 填充数据
    int rowNum = 1;
    for (TeacherSelectStudent entity : data) {
      Row row = sheet.createRow(rowNum++);
      // 设置单元格数据
      row.createCell(0).setCellValue(entity.getId());
      row.createCell(1).setCellValue(entity.getStudentId());
      row.createCell(2).setCellValue(entity.getStudentName());
      row.createCell(3).setCellValue(entity.getTeacherId());
      row.createCell(4).setCellValue(entity.getTeacherName());
      // ...
    }
    // 将工作簿转换为字节数组
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    workbook.write(outputStream);
    return outputStream.toByteArray();
  }

  @Override
  public byte[] exportToExcelOfDirection(List<StudentSelectTeacher> data) throws IOException {
    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("Sheet1");

    // 创建表头
    Row headerRow = sheet.createRow(0);
    // 设置表头列名
    headerRow.createCell(0).setCellValue("ID（id）");
    headerRow.createCell(1).setCellValue("学生id（student_id）");
    headerRow.createCell(2).setCellValue("学生姓名（student_name）");
    headerRow.createCell(3).setCellValue("第一志愿填报老师姓名（select_teacher1）");
    headerRow.createCell(4).setCellValue("第一志愿填报老师id（select_teacher1_id）");
    headerRow.createCell(5).setCellValue("第一志愿是否成功（isaccept1）");

    headerRow.createCell(6).setCellValue("第二志愿填报老师姓名（select_teacher2）");
    headerRow.createCell(7).setCellValue("第二志愿填报老师id（select_teacher2_id）");
    headerRow.createCell(8).setCellValue("第二志愿是否成功（isaccept2）");

    headerRow.createCell(9).setCellValue("第三志愿填报老师姓名（select_teacher3）");
    headerRow.createCell(10).setCellValue("第三志愿填报老师id（select_teacher3_id）");
    headerRow.createCell(11).setCellValue("第三志愿是否成功（isaccept3）");

    headerRow.createCell(12).setCellValue("第四志愿填报老师姓名（select_teacher4）");
    headerRow.createCell(13).setCellValue("第四志愿填报老师id（select_teacher4_id）");
    headerRow.createCell(14).setCellValue("第四志愿是否成功（isaccept4）");

    headerRow.createCell(15).setCellValue("第五志愿填报老师姓名（select_teacher5）");
    headerRow.createCell(16).setCellValue("第五志愿填报老师id（select_teacher5_id）");
    headerRow.createCell(17).setCellValue("第五志愿是否成功（isaccept5）");

    headerRow.createCell(18).setCellValue("第六志愿填报老师姓名（select_teacher6）");
    headerRow.createCell(19).setCellValue("第六志愿填报老师id（select_teacher6_id）");
    headerRow.createCell(20).setCellValue("第六志愿是否成功（isaccept6）");

    // 填充数据
    int rowNum = 1;
    for (StudentSelectTeacher entity : data) {
      Row row = sheet.createRow(rowNum++);
      // 设置单元格数据
      row.createCell(0).setCellValue(entity.getId());
      row.createCell(1).setCellValue(entity.getStudentId());
      row.createCell(2).setCellValue(entity.getStudentName());

      row.createCell(3).setCellValue(entity.getSelectTeacher1());
      row.createCell(4).setCellValue(entity.getSelectTeacher1Id());
      row.createCell(5).setCellValue(entity.getIsAccept1());

      row.createCell(6).setCellValue(entity.getSelectTeacher2());
      row.createCell(7).setCellValue(entity.getSelectTeacher2Id());
      row.createCell(8).setCellValue(entity.getIsAccept2());

      row.createCell(9).setCellValue(entity.getSelectTeacher3());
      row.createCell(10).setCellValue(entity.getSelectTeacher3Id());
      row.createCell(11).setCellValue(entity.getIsAccept3());

      row.createCell(12).setCellValue(entity.getSelectTeacher4());
      row.createCell(13).setCellValue(entity.getSelectTeacher4Id());
      row.createCell(14).setCellValue(entity.getIsAccept4());

      row.createCell(15).setCellValue(entity.getSelectTeacher5());
      row.createCell(16).setCellValue(entity.getSelectTeacher5Id());
      row.createCell(17).setCellValue(entity.getIsAccept5());

      row.createCell(18).setCellValue(entity.getSelectTeacher6());
      row.createCell(19).setCellValue(entity.getSelectTeacher6Id());
      row.createCell(20).setCellValue(entity.getIsAccept6());

      // ...
    }
    // 将工作簿转换为字节数组
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    workbook.write(outputStream);
    return outputStream.toByteArray();
  }
}
