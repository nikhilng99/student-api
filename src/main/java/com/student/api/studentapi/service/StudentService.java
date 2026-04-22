package com.student.api.studentapi.service;

import com.student.api.studentapi.dto.StudentDTO;
import com.student.api.studentapi.entity.Department;
import com.student.api.studentapi.entity.Student;
import com.student.api.studentapi.exception.ResourceNotFoundException;
import com.student.api.studentapi.mapper.StudentMapper;
import com.student.api.studentapi.repository.DepartmentRepository;
import com.student.api.studentapi.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final DepartmentRepository departmentRepository;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper, DepartmentRepository departmentRepository) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.departmentRepository = departmentRepository;
    }

    public StudentDTO getStudentById(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with id: "+id));
        return studentMapper.toDTO(student);
    }

    public Page<StudentDTO> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable).map(studentMapper::toDTO);
    }

    public StudentDTO addStudent(StudentDTO studentDTO) {
        Student student = studentMapper.toEntity(studentDTO);
        if(studentDTO.getDeptId() != null){
            Department department = departmentRepository.findById(studentDTO.getDeptId()).orElseThrow(()-> new ResourceNotFoundException("Dept not found with id:" + studentDTO.getDeptId()));
            student.setDepartment(department);
        }
        Student addedStudent = studentRepository.save(student);
        return studentMapper.toDTO(addedStudent);
    }

    @Transactional
    public void deleteStudent(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with id: "+id));
        studentRepository.delete(student);
    }

    @Transactional
    public StudentDTO updateStudent(Integer id, StudentDTO studentDTO) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with id: "+id));
        studentMapper.updateEntityFromDTO(studentDTO,student);

        Student savedStudent = studentRepository.save(student);
        return studentMapper.toDTO(savedStudent);
    }

    public List<StudentDTO> getStudentsByDeptAndMinAge(Integer deptId, Integer minAge) {
        return studentRepository.findByDeptIdAndMinAge(deptId, minAge)
                .stream()
                .map(studentMapper::toDTO)
                .toList();
    }
}
