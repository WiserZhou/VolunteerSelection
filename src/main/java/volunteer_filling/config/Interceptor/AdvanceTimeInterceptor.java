package volunteer_filling.config.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import volunteer_filling.service.AdministerService;

@Component
public class AdvanceTimeInterceptor implements HandlerInterceptor {
  @Autowired private AdministerService administerService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    if (!administerService.CheckAdvanceTime()) {
      HandleFalseResponse.handleFalseResponse(response);
      return false;
    }
    return true;
  }
}
