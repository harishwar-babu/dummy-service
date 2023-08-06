package com.college.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "code")
    @GenericGenerator(name="code",strategy = "com.college.customization.DepartmentCodeGenerator")
    private String code;

    @NotNull(message = "department name is required")
    @Pattern(regexp = "[a-zA-Z]+",message = "department name should be in a valid form")
    private String departmentname;

    @OneToMany(targetEntity = StudentDetailsModel.class,cascade = CascadeType.ALL)
    @JoinColumn(name="departmentcode",referencedColumnName = "code")
    private List<StudentDetailsModel> students;

    @OneToMany(targetEntity = TeacherDetailsModel.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "deptcode",referencedColumnName = "code")
    private List<TeacherDetailsModel> teachers;
}