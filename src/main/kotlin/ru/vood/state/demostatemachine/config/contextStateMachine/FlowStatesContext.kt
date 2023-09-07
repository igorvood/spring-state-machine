package ru.vood.state.demostatemachine.config.contextStateMachine

enum class FlowStatesContext(
    val isBeginState: Boolean,
    val isEndState: Boolean) {
    STEP_4(true, false),
    STEP_5(false, false),
    STEP_6(false, false),
    STEP_7(false, true),
}