package com.kalekt.school.service;

import com.kalekt.school.domain.Teacher;
import com.kalekt.school.dto.request.TeacherRequest;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    List<Teacher> getAllTeachers();
    Teacher getTeacherByUuid(String uuid);
    Teacher createTeacher(TeacherRequest teacherDetails);
    Optional<Teacher> updateTeacher(String uuid, TeacherRequest teacherDetails);
    void deleteTeacher(String uuid);
}
