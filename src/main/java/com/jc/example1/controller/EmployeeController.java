package com.jc.example1.controller;

import org.springframework.ui.Model; // Corrección aquí
import com.jc.example1.model.Employee;
import com.jc.example1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    // Modo FULL
    // Modo CHUNKED: añadir propiedad en spring.thymeleaf
    @GetMapping("/employees")
    public String findAll(Model model) {
        Flux<Employee> employees = service.findAll();
        model.addAttribute("employees", employees);
        return "employee-list";
    }

    @GetMapping("/employees/reactive")
    public String findAllReactive(Model model) {
        Flux<Employee> employees = service.findAll();
        model.addAttribute("employees", new ReactiveDataDriverContextVariable(employees, 50));
        return "employee-list";
    }
}
