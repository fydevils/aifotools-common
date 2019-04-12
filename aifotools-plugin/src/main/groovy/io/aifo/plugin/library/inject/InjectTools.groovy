package io.aifo.plugin.library.inject


import io.aifo.plugin.library.inject.util.InjectHelper
import io.aifo.plugin.library.inject.util.InjectUtil
import javassist.CtField;
import org.gradle.api.Project
import javassist.ClassPool;
import javassist.CtClass

public class InjectTools {

    private final static ClassPool pool = ClassPool.getDefault()

    public static void injectDir(String path, String packageName, Project project) {

        pool.appendClassPath(path)
        //project.android.bootClasspath 加入android.jar，否则找不到android相关的所有类
        pool.appendClassPath(project.android.bootClasspath[0].toString());

//      pool.importPackage(InjectHelper.InjectAnnotation)
//      InjectUtil.importBaseClass(pool);

        File dir = new File(path)
        if (dir.isDirectory()) {

            project.logger.error("transform library isDirectory --------" + packageName)
            dir.eachFileRecurse { File file ->

                String filePath = file.absolutePath//确保当前文件是class文件，并且不是系统自动生成的class文件
                if (filePath.endsWith(".class") && !filePath.contains('R$') && !filePath.contains('$')//代理类
                        && !filePath.contains('R.class') && !filePath.contains("BuildConfig.class")) {

                    project.logger.error("transform library !R.class --------" + filePath)

                    // 判断当前目录是否是在我们的应用包里面
                    int index = filePath.indexOf(packageName);
                    boolean isMyPackage = index != -1;

                    project.logger.error("transform library index --------ooo" + index)

                    if (isMyPackage) {

                        String className = InjectUtil.getClassName(index, filePath);

                        project.logger.error("transform library injectDir className: --------" + className)

                        CtClass c = pool.getCtClass(className)

                        if (c.isFrozen()) c.defrost()

                        pool.importPackage(packageName)

                        InjectInfo injectInfo = new InjectInfo()
                        injectInfo.setProject(project)
                        injectInfo.setClazz(c)
                        //io.aifo.api.javassist.Inject

                        //getDeclaredMethods获取自己申明的方法，c.getMethods()会把所有父类的方法都加上

                        boolean isAnnotationByInject = false;
                        for (CtField ctField : c.getDeclaredFields()) {

                            project.logger.error("transform library getFields --------" + ctField.name)

                            def annotation = ctField.hasAnnotation(InjectHelper.InjectAnnotation)
                            project.logger.error("transform  library has  annotation--------" + annotation)

                            def annotationsAvailible = ctField.getAnnotations();
                            project.logger.error("transform  library has  annotation availible--------" + annotationsAvailible.length)

//                            for (Annotation mAnnotation : ctField.getAnnotations()) {
//                                mAnnotation.annotationType().name
//                                project.logger.error("transform getFields Annotation--------" + mAnnotation.annotationType().canonicalName)
//                                if (mAnnotation.annotationType().canonicalName.equals(InjectHelper.InjectAnnotation)) {
//                                    project.logger.error("transform annotationType --------" + ctField.name)
//
//                                    injectInfo.fields.add(ctField);
//                                    if (!isAnnotationByInject) isAnnotationByInject = true
//                                }
//                            }

                        }

//                        if (injectInfo != null && isAnnotationByInject) {
//                            try {
//                                InjectHelper.initInject(mBusInfo, path)
//                            } catch (DuplicateMemberException e) {
//                                e.printStackTrace()
//                            }
//                        }
                        c.detach()//用完一定记得要卸载，否则pool里的永远是旧的代码
                    }
                }
            }
        }
    }
}
