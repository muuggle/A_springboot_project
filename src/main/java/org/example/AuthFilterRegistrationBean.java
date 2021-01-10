//package org.example;
//
//import org.example.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//
//import javax.servlet.Filter;
//
//public class AuthFilterRegistrationBean extends FilterRegistrationBean<Filter> {
//    @Autowired
//    UserService userService;
//
//    @Override
//    public Filter getFilter(){
//        return new AuthFilter();
//    }
//    class AuthFilter implements Filter{
//
//    }
//}
