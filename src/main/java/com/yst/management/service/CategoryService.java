package com.yst.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yst.management.entity.Category;
import com.yst.management.entity.vo.CategoryVo;

import java.util.List;

public interface CategoryService extends IService<Category> {
    List<CategoryVo> getCategoryTreeByType(Integer type);
    List<CategoryVo> getCategoryTreeByPage(Integer startPage,Integer pageSize);
    long getCategoryCount();
    Boolean addCategory(Category category);
    Boolean updateCategory(Category category);
    Boolean deleteCategory(Integer id);
    Category getCategoryById(Integer id);
}
