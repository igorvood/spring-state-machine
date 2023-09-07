package ru.vood.state.demostatemachine.config.contextStateMachine.abstraction

import org.slf4j.LoggerFactory
import org.springframework.messaging.Message
import org.springframework.statemachine.StateContext
import org.springframework.statemachine.StateMachine
import org.springframework.statemachine.listener.StateMachineListener
import org.springframework.statemachine.state.State
import org.springframework.statemachine.transition.Transition
import org.springframework.stereotype.Service
import java.lang.Exception


@Service
class CommonStateMachineApplicationListenerContext<NODE: Node<NODE, ARROW>, ARROW: Arrow<ARROW, NODE>> : StateMachineListener<NODE, ARROW> {
    val LOGGER = LoggerFactory.getLogger(this.javaClass)
    override fun stateChanged(from: State<NODE, ARROW>?, to: State<NODE, ARROW>) {
        if (from?.id != null) {
            LOGGER.info("stateChanged: Переход из статуса " + from.id + " в статус " + to.id)
        } else {
            LOGGER.info("stateChanged: Начальный статус " + to.id)
        }
    }

    override fun stateEntered(state: State<NODE, ARROW>) {
        LOGGER.info("stateEntered: Зашли в статус " + state.id)
    }

    override fun stateExited(state: State<NODE, ARROW>) {
        LOGGER.info("stateExited: Вышли из статуса " + state.id)
    }

    override fun eventNotAccepted(event: Message<ARROW>) {
        LOGGER.warn("eventNotAccepted: Евент не принят $event")
    }

    override fun transition(transition: Transition<NODE, ARROW>?) {

    }

    override fun transitionStarted(transition: Transition<NODE, ARROW>?) {

    }

    override fun transitionEnded(transition: Transition<NODE, ARROW>?) {

    }

    override fun stateMachineStarted(stateMachine: StateMachine<NODE, ARROW>?) {

    }

    override fun stateMachineStopped(stateMachine: StateMachine<NODE, ARROW>?) {

    }

    override fun stateMachineError(stateMachine: StateMachine<NODE, ARROW>?, exception: Exception?) {

    }

    override fun extendedStateChanged(key: Any?, value: Any?) {

    }

    override fun stateContext(stateContext: StateContext<NODE, ARROW>?) {

    }
}