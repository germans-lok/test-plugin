package com.github.germanslok.testplugin

import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project

@Service
class PikachuAsciiService(val myProject: Project) {

    fun getPikachuAsciiStrings(): List<String> {
        val asciiString = this::class.java.getResourceAsStream("/ascii-art.txt").readBytes().toString(Charsets.UTF_8)
        return asciiString.lines()
    }
}
