package ru.hh.ToDoList.service;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    Task save = taskRepository.save(task);
    taskStatusHistoryService.write(task, TaskStatusEnum.CREATED);
    taskStatusHistoryService.write(task, TaskStatusEnum.IN_PROGRESS);
    return save;
  }

  @Transactional
  public Task update(Long id, Task task) {
    Task foundTask = findById(id);
    task.setId(id);
    if (task.getIsDone()) {
      task.setCompletionDate(LocalDateTime.now());
    }
    taskStatusHistoryService.write(task);
    return taskRepository.save(taskMapper.toTaskForUpdate(task, foundTask));
  }

}
