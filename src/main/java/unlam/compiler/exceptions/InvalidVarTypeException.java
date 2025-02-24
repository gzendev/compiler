package unlam.compiler.exceptions;

import java.io.Serial;

public class InvalidVarTypeException extends CompilationException {

    @Serial
    private static final long serialVersionUID = -8839023592847976068L;

    public InvalidVarTypeException(String var) {
        super("Invalid type for variable [" + var + "]");
    }
}
