package com.melodicalbuild.lang.zennix.expression;

import com.melodicalbuild.lang.zennix.expression.value.Value;

public interface AssignExpression {
    void assign(Value<?> value);
}
