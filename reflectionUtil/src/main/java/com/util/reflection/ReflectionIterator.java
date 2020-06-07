package com.util.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;

public class ReflectionIterator {

    public static void fields(final Collection<Class> classes, final Class<? extends Annotation> annotation, BiConsumer<Class, Field> callBack) {
        classes.forEach(clazz -> ReflectionIterator.fields(clazz, annotation, callBack));
    }

    public static void fields(final Class clazz, final Class<? extends Annotation> annotation, BiConsumer<Class, Field> callBack) {
        Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(annotation))
                .forEach(field -> callBack.accept(clazz, field));
    }

    public static void methods(final List<Class> classes, final Class<? extends Annotation> annotation, BiConsumer<Class, Method> callBack) {
        classes.forEach(clazz -> Arrays.stream(clazz.getDeclaredMethods())
                .filter(field -> field.isAnnotationPresent(annotation))
                .forEach(method -> callBack.accept(clazz, method))
        );
    }
}
