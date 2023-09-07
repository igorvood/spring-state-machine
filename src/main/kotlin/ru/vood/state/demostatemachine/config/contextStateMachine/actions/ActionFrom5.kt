package ru.vood.state.demostatemachine.config.contextStateMachine.actions

import org.springframework.statemachine.StateContext
import org.springframework.stereotype.Service
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext.STEP_5_to_7
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowStatesContext
import ru.vood.state.demostatemachine.config.contextStateMachine.SomeOtherContextData
import ru.vood.state.demostatemachine.config.contextStateMachine.abstraction.AbstractAction
import ru.vood.state.demostatemachine.config.contextStateMachine.abstraction.AbstractFlowAction

@Service
class ActionFrom5 : AbstractFlowAction<SomeOtherContextData>(FlowStatesContext.STEP_5) {
    override fun executeWithRunNext(context: StateContext<FlowStatesContext, FlowEventContext>): SomeOtherContextData {
     return SomeOtherContextData(STEP_5_to_7, STEP_5_to_7.toString())
    }

}