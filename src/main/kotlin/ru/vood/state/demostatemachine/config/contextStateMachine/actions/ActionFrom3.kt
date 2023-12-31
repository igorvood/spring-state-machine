package ru.vood.state.demostatemachine.config.contextStateMachine.actions

import org.springframework.statemachine.StateContext
import org.springframework.stereotype.Service
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowStatesContext
import ru.vood.state.demostatemachine.config.contextStateMachine.SomeContextData
import ru.vood.state.demostatemachine.config.contextStateMachine.abstraction.AbstractFlowAction

@Service
class ActionFrom3 : AbstractFlowAction<SomeContextData>(FlowStatesContext.STEP_3) {
    override fun executeWithRunNext(context: StateContext<FlowStatesContext, FlowEventContext>): SomeContextData {
        return SomeContextData(FlowEventContext.STEP_3_to_4, "1")
    }


}