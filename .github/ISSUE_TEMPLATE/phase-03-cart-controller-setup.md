---
name: Shopping Cart Controller Setup
about: Add annotations and dependencies to ShoppingCartController
labels: enhancement, phase-3
title: Shopping Cart Controller Setup
---

## Shopping Cart Controller Setup

**Severity:** High  
**Component:** ShoppingCartController  
**Priority:** High

### Description
The ShoppingCartController class exists but needs annotations and dependency injection to function as a REST controller. Only logged-in users should be able to access shopping cart features.

### Requirements
- Add `@RestController` annotation
- Add `@RequestMapping("cart")` to set base URL path
- Add `@CrossOrigin` annotation
- Add `@PreAuthorize("isAuthenticated()")` for logged-in users only
- Add `@Autowired` constructor to inject ShoppingCartDao, UserDao, and ProductDao

### Files to Modify
- `src/main/java/org/yearup/controllers/ShoppingCartController.java`

### Acceptance Criteria
- [ ] Class has `@RestController` annotation
- [ ] Class has `@RequestMapping("cart")` annotation
- [ ] Class has `@CrossOrigin` annotation
- [ ] Constructor is annotated with `@Autowired`
- [ ] ShoppingCartDao, UserDao, and ProductDao are injected
- [ ] Application starts without errors