package com.jjy.blog.service;

import com.jjy.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {

    Type getType(Long id);

    Type getTypeByName(String name);

    Page<Type> listType(Pageable pageable);

    List<Type> listType();

    //首页分类栏
    List<Type> listTypeTop(Integer size);

    Type saveType(Type type);

    Type updateType(Long id, Type type);

    void deleteType(Long id);



}
