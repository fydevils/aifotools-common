package io.aifo.plugin.application.inject

import javassist.CtClass
import javassist.CtConstructor
import javassist.CtField
import javassist.CtMethod
import javassist.bytecode.annotation.Annotation
import org.gradle.api.Project

import java.lang.reflect.Constructor

public class InjectInfo {

    Project project//保留当前工程的引用
    CtClass clazz//当前处理的class
    List<Annotation> annotations = new ArrayList<>()//带有Inject注解的注解列表
    List<CtField> fields = new ArrayList<>()//带Inject 注解的变量 列表
    CtConstructor constructMethod;
    CtMethod OnCreateMethod;//Activity或Fragment的初始化方法
    CtMethod OnDestroyMethod;//Activity或Fragment的销毁方法

}
