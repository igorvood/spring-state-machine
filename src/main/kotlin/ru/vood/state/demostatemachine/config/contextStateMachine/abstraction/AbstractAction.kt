package ru.vood.state.demostatemachine.config.contextStateMachine.abstraction

import org.slf4j.LoggerFactory
import org.springframework.statemachine.action.Action
import ru.vood.state.demostatemachine.config.contextStateMachine.actions.ActionTransition

//@Service
abstract class AbstractAction<NODE: Node<NODE, ARROW>, ARROW: Arrow<ARROW, NODE>>(
    val from: NODE,
    ): Action<NODE, ARROW> {

    abstract val to: Set<ActionTransition<NODE, ARROW>>

    protected val LOGGER = LoggerFactory.getLogger(this.javaClass)


}