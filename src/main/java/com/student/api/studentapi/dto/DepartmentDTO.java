package com.student.api.studentapi.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class DepartmentDTO {
    private Integer id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    private List<StudentDTO> studentList;

    public DepartmentDTO(Integer id, String name, List<StudentDTO> studentList) {
        this.id = id;
        this.name = name;
        this.studentList = studentList;
    }

    public DepartmentDTO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StudentDTO> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentDTO> studentDTOList) {
        this.studentList = studentDTOList;
    }
}
