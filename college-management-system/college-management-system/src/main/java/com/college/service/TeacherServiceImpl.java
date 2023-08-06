package com.college.service;
import com.college.dto.StudentDetailsDTO;
import com.college.dto.TeacherDetailsDTO;
import com.college.exception.NoStudentsAvailableException;
import com.college.exception.TeacherNotFoundException;
import com.college.model.DepartmentModel;
import com.college.model.TeacherDetailsModel;
import com.college.repository.DepartmentRepository;
import com.college.repository.StudentDetailsRepository;
import com.college.repository.TeacherDetailsRepository;
import com.college.response.TeacherResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService{
    private static final String SUCCESS_MESSAGE ="success";

    @Autowired
    private TeacherDetailsRepository teacherDetailsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StudentDetailsRepository studentDetailsRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TeacherResponse response;
    @Override
    public ResponseEntity<String> addteacher(TeacherDetailsModel teacher) {
        teacherDetailsRepository.save(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body("Added SuccessFully");
    }

    @Cacheable(cacheNames = "collegemanagement",key = "#teachercode")
    @Override
    public ResponseEntity<TeacherResponse> getprofile(String teachercode) throws TeacherNotFoundException {
        TeacherDetailsModel teacherDetailsModel = teacherDetailsRepository.findByTeacherid(teachercode);
        if(teacherDetailsModel==null){
            throw new TeacherNotFoundException("No Teacher available under this ID: "+teachercode);
        }
        TeacherDetailsDTO teacherDetails = modelMapper.map(teacherDetailsModel,TeacherDetailsDTO.class);
        DepartmentModel departmentModel = departmentRepository.findByCode(teacherDetailsModel.getDeptcode());
        teacherDetails.setDepartmentname(departmentModel.getDepartmentname());
        response.setStatus(SUCCESS_MESSAGE);
        response.setTeacherDetailsDTO(teacherDetails);
        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }

    @CachePut(cacheNames = "collegemanagement")
    @Override
    public ResponseEntity<List<StudentDetailsDTO>> getstudents(String teachercode) throws NoStudentsAvailableException {
        List<StudentDetailsDTO> students = studentDetailsRepository.findAllByTeachercode(teachercode)
                .stream().map(student->modelMapper.map(student, StudentDetailsDTO.class)).toList();
        if(students.isEmpty()){
            throw new NoStudentsAvailableException("No students Available");
        }
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }
}