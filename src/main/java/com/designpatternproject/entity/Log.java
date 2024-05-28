package com.designpatternproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 64)
    @NotNull
    @Column(name = "level", nullable = false, length = 64)
    private String level;

    @Size(max = 64)
    @NotNull
    @Column(name = "target_table", nullable = false, length = 64)
    private String targetTable;

    @Size(max = 512)
    @Column(name = "text", length = 512)
    private String text;

    @Column(name = "user")
    private Integer user;

    @NotNull
    @Column(name = "insert_date", nullable = false)
    private Instant insertDate;

}