package ru.vood.state.demostatemachine.config.contextStateMachine.abstraction

import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext

interface Arrow<SELF: Arrow<SELF, TNODE>, TNODE: Node<TNODE, SELF>> {
    val from: Node<TNODE, SELF>
    val to: Node<TNODE, SELF>

    val allArrow: Set<SELF>
}
