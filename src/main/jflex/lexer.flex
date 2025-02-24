package unlam.compiler;

import java_cup.runtime.Symbol;
import unlam.compiler.ParserSym;
import unlam.compiler.exceptions.*;
import unlam.compiler.entities.SymbolTableEntry;
import java.util.ArrayList;
import java.util.List;
import static unlam.compiler.constants.Constants.*;

%%

%public
%class Lexer
%unicode
%cup
%line
%column
%throws CompilationException
%eofval{
  return symbol(ParserSym.EOF);
%eofval}

%{
  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }

  public List<SymbolTableEntry> symbolList = new ArrayList();

  private void addSymbolIfNotExists(SymbolTableEntry symbol){
    if (!symbolList.contains(symbol)) {
      symbolList.add(symbol);
    }
  }
%}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
Identation =  [ \t\f]

Comment = #+([^\r?\n])*?+#

Letter = [a-zA-Z]
Digit = [0-9]
DoubleQuote = "\""
Arroba = "@"
Percent = "%"
WhiteSpace = {LineTerminator} | {Identation}
Identifier = {Letter} ({Letter}|{Digit})*
IntegerConstant = {Digit}+
StringConstant = {DoubleQuote}({Letter}|{Digit}|{WhiteSpace}|{Arroba}|{Percent})+{DoubleQuote}
FloatConstants = {Digit}+[.]{Digit}+ | [.]{Digit}+ | {Digit}+[.]

Plus = "+"
Mult = "*"
Sub = "-"
Div = "/"
Assig = "="
OpenBracket = "("
CloseBracket = ")"
OpenCurly = "{"
CloseCurly = "}"
CorcheteAbre = "["
CorcheteCierra = "]"
Semicolon = ";"
Op_men = "<"
Op_may = ">"
Distinct  = "!="
Coma = ","
DosPuntos = ":"

%%

/* Operators */
<YYINITIAL> {
  {Plus}          { System.out.println("Token: " + yytext() + " | Tipo: PLUS"); return symbol(ParserSym.PLUS, yytext()); }
  {Sub}           { System.out.println("Token: " + yytext() + " | Tipo: SUB"); return symbol(ParserSym.SUB, yytext()); }
  {Mult}          { System.out.println("Token: " + yytext() + " | Tipo: MULT    "); return symbol(ParserSym.MULT, yytext()); }
  {Div}           { System.out.println("Token: " + yytext() + " | Tipo: DIV"); return symbol(ParserSym.DIV, yytext()); }
  {Assig}         { System.out.println("Token: " + yytext() + " | Tipo: ASSIG"); return symbol(ParserSym.ASSIG, yytext()); }
  {OpenBracket}   { System.out.println("Token: " + yytext() + " | Tipo: OPEN_BRACKET"); return symbol(ParserSym.OPEN_BRACKET, yytext()); }
  {CloseBracket}  { System.out.println("Token: " + yytext() + " | Tipo: CLOSE_BRACKET"); return symbol(ParserSym.CLOSE_BRACKET, yytext()); }
  {OpenCurly}     { System.out.println("Token: " + yytext() + " | Tipo: OPEN_CURLY"); return symbol(ParserSym.OPEN_CURLY, yytext()); }
  {CloseCurly}    { System.out.println("Token: " + yytext() + " | Tipo: CLOSE_CURLY"); return symbol(ParserSym.CLOSE_CURLY, yytext()); }
  {CorcheteAbre}  { System.out.println("Token: " + yytext() + " | Tipo: COR_A"); return symbol(ParserSym.COR_A, yytext()); }
  {CorcheteCierra} { System.out.println("Token: " + yytext() + " | Tipo: COR_C"); return symbol(ParserSym.COR_C, yytext()); }
  {Op_men}        { System.out.println("Token: " + yytext() + " | Tipo: OP_MEN"); return symbol(ParserSym.OP_MEN, yytext()); }
  {Op_may}        { System.out.println("Token: " + yytext() + " | Tipo: OP_MAY"); return symbol(ParserSym.OP_MAY, yytext()); }
  {Semicolon}     { System.out.println("Token: " + yytext() + " | Tipo: PYC"); return symbol(ParserSym.PYC, yytext()); }
  {Coma}          { System.out.println("Token: " + yytext() + " | Tipo: COMA"); return symbol(ParserSym.COMA, yytext()); }
  {DosPuntos}     { System.out.println("Token: " + yytext() + " | Tipo: DOS_PUNTOS"); return symbol(ParserSym.DOS_PUNTOS, yytext()); }
}

