package ru.vood.state.demostatemachine.config.contextStateMachine

enum class FlowEventContext(val from : FlowStatesContext, to: FlowStatesContext) {
    STEP_4_to_5(FlowStatesContext.STEP_4, FlowStatesContext.STEP_5),
    STEP_5_to_7(FlowStatesContext.STEP_5, FlowStatesContext.STEP_7),
    STEP_4_TO_6(FlowStatesContext.STEP_4, FlowStatesContext.STEP_6),
    STEP_6_TO_7(FlowStatesContext.STEP_6, FlowStatesContext.STEP_6),

}