package com.college.controller;

import com.college.exception.StudentNotFoundException;
import com.college.model.StudentDetailsModel;
import com.college.response.StudentResponse;
import com.college.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/addstudent")
    public ResponseEntity<String> addstudent(@Valid  @RequestBody StudentDetailsModel student){
        return studentService.addstudent(student);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<StudentResponse> profile(@PathVariable String id) throws StudentNotFoundException {
        return studentService.getprofile(id);
    }
}
