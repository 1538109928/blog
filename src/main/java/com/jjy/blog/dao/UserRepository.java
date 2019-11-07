package com.jjy.blog.dao;

import com.jjy.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
