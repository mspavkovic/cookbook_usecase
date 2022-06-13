package com.valcon.cookbook.domain.user;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final String ID_SEQUENCE = "user_id_sequence";
    private static final String ID_SEQUENCE_GENERATOR = "user_id_sequence_generator";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQUENCE_GENERATOR)
    @SequenceGenerator(name = ID_SEQUENCE_GENERATOR, sequenceName = ID_SEQUENCE, allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private LocalDateTime creationTime = LocalDateTime.now();

}
