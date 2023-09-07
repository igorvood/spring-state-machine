package ru.vood.state.demostatemachine.config.contextStateMachine.abstraction

interface ContextData<
        NODE : Node<NODE, ARROW>,
        ARROW : Arrow<ARROW, NODE>,
        > {

    val arrow: ARROW

    fun getContextData(): ContextData<NODE, ARROW> = this
}