package com.melodicalbuild.lang.zennix.expression.operator;

import com.melodicalbuild.lang.zennix.exception.ExecutionException;
import com.melodicalbuild.lang.zennix.expression.ClassExpression;
import com.melodicalbuild.lang.zennix.expression.Expression;
import com.melodicalbuild.lang.zennix.expression.value.ThisValue;
import com.melodicalbuild.lang.zennix.expression.value.Value;
import com.melodicalbuild.lang.zennix.expression.value.ClassValue;

public class NestedClassInstanceOperator extends BinaryOperatorExpression {
    public NestedClassInstanceOperator(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Value<?> evaluate() {
        Value<?> left = getLeft().evaluate();

        // access class's property via this instance
        // this :: new NestedClass []
        if (left instanceof ThisValue) {
            left = ((ThisValue) left).getValue();
        }

        if (left instanceof ClassValue && getRight() instanceof ClassExpression) {
            // instantiate nested class
            // new Class [] :: new NestedClass []
            return ((ClassExpression) getRight()).evaluate((ClassValue) left);
        } else {
            throw new ExecutionException(String.format("Unable to access class's nested class `%s``", getRight()));
        }
    }
}
