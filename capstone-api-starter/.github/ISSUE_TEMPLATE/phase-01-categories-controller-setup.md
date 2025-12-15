---
name: CategoriesController Class Setup
about: Add class-level annotations and constructor to CategoriesController
labels: enhancement, phase-1, high-priority
title: CategoriesController Class Setup
---

## 1.1 - CategoriesController Class Setup

**Severity:** High  
**Component:** CategoriesController  
**Priority:** Fix First

### Description
The CategoriesController class exists but is missing the required annotations and dependency injection to function as a REST controller.

### Requirements
- Add `@RestController` annotation to make this a REST controller
- Add `@RequestMapping("categories")` to set base URL path
- Add `@CrossOrigin` to allow frontend JavaScript to call the API
- Add `@Autowired` constructor to inject CategoryDao and ProductDao

### Files to Modify
- `src/main/java/org/yearup/controllers/CategoriesController.java`

### Acceptance Criteria
- [ ] Class has `@RestController` annotation
- [ ] Class has `@RequestMapping("categories")` annotation
- [ ] Class has `@CrossOrigin` annotation
- [ ] Constructor is annotated with `@Autowired`
- [ ] CategoryDao and ProductDao are injected via constructor
- [ ] Application starts without errors