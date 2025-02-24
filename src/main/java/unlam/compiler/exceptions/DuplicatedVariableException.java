package unlam.compiler.exceptions;

import java.io.Serial;

public class DuplicatedVariableException extends CompilationException {

    @Serial
    private static final long serialVersionUID = -8839023592847976068L;

    public DuplicatedVariableException(String varName) {
        super("The variable name [" + varName + "]" + " is already in use");
    }
}
