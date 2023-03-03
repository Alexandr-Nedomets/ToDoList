package ru.hh.ToDoList.mapper;

import java.util.Arrays;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.hh.ToDoList.dto.TaskRequest;
import ru.hh.ToDoList.dto.TaskResponse;
import ru.hh.ToDoList.entity.Task;
import ru.hh.ToDoList.enumeration.TaskStatusEnum;

@Mapper(componentModel = "spring", uses = {TaskStatusHistoryMapper.class})
public interface TaskMapper {

  @Mapping(source = "completionDate", target = "completionDateForShow")
  @Mapping(source = "deadlineDate", target = "deadlineDateForShow")
  @Mapping(source = "createDate", target = "createDateForShow")
  @Mapping(source = "currentStatus", target = "currentStatus", qualifiedByName = "mapCurrentStatus")
  TaskResponse toTaskResponse(Task task);

  @Named("mapCurrentStatus")
  default String mapCurrentStatus(TaskStatusEnum currentStatus) {
    return currentStatus.getDescription();
  }

  List<TaskResponse> toTaskResponse(List<Task> tasks);

  @Mapping(source = "currentStatus", target = "currentStatus", qualifiedByName = "mapStatus")
  Task toTask(TaskRequest taskRequest);

  @Named("mapStatus")
  default String mapCurrentStatus(String currentStatus) {
    return Arrays.stream(TaskStatusEnum.values())
        .filter(status -> status.getDescription().equals(currentStatus))
        .map(TaskStatusEnum::getDescription)
        .findFirst().get();
  }

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Task toTaskForUpdate(Task task, @MappingTarget Task newTask);

}
