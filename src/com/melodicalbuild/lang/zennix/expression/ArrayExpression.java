package com.melodicalbuild.lang.zennix.expression;

import com.melodicalbuild.lang.zennix.expression.value.ArrayValue;
import com.melodicalbuild.lang.zennix.expression.value.Value;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class ArrayExpression implements Expression {
    private final List<Expression> values;

    @Override
    public Value<?> evaluate() {
        return new ArrayValue(this);
    }
}
