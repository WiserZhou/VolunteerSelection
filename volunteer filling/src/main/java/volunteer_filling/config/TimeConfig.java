package volunteer_filling.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import volunteer_filling.config.Interceptor.*;

@Configuration
public class TimeConfig implements WebMvcConfigurer {
  @Autowired private AdvanceTimeInterceptor advanceTimeInterceptor;
  @Autowired private SecondTimeInterceptor secondTimeInterceptor;
  @Autowired private ThirdTimeInterceptor thirdTimeInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
        .addInterceptor(advanceTimeInterceptor)
        .addPathPatterns(
            "/hit_teacher_select_student/tea_select_stu", "/hit_teacher_select_student/isaccept");

    registry
        .addInterceptor(secondTimeInterceptor)
        .addPathPatterns(
            "/hit_student_select_teacher/isSelect",
            "/hit_student_select_teacher/select_teacher1",
            "/hit_student_select_teacher/select_teacher2",
            "/hit_student_select_teacher/select_teacher3",
            "/hit_student_select_teacher/select_teacher4",
            "/hit_student_select_teacher/select_teacher5",
            "/hit_student_select_teacher/select_teacher6",
            "/hit_student_select_teacher/get_student_has_select1/{id}",
            "/hit_student_select_teacher/accept1",
            "/hit_student_select_teacher/get_student_has_select2/{id}",
            "/hit_student_select_teacher/accept2",
            "/hit_student_select_teacher/get_student_has_select3/{id}",
            "/hit_student_select_teacher/accept3",
            "/hit_student_select_teacher/get_student_has_select4/{id}",
            "/hit_student_select_teacher/accept4",
            "/hit_student_select_teacher/get_student_has_select5/{id}",
            "/hit_student_select_teacher/accept5",
            "/hit_student_select_teacher/get_student_has_select6/{id}",
            "/hit_student_select_teacher/accept6");

    registry
        .addInterceptor(thirdTimeInterceptor)
        .addPathPatterns(
            "/hit_student/updateStudentSatisfaction", "/hit_teacher/updateTeacherSatisfaction");
  }
}
