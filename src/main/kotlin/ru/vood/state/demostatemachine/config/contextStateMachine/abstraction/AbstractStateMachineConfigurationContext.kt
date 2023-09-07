package ru.vood.state.demostatemachine.config.contextStateMachine.abstraction

import org.slf4j.LoggerFactory
import org.springframework.statemachine.config.StateMachineConfigurerAdapter
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.config.configurers.StateConfigurer


abstract class AbstractStateMachineConfigurationContext<
        NODE : Node<NODE, ARROW>,
        ARROW : Arrow<ARROW, NODE>,
        DATA : ContextData<NODE, ARROW>
        >(
    private val setActions: Set<AbstractAction<NODE, ARROW, DATA>>,
//    private val arrows: Arrow<ARROW, NODE>,
    private val nodes: Set<Node<NODE, ARROW>>
) : StateMachineConfigurerAdapter<NODE, ARROW>() {
    val LOGGER = LoggerFactory.getLogger(this.javaClass)
    override fun configure(states: StateMachineStateConfigurer<NODE, ARROW>) {


        val begin: NODE = nodes.first { it.isBeginState } as NODE

        val end = nodes.filter { it.isEndState }.first() as NODE

        val actions = setActions.associateBy { it.from }
        val stateConfigurer = states
            .withStates()
            .initial(begin)
            .end(end)

        configureStates(stateConfigurer, actions)

    }

    private tailrec fun configureStates(
        stateConfigurer: StateConfigurer<NODE, ARROW>,
        actions: Map<NODE, AbstractAction<NODE, ARROW, *>>
    ): StateConfigurer<NODE, ARROW> {
        return if (actions.isNotEmpty()) {
            val stepProps = actions.entries.toList()
            val drop = stepProps.drop(1)

            val with = with(stepProps[0]) {
                stateConfigurer
                    .state(this.key, this.value, null)

            }
            configureStates(with, drop.associate { it.key to it.value })
        } else stateConfigurer
    }

    override fun configure(config: StateMachineConfigurationConfigurer<NODE, ARROW>) {
        config
            .withConfiguration()
            .autoStartup(true)
            .listener(CommonStateMachineApplicationListenerContext<NODE, ARROW>())

    }


    override fun configure(
        transitions: StateMachineTransitionConfigurer<NODE, ARROW>
    ) {

        val stepProps = setActions
            .flatMap { qw ->
                qw.to.map { ass -> ArrowProperty(qw.from, ass.toState, ass.toEvent, qw) }
            }

        configureTransition(transitions, stepProps)
    }

    private tailrec fun configureTransition(
        transitions: StateMachineTransitionConfigurer<NODE, ARROW>,
        stepProps: List<ArrowProperty<NODE, ARROW>>
    ): StateMachineTransitionConfigurer<NODE, ARROW> {

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