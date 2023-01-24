package com.melodicalbuild.lang.zennix.context.definition;

import com.melodicalbuild.lang.zennix.statement.ClassStatement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ClassDefinition implements Definition {
    @EqualsAndHashCode.Include
    private final String name;
    private final List<String> arguments;
    private final ClassStatement statement;
    private final DefinitionScope definitionScope;
}
