package com.zy.service;

import com.zy.pojo.Category;
import java.util.List;

public interface CategoryService {
    List<Category> findCategoryListByName(String flag);

    List<Category> findAllCategory();

    Category findCategoryByCid(int category_id);

    void addCategory(Category category);

    void delCategoryById(int category_id);

    void updateCategoryName(String categoryName, int categoryId);
}