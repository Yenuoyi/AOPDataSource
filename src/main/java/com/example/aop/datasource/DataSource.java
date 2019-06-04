package com.example.aop.datasource;

import java.lang.annotation.*;

@Target(value = {ElementType.METHOD,ElementType.FIELD,ElementType.PARAMETER,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DataSource {
    String value() default "MASTER-DATASOURCE";
}
