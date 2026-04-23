package com.student.api.studentapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.api.studentapi.dto.StudentDTO;
import com.student.api.studentapi.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class StudentControllerTest {

    private MockMvc mockMvc;

    private StudentService studentService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        studentService = Mockito.mock(StudentService.class);
        StudentController controller = new StudentController(studentService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldCreateStudent() throws Exception {
        StudentDTO dto = new StudentDTO(null, "Alice", "alice@test.com", 20, "A", 1);

        Mockito.when(studentService.addStudent(Mockito.any()))
                .thenReturn(dto);

        mockMvc.perform(post("/api/v1/student")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }
}