package unlam.compiler;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import unlam.compiler.services.LexicalSyntacticServiceImpl;
import unlam.compiler.services.interfaces.ILexicalSyntacticService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParserTest {

    private ILexicalSyntacticService parserService;

    @BeforeEach
    void initialize() {
        parserService = new LexicalSyntacticServiceImpl();
    }

    @Test
    void validArithmeticExpression() throws Exception {
        Parser parser = createParser("x := (a + 10) * 2 / y - 5");
        assertSuccessfulParsing(parser);
    }

    @Test
    void invalidNumericLiteral() {
        Parser parser = createParser("007invalidNumber");
        assertParsingError(parser);
    }

    private void assertSuccessfulParsing(Parser parser) throws Exception {
        assertThat(parser.parse().sym).isEqualTo(ParserSym.EOF);
    }

    private void assertParsingError(Parser parser) {
        assertThrows(Exception.class, () -> parser.parse());
    }

    private Parser createParser(String input) {
        return parserService.createParser(input);
    }
}