package br.com.gabriel.todolist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class Root {
    @GetMapping
    public String appIsWorking () {
        return "App is working!!";
    }

}
