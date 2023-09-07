package ru.vood.state.demostatemachine.config.contextStateMachine

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.statemachine.config.EnableStateMachineFactory
import org.springframework.statemachine.config.StateMachineConfigurerAdapter
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.config.configurers.StateConfigurer
import ru.vood.state.demostatemachine.config.contextStateMachine.abstraction.AbstractAction
import ru.vood.state.demostatemachine.config.contextStateMachine.abstraction.Arrow
import ru.vood.state.demostatemachine.config.contextStateMachine.abstraction.Node


@Configuration
@EnableStateMachineFactory(name = ["SimpleStateMachineConfigurationContext"])
class SimpleStateMachineConfigurationContext(
    private val setActions: Set<AbstractAction<FlowStatesContext, FlowEventContext>>
) : StateMachineConfigurerAdapter<FlowStatesContext, FlowEventContext>() {
    val LOGGER = LoggerFactory.getLogger(this.javaClass)
    override fun configure(states: StateMachineStateConfigurer<FlowStatesContext, FlowEventContext>) {

        val begin = FlowStatesContext.values().filter { it.isBeginState }.first()

//        val begin = when (filter.size) {
//            0 -> error("Не настроено начальное состояние")
//            1 -> filter.first()
//            else -> error("настроено несколько начальных состояний ${filter}")
//        }

        val end = FlowStatesContext.values().filter { it.isEndState }.first()

//        val end = when (filter2.size) {
//            0 -> error("Не настроено начальное состояние")
//            1 -> filter2.first()
//            else -> error("настроено несколько начальных состояний ${filter2}")
//        }

        val actions = setActions.associateBy { it.from }
        val stateConfigurer: StateConfigurer<FlowStatesContext, FlowEventContext> = states
            .withStates()
            .initial(begin)
            .end(end)

        configureStates(stateConfigurer, actions)

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
            .listener(CommonStateMachineApplicationListenerContext<FlowStatesContext, FlowEventContext>())

    }

    data class OneStepProp<NODE: Node<NODE, ARROW>, ARROW: Arrow<ARROW, NODE>>(
        val source: NODE,
        val target: NODE,
        val event: ARROW,
        val action: AbstractAction<NODE, ARROW>
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