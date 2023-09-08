package ru.vood.state.demostatemachine

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.messaging.support.GenericMessage
import org.springframework.statemachine.StateMachine
import org.springframework.statemachine.config.StateMachineFactory
import reactor.core.publisher.Mono
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowEventContext
import ru.vood.state.demostatemachine.config.contextStateMachine.FlowStatesContext

@SpringBootTest
class DemoStateMachineApplicationContextTests {

    val LOGGER = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var stateMachineFactory: StateMachineFactory<FlowStatesContext, FlowEventContext>

    lateinit var stateMachine: StateMachine<FlowStatesContext, FlowEventContext>

    @BeforeEach
    fun asdkjh() {
        stateMachine = stateMachineFactory.stateMachine
    }

    @Test
    fun contextLoads1() {
//        LOGGER.info("BEGIN "+stateMachine)
        stateMachine.extendedState.variables.put("a","s")
        val startReactively = stateMachine.startReactively()
        startReactively.block()
//        stateMachine.start()
//        listOf(
//            FlowEventContext.STEP_4_to_5,
////            FlowEventContext.STEP_5_to_7,
//        )
//            .forEach {
//
//                stateMachine.sendEvent(Mono.fromCallable { GenericMessage(it) })
////                stateMachine.sendEvent(it)
//            }
//        Thread.sleep(10000)

        Assertions.assertEquals(FlowStatesContext.STEP_7, stateMachine.state.id)
        val message = stateMachine.transitions
//        LOGGER.info("END "+stateMachine)
    }

    @Test
    fun contextLoads2() {

        stateMachine.extendedState.variables.put("a","s")
        val startReactively = stateMachine.startReactively()
        startReactively.block()


//        LOGGER.info("BEGIN "+stateMachine)
//        listOf(
//            FlowEventContext.STEP_4_TO_6,
////            FlowEventContext.STEP_6_TO_7,
//        )
//            .forEach {
////                stateMachine.sendEvent(Mono.fromCallable { GenericMessage(it) })
//                stateMachine.sendEvent(it)
//            }
//        Thread.sleep(1000)
        Assertions.assertEquals(FlowStatesContext.STEP_7, stateMachine.state.id)
        val message = stateMachine.transitions
//        LOGGER.info("END "+stateMachine)

    }

}
