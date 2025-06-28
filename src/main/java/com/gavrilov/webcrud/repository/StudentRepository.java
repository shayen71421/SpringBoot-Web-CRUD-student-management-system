package com.gavrilov.webcrud.repository;

import com.gavrilov.webcrud.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', ?1, '%')) OR LOWER(s.email) LIKE LOWER(CONCAT('%', ?1, '%')) OR LOWER(s.universityRegNo) LIKE LOWER(CONCAT('%', ?1, '%')) ORDER BY s.universityRegNo ASC")
    List<Student> findAll(String keyword);

    Page<Student> findAllByOrderByUniversityRegNoAsc(Pageable pageable);

    @Query("SELECT DISTINCT SUBSTRING(s.universityRegNo, 6, 2) FROM Student s WHERE s.universityRegNo IS NOT NULL")
    List<String> findDistinctBranches();

    List<Student> findByUniversityRegNoContainingOrderByUniversityRegNoAsc(String branchCode);
}
