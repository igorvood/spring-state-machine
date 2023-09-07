package ru.vood.state.demostatemachine.config

import org.slf4j.LoggerFactory
import org.springframework.messaging.Message
import org.springframework.statemachine.StateContext
import org.springframework.statemachine.StateMachine
import org.springframework.statemachine.listener.StateMachineListener
import org.springframework.statemachine.state.State
import org.springframework.statemachine.transition.Transition
import org.springframework.stereotype.Service


@Service
class PurchaseStateMachineApplicationListener : StateMachineListener<FlowStates, FlowEvent> {
    val LOGGER = LoggerFactory.getLogger(this.javaClass)
    override fun stateChanged(from: State<FlowStates, FlowEvent>?, to: State<FlowStates, FlowEvent>) {
        if (from?.id != null) {
            LOGGER.info("stateChanged: Переход из статуса " + from.id + " в статус " + to.id)
        }else {
            LOGGER.info("stateChanged: Начальный статус " + to.id)
        }
    }

    override fun stateEntered(state: State<FlowStates, FlowEvent>) {
            LOGGER.info("stateEntered: Зашли в статус " + state.id)
    }

    override fun stateExited(state: State<FlowStates, FlowEvent>) {
        LOGGER.info("stateExited: Вышли из статуса " + state.id)
    }

    override fun eventNotAccepted(event: Message<FlowEvent>) {
        LOGGER.warn("eventNotAccepted: Евент не принят $event")
    }

    override fun transition(transition: Transition<FlowStates, FlowEvent>) {

    }

    override fun transitionStarted(transition: Transition<FlowStates, FlowEvent>) {

    }

    override fun transitionEnded(transition: Transition<FlowStates, FlowEvent>) {

    }

    override fun stateMachineStarted(stateMachine: StateMachine<FlowStates, FlowEvent>) {

    }

    override fun stateMachineStopped(stateMachine: StateMachine<FlowStates, FlowEvent>) {

    }

    override fun stateMachineError(stateMachine: StateMachine<FlowStates, FlowEvent>, exception: Exception) {

    }

    override fun extendedStateChanged(key: Any, value: Any) {

    }

    override fun stateContext(stateContext: StateContext<FlowStates, FlowEvent>?) {

    }
}