package org.yearup.data;

import org.yearup.models.Category;

import java.util.List;

// tells us what can be done with categories, but doesn't tell how
public interface CategoryDao
{
    List<Category> getAllCategories();
    Category getById(int categoryId);
    Category create(Category category);
    void update(int categoryId, Category category);
    void delete(int categoryId);
}
