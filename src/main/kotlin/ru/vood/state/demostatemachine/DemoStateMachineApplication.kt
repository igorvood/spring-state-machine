package ru.vood.state.demostatemachine

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoStateMachineApplication

fun main(args: Array<String>) {
    runApplication<DemoStateMachineApplication>(*args)
}
