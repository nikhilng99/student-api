package com.student.api.studentapi.mapper;

import com.student.api.studentapi.dto.DepartmentDTO;
import com.student.api.studentapi.entity.Department;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {StudentMapper.class})
public interface DepartmentMapper {

    public DepartmentDTO toDTO(Department department);

    @Mapping(target = "studentList", ignore = true)
    public Department toEntity(DepartmentDTO departmentDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "studentList", ignore = true)
    public void updateEntityFromDTO(DepartmentDTO departmentDTO, @MappingTarget Department department);
}
