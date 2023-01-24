package com.melodicalbuild.lang.zennix.expression;

import com.melodicalbuild.lang.zennix.expression.value.Value;

public interface Expression {
    Value<?> evaluate();
}
