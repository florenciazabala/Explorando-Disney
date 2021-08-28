package com.alkemy.explorandodisney.persistence.entity;

import org.hibernate.annotations.ColumnTransformer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Map;

@Component(value = "transformerColumnKeyLoader")
public class TransformerColumnKeyLoader {

    public static final String KEY_ANNOTATION_PROPERTY = "${encryption.key}";

    @Value(value = "${secret.key}")
    private String key;

    @PostConstruct
    public void postConstruct() {
        setKey(Usuario.class, "clave");
    }

    private void setKey(Class<?> clazz, String columnName) {
        try {
            Field field = clazz.getDeclaredField(columnName);

            ColumnTransformer columnTransformer = field.getDeclaredAnnotation(ColumnTransformer.class);
            updateAnnotationValue(columnTransformer, "read");
            updateAnnotationValue(columnTransformer, "write");
        } catch (NoSuchFieldException | SecurityException e) {
            throw new RuntimeException(
                    String.format("Encryption key cannot be loaded into %s,%s", clazz.getName(), columnName));
        }
    }

    @SuppressWarnings("unchecked")
    private void updateAnnotationValue(Annotation annotation, String annotationProperty) {
        Object handler = Proxy.getInvocationHandler(annotation);
        Field merberValuesField;
        try {
            merberValuesField = handler.getClass().getDeclaredField("memberValues");
        } catch (NoSuchFieldException | SecurityException e) {
            throw new IllegalStateException(e);
        }
        merberValuesField.setAccessible(true);
        Map<String, Object> memberValues;
        try {
            memberValues = (Map<String, Object>) merberValuesField.get(handler);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
        Object oldValue = memberValues.get(annotationProperty);
        if (oldValue == null || oldValue.getClass() != String.class) {
            throw new IllegalArgumentException(String.format(
                    "Annotation value should be String. Current value is of type: %s", oldValue.getClass().getName()));
        }

        String oldValueString = oldValue.toString();
        if (!oldValueString.contains(TransformerColumnKeyLoader.KEY_ANNOTATION_PROPERTY)) {
            throw new IllegalArgumentException(
                    String.format("Annotation value should be contain %s. Current value is : %s",
                            TransformerColumnKeyLoader.KEY_ANNOTATION_PROPERTY, oldValueString));
        }
        String newValueString = oldValueString.replace(TransformerColumnKeyLoader.KEY_ANNOTATION_PROPERTY, key);

        memberValues.put(annotationProperty, newValueString);
    }
}
