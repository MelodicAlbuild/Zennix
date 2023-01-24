package com.melodicalbuild.lang.zennix.expression.operator;

import com.melodicalbuild.lang.zennix.exception.ExecutionException;
import com.melodicalbuild.lang.zennix.expression.Expression;
import com.melodicalbuild.lang.zennix.expression.value.LogicalValue;
import com.melodicalbuild.lang.zennix.expression.value.Value;

public class NotOperator extends UnaryOperatorExpression {
    public NotOperator(Expression value) {
        super(value);
    }

    @Override
    public Value<?> evaluate() {
        Value<?> value = getValue().evaluate();
        if (value instanceof LogicalValue) {
            return new LogicalValue(!(((LogicalValue) value).getValue()));
        } else {
            throw new ExecutionException(String.format("Unable to perform NOT operator for non logical value `%s`", value));
        }
    }
}
