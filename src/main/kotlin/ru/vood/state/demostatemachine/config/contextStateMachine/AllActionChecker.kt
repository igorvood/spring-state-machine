package ru.vood.state.demostatemachine.config.contextStateMachine

import org.springframework.stereotype.Service
import ru.vood.state.demostatemachine.config.contextStateMachine.abstraction.AbstractAction
import ru.vood.state.demostatemachine.config.contextStateMachine.abstraction.Checker

@Service
class AllActionChecker(
    protected val setActions: Set<AbstractAction<FlowStatesContext, FlowEventContext>>
) : Checker {
    override fun check() {

        val map = setActions.map { it.from }.toSet().plus(FlowStatesContext.values().filter { it.isEndState })
        val values = FlowStatesContext.values().toSet()
        assert( values.containsAll(map))
        assert( map.containsAll(values))

//        val equals = fromBeans.equals(fromEvents)
//        assert(equals)


    }
}