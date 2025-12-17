---
name: Implement GET Shopping Cart Endpoint
about: Implement the endpoint to retrieve the current user's shopping cart
labels: enhancement, phase-3
title: Implement GET /cart Endpoint
---

## Implement GET Shopping Cart Endpoint

**Severity:** High  
**Component:** ShoppingCartController, MySqlShoppingCartDao  
**Priority:** High

### Description
Implement the REST endpoint to retrieve the current logged-in user's shopping cart with all items.

### API Specification
| Method | URL | Body | Access |
|--------|-----|------|--------|
| GET | http://localhost:8080/cart | No body | Logged-in users only |

### Expected Response Format
```json
{
    "items": {
        "1": {
            "product": {
                "productId": 1,
                "name": "Smartphone",
                "price": 499.99,
                ...
            },
            "quantity": 2,
            "discountPercent": 0,
            "lineTotal": 999.98
        }
    },
    "total": 999.98
}
```

### Implementation Notes
- Use `Principal` object to get the current logged-in username
- Use `userDao.getByUserName()` to get the user's ID
- Use `shoppingCartDao.getByUserId()` to get the cart

### Files to Modify
- `src/main/java/org/yearup/controllers/ShoppingCartController.java`
- `src/main/java/org/yearup/data/mysql/MySqlShoppingCartDao.java` (create this file)
- `src/main/java/org/yearup/data/ShoppingCartDao.java` (add methods)

### Acceptance Criteria
- [ ] GET /cart returns the user's shopping cart
- [ ] Cart includes all items with product details
- [ ] Cart total is calculated correctly
- [ ] Only authenticated users can access
- [ ] Returns empty cart if user has no items