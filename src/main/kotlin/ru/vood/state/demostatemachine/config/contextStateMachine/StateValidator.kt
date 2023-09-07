package ru.vood.state.demostatemachine.config.contextStateMachine

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service

@Service
class StateValidator(val check: Set<Checker>) : CommandLineRunner {
    override fun run(vararg args: String?) {
        check.forEach { it.check() }
    }
}