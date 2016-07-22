package com.wanda.creditapp.common.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.wanda.creditapp.common.validator.impl.TelValidator;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=TelValidator.class)
public @interface Tel {

    int min() default 0;

    String message();

    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};

}
