package ru.hh.ToDoList.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value = "TaskStatusResponse - статус задачи для ответа", parent = TaskStatusResponse.class)
public class TaskStatusResponse {

  @ApiModelProperty(value = "Идентификатор статуса задачи", name = "id", dataType = "Long", example = "1", position = 1)
  private Long id;

  @ApiModelProperty(value = "Название статуса задачи", name = "name", dataType = "String", example = "CREATED", position = 2)
  private String name;

  @ApiModelProperty(value = "Описание статуса задачи", name = "description", dataType = "String", example = "Создана", position = 3)
  private String description;

}
