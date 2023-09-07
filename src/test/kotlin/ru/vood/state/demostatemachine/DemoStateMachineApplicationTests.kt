package ru.vood.state.demostatemachine

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.statemachine.StateMachine
import org.springframework.statemachine.config.StateMachineConfigurerAdapter
import ru.vood.state.demostatemachine.config.FlowEvent
import ru.vood.state.demostatemachine.config.FlowEvent.*
import ru.vood.state.demostatemachine.config.FlowStates

@SpringBootTest
class DemoStateMachineApplicationTests {

    @Autowired
    lateinit var stateMachine: StateMachine<FlowStates, FlowEvent>

    @Test
    fun contextLoads() {

        stateMachine.sendEvent(STEP_5)
        stateMachine.sendEvent(STEP_6)
        stateMachine.sendEvent(STEP_7)
        stateMachine.sendEvent(STEP_8)
        stateMachine.sendEvent(STEP_9)

    }

}
