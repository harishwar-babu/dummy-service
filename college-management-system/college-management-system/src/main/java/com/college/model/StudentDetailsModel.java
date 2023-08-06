package com.college.model;

import com.college.customization.AgeValidator;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDetailsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "studentid")
    @GenericGenerator(name = "studentid",strategy = "com.college.customization.StudentIdGenerator")
    private String studentid;
    @NotNull(message = "name is required")
    @Pattern(regexp = "[a-zA-Z]+",message = "Enter name in a valid form")
    private String studentname;
    @NotNull(message = "email is required")
    @Email(regexp = "[a-zA-Z0-9._*]+@[a-zA-Z]+.[a-zA-Z]{2,3}",message = "Enter a email in a valid form",flags = Pattern.Flag.CASE_INSENSITIVE)
    private String studentemail;

    @NotNull(message = "date of birth is required")
    @AgeValidator(message = "Age should be atleast of 18 years!!")
    private LocalDate dateofbirth;

    @NotNull(message = "department code is required")
    private String departmentcode;

    @NotNull(message = "teacher code is required")
    private String teachercode;
}