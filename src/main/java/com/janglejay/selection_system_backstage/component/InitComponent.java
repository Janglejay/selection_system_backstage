package com.janglejay.selection_system_backstage.component;

import com.janglejay.selection_system_backstage.entity.Tutor;
import com.janglejay.selection_system_backstage.entity.User;
import com.janglejay.selection_system_backstage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//系统初始化之后调用该组件
@Component
@Slf4j
public class InitComponent implements InitializingBean {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserService userService;
    //完成系统最初的管理员基本的账号的添加
    @Override
    public void afterPropertiesSet() throws Exception {
        int number = 1001;
        User user = userService.getUser(number);
        if(user ==null){
            User u = new User();
            u.setName("Jeffrey");
            u.setNumber(number);
            u.setRole(User.Role.TUTOR);
            u.setPassword(encoder.encode(String.valueOf(number)));

            Tutor t = new Tutor();//注意：tutor和user是组合关系，
            // 直接持久化tutor是不行的 save User之后才能save Tutor

//            userService.addTutor(u,t);//也可以使用级联的方法
            t.setUser(u);
            userService.addTutor(t);
        }
    }
}