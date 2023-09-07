package ru.vood.state.demostatemachine.config.contextStateMachine

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.statemachine.config.EnableStateMachineFactory
import org.springframework.statemachine.config.StateMachineConfigurerAdapter
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.config.configurers.StateConfigurer
import ru.vood.state.demostatemachine.config.contextStateMachine.abstraction.*


@Configuration
@EnableStateMachineFactory(name = ["SimpleStateMachineConfigurationContext"])
class SimpleStateMachineConfigurationContext(
     setActions: Set<AbstractAction<FlowStatesContext, FlowEventContext, *>>
) : AbstractStateMachineConfigurationContext<FlowStatesContext, FlowEventContext, ContextData<FlowStatesContext,FlowEventContext >>(
    setActions,
    FlowStatesContext.values().toSet()
)