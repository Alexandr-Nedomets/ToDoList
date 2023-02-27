package ru.hh.ToDoList.mapper;

import java.util.List;
import org.mapstruct.*;
import ru.hh.ToDoList.dto.TaskRequest;
import ru.hh.ToDoList.dto.TaskResponse;
import ru.hh.ToDoList.entity.Task;

@Mapper(componentModel = "spring", uses = {TaskStatusMapper.class})
public interface TaskMapper {

  @Mapping(source = "completionDate", target = "completionDateForShow")
  @Mapping(source = "deadlineDate", target = "deadlineDateForShow")
  @Mapping(source = "createDate", target = "createDateForShow")
  TaskResponse toTaskResponse(Task task);

  List<TaskResponse> toTaskResponse(List<Task> tasks);

  Task toTask(TaskRequest taskRequest);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Task toTaskForUpdate(Task task, @MappingTarget Task newTask);

}
