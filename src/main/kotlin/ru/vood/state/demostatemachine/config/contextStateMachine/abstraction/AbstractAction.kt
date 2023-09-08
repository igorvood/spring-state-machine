package ru.vood.state.demostatemachine.config.contextStateMachine.abstraction

import org.slf4j.LoggerFactory
import org.springframework.messaging.Message
import org.springframework.messaging.support.GenericMessage
import org.springframework.statemachine.StateContext
import org.springframework.statemachine.action.Action
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

//@Service
abstract class AbstractAction<
        NODE : Node<NODE, ARROW>,
        ARROW : Arrow<ARROW, NODE>,
        out DATA : ContextData<NODE, ARROW>
        >(
    val from: NODE,
) : Action<NODE, ARROW> {


    protected val LOGGER = LoggerFactory.getLogger(this.javaClass)


    abstract val to: Set<ActionTransition<NODE, ARROW>>

    abstract fun executeWithRunNext(context: StateContext<NODE, ARROW>): DATA

    override fun execute(context: StateContext<NODE, ARROW>) {
        executeWithRunNext(context).let { data ->
            LOGGER.info(" Action TO  ${data.arrow} context ${context.stateMachine.extendedState.variables}")
            context.extendedState.variables[data.javaClass.canonicalName] = data.getContextData()
            val message = GenericMessage(data.arrow)
            val sendEvent = context.stateMachine.sendEvent(Mono.fromCallable { GenericMessage(data.arrow) })
            sendEvent.toMono().block()

//            context.stateMachine.sendEvent(data.arrow)
        }

    }
}