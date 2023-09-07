package ru.vood.state.demostatemachine.config.contextStateMachine.abstraction

import org.slf4j.LoggerFactory
import org.springframework.statemachine.action.Action
import ru.vood.state.demostatemachine.config.contextStateMachine.actions.ActionTransition

//@Service
abstract class AbstractAction<STATE, EVENT>(
    val from: STATE,
//    val to: Set<ActionTransition<STATE,EVENT>,

    ): Action<STATE, EVENT> {

    abstract val to: Set<ActionTransition<STATE, EVENT>>

    protected val LOGGER = LoggerFactory.getLogger(this.javaClass)


}