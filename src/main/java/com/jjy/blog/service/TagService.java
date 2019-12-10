package com.jjy.blog.service;

import com.jjy.blog.po.Tag;
import com.jjy.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.List;

public interface TagService {

    Page<Tag> listTag(Pageable pageable);

    List<Tag> listTag();

    Tag getTag(Long id);

    Tag getTagByName(String name);

    // 首页显示热门tags
    List<Tag> listTagTop(Integer size);

    List<Tag> listTag(String ids);

    Tag saveTag(Tag tag);

    Tag updateTag(Long id, Tag tag);

    void deleteTag(Long id);

}
