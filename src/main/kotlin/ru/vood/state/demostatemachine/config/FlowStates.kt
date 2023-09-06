package ru.vood.state.demostatemachine.config

enum class FlowStates(val isBeginState: Boolean, val isEndState: Boolean) {
    STEP_4(true, false),
    STEP_5(false, false),
    STEP_6(false, false),
    STEP_7(false, false),
    STEP_8(false, false),
    STEP_9(false, true),
}