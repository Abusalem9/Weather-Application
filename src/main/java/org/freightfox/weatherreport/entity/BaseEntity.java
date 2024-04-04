package org.freightfox.weatherreport.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
public class BaseEntity {

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean deleted = false;

    @Column(columnDefinition = "boolean default true", nullable = false)
    private boolean active = true;

    @Column(updatable = false, nullable = false, columnDefinition = "varchar(255) default 'NA'")
    private String createdBy;

    @Column(updatable = false, nullable = false, columnDefinition = "varchar(255) default 'NA'")
    private String createdByName;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "UPDATED_BY_NAME")
    private String updatedByName;

}