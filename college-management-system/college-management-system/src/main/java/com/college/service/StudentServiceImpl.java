package com.college.service;
import com.college.dto.DepartmentDTO;
import com.college.dto.StudentDTO;
import com.college.dto.TeacherDTO;
import com.college.exception.StudentNotFoundException;
import com.college.model.StudentDetailsModel;
import com.college.repository.DepartmentRepository;
import com.college.repository.StudentDetailsRepository;
import com.college.repository.TeacherDetailsRepository;
import com.college.response.StudentResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class StudentServiceImpl implements StudentService{
    private static final String SUCCESS_MESSAGE ="success";
    @Autowired
    private StudentDetailsRepository studentDetailsRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TeacherDetailsRepository teacherDetailsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StudentResponse response;
    @Override
    public ResponseEntity<String> addstudent(StudentDetailsModel student) {
        studentDetailsRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body("Success:"+"Click on Profile Option for more Details");
    }
    @Override
    @Cacheable(cacheNames = "getprofile",key = "#studentid")
    public ResponseEntity<StudentResponse> getprofile(String studentid) throws StudentNotFoundException {
        StudentDetailsModel student = studentDetailsRepository.findByStudentid(studentid);
        if(student==null){
            throw new StudentNotFoundException("No student is available under this: "+studentid);
        }
        DepartmentDTO department = modelMapper.map(departmentRepository.findByCode(student.getDepartmentcode()), DepartmentDTO.class);
        TeacherDTO teacher = modelMapper.map(teacherDetailsRepository.findByTeacherid(student.getTeachercode()),TeacherDTO.class);
        StudentDTO studentdata = modelMapper.map(student,StudentDTO.class);
        studentdata.setDepartment(department);
        studentdata.setTeacher(teacher);
        response.setStatus(SUCCESS_MESSAGE);
        response.setData(studentdata);
        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }
}