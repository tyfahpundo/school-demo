package com.kalekt.school.service;

import com.kalekt.school.domain.Student;
import com.kalekt.school.dto.request.StudentRequest;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentByUuid(String uuid);
    Student createStudent(StudentRequest studentDetails);
    Optional<Student> updateStudent(String uuid, StudentRequest studentDetails);
    void deleteStudent(String uuid);
}
