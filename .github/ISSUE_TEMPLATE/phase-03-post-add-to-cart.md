---
name: Implement POST Add Product to Cart Endpoint
about: Implement the endpoint to add a product to the shopping cart
labels: enhancement, phase-3
title: Implement POST /cart/products/{id} Endpoint
---

## Implement POST Add Product to Cart Endpoint

**Severity:** High  
**Component:** ShoppingCartController, MySqlShoppingCartDao  
**Priority:** High

### Description
Implement the REST endpoint to add a product to the current user's shopping cart. If the product is already in the cart, increment the quantity by 1.

### API Specification
| Method | URL | Body | Access |
|--------|-----|------|--------|
| POST | http://localhost:8080/cart/products/15 | No body | Logged-in users only |

### Implementation Logic
1. Get the current user's ID from the Principal
2. Check if the product already exists in the user's cart
3. If NOT in cart: INSERT new row with quantity = 1
4. If already in cart: UPDATE quantity = quantity + 1

### Database Table: shopping_cart
```sql
CREATE TABLE shopping_cart (
    user_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    PRIMARY KEY (user_id, product_id)
);
```

### Files to Modify
- `src/main/java/org/yearup/controllers/ShoppingCartController.java`
- `src/main/java/org/yearup/data/mysql/MySqlShoppingCartDao.java`
- `src/main/java/org/yearup/data/ShoppingCartDao.java`

### Acceptance Criteria
- [ ] POST /cart/products/15 adds product 15 to cart
- [ ] If product not in cart, creates new entry with quantity 1
- [ ] If product already in cart, increments quantity by 1
- [ ] Only authenticated users can add to cart
- [ ] Returns 201 Created on success