package ru.hh.ToDoList.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@ApiModel(value = "TaskRequest - задача для запроса", parent = TaskRequest.class)
public class TaskRequest {

  @JsonIgnore
  private Long id;

  @ApiModelProperty(value = "Флаг указывающий на статус выполнения задачи", name = "isDone", dataType = "Boolean", example = "false", position = 1)
  private Boolean isDone = false;

  @NotBlank(message = "{TaskRequest.description.NotBlank}")
  @Size(min = 12, max = 255, message = "{TaskRequest.description.Size}")
  @ApiModelProperty(value = "Описание задачи", name = "description", dataType = "String", example = "Сделать дз по Spring", position = 2)
  private String description;

  @NotNull(message = "{TaskRequest.deadlineDate.NotBlank}")
  @Future(message = "{TaskRequest.deadlineDate.Past}")
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  @ApiModelProperty(value = "Дата сдачи задачи", name = "deadlineDate", dataType = "LocalDateTime", example = "2020-01-01T12:30", position = 3)
  private LocalDateTime deadlineDate;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  @ApiModelProperty(value = "Дата выполнения задачи", name = "completionDate", dataType = "LocalDateTime", example = "2021-11-23T15:45", position = 4)
  private LocalDateTime completionDate;

  private String currentStatus;

}
