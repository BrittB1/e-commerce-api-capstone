---
name: Implement POST Category Endpoint (Admin Only)
about: Implement the endpoint to create a new category
labels: enhancement, phase-1
title: Implement POST /categories Endpoint
---

## 1.4 - Implement POST Category Endpoint (Admin Only)

**Severity:** High  
**Component:** CategoriesController, MySqlCategoryDao  
**Priority:** High

### Description
Implement the REST endpoint to create a new category. Only administrators should be able to create categories.

### API Specification
| Method | URL                              | Body          | Access     |
|--------|----------------------------------|---------------|------------|
| POST   | http://localhost:8080/categories | Category JSON | Admin only |

### Request Body Format
```json
{
    "name": "New Category",
    "description": "Description of the new category"
}
```

### Expected Response Format
```json
{
    "categoryId": 4,
    "name": "New Category",
    "description": "Description of the new category"
}
```

### Security Requirements
- Only users with ADMIN role can access this endpoint
- Non-admin users should receive 403 Forbidden

### Files to Modify
- `src/main/java/org/yearup/controllers/CategoriesController.java`
    - Add `@PostMapping()` annotation to `addCategory()` method
    - Add `@PreAuthorize("hasRole('ROLE_ADMIN')")` annotation
    - Add `@ResponseStatus(HttpStatus.CREATED)` for 201 response
    - Implement method to call `categoryDao.create(category)`

- `src/main/java/org/yearup/data/mysql/MySqlCategoryDao.java`
    - Implement `create(Category category)` method with INSERT SQL
    - Return the newly created category with generated ID

### Acceptance Criteria
- [ ] POST /categories with admin token returns 201 Created
- [ ] POST /categories without token returns 401 Unauthorized
- [ ] POST /categories with user token returns 403 Forbidden
- [ ] New category is inserted into database
- [ ] Response includes the generated categoryId
- [ ] Insomnia "Add Category - as admin" test passes
- [ ] Insomnia "Add Category - not admin" test passes (expects 403)