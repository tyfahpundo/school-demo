package com.kalekt.school.dto.request;

import com.kalekt.school.domain.util.Gender;
import lombok.Data;

@Data
public class TeacherRequest {
    private String firstName;

    private String lastName;

    private int age;

    private Gender sex;
}
