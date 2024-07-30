package com.kalekt.school.service.impl;

import com.kalekt.school.domain.Teacher;
import com.kalekt.school.dto.request.TeacherRequest;
import com.kalekt.school.exception.RecordNotFoundException;
import com.kalekt.school.repository.TeacherRepository;
import com.kalekt.school.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getTeacherByUuid(final String uuid) {
        return teacherRepository.findByUuid(uuid)
                .orElseThrow(()-> new RecordNotFoundException("Teacher Not Found with uuid: "+uuid));
    }

    @Override
    public Teacher createTeacher(final TeacherRequest teacherDetails) {
        val teacher = Teacher.builder()
                .firstName(teacherDetails.getFirstName())
                .lastName(teacherDetails.getLastName())
                .age(teacherDetails.getAge())
                .sex(teacherDetails.getSex())
                .build();
        return teacherRepository.save(teacher);
    }

    @Override
    public Optional<Teacher> updateTeacher(final String uuid, final TeacherRequest teacherDetails) {
        return teacherRepository.findByUuid(uuid).map(student -> {
            student.setFirstName(teacherDetails.getFirstName());
            student.setLastName(teacherDetails.getLastName());
            student.setAge(teacherDetails.getAge());
            student.setSex(teacherDetails.getSex());
            return teacherRepository.save(student);
        });
    }

    @Override
    public void deleteTeacher(final String uuid) {
        val teacher = getTeacherByUuid(uuid);
        teacherRepository.delete(teacher);
    }
}
