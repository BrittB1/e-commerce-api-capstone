---
name: Implement DELETE Clear Cart Endpoint
about: Implement the endpoint to clear all items from the shopping cart
labels: enhancement, phase-3
title: Implement DELETE /cart Endpoint
---

## Implement DELETE Clear Cart Endpoint

**Severity:** High  
**Component:** ShoppingCartController, MySqlShoppingCartDao  
**Priority:** High

### Description
Implement the REST endpoint to clear all products from the current user's shopping cart.

### API Specification
| Method | URL | Body | Access |
|--------|-----|------|--------|
| DELETE | http://localhost:8080/cart | No body | Logged-in users only |

### Implementation Logic
1. Get the current user's ID from the Principal
2. Delete all rows from shopping_cart where user_id matches

### SQL Example
```sql
DELETE FROM shopping_cart WHERE user_id = ?
```

### Files to Modify
- `src/main/java/org/yearup/controllers/ShoppingCartController.java`
- `src/main/java/org/yearup/data/mysql/MySqlShoppingCartDao.java`
- `src/main/java/org/yearup/data/ShoppingCartDao.java`

### Acceptance Criteria
- [ ] DELETE /cart removes all items from user's cart
- [ ] Only deletes current user's cart items
- [ ] Only authenticated users can clear cart
- [ ] Returns 204 No Content on success
- [ ] GET /cart returns empty cart after clearing