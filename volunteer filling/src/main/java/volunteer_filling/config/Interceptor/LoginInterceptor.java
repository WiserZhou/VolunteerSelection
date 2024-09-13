package volunteer_filling.config.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import volunteer_filling.controller.AdministerController;

@Component
public class LoginInterceptor implements HandlerInterceptor {
  @Autowired AdministerController administerController;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    // 在请求处理之前进行拦截操作

    // 判断用户是否登录，例如判断 session 中是否存在用户信息
    if (request.getSession().getAttribute("user") == null) {
      // 用户未登录，重定向到登录页面或其他处理
      response.sendRedirect("/login");
      return false; // 拦截请求
    }

    // 用户已登录，放行请求
    return true;
  }

  @Override
  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView)
      throws Exception {
    // 请求处理之后进行拦截操作
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    // 请求完成后进行拦截操作
  }
}
