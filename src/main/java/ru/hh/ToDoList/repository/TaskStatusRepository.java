package ru.hh.ToDoList.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.hh.ToDoList.entity.TaskStatus;
import ru.hh.ToDoList.enumeration.TaskStatusEnum;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {

  Optional<TaskStatus> findByName(TaskStatusEnum name);

  @Query(value = "SELECT ts FROM TaskStatusHistory AS tsh INNER JOIN tsh.taskStatus ts " +
      "WHERE tsh.createDate = (SELECT max(tsh1.createDate) FROM TaskStatusHistory AS tsh1 WHERE tsh1.task.id = :taskId)")
  TaskStatus getCurrentStatusByTaskId(@Param(value = "taskId") Long taskId);

}
