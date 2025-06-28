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
import java.util.stream.Collectors;

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
                               @Param("branch") String branch,
                               @Param("page") Integer page) {
        int pageSize = 25;
        int currentPage = (page == null || page < 1) ? 1 : page;
        List<Student> listStudent;
        if (branch != null && !branch.isEmpty()) {
            listStudent = studentService.getStudentsByBranch(branch);
        } else if (keyword != null && !keyword.isEmpty()) {
            listStudent = studentService.listAll(keyword);
        } else {
            listStudent = studentService.getAllStudents(); // Show all students by default
        }
        int total = listStudent.size();
        int from = (currentPage - 1) * pageSize;
        int to = Math.min(from + pageSize, total);
        List<Student> pageList = listStudent.subList(Math.min(from, total), Math.min(to, total));
        int totalPages = (int) Math.ceil((double) total / pageSize);
        model.addAttribute("students", pageList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("branches", studentService.getAllBranches());
        model.addAttribute("selectedBranch", branch);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
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

    @GetMapping("/insta-ids")
    public String instaIdsPage(Model model, @Param("branch") String branch, @Param("page") Integer page) {
        int pageSize = 25;
        int currentPage = (page == null || page < 1) ? 1 : page;
        List<Student> allStudents;
        if (branch != null && !branch.isEmpty()) {
            allStudents = studentService.getStudentsByBranch(branch);
        } else {
            allStudents = studentService.getAllStudents();
        }
        List<Student> known = allStudents.stream()
            .filter(s -> s.getInstagramId() != null && !s.getInstagramId().trim().isEmpty())
            .sorted(Comparator.comparing(Student::getBranch, Comparator.nullsFirst(String.CASE_INSENSITIVE_ORDER))
                .thenComparing(Student::getInstagramId, Comparator.nullsFirst(String.CASE_INSENSITIVE_ORDER)))
            .collect(Collectors.toList());
        List<Student> unknown = allStudents.stream()
            .filter(s -> s.getInstagramId() == null || s.getInstagramId().trim().isEmpty())
            .sorted(Comparator.comparing(Student::getBranch, Comparator.nullsFirst(String.CASE_INSENSITIVE_ORDER))
                .thenComparing(Student::getName, Comparator.nullsFirst(String.CASE_INSENSITIVE_ORDER)))
            .collect(Collectors.toList());
        int knownTotal = known.size();
        int knownFrom = (currentPage - 1) * pageSize;
        int knownTo = Math.min(knownFrom + pageSize, knownTotal);
        List<Student> knownPage = known.subList(Math.min(knownFrom, knownTotal), Math.min(knownTo, knownTotal));
        int unknownTotal = unknown.size();
        int unknownFrom = (currentPage - 1) * pageSize;
        int unknownTo = Math.min(unknownFrom + pageSize, unknownTotal);
        List<Student> unknownPage = unknown.subList(Math.min(unknownFrom, unknownTotal), Math.min(unknownTo, unknownTotal));
        int totalPages = (int) Math.ceil((double) Math.max(knownTotal, unknownTotal) / pageSize);
        model.addAttribute("knownInsta", knownPage);
        model.addAttribute("unknownInsta", unknownPage);
        model.addAttribute("branches", studentService.getAllBranches());
        model.addAttribute("selectedBranch", branch);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        return "insta_ids";
    }

    @GetMapping("/students/contribute-insta/{id}")
    public String contributeInstaIdForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "contribute_insta";
    }

    @PostMapping("/students/contribute-insta/{id}")
    public String saveContributedInstaId(@PathVariable Long id, @Param("instagramId") String instagramId) {
        Student student = studentService.getStudentById(id);
        student.setInstagramId(instagramId);
        studentService.updateStudent(student);
        return "redirect:/insta-ids";
    }

    @GetMapping("/students/contribute-birthday/{id}")
    public String contributeBirthdayForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "contribute_birthday";
    }

    @PostMapping("/students/contribute-birthday/{id}")
    public String saveContributedBirthday(@PathVariable Long id, @Param("dateOfBirth") String dateOfBirth) {
        Student student = studentService.getStudentById(id);
        if (dateOfBirth != null && !dateOfBirth.isEmpty()) {
            student.setDateOfBirth(LocalDate.parse(dateOfBirth));
            studentService.updateStudent(student);
        }
        return "redirect:/birthdays";
    }
}
