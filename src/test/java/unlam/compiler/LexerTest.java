package unlam.compiler;

import unlam.compiler.exceptions.CompilationException;
import unlam.compiler.exceptions.InvalidIntegerException;
import unlam.compiler.exceptions.InvalidLengthException;
import unlam.compiler.exceptions.UnknownCharacterException;
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
    public void comment() throws Exception{
        analyze("#+This is a comment+#");
        assertThat(nextToken()).isEqualTo(ParserSym.EOF);
    }

    @Test
    public void invalidStringConstantLength() {
        assertThrows(InvalidLengthException.class, () -> {
            analyze("\"%s\"".formatted(getRandomString()));
            nextToken();
        });
    }

    @Test
    public void invalidIdLength() {
        assertThrows(InvalidLengthException.class, () -> {
            analyze(getRandomString());
            nextToken();
        });
    }

    @Test
    public void invalidPositiveIntegerConstantValue() {
        assertThrows(InvalidIntegerException.class, () -> {
            analyze("%d".formatted(9223372036854775807L));
            nextToken();
        });
    }

    @Test
    public void invalidNegativeIntegerConstantValue() {
        assertThrows(InvalidIntegerException.class, () -> {
            analyze("%d".formatted(9223372036854775807L));
            nextToken();
        });
    }


    @Test
    public void assignmentWithExpressions() throws Exception {
        analyze("c=d*(e-21)/4");
        assertThat(nextToken()).isEqualTo(ParserSym.IDENTIFIER);
        assertThat(nextToken()).isEqualTo(ParserSym.ASSIG);
        assertThat(nextToken()).isEqualTo(ParserSym.IDENTIFIER);
        assertThat(nextToken()).isEqualTo(ParserSym.MULT);
        assertThat(nextToken()).isEqualTo(ParserSym.OPEN_BRACKET);
        assertThat(nextToken()).isEqualTo(ParserSym.IDENTIFIER);
        assertThat(nextToken()).isEqualTo(ParserSym.SUB);
        assertThat(nextToken()).isEqualTo(ParserSym.INTEGER_CONSTANT);
        assertThat(nextToken()).isEqualTo(ParserSym.CLOSE_BRACKET);
        assertThat(nextToken()).isEqualTo(ParserSym.DIV);
        assertThat(nextToken()).isEqualTo(ParserSym.INTEGER_CONSTANT);
        assertThat(nextToken()).isEqualTo(ParserSym.EOF);
    }

    @Test
    public void unknownCharacter() {
        assertThrows(UnknownCharacterException.class, () -> {
            analyze("#");
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
