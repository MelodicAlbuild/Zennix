package com.melodicalbuild.lang.zennix.context;

import com.melodicalbuild.lang.zennix.expression.value.Value;
import lombok.Getter;

@Getter
public class ReturnScope {
    private boolean invoked;
    private Value<?> result;

    public void invoke(Value<?> result) {
        setInvoked(true);
        setResult(result);
    }

    private void setInvoked(boolean invoked) {
        this.invoked = invoked;
    }

    private void setResult(Value<?> result) {
        this.result = result;
    }
}
