package ru.hh.ToDoList.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import ru.hh.ToDoList.dto.TaskStatusResponse;
import ru.hh.ToDoList.entity.TaskStatus;

@Mapper(componentModel = "spring")
public interface TaskStatusMapper {

  TaskStatusResponse toTaskResponse(TaskStatus taskStatus);

  List<TaskStatusResponse> toTaskResponse(List<TaskStatus> tasks);
}
