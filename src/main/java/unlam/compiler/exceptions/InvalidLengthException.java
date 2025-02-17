package unlam.compiler.exceptions;

import java.io.Serial;

public class InvalidLengthException extends CompilationException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidLengthException() {
        super("Exceeded allowed length.");
    }

    public InvalidLengthException(String message) {
        super(message);
    }
}