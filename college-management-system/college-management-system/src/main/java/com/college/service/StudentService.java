package com.college.service;

import com.college.exception.StudentNotFoundException;
import com.college.model.StudentDetailsModel;
import com.college.response.StudentResponse;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    ResponseEntity<String>  addstudent(StudentDetailsModel student);
    ResponseEntity<StudentResponse> getprofile(String studentid) throws StudentNotFoundException;
}
