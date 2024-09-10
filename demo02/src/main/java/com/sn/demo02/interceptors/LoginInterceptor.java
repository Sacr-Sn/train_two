package com.sn.demo02.interceptors;

import com.sn.demo02.utils.JwtUtil;
import com.sn.demo02.utils.MyConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * 拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * preHandler 前置拦截器
     * @param request 请求，所有请求数据都在这里
     * @param response 响应
     * @param handler 处理器，可以获取到controller方法信息
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从http请求头中取出token，token的名称一定是和前端约定好的
        String token = request.getHeader(MyConstants.HEADER_TOKEN_NAME);
        // 验证token
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            return !claims.isEmpty();  // 若token无效，则不放行
        } catch (Exception e) {
            // 设置http响应状态码
            response.setStatus(401);
            return false;  // 不放行
        }
    }
}
