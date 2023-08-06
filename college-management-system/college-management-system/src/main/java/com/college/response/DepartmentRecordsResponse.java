package com.college.response;
import com.college.dto.DepartmentDetailsDTO;
import com.college.exception.Error;
import lombok.Data;
import java.util.List;
@Data
public class DepartmentRecordsResponse {
    private String status;
    private List<DepartmentDetailsDTO> data;
    private Error error;
}
