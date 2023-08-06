package com.college.exception;
import com.college.response.DepartmentResponse;
import com.college.response.StudentResponse;
import com.college.response.TeacherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;
@ControllerAdvice
public class ExceptionalHandler {
    private static final String FAIL_MESSAGE="failed";
    @Autowired
    private Error error;

    @Autowired
    private StudentResponse studentResponse;
    @Autowired
    private TeacherResponse teacherResponse;

    @Autowired
    private DepartmentResponse departmentResponse;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        Map<String,String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(err->{
            errors.put(err.getField(),err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(errors);
    }
    @ExceptionHandler(NoStudentsAvailableException.class)
    public ResponseEntity<StudentResponse> handleNoStudentsAvailableException(NoStudentsAvailableException exception){
        error.setMessage(exception.getMessage());
        studentResponse.setStatus(FAIL_MESSAGE);
        studentResponse.setError(error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(studentResponse);
    }
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<StudentResponse> handleStudentNotFoundException(StudentNotFoundException exception){
        error.setMessage(exception.getMessage());
        studentResponse.setStatus(FAIL_MESSAGE);
        studentResponse.setError(error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(studentResponse);
    }
    @ExceptionHandler(TeacherNotFoundException.class)
    public ResponseEntity<TeacherResponse> handleTeacherNotFoundException(TeacherNotFoundException exception){
        error.setMessage(exception.getMessage());
        teacherResponse.setStatus(FAIL_MESSAGE);
        teacherResponse.setError(error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(teacherResponse);
    }
    @ExceptionHandler(DepartmentDoesNotExistsException.class)
    public ResponseEntity<DepartmentResponse> handleDepartmentDoesNotExistsException(DepartmentDoesNotExistsException exception){
        error.setMessage(exception.getMessage());
        departmentResponse.setStatus(FAIL_MESSAGE);
        departmentResponse.setError(error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(departmentResponse);
    }
    @ExceptionHandler(DeletionErrorException.class)
    public ResponseEntity<String> handleDeletionErrorException(DeletionErrorException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(exception.getMessage());
    }

}
