package com.college.response;
import com.college.dto.DepartmentDetailsDTO;
import com.college.exception.Error;
import lombok.Data;
@Data
public class DepartmentDetailsResponse {
    private String status;
    private DepartmentDetailsDTO departmentDetailsDTO;
    private Error error;
}
