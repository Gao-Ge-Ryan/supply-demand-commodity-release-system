package com.gaoge.security.handler;

import com.alibaba.fastjson.JSON;
import com.gaoge.common.Result;
import com.gaoge.common.StatusCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author 高歌
 * @Description 登录失败操作
 * @Date 2021-08-24
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//        CodeMsg.USERNAME_OR_PWD_ERROR.renderError(httpServletResponse);
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        String s = JSON.toJSONString(new Result(false, StatusCode.ERROR, "账号或密码错误，请输入正确的的信息"));
        outputStream.write(s.getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
    }
}
