package com.college.repository;
import com.college.model.DepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DepartmentRepository extends JpaRepository<DepartmentModel,String> {
    DepartmentModel findByCode(String deptcode);
}
