package ru.vood.state.demostatemachine.config.contextStateMachine

import ru.vood.state.demostatemachine.config.contextStateMachine.abstraction.Node


enum class FlowStatesContext(
    override val isBeginState: Boolean,
    override val isEndState: Boolean
) : Node<FlowStatesContext, FlowEventContext> {

    STEP_3(true, false),
    STEP_4(false, false),
    STEP_5(false, false),
    STEP_6(false, false),
    STEP_7(false, true);

    override val allNodes: Set<FlowStatesContext>
        get() = FlowStatesContext.values().toSet()
}