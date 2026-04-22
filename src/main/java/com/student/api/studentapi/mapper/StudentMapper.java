package com.student.api.studentapi.mapper;

import com.student.api.studentapi.dto.StudentDTO;
import com.student.api.studentapi.entity.Student;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(source = "department.id", target = "deptId")
    public StudentDTO toDTO(Student student);

    @Mapping(target = "department" ,ignore = true)
    public Student toEntity(StudentDTO studentDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "department" ,ignore = true)
    public void updateEntityFromDTO(StudentDTO studentDTO, @MappingTarget Student student);

}
