package unlam.compiler.services.interfaces;

import unlam.compiler.Lexer;

public interface ISymbolTableService {
    void generate(final String fileName, final Lexer lexer) throws Exception;
    void generateTable(final Lexer lexer) throws Exception;
}
