package ru.hh.ToDoList.restcontroller;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hh.ToDoList.dto.TaskRequest;
import ru.hh.ToDoList.dto.TaskResponse;
import ru.hh.ToDoList.mapper.TaskMapper;
import ru.hh.ToDoList.service.TaskService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/tasks")
public class TaskRestController {

  private final TaskService taskService;
  private final TaskMapper taskMapper;

  @ApiOperation(value = "Получение всех задач")
  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<List<TaskResponse>> findAll() {
    return ResponseEntity.ok(taskMapper.toTaskResponse(taskService.findAll()));
  }

  @ApiOperation(value = "Получение задачи по идентификатору")
  @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<TaskResponse> findById(@PathVariable(name = "id") Long id) {
    return ResponseEntity.ok(taskMapper.toTaskResponse(taskService.findById(id)));
  }

  @ApiOperation(value = "Удаление задачи по идентификатору")
  @DeleteMapping(path = "/{id}")
  public void deleteById(@PathVariable(name = "id") Long id) {
    taskService.deleteById(id);
  }

  @ApiOperation(value = "Создание задачи")
  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<TaskResponse> save(@RequestBody TaskRequest taskRequest) {
    return ResponseEntity.ok(taskMapper.toTaskResponse(taskService.save(taskMapper.toTask(taskRequest))));
  }

  @ApiOperation(value = "Обновление задачи по идентификатору")
  @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<TaskResponse> update(@Valid @PathVariable(name = "id") Long id, @RequestBody TaskRequest taskRequest) {
    return ResponseEntity.ok(taskMapper.toTaskResponse(taskService.update(id, taskMapper.toTask(taskRequest))));
  }

}
