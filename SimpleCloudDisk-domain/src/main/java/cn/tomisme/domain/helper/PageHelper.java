package cn.tomisme.domain.helper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public class PageHelper {
    public static void convertPage(Page source, Page target) {
        if (source == null || target == null) return;
        target.setSize(source.getSize());   // 每页大小
        target.setTotal(source.getTotal()); // 总记录数
        target.setPages(source.getPages()); // 总页数
        target.setCurrent(source.getCurrent()); //当前页
    }
}
