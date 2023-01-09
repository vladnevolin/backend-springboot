package ru.javabegin.tasklist.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Task {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "title", length = 100)
    private String title;
    @Basic
    @Column(name = "completed")
    private Integer completed;
    @Basic
    @Column(name = "date")
    private Timestamp date;
    @Basic
    @Column(name = "priority_id")
    private Long priorityId;
    @Basic
    @Column(name = "category_id")
    private Long categoryId;
    @ManyToOne
    @JoinColumn(name = "priority_id", referencedColumnName = "id")
    private Priority priorityByPriorityId;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category categoryByCategoryId;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getCompleted() {
        return completed;
    }

    public Timestamp getDate() {
        return date;
    }

    public Long getPriorityId() {
        return priorityId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Priority getPriorityByPriorityId() {
        return priorityByPriorityId;
    }

    public Category getCategoryByCategoryId() {
        return categoryByCategoryId;
    }
}
