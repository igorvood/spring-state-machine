package ru.vood.state.demostatemachine

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.messaging.Message
import org.springframework.messaging.support.GenericMessage
import org.springframework.statemachine.StateMachine
import org.springframework.statemachine.config.StateMachineConfigurerAdapter
import reactor.core.publisher.Mono
import ru.vood.state.demostatemachine.config.FlowEvent
import ru.vood.state.demostatemachine.config.FlowEvent.*
import ru.vood.state.demostatemachine.config.FlowStates

@SpringBootTest
class DemoStateMachineApplicationTests {

    @Autowired
    lateinit var stateMachine: StateMachine<FlowStates, FlowEvent>

    @Test
    fun contextLoads1() {

        listOf(
            STEP_5,
            STEP_6,
            STEP_7,
            STEP_8,
            STEP_9)
            .forEach {
//                stateMachine.sendEvent(Mono.fromCallable { GenericMessage(it) })
                stateMachine.sendEvent(it)
            }
        Assertions.assertEquals(FlowStates.STEP_9, stateMachine.state.id)

    }

    @Test
    fun contextLoads2() {
        val initialState = stateMachine.initialState
        listOf(
            STEP_TO_9,
)
            .forEach {
//                stateMachine.sendEvent(Mono.fromCallable { GenericMessage(it) })
                stateMachine.sendEvent(it)
            }
        Assertions.assertEquals(FlowStates.STEP_9, stateMachine.state.id)

    }

}
