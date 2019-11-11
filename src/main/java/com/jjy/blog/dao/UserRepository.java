package com.jjy.blog.dao;

import com.jjy.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*继承JpaRepository的方法以后，可以直接继承方法，实现增删改查*/
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username, String password);
}
