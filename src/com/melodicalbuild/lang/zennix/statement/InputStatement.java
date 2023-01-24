package com.melodicalbuild.lang.zennix.statement;

import com.melodicalbuild.lang.zennix.context.MemoryContext;
import com.melodicalbuild.lang.zennix.expression.value.LogicalValue;
import com.melodicalbuild.lang.zennix.expression.value.NumericValue;
import com.melodicalbuild.lang.zennix.expression.value.TextValue;
import com.melodicalbuild.lang.zennix.expression.value.Value;
import com.melodicalbuild.lang.zennix.token.TokenType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@RequiredArgsConstructor
@Getter
public class InputStatement implements Statement {
    private final String name;
    private final Supplier<String> consoleSupplier;

    @Override
    public void execute() {
        System.out.printf("enter \"%s\" >>> ", name.replace("_", " "));
        String line = consoleSupplier.get();

        Value<?> value;
        if (line.matches(TokenType.Numeric.getRegex())) {
            value = new NumericValue(Double.parseDouble(line));
        } else if (line.matches(TokenType.Logical.getRegex())) {
            value = new LogicalValue(Boolean.valueOf(line));
        } else {
            value = new TextValue(line);
        }

        MemoryContext.getScope().set(name, value);
    }
}
