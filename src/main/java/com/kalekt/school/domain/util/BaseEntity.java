package com.kalekt.school.domain.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.val;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class BaseEntity implements Serializable {
    @NaturalId
    @Column(nullable = false, updatable = false, unique = true)
    private String uuid;

    @Version
    @JsonIgnore
    private Long version;

    @PrePersist
    void setupUuid() {
        val instant = Instant.now();
        final long seconds = instant.getEpochSecond();
        final long nano = instant.getNano();
        this.uuid = String.valueOf(seconds * 1_000_000_000 + nano);
    }

}

