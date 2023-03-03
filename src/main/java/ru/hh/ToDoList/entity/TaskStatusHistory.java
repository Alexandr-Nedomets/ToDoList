package ru.hh.ToDoList.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import ru.hh.ToDoList.enumeration.TaskStatusEnum;

@Entity
@Table(name = "task_status_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskStatusHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "task_id", nullable = false)
  private Long taskId;

  @CreationTimestamp
  @Column(name = "create_date", nullable = false)
  private LocalDateTime createDate;

  @Getter
  @Enumerated(value = EnumType.STRING)
  @Column(name = "status", nullable = false)
  private TaskStatusEnum status;

  public TaskStatusHistory(Long taskId, TaskStatusEnum status) {
    this.taskId = taskId;
    this.status = status;
  }

}
