package ru.hh.ToDoList.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hh.ToDoList.entity.Task;
import ru.hh.ToDoList.entity.TaskStatusHistory;
import ru.hh.ToDoList.enumeration.TaskStatusEnum;
import ru.hh.ToDoList.repository.TaskStatusHistoryRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskStatusHistoryService {

  private final TaskStatusHistoryRepository taskStatusHistoryRepository;
  private final TaskStatusService taskStatusService;


  public void deleteByTaskId(Long taskId) {
    taskStatusHistoryRepository.deleteByTaskId(taskId);

  }

  public void write(Task task, TaskStatusEnum statusName) {
    TaskStatusHistory taskStatusHistory = new TaskStatusHistory();
    taskStatusHistory.setTask(task);
    taskStatusHistory.setTaskStatus(taskStatusService.findByName(statusName));
    taskStatusHistoryRepository.save(taskStatusHistory);
  }

  public void write(Task task) {
    TaskStatusHistory taskStatusHistory = new TaskStatusHistory();
    taskStatusHistory.setTask(task);
    taskStatusHistory.setTaskStatus(taskStatusService.getNextStatus(task));
    taskStatusHistoryRepository.save(taskStatusHistory);
  }

}
