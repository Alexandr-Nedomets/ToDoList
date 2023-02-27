package ru.hh.ToDoList.view.service;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.hh.ToDoList.dto.TaskRequest;
import ru.hh.ToDoList.dto.TaskResponse;

@Service
@RequiredArgsConstructor
public class MainService {

  private final RestTemplate restTemplate;
  @Value("${url.base}")
  private String urlBase;
  @Value("${url.task}")
  private String urlTask;

  @Value("${url.task-status}")
  private String urlTaskStatus;


  public List<TaskResponse> findAll() {
    return restTemplate.getForObject(URI.create(urlBase + urlTask), List.class);
  }

  public TaskResponse findById(Long id) {
    return restTemplate.getForObject(URI.create(urlBase + urlTask + id), TaskResponse.class);
  }

  public TaskResponse save(TaskRequest taskRequest) {
    RequestEntity request = new RequestEntity(taskRequest, HttpMethod.POST, URI.create(urlBase + urlTask));
    ResponseEntity<TaskResponse> response = restTemplate.exchange(request, TaskResponse.class);
    return response.getBody();
  }

  public TaskResponse update(Long id, TaskRequest taskRequest) {
    RequestEntity request = new RequestEntity(taskRequest, HttpMethod.PUT, URI.create(urlBase + urlTask + id));
    ResponseEntity<TaskResponse> response = restTemplate.exchange(request, TaskResponse.class);
    return response.getBody();
  }

  public void deleteById(Long id) {
    restTemplate.delete(URI.create(urlBase + urlTask + id));
  }

}
