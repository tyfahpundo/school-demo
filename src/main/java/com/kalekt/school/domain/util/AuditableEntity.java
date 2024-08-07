package com.kalekt.school.domain.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder(toBuilder = true)
@Getter
@NoArgsConstructor
public class AuditableEntity extends BaseEntity {

    @CreatedBy
    @Column
    @JsonIgnore
    private String createdBy;

    @LastModifiedBy
    @Column
    @JsonIgnore
    private String lastModifiedBy;

    @UpdateTimestamp
    @Column
    @JsonIgnore
    private LocalDateTime lastModifiedDate;

    @CreationTimestamp
    @Column
    @JsonIgnore
    private LocalDateTime createdDate;

}
