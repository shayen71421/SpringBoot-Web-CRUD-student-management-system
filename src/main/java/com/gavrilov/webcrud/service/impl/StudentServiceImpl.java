package com.gavrilov.webcrud.service.impl;

import com.gavrilov.webcrud.entity.Student;
import com.gavrilov.webcrud.repository.StudentRepository;
import com.gavrilov.webcrud.service.StudentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAllByOrderByUniversityRegNoAsc(PageRequest.of(0, Integer.MAX_VALUE)).getContent();
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> listAll(String keyword) {
        if (keyword != null){
            return studentRepository.findAll(keyword);
        }
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getFirstNStudents(int n) {
        return studentRepository.findAllByOrderByUniversityRegNoAsc(PageRequest.of(0, n)).getContent();
    }

    @Override
    public List<String> getAllBranches() {
        return studentRepository.findDistinctBranches();
    }

    @Override
    public List<Student> getStudentsByBranch(String branchCode) {
        return studentRepository.findByUniversityRegNoContainingOrderByUniversityRegNoAsc(branchCode);
    }
}
