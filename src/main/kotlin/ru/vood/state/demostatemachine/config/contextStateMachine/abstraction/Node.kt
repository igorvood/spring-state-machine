package ru.vood.state.demostatemachine.config.contextStateMachine.abstraction

interface Node<SELF: Node<SELF, TARROW>, TARROW: Arrow<TARROW, SELF>> {

    val isBeginState: Boolean
    val isEndState: Boolean


    val allNodes: Set<SELF>
}
