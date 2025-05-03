package com.yst.management.controller;

import com.yst.management.common.Pager;
import com.yst.management.common.Result;
import com.yst.management.entity.Category;
import com.yst.management.entity.vo.CategoryVo;
import com.yst.management.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categorys", produces = "application/json;charset=utf-8")
@Tag(name = "分类接口", description = "分类相关接口")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 获取商品分类树状列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Operation(summary = "获取商品分类树状列表")
    @Parameters({
            @Parameter(name = "pageNum",description = "页码"),
            @Parameter(name = "pageSize",description = "每页条数")
    })
    @GetMapping("/getCategoryTreeByPage")
    public Result<Object> getCategoryTreeByPage(Integer pageNum, Integer pageSize) {
        List<CategoryVo> categoryTreeByPage = categoryService.getCategoryTreeByPage((pageNum - 1) * pageSize, pageSize);
        if (categoryTreeByPage != null) {
            Pager<CategoryVo> categoryVoPager = new Pager<>();
            categoryVoPager.setData(categoryTreeByPage);
            categoryVoPager.setPageNum(pageNum);
            categoryVoPager.setPageSize(pageSize);
            categoryVoPager.setTotal(categoryService.getCategoryCount());
            return Result.success(categoryVoPager);
        } else {
            return Result.error("未查到数据！");
        }
    }

    /**
     * 获取商品分类树状列表：按能级查询
     *
     * @param type 值为：1，2，3 分别表示显示一层二层三层分类列表
     * @return
     */
    @Operation(summary = "按能级查询商品分类树状列表")
    @Parameter(name = "type",description = "能级")
    @GetMapping("/getCategoryTreeByType/{type}")
    public Result<List<CategoryVo>> getCategoryTreeByType(@PathVariable Integer type) {
        List<CategoryVo> categoryTreeByType = categoryService.getCategoryTreeByType(type);
        if (categoryTreeByType != null) {
            return Result.success(categoryTreeByType);
        } else {
            return Result.error("未查到数据！");
        }
    }

    /**
     * 添加分类
     *
     * @param category
     * @return
     */
    @Operation(summary = "添加分类")
    @PostMapping("/addCategory")
    public Result<String> addCategory(@RequestBody Category category) {
        Boolean flag = categoryService.addCategory(category);
        if (flag) {
            return Result.success("分类添加成功！");
        } else {
            return Result.error("分类添加成功");
        }
    }

    /**
     * 修改分类
     *
     * @param category
     * @return
     */
    @Operation(summary = "修改分类")
    @PutMapping("/updateCategory")
    public Result<String> updateCategory(@RequestBody Category category) {
        Boolean flag = categoryService.updateCategory(category);
        if (flag) {
            return Result.success("分类修改成功！");
        } else {
            return Result.error("分类修改失败！");
        }
    }

    /**
     * 删除分类
     *
     * @param id
     * @return
     */
    @Operation(summary = "删除分类")
    @Parameter(name = "id",description = "分类id")
    @DeleteMapping("/deleteCategory/{id}")
    public Result<String> deleteCategory(@PathVariable Integer id) {
        Boolean flag = categoryService.deleteCategory(id);
        if (flag) {
            return Result.success("分类删除成功！");
        } else {
            return Result.error("分类删除失败！");
        }
    }

    /**
     * 通过id查询分类
     */
    @Operation(summary = "通过id查询分类")
    @Parameter(name = "id",description = "分类id")
    @GetMapping("/getCategoryById/{id}")
    public Result<Category> getCategoryById(@PathVariable Integer id) {
        Category categoryById = categoryService.getCategoryById(id);
        if (categoryById != null) {
            return Result.success(categoryById);
        } else {
            return Result.error("未查到数据！");
        }
    }

}
