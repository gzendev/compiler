package unlam.compiler.exceptions;

import java.io.Serial;

public class ExceededLengthException extends CompilationException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ExceededLengthException() {
        super("Exceeded allowed length.");
    }

    public ExceededLengthException(String message) {
        super(message);
    }
}