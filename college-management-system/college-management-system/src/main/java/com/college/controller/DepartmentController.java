package com.college.controller;
import com.college.exception.DeletionErrorException;
import com.college.exception.DepartmentDoesNotExistsException;
import com.college.model.DepartmentModel;
import com.college.response.DepartmentDetailsResponse;
import com.college.response.DepartmentRecordsResponse;
import com.college.response.DepartmentResponse;
import com.college.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/addDepartment")
    public ResponseEntity<DepartmentResponse> addDepartment(@Valid  @RequestBody DepartmentModel department){
        return departmentService.addDepartment(department);
    }
    @PutMapping("/updateDepartment/{id}")
    public ResponseEntity<DepartmentResponse> updateDepartment(@PathVariable String id,@RequestBody DepartmentModel department) throws DepartmentDoesNotExistsException {
        return departmentService.updateDepartment(id,department);
    }
    @GetMapping("/getDepartmentDetails/{id}")
    public ResponseEntity<DepartmentDetailsResponse> getDepartmentDetails(@PathVariable String id) throws DepartmentDoesNotExistsException {
        return departmentService.getDepartmentDetails(id);
    }

    @GetMapping("/getAllDepartmentRecords")
    public ResponseEntity<DepartmentRecordsResponse> getAllDepartmentRecords(){
        return departmentService.getallDepartmentDetails();
    }
    @DeleteMapping("/deleteDepartment/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable String id) throws DepartmentDoesNotExistsException, DeletionErrorException {
        return departmentService.deleteDepartment(id);
    }
}
