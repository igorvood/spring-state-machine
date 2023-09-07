package ru.vood.state.demostatemachine.config.contextStateMachine

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.statemachine.config.EnableStateMachineFactory
import org.springframework.statemachine.config.StateMachineConfigurerAdapter
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.config.configurers.StateConfigurer
import ru.vood.state.demostatemachine.config.contextStateMachine.actions.AbstractAction


@Configuration
@EnableStateMachineFactory(name = ["SimpleStateMachineConfigurationContext"])
class SimpleStateMachineConfigurationContext(
    private val setActions: Set<AbstractAction<FlowStatesContext, FlowEventContext>>
) : StateMachineConfigurerAdapter<FlowStatesContext, FlowEventContext>() {
    val LOGGER = LoggerFactory.getLogger(this.javaClass)
    override fun configure(states: StateMachineStateConfigurer<FlowStatesContext, FlowEventContext>) {

        val actions = setActions.map { it.from to it }.toMap()
        val begin = FlowStatesContext.values().firstOrNull { it.isBeginState }!!
        val end = FlowStatesContext.values().firstOrNull { it.isEndState }!!
        val stateConfigurer: StateConfigurer<FlowStatesContext, FlowEventContext> = states
            .withStates()
            .initial(begin)
            .end(end)

        val endasd2: StateConfigurer<FlowStatesContext, FlowEventContext> = configureStates(stateConfigurer, actions)

//        val end1 = stateConfigurer
//            .states(
//                HashSet(FlowStatesContext.values().toList())
//            )
    }

    private fun configureStates(
        stateConfigurer: StateConfigurer<FlowStatesContext, FlowEventContext>,
        actions: Map<FlowStatesContext, AbstractAction<FlowStatesContext, FlowEventContext>>
    ): StateConfigurer<FlowStatesContext, FlowEventContext> {
        if (actions.isNotEmpty()) {
            val stepProps = actions.entries.toList()
            val drop = stepProps.drop(1)

            val with = with(stepProps[0]) {
                stateConfigurer
                    .state(this.key, this.value, null)

            }
            return configureStates(with, drop.associate { it.key to it.value })
        } else return stateConfigurer
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
//                    .action(action)
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