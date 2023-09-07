package ru.vood.state.demostatemachine.config.contextStateMachine.abstraction

data class ArrowProperty<NODE : Node<NODE, ARROW>, ARROW : Arrow<ARROW, NODE>>(
    val source: NODE,
    val target: NODE,
    val event: ARROW,
    val action: AbstractAction<NODE, ARROW, *>
)
