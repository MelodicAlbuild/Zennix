package com.melodicalbuild.lang.zennix.context;

import com.melodicalbuild.lang.zennix.expression.value.ClassValue;
import com.melodicalbuild.lang.zennix.expression.value.ThisValue;

import java.util.Stack;

/**
 * Holds class instances at the current point of execution
 *
 * @see ThisValue#getValue()
 * @see ThisValue#getValue()
 */
public class ClassInstanceContext {
    private static final Stack<ClassValue> values = new Stack<>();

    public static ClassValue getValue() {
        return values.peek();
    }

    public static void pushValue(ClassValue instance) {
        values.push(instance);
    }

    public static void popValue() {
        values.pop();
    }
}
