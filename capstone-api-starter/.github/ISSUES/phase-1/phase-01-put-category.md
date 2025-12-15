---
name: Implement PUT Category Endpoint (Admin Only)
about: Implement the endpoint to update an existing category
labels: enhancement, phase-1
title: Implement PUT /categories/{id} Endpoint
---

## 1.5 - Implement PUT Category Endpoint (Admin Only)

**Severity:** High  
**Component:** CategoriesController, MySqlCategoryDao  
**Priority:** High

### Description
Implement the REST endpoint to update an existing category. Only administrators should be able to update categories.

### API Specification
| Method | URL                                   | Body          | Access     |
|--------|---------------------------------------|---------------|------------|
| PUT    | http://localhost:8080/categories/{id} | Category JSON | Admin only |

### Request Body Format
```json
{
    "name": "Updated Category Name",
    "description": "Updated description"
}
```

### Expected Response
- 204 No Content on successful update
- 404 Not Found if category doesn't exist

### Security Requirements
- Only users with ADMIN role can access this endpoint
- Non-admin users should receive 403 Forbidden

### Files to Modify
- `src/main/java/org/yearup/controllers/CategoriesController.java`
    - Add `@PutMapping("{id}")` annotation to `updateCategory()` method
    - Add `@PreAuthorize("hasRole('ROLE_ADMIN')")` annotation
    - Implement method to call `categoryDao.update(id, category)`

- `src/main/java/org/yearup/data/mysql/MySqlCategoryDao.java`
    - Implement `update(int categoryId, Category category)` method with UPDATE SQL

### Acceptance Criteria
- [ ] PUT /categories/1 with admin token returns 204 No Content
- [ ] Category is updated in database
- [ ] PUT /categories/1 without token returns 401 Unauthorized
- [ ] PUT /categories/1 with user token returns 403 Forbidden