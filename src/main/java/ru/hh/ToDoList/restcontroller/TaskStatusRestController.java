package ru.hh.ToDoList.restcontroller;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hh.ToDoList.dto.TaskStatusResponse;
import ru.hh.ToDoList.enumeration.TaskStatusEnum;
import ru.hh.ToDoList.mapper.TaskStatusMapper;
import ru.hh.ToDoList.service.TaskStatusService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/task-status")
public class TaskStatusRestController {

  private final TaskStatusService taskStatusService;
  private final TaskStatusMapper taskStatusMapper;

  @ApiOperation(value = "Получение всех статусов задач")
  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<List<TaskStatusResponse>> findAll() {
    return ResponseEntity.ok(taskStatusMapper.toTaskResponse(taskStatusService.findAll()));
  }

  @ApiOperation(value = "Получение статуса задачи по идентификатору")
  @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<TaskStatusResponse> findById(@PathVariable(name = "id") Long id) {
    return ResponseEntity.ok(taskStatusMapper.toTaskResponse(taskStatusService.findById(id)));
  }

  @ApiOperation(value = "Получение статуса задачи по названию")
  @GetMapping(path = "/{name}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<TaskStatusResponse> findByName(@PathVariable(name = "name") String name) {
    return ResponseEntity.ok(taskStatusMapper.toTaskResponse(taskStatusService.findByName(TaskStatusEnum.valueOf(name))));
  }

  @ApiOperation(value = "Получение текущего статуса задачи по идентификатору задачи")
  @GetMapping(path = "/by/{taskId}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<TaskStatusResponse> getCurrentStatusByTaskId(@PathVariable(name = "taskId") Long taskId) {
    return ResponseEntity.ok(taskStatusMapper.toTaskResponse(taskStatusService.getCurrentStatusByTaskId(taskId)));
  }

}
