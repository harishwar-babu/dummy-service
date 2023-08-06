package com.college.customization;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Random;

public class TeacherIdGenerator implements IdentifierGenerator{
    private static final String TEACHER_ID="TEACH";
    private static final Random ID_GENERATOR=new Random();
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        return TEACHER_ID+ ID_GENERATOR.nextInt(10);
    }
}
