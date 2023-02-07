package ru.javabegin.tasklist.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.javabegin.tasklist.entity.Stat;

@Repository
public interface StatRepository extends CrudRepository<Stat, Long> {
}
