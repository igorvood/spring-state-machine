package ru.vood.state.demostatemachine.config.contextStateMachine

import ru.vood.state.demostatemachine.config.contextStateMachine.abstraction.Arrow

enum class FlowEventContext(
    override val from: FlowStatesContext,
    override val to: FlowStatesContext
) : Arrow<FlowEventContext, FlowStatesContext> {
    STEP_3_to_4(FlowStatesContext.STEP_3, FlowStatesContext.STEP_4),
    STEP_4_to_5(FlowStatesContext.STEP_4, FlowStatesContext.STEP_5),
    STEP_5_to_7(FlowStatesContext.STEP_5, FlowStatesContext.STEP_7),
    STEP_4_TO_6(FlowStatesContext.STEP_4, FlowStatesContext.STEP_6),
    STEP_6_TO_7(FlowStatesContext.STEP_6, FlowStatesContext.STEP_7);

    override val allArrow: Set<FlowEventContext>
        get() = FlowEventContext.values().toSet()
}