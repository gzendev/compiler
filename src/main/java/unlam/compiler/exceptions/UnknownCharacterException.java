package unlam.compiler.exceptions;

import java.io.Serial;

public class UnknownCharacterException extends CompilationException {

  @Serial
  private static final long serialVersionUID = 1L;

  public UnknownCharacterException(String symbol) {
    super("Unrecognized symbol: « " + symbol + " »");
  }
}