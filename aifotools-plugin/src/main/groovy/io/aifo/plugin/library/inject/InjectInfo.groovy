package io.aifo.plugin.library.inject

import javassist.CtField;
import org.gradle.api.Project;

import java.util.ArrayList;
import java.util.List;

import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.annotation.Annotation;

public class InjectInfo {

    Project project//保留当前工程的引用
    CtClass clazz//当前处理的class
    List<Annotation> annotations = new ArrayList<>()//带有Inject注解的注解列表
    List<CtField> fields = new ArrayList<>()//带Inject 注解的变量 列表
    CtMethod constructMethod ;

}
