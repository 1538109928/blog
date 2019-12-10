package com.jjy.blog.dao;

import com.jjy.blog.po.Tag;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

//接口可以继承多个接口，一个类只能继承一个类，一个类能实现多个接口
public interface TagRepository extends JpaRepository<Tag,Long> {

    Tag findByName(String name);

    @Query("select t from Tag t")
    List<Tag> findTopTags(Pageable pageable);

}
