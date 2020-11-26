package com.hrms.exception;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


@ControllerAdvice //当下面的方法被回调时会以转发或重定向的方式出去
//@RestControllerAdvise//下面的方法会以json串的形式返回给页面

public class AppExceptionAdvise {

    @ExceptionHandler(value = {UnauthorizedException.class})
    @ResponseBody
    public String error(HttpServletResponse response) throws IOException {

     /*Map<String ,Object> map= new HashedMap<>();
     map.put("code",-1);
     map.put("msg","没有权限");
        return map;*/

        response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();

        return  "没有权限";
    }


}
