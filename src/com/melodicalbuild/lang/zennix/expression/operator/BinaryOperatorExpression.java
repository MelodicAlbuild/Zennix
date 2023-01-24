package com.melodicalbuild.lang.zennix.expression.operator;

import com.melodicalbuild.lang.zennix.expression.Expression;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class BinaryOperatorExpression implements OperatorExpression {
    private final Expression left;
    private final Expression right;
}
