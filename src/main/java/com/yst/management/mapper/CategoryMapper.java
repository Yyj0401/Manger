package com.yst.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yst.management.entity.Category;
import com.yst.management.entity.vo.CategoryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {


    // 获取商品分类树状列表：按能级查询
    List<CategoryVo> getCategoryTreeByType(Integer type);

    // 获取商品分类树状列表：分页查询
    List<CategoryVo> getCategoryTreeByPage(@Param("startPage") Integer startPage, @Param("pageSize") Integer pageSize);

    // 获取商品分类表总数
    long getCategoryCount();

    // 添加分类
    Boolean addCategory(Category category);

    // 修改分类
    Boolean updateCategory(Category category);

    // 删除分类
    Boolean deleteCategory(Integer id);

    // 通过Id查询分类
    Category getCategoryById(Integer id);
}
