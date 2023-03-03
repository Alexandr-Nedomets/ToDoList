package ru.hh.ToDoList.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hh.ToDoList.entity.Task;
import ru.hh.ToDoList.entity.TaskStatusHistory;
import ru.hh.ToDoList.repository.TaskStatusHistoryRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskStatusHistoryService {

  private final TaskStatusHistoryRepository taskStatusHistoryRepository;


  public void deleteByTaskId(Long taskId) {
    taskStatusHistoryRepository.deleteByTaskId(taskId);

  }

  public TaskStatusHistory save(Task task) {
    TaskStatusHistory taskStatusHistory = new TaskStatusHistory(task.getId(), task.getCurrentStatus());
    return taskStatusHistoryRepository.save(taskStatusHistory);
  }

}
