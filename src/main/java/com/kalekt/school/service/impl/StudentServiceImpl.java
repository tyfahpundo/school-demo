package com.kalekt.school.service.impl;

import com.kalekt.school.domain.Student;
import com.kalekt.school.dto.request.StudentRequest;
import com.kalekt.school.exception.RecordNotFoundException;
import com.kalekt.school.repository.StudentRepository;
import com.kalekt.school.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentByUuid(final String uuid) {
        return studentRepository.findByUuid(uuid)
                .orElseThrow(()-> new RecordNotFoundException("Student not found with uuid: "+uuid));
    }

    @Override
    public Student createStudent(final StudentRequest studentDetails) {
        val student = Student.builder()
                .firstName(studentDetails.getFirstName())
                .lastName(studentDetails.getLastName())
                .age(studentDetails.getAge())
                .sex(studentDetails.getSex())
                .build();
        return studentRepository.save(student);
    }

    @Override
    public Optional<Student> updateStudent(final String uuid, final StudentRequest studentDetails) {
        return studentRepository.findByUuid(uuid).map(student -> {
            student.setFirstName(studentDetails.getFirstName());
            student.setLastName(studentDetails.getLastName());
            student.setAge(studentDetails.getAge());
            student.setSex(studentDetails.getSex());
            return studentRepository.save(student);
        });
    }

    @Override
    public void deleteStudent(final String uuid) {
        val student = getStudentByUuid(uuid);
        studentRepository.delete(student);
    }
}
