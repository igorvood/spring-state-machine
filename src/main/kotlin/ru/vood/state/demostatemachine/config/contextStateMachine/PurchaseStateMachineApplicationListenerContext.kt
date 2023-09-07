package ru.vood.state.demostatemachine.config.contextStateMachine

import org.slf4j.LoggerFactory
import org.springframework.messaging.Message
import org.springframework.statemachine.StateContext
import org.springframework.statemachine.StateMachine
import org.springframework.statemachine.listener.StateMachineListener
import org.springframework.statemachine.state.State
import org.springframework.statemachine.transition.Transition
import org.springframework.stereotype.Service


@Service
@Deprecated("asd")
class PurchaseStateMachineApplicationListenerContext : StateMachineListener<FlowStatesContext, FlowEventContext> {
    val LOGGER = LoggerFactory.getLogger(this.javaClass)
    override fun stateChanged(
        from: State<FlowStatesContext, FlowEventContext>?,
        to: State<FlowStatesContext, FlowEventContext>
    ) {
        if (from?.id != null) {
            LOGGER.info("stateChanged: Переход из статуса " + from.id + " в статус " + to.id)
        } else {
            LOGGER.info("stateChanged: Начальный статус " + to.id)
        }
    }

    override fun stateEntered(state: State<FlowStatesContext, FlowEventContext>) {
        LOGGER.info("stateEntered: Зашли в статус " + state.id)
    }

    override fun stateExited(state: State<FlowStatesContext, FlowEventContext>) {
        LOGGER.info("stateExited: Вышли из статуса " + state.id)
    }

    override fun eventNotAccepted(event: Message<FlowEventContext>) {
        LOGGER.warn("eventNotAccepted: Евент не принят $event")
    }

    override fun transition(transition: Transition<FlowStatesContext, FlowEventContext>) {

    }

    override fun transitionStarted(transition: Transition<FlowStatesContext, FlowEventContext>) {

    }

    override fun transitionEnded(transition: Transition<FlowStatesContext, FlowEventContext>) {

    }

    override fun stateMachineStarted(stateMachine: StateMachine<FlowStatesContext, FlowEventContext>) {

    }

    override fun stateMachineStopped(stateMachine: StateMachine<FlowStatesContext, FlowEventContext>) {

    }

    override fun stateMachineError(
        stateMachine: StateMachine<FlowStatesContext, FlowEventContext>,
        exception: Exception
    ) {

    }

    override fun extendedStateChanged(key: Any, value: Any) {

    }

    override fun stateContext(stateContext: StateContext<FlowStatesContext, FlowEventContext>?) {

    }
}