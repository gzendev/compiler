package unlam.compiler.services.interfaces;

import unlam.compiler.Lexer;
import unlam.compiler.Parser;
import java.io.Reader;

public interface ILexicalSyntacticService {
    Lexer createLexer(Reader in);

    Lexer createLexer(String in);

    Parser createParser(Lexer lexer);

    Parser createParser(String in);

    Lexer parser(final String fileName) throws Exception;

}
