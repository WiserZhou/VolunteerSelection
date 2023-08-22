package volunteer_filling.config.Interceptor;

import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

public class HandleFalseResponse {
  public static void handleFalseResponse(HttpServletResponse response) throws Exception {
    response.setContentType("application/json; charset=utf-8");
    response.setCharacterEncoding("UTF-8");

    PrintWriter out;
    JSONObject res = new JSONObject();
    res.put("flag", false);
    res.put("data", "");
    res.put("message", "未在规定时间内，填写无效！");
    // JSONObject object = (JSONObject)JSONObject.parse(res.replaceAll("null","\"\""));
    out = response.getWriter();
    out.append(res.toString());
  }
}
