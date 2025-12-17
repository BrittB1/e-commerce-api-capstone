---
name: "Bug Fix: Product Search Returning Incorrect Results"
about: Fix the product search functionality that returns incorrect results
labels: bug, phase-2, high-priority
title: "Bug Fix: Product Search Returning Incorrect Results"
---

## Bug Fix 1: Product Search Returning Incorrect Results

**Severity:** High  
**Component:** MySqlProductDao  
**Priority:** High

### Description
Users have reported that the product search functionality is returning incorrect results. The search filters for price range and other parameters are not working as expected.

### How to Reproduce
1. Start the Spring Boot application
2. Test the following searches in Insomnia:
    - `GET http://localhost:8080/products?minPrice=25` - Should return products $25 and UP
    - `GET http://localhost:8080/products?maxPrice=100` - Should return products $100 and BELOW
    - `GET http://localhost:8080/products?minPrice=25&maxPrice=100` - Should return products between $25-$100

### Expected Behavior
- `minPrice=25` should return products where price >= 25
- `maxPrice=100` should return products where price <= 100
- Combining both should return products in the price range

### Actual Behavior
- Search results are incorrect
- Price filters don't work as expected

### Files to Investigate
- `src/main/java/org/yearup/data/mysql/MySqlProductDao.java`
    - Look at the `search()` method
    - Check the SQL query logic
    - Check the PreparedStatement parameters

### Debugging Tips
1. Look at the SQL WHERE clause for price comparisons
2. Check if the correct comparison operators are used (`<=` vs `>=`)
3. Verify that ALL parameters (minPrice AND maxPrice) are being used
4. Check which variables are being set in the PreparedStatement

### Search Parameters Reference
| Key | Type | Filter |
|-----|------|--------|
| cat | int | categoryId |
| minPrice | BigDecimal | price (lower range) - products should cost AT LEAST this much |
| maxPrice | BigDecimal | price (upper range) - products should cost AT MOST this much |
| subCategory | String | subCategory |

### Acceptance Criteria
- [ ] `minPrice` filter returns products with price >= minPrice
- [ ] `maxPrice` filter returns products with price <= maxPrice
- [ ] Combined filters return products in the correct price range
- [ ] Category filter still works correctly
- [ ] SubCategory filter still works correctly
- [ ] All Insomnia search tests pass