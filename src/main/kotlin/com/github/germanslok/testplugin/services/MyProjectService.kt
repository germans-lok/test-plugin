package com.github.germanslok.testplugin.services

import com.intellij.openapi.project.Project
import com.github.germanslok.testplugin.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
