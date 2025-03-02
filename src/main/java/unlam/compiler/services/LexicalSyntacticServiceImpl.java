package unlam.compiler.services;

import unlam.compiler.Lexer;
import unlam.compiler.Parser;
import unlam.compiler.constants.Constants;
import unlam.compiler.services.interfaces.ILexicalSyntacticService;
import org.springframework.stereotype.Service;
import java.io.*;

@Service
public class LexicalSyntacticServiceImpl implements ILexicalSyntacticService {

    private Lexer lexer;
    private Parser parser;

    public LexicalSyntacticServiceImpl() {}

    @Override
    public Lexer createLexer(java.io.Reader in) {
        return new Lexer(in);
    }

    @Override
    public Lexer createLexer(final String in) {
        return new Lexer(new StringReader(in));
    }

    @Override
    public Parser createParser(Lexer lexer) {
        return new Parser(lexer);
    }

    @Override
    public Parser createParser(final String in) {
        return new Parser(new Lexer(new StringReader(in)));
    }

    public Lexer getLexer() {
        return lexer;
    }

    public Parser getParser() {
        return parser;
    }

    @Override
    public void parser(final String in) throws Exception {
        try {
            Reader reader = new BufferedReader(new FileReader(in));
            this.lexer = new Lexer(reader);
            this.parser = new Parser(lexer);
            this.parser.parse();
        } catch (Exception e) {
            throw new Exception(Constants.MSG_PARSINNG_ERROR);
        }
    }
}
