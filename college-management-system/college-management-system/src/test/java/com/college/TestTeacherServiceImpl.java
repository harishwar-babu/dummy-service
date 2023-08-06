package com.college;

import com.college.exception.TeacherNotFoundException;
import com.college.model.TeacherDetailsModel;
import com.college.repository.TeacherDetailsRepository;
import com.college.service.TeacherServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestTeacherServiceImpl {
    @Mock
    private TeacherDetailsRepository teacherDetailsRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private TeacherServiceImpl teacherService;

    @Test
    public void AddTeacher(){
        TeacherDetailsModel model=new TeacherDetailsModel();
        model.setTeacherid("1");
        model.setTeachername("Arul");
        model.setDateofbirth(LocalDate.parse("2010-03-20"));
        model.setDeptcode("DEPT6");
        model.setDesignation("Arch");
        model.setTeacheremail("email123@gmail.com");
        when(teacherDetailsRepository.save(model)).thenReturn(model);
        assertEquals(201,teacherService.addteacher(model).getStatusCode().value());
        verify(teacherDetailsRepository).save(model);
    }
    @Test
    public void getProfile(){
        when(teacherDetailsRepository.findByTeacherid(anyString())).thenReturn(null);
        assertThrows(TeacherNotFoundException.class,()->teacherService.getprofile(anyString()));
        verify(teacherDetailsRepository).findByTeacherid(anyString());
    }
}
