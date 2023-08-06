package com.college.response;
import com.college.dto.StudentDTO;
import com.college.exception.Error;
import lombok.Data;
@Data
public class StudentResponse {
    private String status;
    private StudentDTO data;
    private Error error;
}
