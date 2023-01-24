package com.melodicalbuild.lang.zennix.expression.operator;

import com.melodicalbuild.lang.zennix.exception.ExecutionException;
import com.melodicalbuild.lang.zennix.expression.AssignExpression;
import com.melodicalbuild.lang.zennix.expression.Expression;
import com.melodicalbuild.lang.zennix.expression.FunctionExpression;
import com.melodicalbuild.lang.zennix.expression.VariableExpression;
import com.melodicalbuild.lang.zennix.expression.value.ClassValue;
import com.melodicalbuild.lang.zennix.expression.value.ThisValue;
import com.melodicalbuild.lang.zennix.expression.value.Value;

public class ClassPropertyOperator extends BinaryOperatorExpression implements AssignExpression {
    public ClassPropertyOperator(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Value<?> evaluate() {
        Value<?> left = getLeft().evaluate();

        // access class's property via this instance
        // this :: class_argument
        if (left instanceof ThisValue) {
            left = ((ThisValue) left).getValue();
        }

        if (left instanceof ClassValue) {
            if (getRight() instanceof VariableExpression) {
                // access class's property
                // new Class [] :: class_property
                return ((ClassValue) left).getValue(((VariableExpression) getRight()).getName());
            } else if (getRight() instanceof FunctionExpression) {
                // execute class's function
                // new Class [] :: class_function []
                return ((FunctionExpression) getRight()).evaluate((ClassValue) left);
            }
        }

        throw new ExecutionException(String.format("Unable to access class's property `%s``", getRight()));
    }

    @Override
    public void assign(Value<?> value) {
        Value<?> left = getLeft().evaluate();

        // access class's property via this instance
        // this :: class_argument
        if (left instanceof ThisValue) {
            left = ((ThisValue) left).getValue();
        }

        if (left instanceof ClassValue && getRight() instanceof VariableExpression) {
            String propertyName = ((VariableExpression) getRight()).getName();
            ((ClassValue) left).setValue(propertyName, value);
        }
    }
}