package ru.vood.state.demostatemachine.config.contextStateMachine

import org.springframework.context.annotation.Configuration
import org.springframework.statemachine.config.EnableStateMachineFactory
import ru.vood.state.demostatemachine.config.contextStateMachine.abstraction.AbstractAction
import ru.vood.state.demostatemachine.config.contextStateMachine.abstraction.AbstractStateMachineConfigurationContext
import ru.vood.state.demostatemachine.config.contextStateMachine.abstraction.ContextData


@Configuration
@EnableStateMachineFactory(name = ["SimpleStateMachineConfigurationContext"])
class SimpleStateMachineConfigurationContext(
    setActions: Set<AbstractAction<FlowStatesContext, FlowEventContext, *>>
) : AbstractStateMachineConfigurationContext<FlowStatesContext, FlowEventContext, ContextData<FlowStatesContext, FlowEventContext>>(
    setActions,
    FlowStatesContext.values().toSet()
)