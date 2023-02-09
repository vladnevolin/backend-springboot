package ru.javabegin.tasklist.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javabegin.tasklist.entity.Priority;
import ru.javabegin.tasklist.repo.PriorityRepository;
import ru.javabegin.tasklist.search.PrioritySearchValues;
import ru.javabegin.tasklist.util.MyLogger;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping ("/priority")
public class PriorityController {

    private PriorityRepository priorityRepository;

    public PriorityController(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @GetMapping("/all")
    public List<Priority> findAll() {

        MyLogger.showMethodName("PriorityController: all() -----------------------------------------------------------------------------------------");

        return priorityRepository.findAllByOrderByIdAsc();

    }

    @PostMapping("/add")
    public ResponseEntity<Priority> add(@RequestBody Priority priority){

        MyLogger.showMethodName("PriorityController: add() -----------------------------------------------------------------------------------------");

        //проверка на обязательные параметры
        if (priority.getId() != null && priority.getId() != 0) {
            //передавать id нельзя, он выдается автоматически
            return new ResponseEntity( "redudant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);

        }

        //если передется пустое значение title
        if (priority.getId() == null || priority.getTitle().trim().length() == 0){
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityRepository.save(priority));

    }


    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Priority priority) {

        MyLogger.showMethodName("PriorityController: update() -----------------------------------------------------------------------------------------");

        if (priority.getId() == null && priority.getId() != 0) {
            //передавать id нельзя, он выдается автоматически
            return new ResponseEntity("redudant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передется пустое значение title
        if (priority.getId() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передется пустое значение color
        if (priority.getColor() == null || priority.getColor().trim().length() == 0) {
            return new ResponseEntity("missed param: color", HttpStatus.NOT_ACCEPTABLE);
        }

        // save работает ка на добавление, так и на обновление
        return ResponseEntity.ok(priorityRepository.save(priority));

    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Priority> findById(@PathVariable Long id) {

        MyLogger.showMethodName("PriorityController: id() -----------------------------------------------------------------------------------------");

        Priority priority = null;

        try {
            priority = priorityRepository.findById(id).get();
        } catch (NoSuchElementException e) { //если объект не будет найдет
            e.printStackTrace();
            return new ResponseEntity("id " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priority);

    }
    //параметр id передается не в BODY запроса, а в URl
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        MyLogger.showMethodName("PriorityController: delete() -----------------------------------------------------------------------------------------");

        try{
            priorityRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id " +id+ " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK); //не возвращаем удаленный объект
    }

    @PostMapping("/search")
    public ResponseEntity<List<Priority>> search(@RequestBody PrioritySearchValues prioritySearchValues){

        MyLogger.showMethodName("PriorityController: search() -----------------------------------------------------------------------------------------");

        return ResponseEntity.ok(priorityRepository.findByTitle(prioritySearchValues.getText()));
    }


}
