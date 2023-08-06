package com.college.configurations;
import com.college.response.*;
import org.modelmapper.ModelMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import com.college.exception.Error;
@Configuration
public class ConfigurationClass {

    @Bean
    public ModelMapper modelMapper(){

        return new ModelMapper();
    }
    @Bean(name = "cachemanager")
    public CacheManager cacheManager(){
        return new ConcurrentMapCacheManager("collegemanagement","getprofile","getDepartmentDetails","getallDepartmentDetails");
    }
    @Bean
    @Scope(scopeName = "prototype")
    public DepartmentDetailsResponse departmentDetailsResponse(){
        return new DepartmentDetailsResponse();
    }
    @Bean
    @Scope(scopeName = "prototype")
    public DepartmentRecordsResponse departmentRecordsResponse(){
        return new DepartmentRecordsResponse();
    }
    @Bean
    @Scope(scopeName = "prototype")
    public DepartmentResponse departmentResponse(){
        return new DepartmentResponse();
    }
    @Bean
    @Scope(scopeName = "prototype")
    public StudentResponse studentResponse(){
        return new StudentResponse();
    }
    @Bean
    @Scope(scopeName = "prototype")
    public TeacherResponse teacherResponse(){
        return new TeacherResponse();
    }
    @Bean
    @Scope(scopeName = "prototype")
    public Error error(){
        return new Error();
    }
}
