package com.melodicalbuild.lang.zennix.context.definition;

import com.melodicalbuild.lang.zennix.statement.FunctionStatement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FunctionDefinition implements Definition {
    @EqualsAndHashCode.Include
    private final String name;
    private final List<String> arguments;
    private final FunctionStatement statement;
    private final DefinitionScope definitionScope;
}
