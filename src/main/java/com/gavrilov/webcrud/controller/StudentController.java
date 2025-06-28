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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
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
                               @Param("keyword") String keyword,
                               @Param("branch") String branch) {
        List<Student> listStudent;
        if (branch != null && !branch.isEmpty()) {
            listStudent = studentService.getStudentsByBranch(branch);
        } else if (keyword != null && !keyword.isEmpty()) {
            listStudent = studentService.listAll(keyword);
        } else {
            listStudent = studentService.getAllStudents(); // Show all students by default
        }
        model.addAttribute("students", listStudent);
        model.addAttribute("keyword", keyword);
        model.addAttribute("branches", studentService.getAllBranches());
        model.addAttribute("selectedBranch", branch);
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

    @GetMapping("/birthdays")
    public String birthdaysPage(Model model) {
        List<Student> allStudents = studentService.getAllStudents();
        LocalDate today = LocalDate.now();
        List<Student> todayBirthdays = new ArrayList<>();
        List<Student> upcomingBirthdays = new ArrayList<>();
        List<Student> pastBirthdays = new ArrayList<>();
        List<Student> unknownBirthdays = new ArrayList<>();
        for (Student s : allStudents) {
            if (s.getDateOfBirth() == null || s.getDateOfBirth().equals(LocalDate.of(1970, 1, 1))) {
                unknownBirthdays.add(s);
            } else {
                LocalDate birthdayThisYear = s.getDateOfBirth().withYear(today.getYear());
                if (birthdayThisYear.isEqual(today)) {
                    todayBirthdays.add(s);
                } else if (birthdayThisYear.isAfter(today)) {
                    upcomingBirthdays.add(s);
                } else {
                    pastBirthdays.add(s);
                }
            }
        }
        upcomingBirthdays.sort(Comparator.comparing(s -> s.getDateOfBirth().withYear(today.getYear())));
        pastBirthdays.sort(Comparator.comparing(s -> s.getDateOfBirth().withYear(today.getYear())));
        model.addAttribute("todayBirthdays", todayBirthdays);
        model.addAttribute("upcomingBirthdays", upcomingBirthdays);
        model.addAttribute("pastBirthdays", pastBirthdays);
        model.addAttribute("unknownBirthdays", unknownBirthdays);
        return "birthdays";
    }
}
