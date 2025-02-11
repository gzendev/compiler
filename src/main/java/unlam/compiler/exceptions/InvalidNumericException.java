package unlam.compiler.exceptions;

import java.io.Serial;

public class InvalidNumericException extends CompilationException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidNumericException() {
        super("Invalid numeric value encountered.");
    }

    public InvalidNumericException(String message) {
        super(message);
    }
}
