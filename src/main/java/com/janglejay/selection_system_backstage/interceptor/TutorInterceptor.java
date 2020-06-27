package com.janglejay.selection_system_backstage.interceptor;

import com.janglejay.selection_system_backstage.component.EncryptComponent;
import com.janglejay.selection_system_backstage.component.MyToken;
import com.janglejay.selection_system_backstage.component.RequestComponent;
import com.janglejay.selection_system_backstage.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class TutorInterceptor implements HandlerInterceptor {
    @Autowired
    private RequestComponent requestComponent;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(requestComponent.getRole()!= User.Role.TUTOR){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"无权限");
        }
        return true;
    }
}

