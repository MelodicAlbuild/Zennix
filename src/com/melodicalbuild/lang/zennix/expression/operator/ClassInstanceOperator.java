package com.melodicalbuild.lang.zennix.expression.operator;

import com.melodicalbuild.lang.zennix.expression.Expression;
import com.melodicalbuild.lang.zennix.expression.value.Value;

public class ClassInstanceOperator extends UnaryOperatorExpression {
    public ClassInstanceOperator(Expression value) {
        super(value);
    }

    @Override
    public Value<?> evaluate() {
        return getValue().evaluate(); // will return toString() value
    }
}