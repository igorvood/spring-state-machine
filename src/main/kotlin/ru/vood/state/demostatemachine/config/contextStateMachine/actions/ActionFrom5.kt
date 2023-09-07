package ru.vood.state.demostatemachine.config.contextStateMachine.actions

import org.springframework.statemachine.StateContext
import org.springframework.stereotype.Service
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext.STEP_5_to_7
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowStatesContext
import ru.vood.state.demostatemachine.config.contextStateMachine.abstraction.AbstractAction
import ru.vood.state.demostatemachine.config.contextStateMachine.abstraction.AbstractFlowAction

@Service
class ActionFrom5 : AbstractFlowAction(FlowStatesContext.STEP_5) {

    override fun execute(context: StateContext<FlowStatesContext, FlowEventContext>) {
        LOGGER.info(" Action TO  $STEP_5_to_7")
        context.stateMachine.sendEvent(STEP_5_to_7)

    }
}