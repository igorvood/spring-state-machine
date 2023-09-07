package ru.vood.state.demostatemachine.config.contextStateMachine

import ru.vood.state.demostatemachine.config.contextStateMachine.abstraction.ContextData

data class SomeContextData(
    override val arrow: FlowEventContext,
    val string: String
) : ContextData<FlowStatesContext, FlowEventContext>