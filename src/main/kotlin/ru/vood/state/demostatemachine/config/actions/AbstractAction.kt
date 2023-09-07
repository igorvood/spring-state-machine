package ru.vood.state.demostatemachine.config.actions

import org.slf4j.LoggerFactory
import org.springframework.statemachine.StateContext
import org.springframework.statemachine.action.Action
import org.springframework.stereotype.Service
import ru.vood.state.demostatemachine.config.FlowEvent
import ru.vood.state.demostatemachine.config.FlowStates

//@Service
abstract class AbstractAction(
    val from: FlowStates,
    val to: List<FlowStates>,

    ): Action<FlowStates, FlowEvent> {



    val LOGGER = LoggerFactory.getLogger(this.javaClass)

    override fun execute(context: StateContext<FlowStates, FlowEvent>) {

        LOGGER.info(context.event.toString())

    }
}