package ru.vood.state.demostatemachine.config.contextStateMachine

interface Node<SELF: Node<SELF, TARROW>, TARROW: Arrow<TARROW, SELF>> {

    val isBeginState: Boolean
    val isEndState: Boolean


}
