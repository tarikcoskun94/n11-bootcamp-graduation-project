package com.n11.graduationproject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            unique = true,
            nullable = false,
            insertable = false,
            updatable = false)
    private Long id;

    @Column(name = "creation_time",
            nullable = false,
            updatable = false)
    private LocalDateTime creationTime;

    @Column(name = "update_time",
            nullable = false)
    private LocalDateTime updateTime;
}