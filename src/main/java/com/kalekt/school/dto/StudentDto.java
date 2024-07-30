package com.kalekt.school.dto;

import com.kalekt.school.domain.Student;
import com.kalekt.school.domain.Teacher;
import com.kalekt.school.domain.util.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private String uuid;

    private String firstName;

    private String lastName;

    private int age;

    private Gender sex;

    public static StudentDto of(final Student student) {
        if (Objects.isNull(student)) {
            return null;
        }
        return new StudentDto(student.getUuid(), student.getFirstName(), student.getLastName(), student.getAge(), student.getSex());
    }

    public static List<StudentDto> of(final List<Student> students) {
        if (Objects.isNull(students)) {
            return Collections.emptyList();
        }
        return students.stream()
                .map(StudentDto::of)
                .collect(Collectors.toList());
    }
}
