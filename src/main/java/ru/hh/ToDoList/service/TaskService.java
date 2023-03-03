package ru.hh.ToDoList.service;

import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hh.ToDoList.entity.Task;
import ru.hh.ToDoList.enumeration.TaskStatusEnum;
import ru.hh.ToDoList.exception.NotFoundException;
import ru.hh.ToDoList.mapper.TaskMapper;
import ru.hh.ToDoList.repository.TaskRepository;

@Service
@RequiredArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;
  private final TaskMapper taskMapper;
  private final TaskStatusHistoryService taskStatusHistoryService;

  public List<Task> findAll() {
    return taskRepository.findAll();
  }

  public Task findById(Long id) {
    return taskRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(Task.class, id));
  }

  @Transactional
  public void deleteById(Long id) {
    if (taskRepository.existsById(id)) {
      taskStatusHistoryService.deleteByTaskId(id);
      taskRepository.deleteById(id);
    }
  }

  @Transactional
  public Task save(Task task) {
    task.setCurrentStatus(TaskStatusEnum.IN_PROGRESS);
    task = taskRepository.save(task);
    taskStatusHistoryService.save(task);
    return task;
  }

  @Transactional
  public Task update(Long id, Task task) {
    Task foundTask = findById(id);
    task.setId(id);
    TaskStatusEnum nextStatus = getNextStatus(task);
    if (nextStatus != task.getCurrentStatus()) {
      task.setCurrentStatus(nextStatus);
      taskStatusHistoryService.save(task);
    }
    if (task.getIsDone()) {
      task.setCompletionDate(LocalDateTime.now());
    }
    return taskRepository.save(taskMapper.toTaskForUpdate(task, foundTask));
  }

  private TaskStatusEnum getNextStatus(Task task) {
    TaskStatusEnum nextStatus = task.getCurrentStatus();
    LocalDateTime now = LocalDateTime.now();
    if (task.getIsDone() && !now.isAfter(task.getDeadlineDate())) {
      nextStatus = TaskStatusEnum.COMPLETED_ON_TIME;
    } else if (task.getIsDone() && now.isAfter(task.getDeadlineDate())) {
      nextStatus = TaskStatusEnum.COMPLETED_LATE;
    } else if (!task.getIsDone() && now.isAfter(task.getDeadlineDate())) {
      nextStatus = TaskStatusEnum.EXPIRED;
    }
    return nextStatus;
  }

}
