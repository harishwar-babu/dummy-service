package com.college.repository;

import com.college.model.TeacherDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherDetailsRepository extends JpaRepository<TeacherDetailsModel,String> {
    TeacherDetailsModel findByTeacherid(String teacherid);
}
