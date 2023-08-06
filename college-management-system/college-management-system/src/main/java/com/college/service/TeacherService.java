package com.college.service;
import com.college.dto.StudentDetailsDTO;
import com.college.exception.NoStudentsAvailableException;
import com.college.exception.TeacherNotFoundException;
import com.college.model.TeacherDetailsModel;
import com.college.response.TeacherResponse;
import org.springframework.http.ResponseEntity;
import java.util.List;
public interface TeacherService {
    ResponseEntity<String> addteacher(TeacherDetailsModel teacher);

    ResponseEntity<TeacherResponse> getprofile(String teachercode) throws TeacherNotFoundException;

    ResponseEntity<List<StudentDetailsDTO>> getstudents(String teachercode) throws NoStudentsAvailableException;
}
