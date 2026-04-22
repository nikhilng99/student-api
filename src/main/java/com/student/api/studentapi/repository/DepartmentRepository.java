package com.student.api.studentapi.repository;

import com.student.api.studentapi.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    @Query("SELECT d FROM Department d LEFT JOIN FETCH d.studentList")
    public List<Department> findAllStudents();

    @Query("SELECT d FROM Department d LEFT JOIN FETCH d.studentList where d.id=:id")
    public Optional<Department> findStudentsByDeptId(@Param("id") Integer id);
}
