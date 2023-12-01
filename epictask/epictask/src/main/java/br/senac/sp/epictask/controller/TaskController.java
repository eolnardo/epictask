package br.senac.sp.epictask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.senac.sp.epictask.model.Task;
import br.senac.sp.epictask.repository.TaskRepository;
import jakarta.validation.Valid;


@Controller
@RequestMapping("task")
public class TaskController {
    
    @Autowired
    TaskRepository repository;

    @GetMapping
    public String index(Model model) {
        var lista = repository.findAll();
        model.addAttribute("tasks", lista);
        return "task/index";
    }


    @GetMapping("new") // http://localhost:8080/task/new
    public String form(Task task){


        return "task/form";
    }

    @PostMapping("new")
    public String save(@Valid Task task, BindingResult result){
        //receber os valores do form
        //salvar no bd
        //redirecionar para o lista /task
        if(result.hasErrors()) return "task/form";
        repository.save(task);
        return "redirect:/task";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id){
        repository.deleteById(id);
        return "redirect:/task";
    }
    
}
