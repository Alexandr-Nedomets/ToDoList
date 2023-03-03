package ru.hh.ToDoList.view.controller;

import java.util.Objects;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import ru.hh.ToDoList.dto.TaskRequest;
import ru.hh.ToDoList.dto.TaskResponse;
import ru.hh.ToDoList.view.service.MainService;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequiredArgsConstructor
@ApiIgnore
public class MainController {

  private final MainService mainService;

  @GetMapping(path = "/")
  public String getMain(Model model) {
    model.addAttribute("task", new TaskRequest());
    addAttribute(model, false, false);
    return "index";
  }

  @GetMapping(path = "/show-create-form")
  public String showCreateForm(Model model) {
    model.addAttribute("task", new TaskRequest());
    addAttribute(model, true, false);
    return "index";
  }

  @PostMapping(path = "/create")
  public String save(@ModelAttribute(value = "task") @Valid TaskRequest task, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      addAttribute(model, true, false);
      return "index";
    }
    mainService.save(task);
    return "redirect:/";
  }

  @DeleteMapping(path = "/delete/{id}")
  public String deleteById(@PathVariable(name = "id") Long id) {
    mainService.deleteById(id);
    return "redirect:/";
  }

  @PutMapping(path = "/update/{id}")
  public String update(@PathVariable(name = "id") Long id,
                       @Valid @ModelAttribute(value = "task") TaskRequest task,
                       BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      if (Objects.nonNull(task.getDeadlineDate())) {
        TaskResponse taskResponse = mainService.findById(id);
        if (Objects.equals(task.getDeadlineDate(), taskResponse.getDeadlineDate())) {
          mainService.update(id, task);
          return "redirect:/";
        }
      }
      addAttribute(model, false, true);
      return "index";
    }
    mainService.update(id, task);
    return "redirect:/";
  }

  @GetMapping(path = "/change/{id}")
  public String change(@PathVariable(name = "id") Long id, Model model) {
    model.addAttribute("task", mainService.findById(id));
    addAttribute(model, false, true);
    return "index";
  }

  private Model addAttribute(Model model, boolean isCreated, boolean isUpdated) {
    model.addAttribute("tasks", mainService.findAll());
    model.addAttribute("isCreated", isCreated);
    model.addAttribute("isUpdated", isUpdated);
    return model;
  }
}
