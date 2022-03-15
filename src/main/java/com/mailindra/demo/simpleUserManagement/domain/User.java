package com.mailindra.demo.simpleUserManagement.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$")
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    @Pattern(regexp = "^[0-9]+$")
    @Size(min = 5, max = 5)
    private String ssn;

    @NotNull
    private Date dob;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_]+$")
    private String updatedBy;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_]+$")
    private String createdBy;

    private Boolean isDeleted=false;


}
