package ru.vood.state.demostatemachine

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.statemachine.StateMachine
import org.springframework.statemachine.config.StateMachineFactory
import ru.vood.state.demostatemachine.config.FlowEvent.*
import ru.vood.state.demostatemachine.config.FlowStates
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowStatesContext

@SpringBootTest
class DemoStateMachineApplicationContextTests {

    @Autowired
    lateinit var stateMachineFactory: StateMachineFactory<FlowStatesContext, FlowEventContext>

    lateinit var stateMachine: StateMachine<FlowStatesContext, FlowEventContext>

    @BeforeEach
    fun asdkjh() {
        stateMachine = stateMachineFactory.stateMachine
    }

    @Test
    fun contextLoads1() {
        println(stateMachine)
        listOf(
            FlowEventContext.STEP_4_to_5,
            FlowEventContext.STEP_5_to_7,
        )
            .forEach {
//                stateMachine.sendEvent(Mono.fromCallable { GenericMessage(it) })
                stateMachine.sendEvent(it)
            }
        Assertions.assertEquals(FlowStatesContext.STEP_7, stateMachine.state.id)
        val message = stateMachine.transitions
        println(message)
    }

    @Test
    fun contextLoads2() {
        println(stateMachine)
        listOf(
            FlowEventContext.STEP_4_TO_6,
            FlowEventContext.STEP_6_TO_7,
        )
            .forEach {
//                stateMachine.sendEvent(Mono.fromCallable { GenericMessage(it) })
                stateMachine.sendEvent(it)
            }
        Assertions.assertEquals(FlowStatesContext.STEP_7, stateMachine.state.id)
        val message = stateMachine.transitions
        println(message)

    }

}
