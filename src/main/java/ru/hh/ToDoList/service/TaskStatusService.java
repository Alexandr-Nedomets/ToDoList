package ru.hh.ToDoList.service;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hh.ToDoList.entity.Task;
import ru.hh.ToDoList.entity.TaskStatus;
import ru.hh.ToDoList.enumeration.TaskStatusEnum;
import ru.hh.ToDoList.exception.NotFoundException;
import ru.hh.ToDoList.repository.TaskStatusRepository;

@Service
@RequiredArgsConstructor
public class TaskStatusService {

  private final TaskStatusRepository taskStatusRepository;

  public List<TaskStatus> findAll() {
    return taskStatusRepository.findAll();
  }

  public TaskStatus findById(Long id) {
    return taskStatusRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(TaskStatus.class, id));
  }

  public TaskStatus findByName(TaskStatusEnum name) {
    return taskStatusRepository
        .findByName(name)
        .orElseThrow(() -> new NotFoundException(TaskStatus.class, name));
  }

  public TaskStatus getCurrentStatusByTaskId(Long taskId) {
    return taskStatusRepository.getCurrentStatusByTaskId(taskId);
  }

  public TaskStatus getNextStatus(Task task) {
    TaskStatusEnum nextStatus = TaskStatusEnum.IN_PROGRESS;
    LocalDateTime now = LocalDateTime.now();
    if (task.getIsDone() && !now.isAfter(task.getDeadlineDate())) {
      nextStatus = TaskStatusEnum.COMPLETED_ON_TIME;
    } else if (task.getIsDone() && now.isAfter(task.getDeadlineDate())) {
      nextStatus = TaskStatusEnum.COMPLETED_LATE;
    } else if (!task.getIsDone() && now.isAfter(task.getDeadlineDate())) {
      nextStatus = TaskStatusEnum.EXPIRED;
    }
    return findByName(nextStatus);
  }
}
