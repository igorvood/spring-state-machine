package ru.vood.state.demostatemachine.config.contextStateMachine.abstraction

import org.slf4j.LoggerFactory
import org.springframework.statemachine.StateContext
import org.springframework.statemachine.action.Action
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext

//@Service
abstract class AbstractAction<NODE : Node<NODE, ARROW>, ARROW : Arrow<ARROW, NODE>>(
    val from: NODE,
) : Action<NODE, ARROW> {


    protected val LOGGER = LoggerFactory.getLogger(this.javaClass)


    abstract val to: Set<ActionTransition<NODE, ARROW>>

    abstract fun executeWithRunNext(context: StateContext<NODE, ARROW>): ARROW

    override fun execute(context: StateContext<NODE, ARROW>) {
        executeWithRunNext(context).let { qw ->
            LOGGER.info(" Action TO  ${FlowEventContext.STEP_5_to_7}")
            context.stateMachine.sendEvent(qw)
        }

    }
}