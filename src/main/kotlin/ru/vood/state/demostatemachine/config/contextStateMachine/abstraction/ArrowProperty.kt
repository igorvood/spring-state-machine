package ru.vood.state.demostatemachine.config.contextStateMachine.abstraction

import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowStatesContext

data class ArrowProperty<NODE : Node<NODE, ARROW>, ARROW : Arrow<ARROW, NODE>>(
    val source: NODE,
    val target: NODE,
    val event: ARROW,
    val action: AbstractAction<NODE, ARROW, *>
)
