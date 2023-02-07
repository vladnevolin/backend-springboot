package ru.javabegin.tasklist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javabegin.tasklist.entity.Priority;

import java.util.List;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
    //получить все значения, сортировка по id
    List<Priority> findAllByOrderByIdAsc();
}
