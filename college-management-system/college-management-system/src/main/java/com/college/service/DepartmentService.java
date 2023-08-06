package com.college.service;
import com.college.exception.DeletionErrorException;
import com.college.exception.DepartmentDoesNotExistsException;
import com.college.model.DepartmentModel;
import com.college.response.DepartmentDetailsResponse;
import com.college.response.DepartmentRecordsResponse;
import com.college.response.DepartmentResponse;
import org.springframework.http.ResponseEntity;
public interface DepartmentService {
    ResponseEntity<DepartmentResponse> addDepartment(DepartmentModel department);
    ResponseEntity<DepartmentResponse> updateDepartment(String deptcode,DepartmentModel department) throws DepartmentDoesNotExistsException;
    ResponseEntity<DepartmentDetailsResponse> getDepartmentDetails(String deptcode) throws DepartmentDoesNotExistsException;
    ResponseEntity<DepartmentRecordsResponse> getallDepartmentDetails();
    ResponseEntity<String> deleteDepartment(String deptcode) throws DepartmentDoesNotExistsException, DeletionErrorException;
}
