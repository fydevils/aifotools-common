package io.aifo.plugin.application


import org.gradle.api.Plugin
import org.gradle.api.Project

public class JavassistPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.android.registerTransform(new InjectTransform(project))
    }

}