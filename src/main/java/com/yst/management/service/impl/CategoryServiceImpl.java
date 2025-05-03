package com.yst.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yst.management.entity.Category;
import com.yst.management.entity.vo.CategoryVo;
import com.yst.management.mapper.CategoryMapper;
import com.yst.management.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper,Category> implements CategoryService{

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryVo> getCategoryTreeByType(Integer type) {
        List<CategoryVo> categoryTreeByType = categoryMapper.getCategoryTreeByType(type);
        if(categoryTreeByType.size()==0){
            return null;
        }
        return categoryTreeByType;
    }

    @Override
    public List<CategoryVo> getCategoryTreeByPage(Integer startPage, Integer pageSize) {
        List<CategoryVo> categoryTreeByPage = categoryMapper.getCategoryTreeByPage(startPage, pageSize);
        if(categoryTreeByPage.size()==0){
            return null;
        }
        return categoryTreeByPage;
    }

    @Override
    public long getCategoryCount() {
        return categoryMapper.getCategoryCount();
    }

    @Override
    public Boolean addCategory(Category category) {
        return categoryMapper.addCategory(category);
    }

    @Override
    public Boolean updateCategory(Category category) {
        return categoryMapper.updateCategory(category);
    }

    @Override
    public Boolean deleteCategory(Integer id) {
        return categoryMapper.deleteCategory(id);
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryMapper.getCategoryById(id);
    }
}
