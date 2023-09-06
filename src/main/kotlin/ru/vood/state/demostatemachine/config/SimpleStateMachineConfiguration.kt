package ru.vood.state.demostatemachine.config

import org.springframework.context.annotation.Configuration
import org.springframework.statemachine.config.StateMachineConfigurerAdapter
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import ru.vood.state.demostatemachine.config.FlowStates.*
import java.util.*


@Configuration
class SimpleStateMachineConfiguration : StateMachineConfigurerAdapter<FlowStates, String>() {

    override fun configure(states: StateMachineStateConfigurer<FlowStates, String>) {

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

    override fun configure(
        transitions: StateMachineTransitionConfigurer<FlowStates, String>
    ) {
        transitions.withExternal()
            .source(STEP_4).target(STEP_5).event("E1").and()
            .withExternal()
            .source(STEP_5).target(STEP_6).event("E2").and()
            .withExternal()
            .source(STEP_6).target(STEP_7).event("end").and()
            .withExternal()
            .source(STEP_7).target(STEP_8).event("end").and()
            .withExternal()
            .source(STEP_8).target(STEP_9).event("end")
    }
}