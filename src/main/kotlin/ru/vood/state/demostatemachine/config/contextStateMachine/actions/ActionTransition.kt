package ru.vood.state.demostatemachine.config.contextStateMachine.actions

data class ActionTransition<T, U>(
    val toState: T,
    val toEvent: U,
)
