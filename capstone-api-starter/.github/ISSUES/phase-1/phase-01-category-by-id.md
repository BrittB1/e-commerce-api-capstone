---
name: Implement GET Category By ID Endpoint
about: Implement the endpoint to retrieve a single category by ID
labels: enhancement, phase-1
title: Implement GET /categories/{id} Endpoint
---

## 1.3 -Implement GET Category By ID Endpoint

**Severity:** High  
**Component:** CategoriesController, MySqlCategoryDao  
**Priority:** High

### Description
Implement the REST endpoint to retrieve a single category by its ID.

### API Specification
| Method | URL                                   | Body    | Access               |
|--------|---------------------------------------|---------|----------------------|
| GET    | http://localhost:8080/categories/{id} | No body | Everyone (permitAll) |

### Expected Response Format
```json
{
    "categoryId": 1,
    "name": "Console Games",
    "description": "Latest games for PlayStation, Xbox, and Nintendo platforms."
}
```

### Error Handling
- If category ID does not exist, return 404 Not Found

### Files to Modify
- `src/main/java/org/yearup/controllers/CategoriesController.java`
    - Add `@GetMapping("{id}")` annotation to `getById()` method
    - Add `@PreAuthorize("permitAll()")` annotation
    - Implement method to call `categoryDao.getById(id)`
    - Handle case where category is not found (return 404)

- `src/main/java/org/yearup/data/mysql/MySqlCategoryDao.java`
    - Implement `getById(int categoryId)` method with SQL query
    - Use `mapRow()` helper method to convert ResultSet to Category object

### Acceptance Criteria
- [ ] GET /categories/1 returns 200 OK with category data
- [ ] GET /categories/999 returns 404 Not Found
- [ ] Response is in correct JSON format
- [ ] Endpoint is accessible without authentication
- [ ] Insomnia "Get Category By Id" test passes