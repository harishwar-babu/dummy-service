package com.college.dto;
import lombok.Data;
@Data
public class StudentDTO {
    private String studentid;
    private String studentname;
    private String studentemail;
    private DepartmentDTO department;
    private TeacherDTO teacher;
}