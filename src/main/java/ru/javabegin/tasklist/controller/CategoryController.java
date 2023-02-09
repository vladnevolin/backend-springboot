package ru.javabegin.tasklist.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javabegin.tasklist.entity.Category;
import ru.javabegin.tasklist.repo.CategoryRepository;
import ru.javabegin.tasklist.search.CategorySearchValues;
import ru.javabegin.tasklist.util.MyLogger;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/category")
public class CategoryController {

    //доступ к бд
    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {

        this.categoryRepository = categoryRepository;

    }

    @GetMapping("/all")
    public List<Category> findAll() {

        MyLogger.showMethodName("CategoryController: all() -----------------------------------------------------------------------------------------");

        return categoryRepository.findAllByOrderByTitleAsc();

    }

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category){

        MyLogger.showMethodName("CategoryController: add() -----------------------------------------------------------------------------------------");

        //проверка на обязательные параметры
        if (category.getId() != null && category.getId() != 0) {
            //передавать id нельзя, он выдается автоматически
            return new ResponseEntity( "redudant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передется пустое значение title
        if (category.getId() == null || category.getTitle().trim().length() == 0){
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryRepository.save(category));

    }


    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Category category) {

        MyLogger.showMethodName("CategoryController: update() -----------------------------------------------------------------------------------------");

        if (category.getId() == null && category.getId() != 0) {
            //передавать id нельзя, он выдается автоматически
            return new ResponseEntity("redudant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передется пустое значение title
        if (category.getId() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        // save работает ка на добавление, так и на обновление
        return ResponseEntity.ok(categoryRepository.save(category));

    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {

        MyLogger.showMethodName("CategoryController: id() -----------------------------------------------------------------------------------------");

        Category category = null;

        try{
            category = categoryRepository.findById(id).get();
            }catch (NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity("id "+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(category);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        MyLogger.showMethodName("CategoryController: delete() -----------------------------------------------------------------------------------------");

        try{
            categoryRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id " +id+ " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK); //не возвращаем удаленный объект

    }

    // поиск по любым параметрам CategorySearchValues
    @PostMapping("/search")
    public ResponseEntity<List<Category>> search(@RequestBody CategorySearchValues categorySearchValues){

        MyLogger.showMethodName("CategoryController: search() -----------------------------------------------------------------------------------------");

        // если вместо текста будет пусто или null - вернутся все категории
        return ResponseEntity.ok(categoryRepository.findByTitle(categorySearchValues.getText()));

    }
    
    
}
