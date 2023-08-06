package com.college.customization;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import java.io.Serializable;
import java.util.Random;
public class StudentIdGenerator implements IdentifierGenerator{
    private static final String STUDENT_ID="STUD";
    private static final Random ID_GENERATOR=new Random();
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        return STUDENT_ID+ ID_GENERATOR.nextInt(10);
    }
}
