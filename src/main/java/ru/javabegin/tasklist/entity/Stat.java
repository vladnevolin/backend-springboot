package ru.javabegin.tasklist.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class Stat {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "completed_total")
    private Long completedTotal;
    @Basic
    @Column(name = "uncompleted_total")
    private Long uncompletedTotal;

    public Long getId() {
        return id;
    }

    public Long getCompletedTotal() {
        return completedTotal;
    }

    public Long getUncompletedTotal() {
        return uncompletedTotal;
    }

}
