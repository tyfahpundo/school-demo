package com.kalekt.school.controller;

import com.kalekt.school.dto.StudentDto;
import com.kalekt.school.dto.TeacherDto;
import com.kalekt.school.dto.request.StudentRequest;
import com.kalekt.school.dto.request.TeacherRequest;
import com.kalekt.school.service.StudentService;
import com.kalekt.school.service.TeacherService;
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
@RequestMapping("/api/teachers")
@AllArgsConstructor
@Tag(name = "Teachers Controller", description = "Rest Controller for teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("get/{uuid}")
    @Operation(summary = "Get Teacher", description = "Returns a teacher by uuid")
    public TeacherDto getTeacherByUuid(@PathVariable final String uuid) {
        return TeacherDto.of(teacherService.getTeacherByUuid(uuid));
    }

    @GetMapping
    @Operation(summary = "Get Teachers", description = "Returns a list of teachers")
    public List<TeacherDto> getAllTeachers() {
        return teacherService.getAllTeachers().stream()
                .map(TeacherDto::of)
                .collect(Collectors.toList());
    }

    @PostMapping
    @Operation(summary = "Create Teacher", description = "Creates a new teacher")
    public TeacherDto createTeacher(@RequestBody final TeacherRequest teacherRequest) {
        return TeacherDto.of(teacherService.createTeacher(teacherRequest));
    }

    @PutMapping("update/{uuid}")
    @Operation(summary = "Update Teacher", description = "Updates an existing teacher by uuid")
    public TeacherDto updateStudent(@PathVariable final String uuid, @RequestBody final TeacherRequest teacherRequest) {
        return TeacherDto.of(teacherService.updateTeacher(uuid, teacherRequest).get());
    }

    @DeleteMapping("delete/{uuid}")
    @Operation(summary = "Delete Teacher", description = "Deletes a teacher by uuid")
    public void deleteStudent(@PathVariable final String uuid) {
        teacherService.deleteTeacher(uuid);
    }
}
