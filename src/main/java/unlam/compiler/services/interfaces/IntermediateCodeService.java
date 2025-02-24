package unlam.compiler.services.interfaces;

import unlam.compiler.Lexer;
import unlam.compiler.Parser;

public interface IntermediateCodeService {
    void generate(final String fileName, final Parser parser) throws Exception;
}
