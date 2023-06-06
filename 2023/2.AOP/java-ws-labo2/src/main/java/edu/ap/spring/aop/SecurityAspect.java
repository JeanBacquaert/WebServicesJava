package edu.ap.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class SecurityAspect {

    @Around("@annotation(edu.ap.spring.aop.SecurityInterceptor)")
    public String checkLogin(ProceedingJoinPoint joinPoint) throws Throwable {

        String template = "login";

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("Authorisation".equals(cookie.getName())) {
                    if (cookie.getValue().equalsIgnoreCase("admin")) {
                        switch (joinPoint.getSignature().getName()) {
                            case "getTransactionForm":
                                template = "transaction";
                                break;
                            case "getBalance":
                                template = "balance";
                                break;
                        }
                    }
                }
            }
        }

        joinPoint.proceed();
        return template;
    }
}
