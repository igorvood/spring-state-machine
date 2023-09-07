package ru.vood.state.demostatemachine.config.contextStateMachine.actions

import org.springframework.statemachine.StateContext
import org.springframework.stereotype.Service
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext.STEP_3_to_4
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext.values
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowStatesContext

@Service
class ActionFrom3 : AbstractAction<FlowStatesContext, FlowEventContext>(FlowStatesContext.STEP_3) {
    override val to: Set<ActionTransition<FlowStatesContext, FlowEventContext>>
        get() = values()
            .filter { it.from == from }
            .map { qw -> ActionTransition(qw.to, qw) }
            .toSet()

    override fun execute(context: StateContext<FlowStatesContext, FlowEventContext>) {
        LOGGER.info(" Action TO  $STEP_3_to_4")
        context.stateMachine.sendEvent(STEP_3_to_4)
    }
}