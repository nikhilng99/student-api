package com.student.api.studentapi.controller;

import com.student.api.studentapi.dto.StudentDTO;
import com.student.api.studentapi.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Integer id){
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping
    public ResponseEntity<Page<StudentDTO>> getAllStudents(@PageableDefault(size = 5, sort = "name") Pageable pageable){
        return ResponseEntity.ok(studentService.getAllStudents(pageable));
    }

    @PostMapping
    public ResponseEntity<StudentDTO> addStudent(@Valid @RequestBody StudentDTO studentDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.addStudent(studentDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Integer id, @Valid @RequestBody StudentDTO studentDTO){
        return ResponseEntity.ok(studentService.updateStudent(id, studentDTO));
    }

    @GetMapping("/dept/{deptId}/min-age/{minAge}")
    public ResponseEntity<List<StudentDTO>> getStudentsByDeptAndMinAge(@PathVariable Integer deptId, @PathVariable Integer minAge) {
        return ResponseEntity.ok(studentService.getStudentsByDeptAndMinAge(deptId, minAge));
    }
}
