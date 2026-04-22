package com.student.api.studentapi.service;

import com.student.api.studentapi.dto.DepartmentDTO;
import com.student.api.studentapi.entity.Department;
import com.student.api.studentapi.exception.ResourceNotFoundException;
import com.student.api.studentapi.mapper.DepartmentMapper;
import com.student.api.studentapi.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    public DepartmentDTO getDepartmentById(Integer id) {
        Department department = departmentRepository.findStudentsByDeptId(id).orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
        return departmentMapper.toDTO(department);

    }

    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentDTO> departmentDTOList = departmentRepository.findAllStudents()
                .stream()
                .map(departmentMapper::toDTO)
                .toList();
        return departmentDTOList;
    }

    public DepartmentDTO addDepartment(DepartmentDTO departmentDTO) {
        Department department = departmentRepository.save(departmentMapper.toEntity(departmentDTO));
        return departmentMapper.toDTO(department);
    }

    @Transactional
    public void deleteDepartment(Integer id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
        departmentRepository.delete(department);
    }

    @Transactional
    public DepartmentDTO updateDepartment(Integer id, DepartmentDTO departmentDTO) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
        departmentMapper.updateEntityFromDTO(departmentDTO,department);
        Department savedDepartment = departmentRepository.save(department);

        return departmentMapper.toDTO(savedDepartment);
    }
}
