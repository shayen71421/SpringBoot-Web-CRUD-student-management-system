package com.gavrilov.webcrud.controller;

import com.gavrilov.webcrud.entity.Student;
import com.gavrilov.webcrud.service.StudentService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // handler method to handle list students and return mode and view

    @GetMapping("/students")
    public String listStudents(Model model,
                               @Param("keyword") String keyword){
        List<Student> listStudent;
        if (keyword != null && !keyword.isEmpty()) {
            listStudent = studentService.listAll(keyword);
        } else {
            listStudent = studentService.getFirstNStudents(10);
        }
        model.addAttribute("students", listStudent);
        model.addAttribute("keyword", keyword);
        return "students";
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model){
        // create student object to hold student form data
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student){
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model){
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute("student") Student student,
                                Model model){
        // get student from database by id
        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setId(id);
        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());

        // save updated student object
        studentService.updateStudent(existingStudent);
        return "redirect:/students";
    }

    // handler method to handle delete student request
    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }

    @GetMapping("/students/view/{id}")
    public String viewStudent(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "view_student";
    }
}
