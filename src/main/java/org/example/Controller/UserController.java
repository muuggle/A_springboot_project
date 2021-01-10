package org.example.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.User;
import org.example.service.RedisService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;


@Controller
public class UserController {
    public static final String KEY_USER_ID = "__userid__";
    public static final String KEY_USERS = "__users__";


    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    RedisService redisService;
    @Autowired
    UserService userService;

    // 把User写入Redis:
    private void putUserIntoRedis(User user) throws Exception {
        redisService.hset(KEY_USERS, user.getId().toString(),
                objectMapper.writeValueAsString(user));
    }

    //从Redis读取User:
    private User getUserFromRedis(HttpSession session) throws Exception {

        Long id = (Long) session.getAttribute(KEY_USER_ID);
        if (id != null) {
            String s = redisService.hget(KEY_USERS, id.toString());
            if (s != null) {
                return objectMapper.readValue(s, User.class);
            }
        }
        return null;
    }

    @PostMapping("/signin")
    @Validated
    public ModelAndView doSignin(@RequestParam("email") String email,
                                 @RequestParam("password") String password,
                                 HttpSession session) throws Exception {
        try {
            User user = userService.signin(email, password);
            session.setAttribute(KEY_USER_ID, user.getId());
            putUserIntoRedis(user);
        } catch (RuntimeException e) {
            return new ModelAndView("signin.html", Map.of("email", email, "error", "Signin failed"));

        }
        return new ModelAndView("redirect:/profile");
    }

    @GetMapping("/profile")
    public ModelAndView profile(HttpSession session) throws Exception {
        User user = getUserFromRedis(session);
        if (user == null) {
            return new ModelAndView("redirect:/signin");
        }
        return new ModelAndView("profile.html", Map.of("user", user));
    }
}
