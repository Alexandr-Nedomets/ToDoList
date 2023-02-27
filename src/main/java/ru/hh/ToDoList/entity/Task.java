package ru.hh.ToDoList.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JoinFormula;

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

  @Column(name = "is_done", nullable = false, columnDefinition = "boolean default false")
  private Boolean isDone;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "deadline_date", nullable = false)
  private LocalDateTime deadlineDate;

  @Column(name = "completion_date")
  private LocalDateTime completionDate;

  @ManyToOne
  @JoinFormula("(SELECT tsh.task_status_id FROM task_status_history AS tsh WHERE tsh.id " +
      "= (SELECT max(tsh1.id) FROM task_status_history AS tsh1 WHERE tsh1.task_id = id))")
  private TaskStatus currentStatus;

}
