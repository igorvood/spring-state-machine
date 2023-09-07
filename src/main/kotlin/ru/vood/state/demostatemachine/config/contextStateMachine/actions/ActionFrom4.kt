package ru.vood.state.demostatemachine.config.contextStateMachine.actions

import org.springframework.statemachine.StateContext
import org.springframework.stereotype.Service
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext.STEP_4_TO_6
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowStatesContext
import ru.vood.state.demostatemachine.config.contextStateMachine.SomeContextData
import ru.vood.state.demostatemachine.config.contextStateMachine.abstraction.AbstractFlowAction

@Service
class ActionFrom4 : AbstractFlowAction<SomeContextData>(FlowStatesContext.STEP_4) {
    override fun executeWithRunNext(context: StateContext<FlowStatesContext, FlowEventContext>): SomeContextData {
        val i = context.hashCode() % 2
        return if (i == 0) {
            SomeContextData(FlowEventContext.STEP_4_TO_6, STEP_4_TO_6.toString())

        } else {
            SomeContextData(FlowEventContext.STEP_4_to_5, "3")

        }


    }

    //    override fun executeWithRunNext(context: StateContext<FlowStatesContext, FlowEventContext>): FlowEventContext {
//        return SomeContextData(FlowEventContext.STEP_3_to_4, "1")
//        val i = context.hashCode() % 2
//        return if (i == 0) {
//            STEP_4_TO_6
//        } else {
//            STEP_4_to_5
//        }
//
//    }

}