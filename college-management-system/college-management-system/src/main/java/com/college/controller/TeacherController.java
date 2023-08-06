package com.college.controller;

import com.college.dto.StudentDetailsDTO;
import com.college.exception.NoStudentsAvailableException;
import com.college.exception.TeacherNotFoundException;
import com.college.model.TeacherDetailsModel;
import com.college.response.TeacherResponse;
import com.college.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @PostMapping("/addteacher")
    public ResponseEntity<String> addteacher(@Valid  @RequestBody TeacherDetailsModel teacher){
        return teacherService.addteacher(teacher);
    }
    @GetMapping("/profile/{id}")
    public ResponseEntity<TeacherResponse> profile(@PathVariable String id) throws TeacherNotFoundException {
        return teacherService.getprofile(id);
    }
    @GetMapping("/getstudents/{id}")
    public ResponseEntity<List<StudentDetailsDTO>> getstudents(@PathVariable String id) throws NoStudentsAvailableException {
        return teacherService.getstudents(id);
    }
}
