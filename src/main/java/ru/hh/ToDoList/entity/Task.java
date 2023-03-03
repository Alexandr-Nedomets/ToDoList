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
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import ru.hh.ToDoList.enumeration.TaskStatusEnum;

@Entity
@Table(name = "task")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @CreationTimestamp
  @Column(name = "create_date", nullable = false)
  private LocalDateTime createDate;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "deadline_date", nullable = false)
  private LocalDateTime deadlineDate;

  @Column(name = "completion_date")
  private LocalDateTime completionDate;

  @Enumerated(value = EnumType.STRING)
  @Column(name = "current_status", nullable = false)
  private TaskStatusEnum currentStatus;

  @Column(name = "is_done", nullable = false, columnDefinition = "boolean default false")
  private Boolean isDone;

}
