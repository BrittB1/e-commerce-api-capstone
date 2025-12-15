---
name: Implement GET All Categories Endpoint
about: Implement the endpoint to retrieve all categories
labels: enhancement, phase-1
title: Implement GET /categories Endpoint
---

## 1.2 - Implement GET All Categories Endpoint

**Severity:** High  
**Component:** CategoriesController, MySqlCategoryDao  
**Priority:** High

### Description
Implement the REST endpoint to retrieve all categories from the database.

### API Specification
| Method | URL                              | Body    | Access               |
|--------|----------------------------------|---------|----------------------|
| GET    | http://localhost:8080/categories | No body | Everyone (permitAll) |

### Expected Response Format
```json
[
    {
        "categoryId": 1,
        "name": "Console Games",
        "description": "Latest games for PlayStation, Xbox, and Nintendo platforms."
    },
    {
        "categoryId": 2,
        "name": "PC Games",
        "description": "Digital and physical PC gaming titles for all genres."
    }
]
```

### Files to Modify
- `src/main/java/org/yearup/controllers/CategoriesController.java`
    - Add `@GetMapping("")` annotation to `getAll()` method
    - Add `@PreAuthorize("permitAll()")` annotation
    - Implement method to call `categoryDao.getAllCategories()`

- `src/main/java/org/yearup/data/mysql/MySqlCategoryDao.java`
    - Implement `getAllCategories()` method with SQL query
    - Use `mapRow()` helper method to convert ResultSet to Category objects

### Acceptance Criteria
- [ ] GET /categories returns 200 OK
- [ ] Response contains all categories from database
- [ ] Response is in correct JSON format
- [ ] Endpoint is accessible without authentication
- [ ] Insomnia "Get Categories" test passes