/* Keywords */
<YYINITIAL> "while"     { System.out.println("Token: " + yytext() + " | Tipo: WHILE"); return symbol(ParserSym.WHILE); }
<YYINITIAL> "if"        { System.out.println("Token: " + yytext() + " | Tipo: IF"); return symbol(ParserSym.IF); }
<YYINITIAL> "else"      { System.out.println("Token: " + yytext() + " | Tipo: ELSE"); return symbol(ParserSym.ELSE); }
<YYINITIAL> "init"      { System.out.println("Token: " + yytext() + " | Tipo: INIT"); return symbol(ParserSym.INIT); }
<YYINITIAL> "AND"       { System.out.println("Token: " + yytext() + " | Tipo: AND"); return symbol(ParserSym.AND); }
<YYINITIAL> "OR"        { System.out.println("Token: " + yytext() + " | Tipo: OR"); return symbol(ParserSym.OR); }
<YYINITIAL> "NOT"       { System.out.println("Token: " + yytext() + " | Tipo: NOT"); return symbol(ParserSym.NOT); }
<YYINITIAL> "DISTINCT"  { System.out.println("Token: " + yytext() + " | Tipo: DISTINCT"); return symbol(ParserSym.DISTINCT); }
<YYINITIAL> "Float"     { System.out.println("Token: " + yytext() + " | Tipo: FLOAT"); return symbol(ParserSym.FLOAT); }
<YYINITIAL> "Int"       { System.out.println("Token: " + yytext() + " | Tipo: INT"); return symbol(ParserSym.INT); }
<YYINITIAL> "String"    { System.out.println("Token: " + yytext() + " | Tipo: STRING"); return symbol(ParserSym.STRING); }
<YYINITIAL> "read"      { System.out.println("Token: " + yytext() + " | Tipo: READ"); return symbol(ParserSym.READ); }
<YYINITIAL> "write"     { System.out.println("Token: " + yytext() + " | Tipo: WRITE"); return symbol(ParserSym.WRITE); }
<YYINITIAL> "reorder"   { System.out.println("Token: " + yytext() + " | Tipo: REORDER"); return symbol(ParserSym.REORDER); }
<YYINITIAL> "sliceAndConcat" { System.out.println("Token: " + yytext() + " | Tipo: SLICEANDCONCAT"); return symbol(ParserSym.SLICEANDCONCAT); }

/* Identifiers */
<YYINITIAL> {
  {Identifier} {
    if (yytext().length() > 50) {
      throw new InvalidLengthException("Identifier too long: " + yytext());
    }
    System.out.println("Token: " + yytext() + " | Tipo: IDENTIFIER");
    addSymbolIfNotExists(new SymbolTableEntry("_".concat(yytext()), null, null, 0));
    return symbol(ParserSym.IDENTIFIER, yytext());
  }
}

/* Constants */
<YYINITIAL> {
  {IntegerConstant} {
    try {
      Integer.parseInt(yytext());
    } catch (NumberFormatException e) {
      throw new InvalidIntegerException("Invalid integer constant: " + yytext());
    }
    System.out.println("Token: " + yytext() + " | Tipo: INTEGER_CONSTANT");
    addSymbolIfNotExists(new SymbolTableEntry("_".concat(yytext()), "Int", yytext(), 0));
    return symbol(ParserSym.INTEGER_CONSTANT, yytext());
  }

  {FloatConstants} {
    System.out.println("Token: " + yytext() + " | Tipo: FLOAT_CONSTANT");
    addSymbolIfNotExists(new SymbolTableEntry("_".concat(yytext()), "Float", yytext(), 0));
    return symbol(ParserSym.FLOAT_CONSTANT, yytext());
  }

  {StringConstant} {
    if (yytext().length() > 50) {
      throw new InvalidLengthException("String constant too long: " + yytext());
    }
    System.out.println("Token: " + yytext() + " | Tipo: STRING_CONSTANT");
    addSymbolIfNotExists(new SymbolTableEntry("_".concat(yytext()), "String", yytext(), yytext().length()));
    return symbol(ParserSym.STRING_CONSTANT, yytext());
  }
}

/* Whitespace and Comments */
<YYINITIAL> {
  {WhiteSpace}    { /* ignore */ }
  {Comment}       { /* ignore */ }
}

/* Error fallback */
[^] { throw new UnknownCharacterException(yytext()); }