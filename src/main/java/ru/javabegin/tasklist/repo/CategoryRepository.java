package ru.javabegin.tasklist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javabegin.tasklist.entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    //получить все значения, сортировка по названию
    List<Category> findAllByOrderByTitleAsc();

}
