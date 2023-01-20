package ru.javabegin.tasklist.controller;

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
    public Priority add(@RequestBody Priority priority){
        return priorityRepository.save(priority);

    }
}
