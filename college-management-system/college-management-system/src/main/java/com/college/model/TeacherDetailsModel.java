package com.college.model;
import com.college.customization.AgeValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDate;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDetailsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "teacherid")
    @GenericGenerator(name = "teacherid",strategy = "com.college.customization.TeacherIdGenerator")
    private String teacherid;

    @NotNull(message = "name is required")
    @Pattern(regexp = "[a-zA-Z]+",message = "enter name in a valid form")
    private String teachername;

    @NotNull(message = "email is required")
    @Email(regexp = "[a-zA-Z0-9._*]+@[a-zA-Z]+.[a-zA-Z]{2,3}",message = "Enter a email in a valid form",flags = Pattern.Flag.CASE_INSENSITIVE)
    private String teacheremail;

    @NotNull(message = "designation is required")
    @Pattern(regexp = "[a-zA-Z]+",message = "enter a valid designation name")
    public String designation;

    @NotNull(message = "date of birth is required")
    @AgeValidator(message = "Age should be atleast of 22 years!!")
    private LocalDate dateofbirth;

    @NotNull(message = "department code is required")
    private String deptcode;

    @OneToMany(targetEntity = StudentDetailsModel.class,cascade = CascadeType.ALL)
    @JoinColumn(name="teachercode",referencedColumnName = "teacherid")
    private List<StudentDetailsModel> students;
}
