package com.college.service;
import com.college.dto.DepartmentDTO;
import com.college.dto.DepartmentDetailsDTO;
import com.college.dto.StudentDetailsDTO;
import com.college.dto.TeacherDTO;
import com.college.exception.DeletionErrorException;
import com.college.exception.DepartmentDoesNotExistsException;
import com.college.model.DepartmentModel;
import com.college.repository.DepartmentRepository;
import com.college.repository.StudentDetailsRepository;
import com.college.repository.TeacherDetailsRepository;
import com.college.response.DepartmentDetailsResponse;
import com.college.response.DepartmentRecordsResponse;
import com.college.response.DepartmentResponse;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class DepartmentServiceImpl implements DepartmentService{
    private static final String SUCCESS_MESSAGE ="success";
    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private StudentDetailsRepository studentDetailsRepository;

    @Autowired
    private TeacherDetailsRepository teacherDetailsRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DepartmentResponse deptresponse;
    @Autowired
    private DepartmentDetailsResponse response;
    @Autowired
    private DepartmentRecordsResponse departmentRecordsResponse;
    @Override
    public ResponseEntity<DepartmentResponse> addDepartment(DepartmentModel department) {
        departmentRepository.save(department);
        DepartmentDTO departmentDTO = modelMapper.map(department, DepartmentDTO.class);
        deptresponse.setStatus(SUCCESS_MESSAGE);
        deptresponse.setData(departmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(deptresponse);
    }

    @Override
    @CachePut(cacheNames = {"collegemanagement","getprofile","getDepartmentDetails","getallDepartmentDetails"},key = "#deptcode")
    public ResponseEntity<DepartmentResponse> updateDepartment(String deptcode, DepartmentModel department) throws DepartmentDoesNotExistsException {
        DepartmentModel olddept = departmentRepository.findByCode(deptcode);
        if(olddept==null){
            logger.error("error when updating");
            throw new DepartmentDoesNotExistsException("No department is available under this Id : "+deptcode);
        }
        olddept.setDepartmentname(department.getDepartmentname());
        departmentRepository.save(olddept);
        deptresponse.setStatus(SUCCESS_MESSAGE);
        deptresponse.setData(modelMapper.map(olddept, DepartmentDTO.class));
        logger.info("updated department");
        return ResponseEntity.status(HttpStatus.ACCEPTED.value()).body(deptresponse);
    }
    @Override
    @Cacheable(cacheNames = "getDepartmentDetails")
    public ResponseEntity<DepartmentDetailsResponse> getDepartmentDetails(String deptcode) throws DepartmentDoesNotExistsException {
        DepartmentModel departmentModel = departmentRepository.findByCode(deptcode);
        if(departmentModel==null){
            logger.error("error as department is null");
            throw new DepartmentDoesNotExistsException("Department Does not exists: "+deptcode);
        }
        DepartmentDetailsDTO departmentDetailsDTO = modelMapper.map(departmentModel,DepartmentDetailsDTO.class);
        List<StudentDetailsDTO> students = departmentModel.getStudents().stream()
                .map(student->modelMapper.map(student,StudentDetailsDTO.class)).toList();
        List<TeacherDTO> teachers = departmentModel.getTeachers().stream()
                .map(teacher-> modelMapper.map(teacher,TeacherDTO.class)).toList();
        departmentDetailsDTO.setTeachers(teachers);
        departmentDetailsDTO.setStudents(students);
        response.setStatus(SUCCESS_MESSAGE);
        response.setDepartmentDetailsDTO(departmentDetailsDTO);
        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }
    @Override
    @CachePut(cacheNames = "getallDepartmentDetails")
    public ResponseEntity<DepartmentRecordsResponse> getallDepartmentDetails() {
        List<DepartmentDetailsDTO> allresponse=new ArrayList<>();
        departmentRepository.findAll().forEach(department->{
            DepartmentDetailsDTO departmentDetailsDTO = modelMapper.map(department,DepartmentDetailsDTO.class);
            List<StudentDetailsDTO> students = department.getStudents().stream()
                    .map(student->modelMapper.map(student,StudentDetailsDTO.class)).toList();
            List<TeacherDTO> teachers = department.getTeachers().stream()
                    .map(teacher-> modelMapper.map(teacher,TeacherDTO.class)).toList();
            departmentDetailsDTO.setTeachers(teachers);
            departmentDetailsDTO.setStudents(students);
            allresponse.add(departmentDetailsDTO);
        });
        departmentRecordsResponse.setStatus(SUCCESS_MESSAGE);
        departmentRecordsResponse.setData(allresponse);
        return ResponseEntity.status(HttpStatus.OK).body(departmentRecordsResponse);
    }

    @CacheEvict(cacheNames = "collegemanagement",key = "#deptcode")
    @Override
    public ResponseEntity<String> deleteDepartment(String deptcode) throws DepartmentDoesNotExistsException, DeletionErrorException {
        DepartmentModel departmentModel = departmentRepository.findByCode(deptcode);
        if(departmentModel==null){
            throw new DepartmentDoesNotExistsException("Department does not exists So Delete is not possible");
        }
        if(!departmentModel.getStudents().isEmpty() || !departmentModel.getTeachers().isEmpty()){
            throw new DeletionErrorException("can't able to delete as there are students and teachers");
        }
        departmentRepository.deleteById(deptcode);
        return ResponseEntity.status(HttpStatus.OK).body("deleted");
    }
}