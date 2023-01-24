package com.melodicalbuild.lang.zennix.statement.loop;

import com.melodicalbuild.lang.zennix.expression.Expression;
import com.melodicalbuild.lang.zennix.expression.value.Value;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WhileLoopStatement extends AbstractLoopStatement {
    private final Expression hasNext;

    @Override
    protected void init() {
    }

    @Override
    protected boolean hasNext() {
        Value<?> value = hasNext.evaluate();
        return value instanceof LogicalValue && ((LogicalValue) value).getValue();
    }

    @Override
    protected void preIncrement() {
    }

    @Override
    protected void postIncrement() {
    }
}
