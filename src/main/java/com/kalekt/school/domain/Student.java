package com.kalekt.school.domain;

import com.kalekt.school.domain.util.DefaultIdentifierAuditedEntity;
import com.kalekt.school.domain.util.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Getter
@Setter
@Table(name = "students",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "uuid")
        }
)
public class Student extends DefaultIdentifierAuditedEntity {
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private int age;
    @Column
    private Gender sex;
}
