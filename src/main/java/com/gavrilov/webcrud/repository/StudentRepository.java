package com.gavrilov.webcrud.repository;

import com.gavrilov.webcrud.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.name LIKE %?1% OR s.email LIKE %?1%")
    List<Student> findAll(String keyword);

    Page<Student> findAll(Pageable pageable);
}
