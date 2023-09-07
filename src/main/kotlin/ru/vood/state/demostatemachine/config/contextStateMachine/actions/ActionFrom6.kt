package ru.vood.state.demostatemachine.config.contextStateMachine.actions

import org.springframework.statemachine.StateContext
import org.springframework.stereotype.Service
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowStatesContext
import ru.vood.state.demostatemachine.config.contextStateMachine.SomeOtherContextData
import ru.vood.state.demostatemachine.config.contextStateMachine.abstraction.AbstractFlowAction

@Service
class ActionFrom6 : AbstractFlowAction<SomeOtherContextData>(FlowStatesContext.STEP_6) {
    override fun executeWithRunNext(context: StateContext<FlowStatesContext, FlowEventContext>): SomeOtherContextData {
        return SomeOtherContextData(FlowEventContext.STEP_6_TO_7, FlowEventContext.STEP_6_TO_7.toString())
    }

    //    override fun executeWithRunNext(context: StateContext<FlowStatesContext, FlowEventContext>): FlowEventContext {
//        return FlowEventContext.STEP_6_TO_7
//    }

}