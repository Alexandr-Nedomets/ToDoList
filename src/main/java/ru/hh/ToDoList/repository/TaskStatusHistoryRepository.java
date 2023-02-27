package ru.hh.ToDoList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import ru.hh.ToDoList.entity.TaskStatusHistory;

public interface TaskStatusHistoryRepository extends JpaRepository<TaskStatusHistory, Long> {

  void deleteByTaskId(@Param(value = "taskId") Long taskId);

}
