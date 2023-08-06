package com.college.dto;
import lombok.Data;
import java.util.List;
@Data
public class DepartmentDetailsDTO {
    private String code;
    private String departmentname;
    private List<StudentDetailsDTO> students;
    private List<TeacherDTO> teachers;
}
