package ru.vood.state.demostatemachine.config.contextStateMachine.actions

import org.springframework.statemachine.StateContext
import org.springframework.stereotype.Service
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowStatesContext

@Service
class ActionFrom4 : AbstractAction<FlowStatesContext, FlowEventContext>(FlowStatesContext.STEP_4) {
    override val to: Set<ActionTransition<FlowStatesContext, FlowEventContext>>
        get() = FlowEventContext.values()
            .filter { it.from == from }
            .map { qw -> ActionTransition(qw.to, qw) }
            .toSet()

    override fun execute(context: StateContext<FlowStatesContext, FlowEventContext>) {
        val i = context.hashCode() % 2
        if (i == 0) {
            context.stateMachine.sendEvent(FlowEventContext.STEP_4_TO_6)
        } else {
            context.stateMachine.sendEvent(FlowEventContext.STEP_4_to_5)
        }
    }
}