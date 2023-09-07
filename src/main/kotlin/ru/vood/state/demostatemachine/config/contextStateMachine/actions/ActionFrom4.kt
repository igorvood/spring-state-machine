package ru.vood.state.demostatemachine.config.contextStateMachine.actions

import org.springframework.statemachine.StateContext
import org.springframework.stereotype.Service
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext.*
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowStatesContext
import ru.vood.state.demostatemachine.config.contextStateMachine.abstraction.AbstractAction
import ru.vood.state.demostatemachine.config.contextStateMachine.abstraction.AbstractFlowAction

@Service
class ActionFrom4 : AbstractFlowAction(FlowStatesContext.STEP_4) {
    override fun execute(context: StateContext<FlowStatesContext, FlowEventContext>) {
        val i = context.hashCode() % 2
        if (i == 0) {
            LOGGER.info(" Action TO  $STEP_4_TO_6")
            context.stateMachine.sendEvent(STEP_4_TO_6)

        } else {
            LOGGER.info(" Action TO  $STEP_4_to_5")
            context.stateMachine.sendEvent(STEP_4_to_5)

        }
    }
}