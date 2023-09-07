package ru.vood.state.demostatemachine.config.contextStateMachine

interface Arrow<SELF: Arrow<SELF, TNODE>, TNODE: Node<TNODE, SELF>> {
    val from: Node<TNODE, SELF>
    val to: Node<TNODE, SELF>
}
