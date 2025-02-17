package unlam.compiler.exceptions;

import java.io.Serial;

public class InvalidIntegerException extends CompilationException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidIntegerException() {
        super("Invalid numeric value encountered.");
    }

    public InvalidIntegerException(String message) {
        super(message);
    }
}
