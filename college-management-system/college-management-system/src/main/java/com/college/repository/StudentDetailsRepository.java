package com.college.repository;
import com.college.model.StudentDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDetailsRepository extends JpaRepository<StudentDetailsModel,String> {
    StudentDetailsModel findByStudentid(String studentid);
    List<StudentDetailsModel> findAllByTeachercode(String teachercode);
}
