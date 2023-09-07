package ru.vood.state.demostatemachine.config.contextStateMachine

import org.springframework.stereotype.Service
import ru.vood.state.demostatemachine.config.contextStateMachine.abstraction.Checker

@Service
class StatesChecker : Checker {
    override fun check() {
        val filter = FlowStatesContext.values().filter { it.isBeginState }

        val begin = when (filter.size) {
            0 -> error("Не настроено начальное состояние")
            1 -> filter.first()
            else -> error("настроено несколько начальных состояний ${filter}")
        }

        val filter2 = FlowStatesContext.values().filter { it.isEndState }

        val end = when (filter2.size) {
            0 -> error("Не настроено конечное состояние")
            1 -> filter2.first()
            else -> error("настроено несколько конечных состояний ${filter2}")
        }

    }
}