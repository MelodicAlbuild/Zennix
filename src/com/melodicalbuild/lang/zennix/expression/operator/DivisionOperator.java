package com.melodicalbuild.lang.zennix.expression.operator;

import com.melodicalbuild.lang.zennix.exception.ExecutionException;
import com.melodicalbuild.lang.zennix.expression.Expression;
import com.melodicalbuild.lang.zennix.expression.value.NumericValue;
import com.melodicalbuild.lang.zennix.expression.value.Value;

public class DivisionOperator extends BinaryOperatorExpression {
    public DivisionOperator(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Value<?> evaluate() {
        Value<?> left = getLeft().evaluate();
        Value<?> right = getRight().evaluate();
        if (left instanceof NumericValue && right instanceof NumericValue) {
            return new NumericValue(((NumericValue) left).getValue() / ((NumericValue) right).getValue());
        } else {
            throw new ExecutionException(
                    String.format("Unable to divide non numeric values `%s` and `%s`", left, right));
        }
    }
}