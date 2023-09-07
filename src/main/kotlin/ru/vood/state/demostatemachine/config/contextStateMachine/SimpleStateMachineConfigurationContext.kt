package ru.vood.state.demostatemachine.config.contextStateMachine

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.statemachine.config.EnableStateMachineFactory
import org.springframework.statemachine.config.StateMachineConfigurerAdapter
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import ru.vood.state.demostatemachine.config.contextStateMachine.actions.AbstractAction


@Configuration
@EnableStateMachineFactory(name = ["SimpleStateMachineConfigurationContext"])
class SimpleStateMachineConfigurationContext(
    private val setActions: Set<AbstractAction<FlowStatesContext, FlowEventContext>>
) : StateMachineConfigurerAdapter<FlowStatesContext, FlowEventContext>() {
    val LOGGER = LoggerFactory.getLogger(this.javaClass)
    override fun configure(states: StateMachineStateConfigurer<FlowStatesContext, FlowEventContext>) {

        val begin = FlowStatesContext.values().firstOrNull { it.isBeginState }!!
        val end = FlowStatesContext.values().firstOrNull { it.isEndState }!!
        states
            .withStates()
            .initial(begin)
            .end(end)
            .states(
                HashSet(FlowStatesContext.values().toList())
            )
    }

    override fun configure(config: StateMachineConfigurationConfigurer<FlowStatesContext, FlowEventContext>) {
        config
            .withConfiguration()
            .autoStartup(true)
            .listener(PurchaseStateMachineApplicationListenerContext())

    }

    data class OneStepProp<STATE, EVENT>(
        val source: STATE,
        val target: STATE,
        val event: EVENT,
        val action: AbstractAction<STATE, EVENT>
    )

    override fun configure(
        transitions: StateMachineTransitionConfigurer<FlowStatesContext, FlowEventContext>
    ) {

        val stepProps = setActions
            .flatMap { qw ->
                qw.to.map { ass -> OneStepProp(qw.from, ass.toState, ass.toEvent, qw) }
            }

        configureTransition(transitions, stepProps)
    }

    private tailrec fun configureTransition(
        transitions: StateMachineTransitionConfigurer<FlowStatesContext, FlowEventContext>,
        stepProps: List<OneStepProp<FlowStatesContext, FlowEventContext>>
    ): StateMachineTransitionConfigurer<FlowStatesContext, FlowEventContext> {

        if (stepProps.isNotEmpty()) {
            val drop = stepProps.drop(1)

            val with = with(stepProps[0]) {
                val action1 = transitions.withExternal()
                    .source(source)
                    .target(target)
                    .event(event)
                    .action(action)
                if (drop.isNotEmpty())
                    action1.and()
                else transitions

            }
            return configureTransition(with, drop)
        } else return transitions
    }

//    @Bean
//    fun reservedAction(s: String): Action<FlowStatesContext, FlowEventContext> {
//
//        val value =
//            Action<FlowStatesContext, FlowEventContext> { context ->
//                LOGGER.info(s + " "+context.extendedState.toString())
//            }
//
//        return value
//    }

//    @Bean
//    fun persister(): StateMachinePersister<FlowStates, FlowEvent, String> {
//        return DefaultStateMachinePersister<FlowStates, FlowEvent, String>(PurchaseStateMachinePersister())
//    }

}