package ru.javabegin.tasklist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javabegin.tasklist.entity.Priority;
import ru.javabegin.tasklist.repo.PriorityRepository;

import java.util.List;

@RestController
@RequestMapping ("/priority")
public class PriorityController {

    private PriorityRepository priorityRepository;

    public PriorityController(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @GetMapping("/test")
    public List<Priority> test(){

        List<Priority> list = priorityRepository.findAll();

        return list;

    }

    @PostMapping("/add")
    public ResponseEntity<Priority> add(@RequestBody Priority priority){

        if (priority.getId() != null && priority.getId() != 0) {
            return new ResponseEntity( "redudant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);

        }

        if (priority.getId() == null || priority.getTitle().trim().length() == 0){
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityRepository.save(priority));

    }
}
