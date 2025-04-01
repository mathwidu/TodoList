package br.com.mathwidu.todoList.user;

import java.util.UUID;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {

    UserModel findByUsername(String username);
    
}