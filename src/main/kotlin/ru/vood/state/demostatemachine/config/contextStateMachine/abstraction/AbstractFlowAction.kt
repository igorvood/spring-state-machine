package ru.vood.state.demostatemachine.config.contextStateMachine.abstraction

import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowStatesContext

abstract class AbstractFlowAction<CONTEXTDATA : ContextData<FlowStatesContext, FlowEventContext>>(from: FlowStatesContext) :
    AbstractAction<FlowStatesContext, FlowEventContext, CONTEXTDATA>(from) {

    override val to: Set<ActionTransition<FlowStatesContext, FlowEventContext>>
        get() = FlowEventContext.values()
            .filter { it.from == from }
            .map { qw -> ActionTransition(qw.to, qw) }
            .toSet()
}