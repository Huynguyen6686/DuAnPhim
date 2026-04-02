package org.example.buoi1.repository;

import org.example.buoi1.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TodoRepository extends JpaRepository<Todo,Long> {


}
