package br.com.mathwidu.todoList.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data // Lombok annotation to generate getters and setters
@Entity(name = "tb_user") // JPA annotation to map the class to a table in the database
public class UserModel {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true)
    private String username;
    public String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime creaedAt;

}
