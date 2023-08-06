package com.college.customization;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Random;

public class DepartmentCodeGenerator implements IdentifierGenerator{
    private static final String DEPT_CODE="DEPT";
    private static final Random ID_GENERATOR=new Random();
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        return DEPT_CODE+ ID_GENERATOR.nextInt(10);
    }
}
