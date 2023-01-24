package com.melodicalbuild.lang.zennix.expression;

import com.melodicalbuild.lang.zennix.context.ClassInstanceContext;
import com.melodicalbuild.lang.zennix.context.MemoryContext;
import com.melodicalbuild.lang.zennix.context.MemoryScope;
import com.melodicalbuild.lang.zennix.context.ReturnContext;
import com.melodicalbuild.lang.zennix.context.definition.ClassDefinition;
import com.melodicalbuild.lang.zennix.context.definition.DefinitionContext;
import com.melodicalbuild.lang.zennix.context.definition.DefinitionScope;
import com.melodicalbuild.lang.zennix.context.definition.FunctionDefinition;
import com.melodicalbuild.lang.zennix.expression.value.Value;
import com.melodicalbuild.lang.zennix.expression.value.ClassValue;
import com.melodicalbuild.lang.zennix.statement.FunctionStatement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Getter
public class FunctionExpression implements Expression {
    private final String name;
    private final List<Expression> argumentExpressions;

    @Override
    public Value<?> evaluate() {
        //initialize function arguments
        List<Value<?>> values = argumentExpressions.stream().map(Expression::evaluate).collect(Collectors.toList());
        return evaluate(values);
    }

    /**
     * Evaluate class's function
     *
     * @param classValue instance of class where the function is placed in
     */
    public Value<?> evaluate(ClassValue classValue) {
        //initialize function arguments
        List<Value<?>> values = argumentExpressions.stream().map(Expression::evaluate).collect(Collectors.toList());

        //get definition and memory scopes from class definition
        ClassDefinition classDefinition = classValue.getValue();
        DefinitionScope classDefinitionScope = classDefinition.getDefinitionScope();
        MemoryScope memoryScope = classValue.getMemoryScope();

        //set class's definition and memory scopes
        DefinitionContext.pushScope(classDefinitionScope);
        MemoryContext.pushScope(memoryScope);
        ClassInstanceContext.pushValue(classValue);

        try {
            //proceed function
            return evaluate(values);
        } finally {
            DefinitionContext.endScope();
            MemoryContext.endScope();
            ClassInstanceContext.popValue();
        }
    }

    private Value<?> evaluate(List<Value<?>> values) {
        //get function's definition and statement
        FunctionDefinition definition = DefinitionContext.getScope().getFunction(name);
        FunctionStatement statement = definition.getStatement();

        //set new memory scope
        MemoryContext.pushScope(MemoryContext.newScope());

        try {
            //initialize function arguments
            IntStream.range(0, definition.getArguments().size()).boxed()
                    .forEach(i -> MemoryContext.getScope()
                            .setLocal(definition.getArguments().get(i), values.size() > i ? values.get(i) : NullValue.NULL_INSTANCE));

            //execute function body
            statement.execute();

            //obtain function result
            return ReturnContext.getScope().getResult();
        } finally {
            // release function memory and return context
            MemoryContext.endScope();
            ReturnContext.reset();
        }
    }
}
