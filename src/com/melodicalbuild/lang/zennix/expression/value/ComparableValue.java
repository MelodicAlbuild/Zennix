package com.melodicalbuild.lang.zennix.expression.value;

public class ComparableValue<T extends Comparable<T>> extends Value<T> {
    public ComparableValue(T value) {
        super(value);
    }
}
