package com.melodicalbuild.lang.zennix.statement.loop;

import com.melodicalbuild.lang.zennix.context.NextContext;
import com.melodicalbuild.lang.zennix.statement.Statement;

public class NextStatement implements Statement {
    @Override
    public void execute() {
        NextContext.getScope().invoke();
    }
}
