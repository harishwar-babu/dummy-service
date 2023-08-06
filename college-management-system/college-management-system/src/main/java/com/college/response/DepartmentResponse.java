package com.college.response;
import com.college.dto.DepartmentDTO;
import com.college.exception.Error;
import lombok.Data;
@Data
public class DepartmentResponse {
    private String status;
    private DepartmentDTO data;
    private Error error;
}
