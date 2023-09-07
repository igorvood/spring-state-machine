package ru.vood.state.demostatemachine.config.contextStateMachine



enum class FlowStatesContext(
    override val isBeginState: Boolean,
    override val isEndState: Boolean
): Node {

    STEP_3(true, false),
    STEP_4(false, false),
    STEP_5(false, false),
    STEP_6(false, false),
    STEP_7(false, true),

}