package ru.hh.ToDoList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hh.ToDoList.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
