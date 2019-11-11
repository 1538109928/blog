package com.jjy.blog.service;

import com.jjy.blog.po.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    /*验证用户名和密码*/
    User checkUser(String username, String password);
}
