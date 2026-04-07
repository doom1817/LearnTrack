package com.interview.software

import groovy.lang.Binding
import groovy.lang.GroovyShell

/**
 * @author: doom
 * @date: 2026/03/20/11:07
 * @description: Groovy 学习示例
 */
class GroovyStudy {
    static void main(String[] args) {
        // 示例 1: 简单的 Groovy 脚本执行
        String scriptText = """
                def name = "Groovy"
                println "Hello, \${name}!"
                
                // 列表操作
                def numbers = [1, 2, 3, 4, 5]
                def squared = numbers.collect { it * it }
                println "原始列表：\${numbers}"
                println "平方后：\${squared}"
                
                // 闭包示例
                def greet = { person ->
                    return "Hi, \${person}!"
                }
                println greet.call("Doom")
                
                // Map 操作
                def person = [name: "张三", age: 25, city: "北京"]
                println "个人信息：\${person}"
                person.each { key, value ->
                    println "\${key}: \${value}"
                }
                """

        GroovyShell shell = new GroovyShell()
        shell.evaluate(scriptText)

        // 示例 2: 带变量的脚本
        Binding binding = new Binding()
        binding.setVariable("x", 10)
        binding.setVariable("y", 20)

        GroovyShell shell2 = new GroovyShell(binding)
        Object result = shell2.evaluate("return x + y")
        System.out.println("x + y = " + result)
    }
}
