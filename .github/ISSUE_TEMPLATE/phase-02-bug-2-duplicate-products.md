---
name: "Bug Fix: Products Duplicated Instead of Updated"
about: Fix the bug where updating a product creates a duplicate instead of updating
labels: bug, phase-2, high-priority
title: "Bug Fix: Products Duplicated Instead of Updated"
---

## Bug Fix 2: Products Duplicated Instead of Updated

**Severity:** High  
**Component:** ProductsController  
**Priority:** High

### Description
Some users have noticed that products appear to be duplicated in the database. When an administrator tries to update a product (change price, description, etc.), instead of updating the existing product, a NEW product is created with the changes.

### How to Reproduce
1. Start the Spring Boot application
2. Login as admin to get a token
3. Note the current products: `GET http://localhost:8080/products`
4. Try to update a product:
   ```
   PUT http://localhost:8080/products/1
   Body: {"name": "Updated Name", "price": 99.99, ...}
   ```
5. Check products again: `GET http://localhost:8080/products`
6. Notice that product ID 1 is unchanged, but a NEW product was created

### Expected Behavior
- PUT request should UPDATE the existing product with the given ID
- No new products should be created
- The product with the specified ID should have its values changed

### Actual Behavior
- PUT request CREATES a new product instead of updating
- The original product remains unchanged
- Duplicate products appear in the database

### Files to Investigate
- `src/main/java/org/yearup/controllers/ProductsController.java`
    - Look at the `updateProduct()` method
    - Check which DAO method is being called

### Debugging Tips
1. Look at the `updateProduct()` method in ProductsController
2. Check if it's calling `productDao.update()` or something else
3. Compare with how `updateCategory()` works in CategoriesController
4. The DAO has both `create()` and `update()` methods - which one should be used?

### Code Comparison
**CategoriesController (CORRECT):**
```java
@PutMapping("{id}")
public void updateCategory(@PathVariable int id, @RequestBody Category category)
{
    categoryDao.update(id, category);  // Calls UPDATE method
}
```

**ProductsController (BUGGY) - What does it currently do?**

### Acceptance Criteria
- [ ] PUT /products/{id} updates the existing product
- [ ] No duplicate products are created when updating
- [ ] Updated product retains its original ID
- [ ] Changes are reflected when fetching the product again
- [ ] Insomnia update product tests pass