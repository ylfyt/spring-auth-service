package com.easy.authservice.middlewares;

import com.easy.authservice.exceptions.ApiRequestException;
import com.easy.authservice.services.TokenManager.ITokenManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class AuthorizationAspect {

    private final ITokenManager tokenManager;

    public AuthorizationAspect(ITokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Around("@annotation(authorization)")
    public Object applyMiddleware(ProceedingJoinPoint joinPoint, Authorization authorization) throws Throwable {
        var request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null) {
            throw new ApiRequestException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");
        }

        var data = authorizationHeader.split(" ");
        if (data.length != 2) {
            throw new ApiRequestException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");
        }

        var claims = tokenManager.verifyAccessToken(data[1]);
        if (claims == null) {
            throw new ApiRequestException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");
        }
        System.out.println("================== AUTHORIZED ACCESS BY: " + claims.username);

        return joinPoint.proceed();
    }
}
