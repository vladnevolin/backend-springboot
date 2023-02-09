package ru.javabegin.tasklist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javabegin.tasklist.entity.Stat;
import ru.javabegin.tasklist.repo.StatRepository;
import ru.javabegin.tasklist.util.MyLogger;

@RestController
public class StatController {

    private final StatRepository statRepository; // сервис для доступа к данным напрямую из репозитория

    // автоматическое внедрение экземпляра класса через конструктор
    // не используем Autowired для переменной класса, т.к. "Field injection no recommended "
    public StatController(StatRepository statRepository) {

        this.statRepository = statRepository;

    }
    private final Long defaultId =1l; // l - чтобы тип числа был Long, иначе будет ошибка компиляции

    // для статистики всегдла получает только  одну строку  с id=1 (согласно таблице БД)
    @GetMapping("/stat")
    public ResponseEntity<Stat> findById() {

        MyLogger.showMethodName("StatController: stat() -----------------------------------------------------------------------------------------");

        // можно не использовать ResponseEntity, а просто вернуть коллекцию, код все равно будет 200
        return ResponseEntity.ok(statRepository.findById(defaultId).get());

    }

}
