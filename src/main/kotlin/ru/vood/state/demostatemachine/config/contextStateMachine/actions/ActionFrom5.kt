package ru.vood.state.demostatemachine.config.contextStateMachine.actions

import org.springframework.statemachine.StateContext
import org.springframework.stereotype.Service
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext.STEP_5_to_7
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowStatesContext

@Service
class ActionFrom5 : AbstractAction<FlowStatesContext, FlowEventContext>(FlowStatesContext.STEP_5) {
    override val to: Set<ActionTransition<FlowStatesContext, FlowEventContext>>
        get() = FlowEventContext.values()
            .filter { it.from == from }
            .map { qw -> ActionTransition(qw.to, qw) }
            .toSet()

    override fun execute(context: StateContext<FlowStatesContext, FlowEventContext>) {
        LOGGER.info(" Action TO  $STEP_5_to_7")
        context.stateMachine.sendEvent(STEP_5_to_7)

    }
}