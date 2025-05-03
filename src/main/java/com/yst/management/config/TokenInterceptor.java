package com.yst.management.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yst.management.common.Result;
import com.yst.management.utils.TokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //跨域请求会首先发一个option请求，直接返回正常状态并通过拦截器
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        // 注意：此处token从request里面获取header里面的key值是token，你要根据自己的情况来，你在前端header里面传的token叫什么名称，这里就取什么名字。
        String token = request.getHeader("token");
        if (token != null) {
            // 验证token：成功放行
            boolean result = TokenUtils.verify(token);
            if (result) {
                System.out.println("通过拦截器");
                return true;
            }
        }
        try {
            // 将错误信息返回
            String jsonMap = new ObjectMapper().writeValueAsString(Result.error(666,"token verify fail"));
            response.getWriter().append(jsonMap);
            System.out.println("认证失败，未通过拦截器");
        } catch (Exception e) {
            return false;
        }
        /**
         * 还可以在此处检验用户存不存在等操作
         */
        return false;
    }
}