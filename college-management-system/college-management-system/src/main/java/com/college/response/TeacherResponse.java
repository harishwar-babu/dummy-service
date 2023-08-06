package com.college.response;
import com.college.dto.TeacherDetailsDTO;
import com.college.exception.Error;
import lombok.Data;
@Data
public class TeacherResponse {
    private String status;
    private TeacherDetailsDTO teacherDetailsDTO;
    private Error error;
}
