package com.melodicalbuild.lang.zennix.expression;

import com.melodicalbuild.lang.zennix.context.ClassInstanceContext;
import com.melodicalbuild.lang.zennix.context.MemoryContext;
import com.melodicalbuild.lang.zennix.context.MemoryScope;
import com.melodicalbuild.lang.zennix.context.definition.ClassDefinition;
import com.melodicalbuild.lang.zennix.context.definition.DefinitionContext;
import com.melodicalbuild.lang.zennix.expression.value.NullValue;
import com.melodicalbuild.lang.zennix.expression.value.Value;
import com.melodicalbuild.lang.zennix.expression.value.ClassValue;
import com.melodicalbuild.lang.zennix.statement.ClassStatement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Getter
public class ClassExpression implements Expression {
    private final String name;
    private final List<Expression> argumentExpressions;

    @Override
    public Value<?> evaluate() {
        //initialize class arguments
        List<Value<?>> values = argumentExpressions.stream().map(Expression::evaluate).collect(Collectors.toList());
        return evaluate(values);
    }

    /**
     * Evaluate nested class
     *
     * @param classValue instance of the parent class
     */
    public Value<?> evaluate(ClassValue classValue) {
        //initialize class arguments
        List<Value<?>> values = argumentExpressions.stream().map(Expression::evaluate).collect(Collectors.toList());

        //set parent class's definition
        ClassDefinition classDefinition = classValue.getValue();
        DefinitionContext.pushScope(classDefinition.getDefinitionScope());

        try {
            return evaluate(values);
        } finally {
            DefinitionContext.endScope();
        }
    }

    private Value<?> evaluate(List<Value<?>> values) {
        //get class's definition and statement
        ClassDefinition definition = DefinitionContext.getScope().getClass(name);
        ClassStatement classStatement = definition.getStatement();

        //set separate scope
        MemoryScope classScope = new MemoryScope(null);
        MemoryContext.pushScope(classScope);

        try {
            //initialize constructor arguments
            ClassValue classValue = new ClassValue(definition, classScope);
            ClassInstanceContext.pushValue(classValue);
            IntStream.range(0, definition.getArguments().size()).boxed()
                    .forEach(i -> MemoryContext.getScope()
                            .setLocal(definition.getArguments().get(i), values.size() > i ? values.get(i) : NullValue.NULL_INSTANCE));

            //execute function body
            DefinitionContext.pushScope(definition.getDefinitionScope());
            try {
                classStatement.execute();
            } finally {
                DefinitionContext.endScope();
            }

            return classValue;
        } finally {
            MemoryContext.endScope();
            ClassInstanceContext.popValue();
        }
    }
}
