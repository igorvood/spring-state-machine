package ru.vood.state.demostatemachine.config.contextStateMachine.actions

import org.springframework.statemachine.StateContext
import org.springframework.stereotype.Service
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext.STEP_6_TO_7
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowStatesContext

@Service
class ActionFrom6 : AbstractAction<FlowStatesContext, FlowEventContext>(FlowStatesContext.STEP_6) {
    override val to: Set<ActionTransition<FlowStatesContext, FlowEventContext>>
        get() = FlowEventContext.values()
            .filter { it.from == from }
            .map { qw -> ActionTransition(qw.to, qw) }
            .toSet()

    override fun execute(context: StateContext<FlowStatesContext, FlowEventContext>) {
        context.stateMachine.sendEvent(STEP_6_TO_7)
        LOGGER.info(" Action TO  $STEP_6_TO_7")
    }
}