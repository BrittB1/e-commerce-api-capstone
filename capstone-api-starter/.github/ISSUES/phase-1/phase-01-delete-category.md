---
name: Implement DELETE Category Endpoint (Admin Only)
about: Implement the endpoint to delete a category
labels: enhancement, phase-1
title: Implement DELETE /categories/{id} Endpoint
---

## 1.6 Implement DELETE Category Endpoint (Admin Only)

**Severity:** High  
**Component:** CategoriesController, MySqlCategoryDao  
**Priority:** High

### Description
Implement the REST endpoint to delete a category. Only administrators should be able to delete categories.

### API Specification
| Method | URL                                   | Body    | Access     |
|--------|---------------------------------------|---------|------------|
| DELETE | http://localhost:8080/categories/{id} | No body | Admin only |

### Expected Response
- 204 No Content on successful deletion

### Security Requirements
- Only users with ADMIN role can access this endpoint
- Non-admin users should receive 403 Forbidden

### Files to Modify
- `src/main/java/org/yearup/controllers/CategoriesController.java`
    - Add `@DeleteMapping("{id}")` annotation to `deleteCategory()` method
    - Add `@PreAuthorize("hasRole('ROLE_ADMIN')")` annotation
    - Add `@ResponseStatus(HttpStatus.NO_CONTENT)` annotation
    - Implement method to call `categoryDao.delete(id)`

- `src/main/java/org/yearup/data/mysql/MySqlCategoryDao.java`
    - Implement `delete(int categoryId)` method with DELETE SQL

### Acceptance Criteria
- [ ] DELETE /categories/4 with admin token returns 204 No Content
- [ ] Category is removed from database
- [ ] DELETE /categories/4 without token returns 401 Unauthorized
- [ ] DELETE /categories/4 with user token returns 403 Forbidden
- [ ] Insomnia "Delete Category - as admin" test passes
- [ ] GET /categories/{id} returns 404 after deletion