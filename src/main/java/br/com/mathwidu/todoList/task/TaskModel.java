package br.com.mathwidu.todoList.task;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.cglib.core.Local;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_tasks")

public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(length = 50)
    private String title;
    private String description;
    private LocalDate startAt;
    private LocalDate endAt;
    private String priority;

    @CreationTimestamp
    private LocalDate createdAt;
    private UUID userId;

    public void setTitle(String title) throws Exception {
        // Validate title length
        // and check for null or empty
        if (title.length() > 50) {
            throw new IllegalArgumentException("Title length exceeds 50 characters");
        }
        if (title == null || title.isEmpty()) {
            throw new Exception("Title cannot be null or empty");
        }
        this.title = title;
    }

}
