package com.melodicalbuild.lang.zennix.statement.loop;

import com.melodicalbuild.lang.zennix.context.BreakContext;
import com.melodicalbuild.lang.zennix.statement.Statement;

public class BreakStatement implements Statement {
    @Override
    public void execute() {
        BreakContext.getScope().invoke();
    }
}
