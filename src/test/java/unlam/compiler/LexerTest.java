package unlam.compiler;

import unlam.compiler.exceptions.CompilationException;
import unlam.compiler.exceptions.ExceededLengthException;
import unlam.compiler.exceptions.InvalidNumericException;
import unlam.compiler.exceptions.UnrecognizedSymbolException;
import unlam.compiler.services.LexicalSyntacticServiceImpl;
import unlam.compiler.services.interfaces.ILexicalSyntacticService;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unlam.compiler.constants.Constants;

import java.io.IOException;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LexerTest {

    private ILexicalSyntacticService lexerService;
    private unlam.compiler.Lexer lexer;

    @BeforeEach
    void setUp() {
        lexerService = new LexicalSyntacticServiceImpl();
    }

    @Test
    public void shouldIgnoreComments() throws Exception {
        analyze("*- This is a comment -*");
        assertThat(nextToken()).isEqualTo(ParserSym.EOF);
    }

    @Test
    public void shouldHandleCommentWithCode() throws Exception {
        analyze("*- Variable declaration -*\n x := \"This is a string literal\";");
        assertThat(nextToken()).isEqualTo(ParserSym.ID);
        assertThat(nextToken()).isEqualTo(ParserSym.OP_ASIG);
        assertThat(nextToken()).isEqualTo(ParserSym.CONST_STR);
        assertThat(nextToken()).isEqualTo(ParserSym.PYC);
        assertThat(nextToken()).isEqualTo(ParserSym.EOF);
    }

    @Test
    public void shouldThrowExceptionForLongString() {
        assertThrows(ExceededLengthException.class, () -> {
            analyze("\"" + getRandomString() + "\"");
            nextToken();
        });
    }

    @Test
    public void shouldThrowExceptionForLongIdentifier() {
        assertThrows(ExceededLengthException.class, () -> {
            analyze(getRandomString());
            nextToken();
        });
    }

    @Test
    public void shouldThrowExceptionForOutOfRangeInteger() {
        assertThrows(InvalidNumericException.class, () -> {
            analyze("999999999999999999999");
            nextToken();
        });
    }

    @Test
    public void shouldRecognizeAssignmentWithExpression() throws Exception {
        analyze("x := y + (z - 10) / 2;");

        assertThat(nextToken()).isEqualTo(ParserSym.ID);
        assertThat(nextToken()).isEqualTo(ParserSym.OP_ASIG);
        assertThat(nextToken()).isEqualTo(ParserSym.ID);
        assertThat(nextToken()).isEqualTo(ParserSym.OP_MAS);
        assertThat(nextToken()).isEqualTo(ParserSym.PAR_A);
        assertThat(nextToken()).isEqualTo(ParserSym.ID);
        assertThat(nextToken()).isEqualTo(ParserSym.OP_RES);
        assertThat(nextToken()).isEqualTo(ParserSym.CONST_ENT);
        assertThat(nextToken()).isEqualTo(ParserSym.PAR_C);
        assertThat(nextToken()).isEqualTo(ParserSym.OP_DIV);
        assertThat(nextToken()).isEqualTo(ParserSym.CONST_ENT);
        assertThat(nextToken()).isEqualTo(ParserSym.PYC);
        assertThat(nextToken()).isEqualTo(ParserSym.EOF);
    }

    @Test
    public void shouldThrowExceptionForUnknownCharacter() {
        assertThrows(UnrecognizedSymbolException.class, () -> {
            analyze("@");
            nextToken();
        });
    }

    @AfterEach
    public void tearDown() {
        lexer = null;
    }

    private void analyze(String input) {
        lexer = lexerService.createLexer(input);
    }

    private int nextToken() throws IOException, CompilationException {
        return lexer.next_token().sym;
    }

    private static String getRandomString() {
        return new RandomStringGenerator.Builder()
                .filteredBy(CharacterPredicates.LETTERS)
                .withinRange('a', 'z')
                .build().generate(Constants.MAX_LENGTH * 2);
    }
}
