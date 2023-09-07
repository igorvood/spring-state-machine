package ru.vood.state.demostatemachine.config.contextStateMachine.abstraction

data class ActionTransition<T, U>(
    val toState: T,
    val toEvent: U,
)
