package unlam.compiler.exceptions;

public abstract class CompilationException extends Exception {

    private static final long serialVersionUID = -3138875452688305726L;

    public CompilationException(String message) {
        super(message);
    }

    public CompilationException(Throwable cause) {
        super(cause);
    }

    public CompilationException(String message, Throwable cause) {
        super(message, cause);
    }
}
