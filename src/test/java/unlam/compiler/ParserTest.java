package unlam.compiler;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java_cup.runtime.Symbol;
import org.apache.commons.io.IOUtils;
import unlam.compiler.services.LexicalSyntacticServiceImpl;
import unlam.compiler.services.interfaces.ILexicalSyntacticService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ParserTest {

    private ILexicalSyntacticService parserService;

    @BeforeEach
    void initialize() {
        parserService = new LexicalSyntacticServiceImpl();
    }

    @Test
    public void assignmentWithExpression() throws Exception {
        compilationSuccessful("init{c : Int d : Int e : Int} c=d*(e-21)/4");
    }

    @Test
    public void syntaxError() {
        compilationError("1234");
    }

    @Test
    void assignments() throws Exception {
        compilationSuccessful(readFromFile("assignments.txt"));
    }

    @Test
    void write() throws Exception {
        compilationSuccessful(readFromFile("write.txt"));
    }

    @Test
    void read() throws Exception {
        compilationSuccessful(readFromFile("read.txt"));
    }

    @Test
    void comment() throws Exception {
        compilationSuccessful(readFromFile("comment.txt"));
    }

    @Test
    void init() throws Exception {
        compilationSuccessful(readFromFile("init.txt"));
    }

    @Test
    void and() throws Exception {
        compilationSuccessful(readFromFile("and.txt"));
    }

    @Test
    void or() throws Exception {
        compilationSuccessful(readFromFile("or.txt"));
    }

    @Test
    void not() throws Exception {
        compilationSuccessful(readFromFile("not.txt"));
    }

    @Test
    void ifStatement() throws Exception {
        compilationSuccessful(readFromFile("if.txt"));
    }

    @Test
    void whileStatement() throws Exception {
        compilationSuccessful(readFromFile("while.txt"));
    }

    @Test
    void funcionReorder() throws Exception {
        compilationSuccessful(readFromFile("reorder.txt"));
    }

    /*@Test
    void funcionSliceAndConcat() throws Exception {
        compilationSuccessful(readFromFile("sliceAndConcat.txt"));
    }*/

    private void compilationSuccessful(String input) throws Exception {
        assertThat(analyze(input).sym).isEqualTo(ParserSym.EOF);
    }

    private void compilationError(String input){
        assertThrows(Exception.class, () -> analyze(input));
    }

    private Symbol analyze(String input) throws Exception {
        return parserService.createParser(input).parse();
    }

    private String readFromFile(String fileName) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        assertThat(inputStream).isNotNull();
        return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
    }
}