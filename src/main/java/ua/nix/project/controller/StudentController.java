package ua.nix.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.nix.project.repository.StudentRepository;
import ua.nix.project.repository.entity.StudentEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students")
    public String homePage(Model model){
        Iterable<StudentEntity> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/students/{id}")
    public String studentDetails(@PathVariable(value = "id") long id, Model model) {
        if(!studentRepository.existsById(id))
            return "redirect:/students";

        Optional<StudentEntity> student = studentRepository.findById(id);
        List<StudentEntity> res = new ArrayList<>();
        student.ifPresent(res::add);
        model.addAttribute("student", res);
        return "student-detail";
    }

    @GetMapping("/students/add")
    public String addStudent(Model model) {
        return "student-add";
    }

    @PostMapping("/students/add")
    public String addStudentPost(@RequestParam String name, @RequestParam String email, Model model) {
        StudentEntity student = new StudentEntity();
        student.setName(name);
        student.setEmail(email);
        studentRepository.save(student);
        return "redirect:/students";
    }

    @PostMapping("/students/{id}")
    public String studentDelete(@PathVariable(value = "id") long id, Model model) {
        StudentEntity student = studentRepository.findById(id).orElseThrow();
        studentRepository.delete(student);
        return "redirect:/students";
    }

    @GetMapping("/students/{id}/edit")
    public String studentEdit(@PathVariable(value = "id") long id,  Model model) {
        if(!studentRepository.existsById(id))
            return "redirect:/students";

        Optional<StudentEntity> student = studentRepository.findById(id);
        List<StudentEntity> res = new ArrayList<>();
        student.ifPresent(res::add);
        model.addAttribute("student", res);
        return "student-edit";
    }

    @PostMapping("/students/{id}/edit")
    public String studentUpdate(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam String email,
                               Model model) {
        StudentEntity student = studentRepository.findById(id).orElseThrow();
        student.setName(name);
        student.setEmail(email);
        studentRepository.save(student);
        return "redirect:/students";
    }
}