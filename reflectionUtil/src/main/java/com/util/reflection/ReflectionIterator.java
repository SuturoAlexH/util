package com.util.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * This util class provides methods to iterate over fields and methods of reflections.
 */
public class ReflectionIterator {

    /**
     * Does the same as #field, but it takes a list of classes instead of a single class
     *
     * @param classes the list of classes
     * @param annotation the annotation
     * @param callBack the callback
     */
    public static void fields(final Collection<Class> classes, final Class<? extends Annotation> annotation, BiConsumer<Class, Field> callBack) {
        classes.forEach(clazz -> ReflectionIterator.field(clazz, annotation, callBack));
    }

    /**
     * Filters the fields of clazz for field annotated with #annotation and calls the provided callback with the clazz and field.
     *
     * @param clazz the class
     * @param annotation the annotation
     * @param callBack the callback
     */
    public static void field(final Class clazz, final Class<? extends Annotation> annotation, BiConsumer<Class, Field> callBack) {
        Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(annotation))
                .forEach(field -> callBack.accept(clazz, field));
    }

    /**
     * Filters the methods of the classes for methods annotated with #annotation and calls the provided callback with the clazz and method.
     *
     * @param classes the list of classes
     * @param annotation the annotation
     * @param callBack the callback
     */
    public static void methods(final List<Class> classes, final Class<? extends Annotation> annotation, BiConsumer<Class, Method> callBack) {
        classes.forEach(clazz -> Arrays.stream(clazz.getDeclaredMethods())
                .filter(field -> field.isAnnotationPresent(annotation))
                .forEach(method -> callBack.accept(clazz, method))
        );
    }
}
