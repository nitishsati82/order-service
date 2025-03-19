package com.nagp.order_service.model.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serial;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@MappedSuperclass
@Data
public class AuditableEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @CreatedDate
    protected LocalDateTime createdAt;

    @LastModifiedDate
    protected LocalDateTime modifiedAt;

    private LocalDateTime getCurrentTimeStamp() {
        return LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = this.modifiedAt = getCurrentTimeStamp();
    }

    @PreUpdate
    public void preUpdate() {
        this.modifiedAt = getCurrentTimeStamp();
    }
}
