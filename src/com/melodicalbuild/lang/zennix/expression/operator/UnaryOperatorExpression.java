package com.melodicalbuild.lang.zennix.expression.operator;

import com.melodicalbuild.lang.zennix.expression.Expression;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class UnaryOperatorExpression implements OperatorExpression {
    private final Expression value;
}
