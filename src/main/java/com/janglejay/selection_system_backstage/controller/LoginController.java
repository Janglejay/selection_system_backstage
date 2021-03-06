package com.janglejay.selection_system_backstage.controller;

import com.janglejay.selection_system_backstage.component.EncryptComponent;
import com.janglejay.selection_system_backstage.component.MyToken;
import com.janglejay.selection_system_backstage.component.RequestComponent;
import com.janglejay.selection_system_backstage.entity.Direction;
import com.janglejay.selection_system_backstage.entity.Student;
import com.janglejay.selection_system_backstage.entity.Tutor;
import com.janglejay.selection_system_backstage.entity.User;
import com.janglejay.selection_system_backstage.repository.UserRepository;
import com.janglejay.selection_system_backstage.service.TutorService;
import com.janglejay.selection_system_backstage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/")
public class LoginController {
    @Value("${my.student}")//@Value("${my.student}")
    private String roleStudent;
    @Value("${my.tutor}")
    private String roleTutor;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TutorService tutorService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private EncryptComponent encrypt;
    @Autowired
    private RequestComponent requestComponent;


    @PostMapping("login")
    public Map login(@RequestBody User loginUser, HttpServletResponse response){//直接在方法中注入HttpServletResponse response对象
        System.out.println(userService.getUser(loginUser.getNumber()));
        User user = Optional.ofNullable(userService.getUser(loginUser.getNumber()))//判断其是否为空，不为空进入filter
                .filter(u -> encoder.matches(loginUser.getPassword(),u.getPassword()))//过滤 返回ture则跳出
                .orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"用户名或密码错误"));
        MyToken token  = new MyToken(user.getId(), user.getRole());
        String auth = encrypt.encryptToken(token);
        response.setHeader(MyToken.AUTHORIZATION, auth);//以键值对形式放入头中
        log.debug("{}", "登陆成功");
        String roleCode = user.getRole()== User.Role.TUTOR?roleTutor:roleStudent;
        return Map.of("role",roleCode);//告诉前端你是什么身份，前端渲染不同界面
    }

    @PostMapping("choice")
    public Map choice(@RequestBody Tutor t0){
        int tid = t0.getId();
        Tutor t = userService.getTutor(tid);
        List<Integer> mapKeyList = tutorService.excuteStudent(t);
        int maxStuScope = t.getScopeStuNum()>mapKeyList.size()?mapKeyList.size():t.getScopeStuNum();
        if(maxStuScope!=0){
            List<Integer> okStudentList = mapKeyList.subList(0, maxStuScope);
            int myNumber = userRepository.findById(requestComponent.getUid()).getNumber();
            boolean success = false;
            for(int number:okStudentList){
                if(number==myNumber){
                    success=true;
                    log.debug("{}", "入选");
                    break;
                }
            }
            if(success){
                Student s = userService.getStudent(requestComponent.getUid());
                Student student = tutorService.addStudent(s, tid);

                return Map.of("choose",1);
            }
            else{
                log.debug("{}", "未入选");
                return Map.of("choose",0);
            }
        }
        else{
            log.debug("{}", "未入选");
            return Map.of("choose",0);
        }
    }

    @PostMapping("addDirection")
    public void addSDirection(@RequestBody List<Direction> Directions){
        log.debug("{}", Directions.get(1));
        Student s = userService.getStudent(requestComponent.getUid());
        userService.addSDirection(s,Directions);

    }


    @PatchMapping("updatePwd")
    public Map patchUpdatePwd(@RequestBody User u){
        User u1 = userService.updatePwd(requestComponent.getUid(),encoder.encode(u.getPassword()));
        return Map.of("user",u1);
    }

    @GetMapping("directions")
    public Map directions(){
        List<Direction> directions = userService.getDirections();
        return Map.of("directions",directions);
    }

    @GetMapping("studentindex")
    public Map getStudent(){
        log.debug("{}", requestComponent.getUid());
        Student s = userService.getStudent(requestComponent.getUid());
        String tname=" ";
        if(s.getTutor()!=null){
            tname = s.getTutor().getUser().getName();
        }
        log.debug("{}", tname);
        return Map.of(
                "student",s,
                "tutorname",tname//springmvc允许延迟加载的get方法
        );
    }
    @GetMapping("tutorsindex")
    public Map getTutors(){
        List<Tutor> tutors = tutorService.getTutors();
        return  Map.of("tutors",tutors);
    }


//    @PostMapping("updatePwd")
//    public Map updatePwd(@RequestBody User updateUser,HttpServletResponse response){
//        User user = Optional.ofNullable(userService.getUser(updateUser.getNumber()))
//                .filter(u -> encoder.matches(updateUser.getPassword(),u.getPassword()))
//                .orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED),"用户名或密码错误");
//        userService.updatePwd(user.getNumber(), user.getPassword());
//
//    }
}
