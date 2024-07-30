package com.kalekt.school.dto;

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
public class TeacherDto {
    private String uuid;

    private String firstName;

    private String lastName;

    private int age;

    private Gender sex;

    public static TeacherDto of(final Teacher teacher) {
        if (Objects.isNull(teacher)) {
            return null;
        }
        return new TeacherDto(teacher.getUuid(), teacher.getFirstName(), teacher.getLastName(), teacher.getAge(), teacher.getSex());
    }

    public static List<TeacherDto> of(final List<Teacher> teachers) {
        if (Objects.isNull(teachers)) {
            return Collections.emptyList();
        }
        return teachers.stream()
                .map(TeacherDto::of)
                .collect(Collectors.toList());
    }
}
