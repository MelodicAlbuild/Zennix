package com.melodicalbuild.lang.zennix;

import com.melodicalbuild.lang.zennix.context.MemoryContext;
import com.melodicalbuild.lang.zennix.context.definition.DefinitionContext;
import com.melodicalbuild.lang.zennix.statement.CompositeStatement;
import com.melodicalbuild.lang.zennix.token.Token;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ZennixLanguage {

    @SneakyThrows
    public void execute(Path path) {
        String source = Files.readString(path);
        LexicalParser lexicalParser = new LexicalParser(source);
        List<Token> tokens = lexicalParser.parse();

        DefinitionContext.pushScope(DefinitionContext.newScope());
        MemoryContext.pushScope(MemoryContext.newScope());
        try {
            CompositeStatement statement = new CompositeStatement();
            StatementParser.parse(tokens, statement);
            statement.execute();
        } finally {
            DefinitionContext.endScope();
            MemoryContext.endScope();
        }
    }

}