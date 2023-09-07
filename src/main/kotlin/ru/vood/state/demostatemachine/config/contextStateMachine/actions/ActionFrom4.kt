package ru.vood.state.demostatemachine.config.contextStateMachine.actions

import org.springframework.statemachine.StateContext
import org.springframework.stereotype.Service
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext.STEP_4_TO_6
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext.STEP_4_to_5
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowStatesContext
import ru.vood.state.demostatemachine.config.contextStateMachine.abstraction.AbstractFlowAction

@Service
class ActionFrom4 : AbstractFlowAction(FlowStatesContext.STEP_4) {

    override fun executeWithRunNext(context: StateContext<FlowStatesContext, FlowEventContext>): FlowEventContext {
        val i = context.hashCode() % 2
        return if (i == 0) {
            STEP_4_TO_6
        } else {
            STEP_4_to_5
        }

    }

}