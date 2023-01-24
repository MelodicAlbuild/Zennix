package com.melodicalbuild.lang.zennix.expression.operator;

import com.melodicalbuild.lang.zennix.exception.ExecutionException;
import com.melodicalbuild.lang.zennix.expression.Expression;
import com.melodicalbuild.lang.zennix.expression.value.ComparableValue;
import com.melodicalbuild.lang.zennix.expression.value.LogicalValue;
import com.melodicalbuild.lang.zennix.expression.value.Value;

import java.util.Objects;

import static com.melodicalbuild.lang.zennix.expression.value.NullValue.NULL_INSTANCE;

public class LessThanOrEqualToOperator extends BinaryOperatorExpression {
    public LessThanOrEqualToOperator(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Value<?> evaluate() {
        Value<?> left = getLeft().evaluate();
        Value<?> right = getRight().evaluate();
        boolean result;
        if (left == NULL_INSTANCE || right == NULL_INSTANCE) {
            throw new ExecutionException(
                    String.format("Unable to perform less than or equal to for NULL values `%s`, '%s'", left, right));
        } else if (Objects.equals(left.getClass(), right.getClass()) && left instanceof ComparableValue) {
            // noinspection unchecked,rawtypes
            result = ((Comparable) left.getValue()).compareTo(right.getValue()) <= 0;
        } else {
            result = left.toString().compareTo(right.toString()) <= 0;
        }
        return new LogicalValue(result);
    }
}