package com.jjy.blog.util;

import java.util.ArrayList;
import java.util.List;

public class ConvertToList {
    //将字符串变为可迭代的列表
    public static List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i<idarray.length; i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }
}
