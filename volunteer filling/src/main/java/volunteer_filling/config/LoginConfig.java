//package volunteer_filling.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import volunteer_filling.config.Interceptor.*;
//
//@Configuration
//public class LoginConfig implements WebMvcConfigurer {
//  @Autowired
//  LoginInterceptor loginInterceptor;
//
//  @Override
//  public void addInterceptors(InterceptorRegistry registry) {
//
//    // 输出操作时间
//    registry
//        .addInterceptor(loginInterceptor)
//        .addPathPatterns(
//            "/hit_administer/*",
//            "/hit_student/*",
//            "/hit_teacher/*",
//            "/hit_student_select_teacher/*",
//            "/hit_teacher_select_student/*",
//            "/hit_match/*");
//  }
//}
