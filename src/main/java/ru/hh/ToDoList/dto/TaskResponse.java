package ru.hh.ToDoList.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value = "TaskResponse - задача для ответа", parent = TaskResponse.class)
public class TaskResponse {

  @ApiModelProperty(value = "Идентификатор задачи", name = "id", dataType = "Long", example = "1", position = 1)
  private Long id;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  @ApiModelProperty(value = "Дата создания задачи", name = "createDate", dataType = "LocalDateTime", example = "2021-03-01T12:30", position = 2)
  private LocalDateTime createDate;

  @ApiModelProperty(value = "Флаг указывающий на статус выполнения задачи", name = "isDone", dataType = "Boolean", example = "false", position = 3)
  private Boolean isDone;

  @ApiModelProperty(value = "Описание задачи", name = "description", dataType = "String", example = "Сделать дз по Spring", position = 4)
  private String description;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  @ApiModelProperty(value = "Дата сдачи задачи", name = "deadlineDate", dataType = "LocalDateTime", example = "2022-01-01T12:30", position = 5)
  private LocalDateTime deadlineDate;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  @ApiModelProperty(value = "Дата выполнения задачи", name = "completionDate", dataType = "LocalDateTime", example = "2021-11-23T15:45", position = 6)
  private LocalDateTime completionDate;

  @JsonFormat(pattern = "dd.MM.yy HH:mm")
  @ApiModelProperty(value = "Дата создания задачи для отображения", name = "createDateForShow", dataType = "LocalDateTime", example = "01.03.21 12:30", position = 7)
  private LocalDateTime createDateForShow;

  @JsonFormat(pattern = "dd.MM.yy HH:mm")
  @ApiModelProperty(value = "Дата сдачи задачи для отображения", name = "deadlineDateForShow", dataType = "deadlineDateForShow", example = "01.01.22 12:30", position = 8)
  private LocalDateTime deadlineDateForShow;

  @JsonFormat(pattern = "dd.MM.yy HH:mm")
  @ApiModelProperty(value = "Дата выполнения задачи для отображения", name = "completionDateForShow", dataType = "LocalDateTime", example = "23.11.21 15:45", position = 9)
  private LocalDateTime completionDateForShow;

  private TaskStatusResponse currentStatus;

}
