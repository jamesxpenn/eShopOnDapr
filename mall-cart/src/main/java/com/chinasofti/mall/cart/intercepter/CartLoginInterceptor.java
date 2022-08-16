package com.chinasofti.mall.cart.intercepter;

import com.chinasofti.mall.cart.exception.UserLoginException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class CartLoginInterceptor implements HandlerInterceptor {


    private static final ThreadLocal<String> THREAD_LOCAL=new ThreadLocal<>();

    /**
     * true 表示继续流程，false表示中断
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle...");
        String userid = request.getHeader("userid");
        if (StringUtils.isBlank(userid)) {
            {
                throw new UserLoginException();
            }
        }
        THREAD_LOCAL.set(userid);
        return true;
    }

    public static String getUserId(){
        return THREAD_LOCAL.get();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        THREAD_LOCAL.remove();
    }
}