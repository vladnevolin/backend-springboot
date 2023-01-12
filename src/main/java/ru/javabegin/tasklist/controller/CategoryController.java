package ru.javabegin.tasklist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
