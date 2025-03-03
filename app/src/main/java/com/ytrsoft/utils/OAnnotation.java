package com.ytrsoft.utils;

import java.lang.annotation.Annotation;

public class OAnnotation {

    private final Class<?> target;
    private final Class<? extends Annotation> annotationClass;

    public OAnnotation(Object target, Class<? extends Annotation> annotationClass) {
        this.target = target.getClass();
        this.annotationClass = annotationClass;
    }

    public boolean isPresent() {
        return get() != null && target.isAnnotationPresent(annotationClass);
    }

    public Annotation get() {
        return target.getAnnotation(annotationClass);
    }

}
