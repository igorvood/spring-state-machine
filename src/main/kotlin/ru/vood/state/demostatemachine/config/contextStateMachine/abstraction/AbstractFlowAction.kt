package ru.vood.state.demostatemachine.config.contextStateMachine.abstraction

import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowStatesContext
import ru.vood.state.demostatemachine.config.contextStateMachine.actions.ActionTransition

abstract class AbstractFlowAction(from: FlowStatesContext) : AbstractAction<FlowStatesContext, FlowEventContext>(from) {

    override val to: Set<ActionTransition<FlowStatesContext, FlowEventContext>>
        get() = FlowEventContext.values()
            .filter { it.from == from }
            .map { qw -> ActionTransition(qw.to, qw) }
            .toSet()
}