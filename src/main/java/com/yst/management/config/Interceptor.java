package com.yst.management.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.yst.management.common.Result;
import com.yst.management.entity.dto.PermissionCustom;
import com.yst.management.mapper.RoleMapper;
import com.yst.management.utils.TokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 拦截器
 */
@Configuration
public class Interceptor implements HandlerInterceptor {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 验证是否登录
     *
     * @param request
     * @return
     */
    public boolean verifyPermissions(HttpServletRequest request, HttpServletResponse response, Object handler) {
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
        return false;
    }

    /**
     * 判断是否有权限
     *
     * @param request
     * @return
     */
    public boolean competence(HttpServletRequest request, HttpServletResponse response, Object handler) {

        Integer roleId= (Integer) request.getSession().getAttribute("roleId");
        PermissionCustom permissionList = roleMapper.getPermissionByRoleId(roleId);


        // //获取当前登录对象的全部信息
        // People people = peopleMapper.selectById(getUserId(request.getHeader(Constant.TOKEN)));
        // //管理员拥有全部权限
        // if (Constant.SUPER_ADMIN.equals(people.getUserName())) {
        //     return true;
        // }
        // //判断是否被授权
        // //防止空指针
        // if (people.getStartDate() != null && people.getEndDate() != null) {
        //     if (dateUtils.ifDate(people.getStartDate(), people.getEndDate(), new Date())) {
        //         return true;
        //     }
        // }
        //从请求头中获取的地址
        // String requestURI = request.getRequestURI();
        // //通过角色id查询当前登陆对象的所有权限
        // List<Power> list = powerMapper.selectUrl(people.getRoleid());
        // ArrayList<String> stringList = new ArrayList<>();
        // if (!StringUtils.isEmpty(list)) {
        //     list.forEach(r -> {
        //         stringList.add(r.getUrl());
        //     });
        //     return lsitUtils.ifcontainString(stringList, requestURI);
        // }
        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            //跨域请求会首先发一个option请求，直接返回正常状态并通过拦截器
            if (request.getMethod().equals("OPTIONS")) {
                response.setStatus(HttpServletResponse.SC_OK);
                return true;
            }
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            //判断是否登录
            boolean verifyPermissions = verifyPermissions(request,response,handler);
            //判断是否有权限
            boolean competence = competence(request,response,handler);
            if (verifyPermissions && competence) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}