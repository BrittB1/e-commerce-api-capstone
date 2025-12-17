---
name: Implement PUT Update Cart Item Endpoint
about: Implement the endpoint to update quantity of a product in the cart
labels: enhancement, phase-3, optional
title: Implement PUT /cart/products/{id} Endpoint (Optional)
---

## Implement PUT Update Cart Item Endpoint (Optional/Bonus)

**Severity:** Medium  
**Component:** ShoppingCartController, MySqlShoppingCartDao  
**Priority:** Optional

### Description
Implement the REST endpoint to update the quantity of a specific product in the user's shopping cart.

### API Specification
| Method | URL | Body | Access |
|--------|-----|------|--------|
| PUT | http://localhost:8080/cart/products/15 | Quantity JSON | Logged-in users only |

### Request Body Format
```json
{
    "quantity": 3
}
```

### Implementation Logic
1. Get the current user's ID from the Principal
2. Update the quantity for the specified product in the cart
3. Only update if the product exists in the user's cart

### Files to Modify
- `src/main/java/org/yearup/controllers/ShoppingCartController.java`
- `src/main/java/org/yearup/data/mysql/MySqlShoppingCartDao.java`
- `src/main/java/org/yearup/data/ShoppingCartDao.java`

### Acceptance Criteria
- [ ] PUT /cart/products/15 updates quantity of product 15
- [ ] Request body contains new quantity
- [ ] Only updates if product exists in cart
- [ ] Only authenticated users can update cart
- [ ] Returns 200 OK on success