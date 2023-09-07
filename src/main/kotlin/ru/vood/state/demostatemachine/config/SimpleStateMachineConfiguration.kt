package ru.vood.state.demostatemachine.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.statemachine.StateContext
import org.springframework.statemachine.action.Action
import org.springframework.statemachine.config.EnableStateMachine
import org.springframework.statemachine.config.EnableStateMachineFactory
import org.springframework.statemachine.config.StateMachineConfigurerAdapter
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import ru.vood.state.demostatemachine.config.FlowStates.*


@Configuration
//@EnableStateMachineFactory
@EnableStateMachine
class SimpleStateMachineConfiguration : StateMachineConfigurerAdapter<FlowStates, FlowEvent>() {

    override fun configure(states: StateMachineStateConfigurer<FlowStates, FlowEvent>) {

        val begin = FlowStates.values().firstOrNull { it.isBeginState }!!
        val end = FlowStates.values().firstOrNull { it.isEndState }!!
        states
            .withStates()
            .initial(begin)
            .end(end)
            .states(
                HashSet(FlowStates.values().toList())
            )
    }

    override fun configure(config: StateMachineConfigurationConfigurer<FlowStates, FlowEvent>) {
        config
            .withConfiguration()
            .autoStartup(true)
            .listener( PurchaseStateMachineApplicationListener())

    }

    override fun configure(
        transitions: StateMachineTransitionConfigurer<FlowStates, FlowEvent>
    ) {
        transitions.withExternal()
            .source(STEP_4).target(STEP_5).event(FlowEvent.STEP_5)
//            .action {  reservedAction()}
            .and()
            .withExternal()
            .source(STEP_5).target(STEP_6).event(FlowEvent.STEP_6)
//            .action {  reservedAction()}
            .and()
            .withExternal()
            .source(STEP_6).target(STEP_7).event(FlowEvent.STEP_7)
//            .action {  reservedAction()}
            .and()
            .withExternal()
            .source(STEP_7).target(STEP_8).event(FlowEvent.STEP_8)
//            .action {  reservedAction()}
            .and()
            .withExternal()
            .source(STEP_8).target(STEP_9).event(FlowEvent.STEP_9)
    }

    @Bean
    fun reservedAction(): Action<FlowStates, FlowEvent> {

        val value =
            Action<FlowStates, FlowEvent> { context ->
                val message = context.message
                println(context.extendedState.toString())
            }

        return value
    }

//    @Bean
//    fun persister(): StateMachinePersister<FlowStates, FlowEvent, String> {
//        return DefaultStateMachinePersister<FlowStates, FlowEvent, String>(PurchaseStateMachinePersister())
//    }

}