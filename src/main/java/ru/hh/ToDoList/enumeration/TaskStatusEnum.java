package ru.hh.ToDoList.enumeration;

public enum TaskStatusEnum {
  IN_PROGRESS( "В работе"),
  EXPIRED( "Просрочена"),
  COMPLETED_ON_TIME("Завершена вовремя"),
  COMPLETED_LATE( "Завершена с опозданием");
  private String description;

  public String getDescription() {
    return description;
  }

  TaskStatusEnum(String description) {
    this.description = description;
  }

}
