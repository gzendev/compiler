package unlam.compiler.exceptions;

import java.io.Serial;

public class UnrecognizedSymbolException extends CompilationException {

  @Serial
  private static final long serialVersionUID = 1L;

  public UnrecognizedSymbolException(String symbol) {
    super("Unrecognized symbol: « " + symbol + " »");
  }
}