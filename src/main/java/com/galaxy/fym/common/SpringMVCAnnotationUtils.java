package com.galaxy.fym.common;

import com.galaxy.fym.model.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fengyiming on 2016/8/15.
 */
public class SpringMVCAnnotationUtils {

    private final static String BANK = "";

    public static List<ClassDocument> getAllController(String packageName) throws IOException {
        List<ClassDocument> list = new ArrayList<ClassDocument>();
        List<Class<?>> controllerList = ClassUtils.getAnnotationClassList(packageName, Controller.class);
        for (Class<?> clazz : controllerList) {
            if (clazz.isAnnotationPresent(RequestMapping.class)) {
                ClassDocument classDocument = getClassDocument(clazz);
                list.add(classDocument);
            }
        }
        return list;
    }

    private static ClassDocument getClassDocument(Class<?> clazz) {
        ClassDocument classDocument = new ClassDocument();
        String packageName = clazz.getPackage().getName();
        String[] module = {};
        int sort = 0;
        String desc = BANK;
        String[] path = {};
        RequestMapping requestMapping = clazz.getDeclaredAnnotation(RequestMapping.class);
        path = requestMapping.value().length > 0 ? requestMapping.value() : requestMapping.path();
        desc = requestMapping.name();
        if (clazz.isAnnotationPresent(SpringDocument.class)) {
            SpringDocument springDocument = clazz.getAnnotation(SpringDocument.class);
            module = springDocument.module();
            sort = springDocument.sort();
            desc = StringUtils.isNotEmpty(springDocument.desc())?springDocument.desc():desc;
        }
        classDocument.setIndex(sort);
        classDocument.setModule(Arrays.asList(module));
        classDocument.setPath(Arrays.asList(path));
        classDocument.setPackagePath(packageName);
        classDocument.setBaseClass(clazz);
        classDocument.setDesc(desc);
        List<FieldDocument> fieldDocuments = getFieldDocuments(clazz);
        classDocument.setFieldDocumentList(fieldDocuments);
        List<MethodDocument> methodDocuments = getMethodDocuments(clazz);
        classDocument.setMethodDocumentList(methodDocuments);
        return classDocument;
    }

    private static List<FieldDocument> getFieldDocuments(Class<?> clazz){
        List<FieldDocument> list = new ArrayList<FieldDocument>();
        Field[] fields = clazz.getDeclaredFields();
        if(fields != null && fields.length > 0) {
            for (Field field : fields) {
                FieldDocument fieldDocument = getFieldDocument(field);
                list.add(fieldDocument);
            }
        }
        return list;
    }

    private static FieldDocument getFieldDocument(Field field){
        FieldDocument fieldDocument = new FieldDocument();
        String name = field.getName();
        Class<?> fieldClass = field.getType();
        int modifierValue = field.getModifiers();
        String desc = BANK;
        int sort = 0;
        if(field.isAnnotationPresent(FieldAnnotation.class)){
            FieldAnnotation fieldAnnotation = field.getAnnotation(FieldAnnotation.class);
            sort = fieldAnnotation.sort();
            desc = StringUtils.isNotEmpty(fieldAnnotation.desc())?fieldAnnotation.desc():desc;
        }
        fieldDocument.setIndex(sort);
        fieldDocument.setName(name);
        fieldDocument.setDesc(desc);
        fieldDocument.setBaseClass(fieldClass);
        fieldDocument.setModifierValue(modifierValue);
        return fieldDocument;
    }

    private static List<MethodDocument> getMethodDocuments(Class<?> clazz){
        List<MethodDocument> list = new ArrayList<MethodDocument>();
        Method[] methods = clazz.getDeclaredMethods();
        if(methods != null && methods.length > 0){
            for(Method method : methods){
                MethodDocument methodDocument = getMethodDocument(method);
                list.add(methodDocument);
            }
        }
        return list;
    }

    private static MethodDocument getMethodDocument(Method method){
        MethodDocument methodDocument = new MethodDocument();
        String name = method.getName();
        int sort = 0;
        String desc = BANK;
        String[] path = {};
        Class<?> methodClass = method.getReturnType();
        if(method.isAnnotationPresent(RequestMapping.class)){
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            desc = requestMapping.name();
            path = requestMapping.value().length > 0 ? requestMapping.value() : requestMapping.path();
        }
        if(method.isAnnotationPresent(MethodAnnotation.class)){
            MethodAnnotation methodAnnotation = method.getAnnotation(MethodAnnotation.class);
            desc = StringUtils.isNotEmpty(methodAnnotation.desc()) ? methodAnnotation.desc():desc;
            sort = methodAnnotation.sort();
            methodClass = methodAnnotation.baseClass();
        }
        methodDocument.setIndex(sort);
        methodDocument.setName(name);
        methodDocument.setDesc(desc);
        methodDocument.setPath(Arrays.asList(path));
        methodDocument.setModifierValue(method.getModifiers());
        methodDocument.setReturnClass(methodClass);
        if(method.isAnnotationPresent(ResponseBody.class)){
            methodDocument.setIsJson(true);
        }else{
            methodDocument.setIsJson(false);
        }
        Parameter[] parameters = method.getParameters();
        if(parameters != null && parameters.length > 0) {
            LocalVariableTableParameterNameDiscoverer loadParameter = new LocalVariableTableParameterNameDiscoverer();
            String[] parameterNames = loadParameter.getParameterNames(method);
            List<ParameterDocument> list = getParameterDocuments(parameters, parameterNames);
            methodDocument.setParameterDocumentList(list);
        }
        return methodDocument;
    }

    private static List<ParameterDocument> getParameterDocuments(Parameter[] parameters,String[] parameterNames){
        List<ParameterDocument> list = new ArrayList<ParameterDocument>(parameters.length);
        for(int index = 0;index< parameters.length;index++){
            ParameterDocument parameterDocument = new ParameterDocument();
            Parameter parameter = parameters[index];
            String value = parameterNames[index];
            String desc = BANK;
            boolean required = true;
            String defaultValue = BANK;
            if(parameter.isAnnotationPresent(RequestParam.class)){
                RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
                value = StringUtils.isNotBlank(requestParam.value()) ? requestParam.value() : value;
                desc = requestParam.name();
                String defaultString = requestParam.defaultValue();
                if(StringUtils.isNotEmpty(defaultString)){
                    if(!isDefaultValue(defaultString)){
                        defaultValue = defaultString;
                    }else{
                        defaultValue = null;
                    }
                }else {
                    defaultValue = null;
                }
                required = requestParam.required();
            }
            parameterDocument.setIndex(index + 1);
            parameterDocument.setValue(value);
            parameterDocument.setDefaultValue(defaultValue);
            parameterDocument.setDesc(desc);
            parameterDocument.setRequired(required);
            parameterDocument.setParameterClass(parameter.getType());
            list.add(parameterDocument);
        }
        return list;
    }

    private static boolean isDefaultValue(String defaultValue){
        char[] chars = defaultValue.toCharArray();
        char[] isNull = new char[]{'\n','\t','\t','\n','\t','\t','\n','\uE000','\ue001','\ue002','\n','\t','\t','\t','\t','\n'};
        if(chars.length == isNull.length){
            for(int index = 0;index < isNull.length;index++){
                if(isNull[index] != chars[index]){
                    return false;
                }
            }
            return true;
        }else{
            return false;
        }
    }
}
