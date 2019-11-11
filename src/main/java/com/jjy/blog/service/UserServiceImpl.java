package com.jjy.blog.service;

import com.jjy.blog.dao.UserRepository;
import com.jjy.blog.po.User;
import com.jjy.blog.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5.code(password));
        return user;
    }
}
