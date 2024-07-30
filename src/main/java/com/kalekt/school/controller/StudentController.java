package com.kalekt.school.controller;

import com.kalekt.school.dto.StudentDto;
import com.kalekt.school.dto.request.StudentRequest;
import com.kalekt.school.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
@AllArgsConstructor
@Tag(name = "Students Controller", description = "Rest Controller for students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("get/{uuid}")
    @Operation(summary = "Get Student", description = "Returns a student by uuid")
    public StudentDto getStudentByUuid(@PathVariable final String uuid) {
        return StudentDto.of(studentService.getStudentByUuid(uuid));
    }

    @GetMapping
    @Operation(summary = "Get Students", description = "Returns a list of students")
    public List<StudentDto> getAllStudents() {
        return studentService.getAllStudents().stream()
                .map(StudentDto::of)
                .collect(Collectors.toList());
    }

    @PostMapping
    @Operation(summary = "Create Student", description = "Creates a new student")
    public StudentDto createStudent(@RequestBody final StudentRequest studentRequest) {
        return StudentDto.of(studentService.createStudent(studentRequest));
    }

    @PutMapping("update/{uuid}")
    @Operation(summary = "Update Student", description = "Updates an existing student by uuid")
    public StudentDto updateStudent(@PathVariable final String uuid, @RequestBody final StudentRequest studentRequest) {
        return StudentDto.of(studentService.updateStudent(uuid, studentRequest).get());
    }

    @DeleteMapping("delete/{uuid}")
    @Operation(summary = "Delete Student", description = "Deletes a student by uuid")
    public void deleteStudent(@PathVariable final String uuid) {
        studentService.deleteStudent(uuid);
    }
}
