package volunteer_filling.service;

import volunteer_filling.entity.*;

import java.io.IOException;
import java.util.List;

public interface ExcelExportService {


    byte[] exportToExcelOfStudent(List<Student> data) throws IOException;


    byte[] exportToExcelOfTeacher(List<Teacher> data) throws IOException;

    byte[] exportToExcelOfMatch(List<Match> data) throws IOException;

    byte[] exportToExcelOfTeacherMatch(List<Match> data) throws IOException;

    byte[] exportToExcelOfPre(List<TeacherSelectStudent> data) throws IOException;

    byte[] exportToExcelOfDirection(List<StudentSelectTeacher> data) throws IOException;
}
