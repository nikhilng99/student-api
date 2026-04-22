package com.student.api.studentapi.repository;

import com.student.api.studentapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT s FROM Student s WHERE s.department.id = :deptId AND s.age >= :minAge")
    List<Student> findByDeptIdAndMinAge(@Param("deptId") Integer deptId, @Param("minAge") Integer minAge);
}
