package com.melodicalbuild.lang.zennix.context;

public class BreakContext {
    private static BreakScope scope = new BreakScope();

    public static BreakScope getScope() {
        return scope;
    }

    public static void reset() {
        BreakContext.scope = new BreakScope();
    }
}
