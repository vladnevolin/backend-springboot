package ru.javabegin.tasklist.controller;

import org.springframework.web.bind.annotation.*;
import ru.javabegin.tasklist.entity.Category;
import ru.javabegin.tasklist.repo.CategoryRepository;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {

        this.categoryRepository = categoryRepository;

    }

    @GetMapping("/test")
    public List<Category> test() {

        List<Category> list = categoryRepository.findAll();

        return list;

    }

    @PostMapping("/add")
    public Category add(@RequestBody Category category){
        return categoryRepository.save(category);

    }

}
