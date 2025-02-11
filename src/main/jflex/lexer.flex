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

Comment = \*-([^\r?\n])*?-\*
SingleLineComment = "//"{InputCharacter}*

Letter = [a-zA-Z]
Digit = [0-9]
DoubleQuote = "\""
Arroba = "@"
Percent = "%"
WhiteSpace = {LineTerminator} | {Identation}
Identifier = {Letter} ({Letter}|{Digit})*
IntegerConstant = [-]?{Digit}+
StringConstant = {DoubleQuote}({Letter}|{Digit}|{WhiteSpace}|{Arroba}|{Percent})+{DoubleQuote}
FloatConstants = {Digit}+[.]{Digit}+ | [.]{Digit}+ | {Digit}+[.]

Plus = "+"
Mult = "*"
Sub = "-"
Div = "/"
Assig = ":="
OpenBracket = "("
CloseBracket = ")"
OpenCurly = "{"
CloseCurly = "}"
CorcheteAbre = "["
CorcheteCierra = "]"
Semicolon = ";"
Op_men = "<"
Op_may = ">"
Coma = ","
DosPuntos = ":"

%%

/* Operators */
<YYINITIAL> {
  {Plus}          { System.out.println("Token: " + yytext() + " | Tipo: OP_MAS"); return symbol(ParserSym.OP_MAS, yytext()); }
  {Sub}           { System.out.println("Token: " + yytext() + " | Tipo: OP_RES"); return symbol(ParserSym.OP_RES, yytext()); }
  {Mult}          { System.out.println("Token: " + yytext() + " | Tipo: OP_MULT"); return symbol(ParserSym.OP_MULT, yytext()); }
  {Div}           { System.out.println("Token: " + yytext() + " | Tipo: OP_DIV"); return symbol(ParserSym.OP_DIV, yytext()); }
  {Assig}         { System.out.println("Token: " + yytext() + " | Tipo: OP_ASIG"); return symbol(ParserSym.OP_ASIG, yytext()); }
  {OpenBracket}   { System.out.println("Token: " + yytext() + " | Tipo: PAR_A"); return symbol(ParserSym.PAR_A, yytext()); }
  {CloseBracket}  { System.out.println("Token: " + yytext() + " | Tipo: PAR_C"); return symbol(ParserSym.PAR_C, yytext()); }
  {OpenCurly}     { System.out.println("Token: " + yytext() + " | Tipo: LLAVE_A"); return symbol(ParserSym.LLAVE_A, yytext()); }
  {CloseCurly}    { System.out.println("Token: " + yytext() + " | Tipo: LLAVE_C"); return symbol(ParserSym.LLAVE_C, yytext()); }
  {CorcheteAbre}  { System.out.println("Token: " + yytext() + " | Tipo: COR_A"); return symbol(ParserSym.COR_A, yytext()); }
  {CorcheteCierra} { System.out.println("Token: " + yytext() + " | Tipo: COR_C"); return symbol(ParserSym.COR_C, yytext()); }
  {Op_men}        { System.out.println("Token: " + yytext() + " | Tipo: OP_MEN"); return symbol(ParserSym.OP_MEN, yytext()); }
  {Op_may}        { System.out.println("Token: " + yytext() + " | Tipo: OP_MAY"); return symbol(ParserSym.OP_MAY, yytext()); }
  {Semicolon}     { System.out.println("Token: " + yytext() + " | Tipo: PYC"); return symbol(ParserSym.PYC, yytext()); }
  {Coma}          { System.out.println("Token: " + yytext() + " | Tipo: COMA"); return symbol(ParserSym.COMA, yytext()); }
  {DosPuntos}     { System.out.println("Token: " + yytext() + " | Tipo: DOS_PUNTOS"); return symbol(ParserSym.DOS_PUNTOS, yytext()); }
}

/* Keywords */
<YYINITIAL> "mientras"  { System.out.println("Token: " + yytext() + " | Tipo: MIENTRAS"); return symbol(ParserSym.MIENTRAS); }
<YYINITIAL> "si"        { System.out.println("Token: " + yytext() + " | Tipo: SI"); return symbol(ParserSym.SI); }
<YYINITIAL> "sino"      { System.out.println("Token: " + yytext() + " | Tipo: SINO"); return symbol(ParserSym.SINO); }
<YYINITIAL> "init"      { System.out.println("Token: " + yytext() + " | Tipo: INIT"); return symbol(ParserSym.INIT); }
<YYINITIAL> "AND"       { System.out.println("Token: " + yytext() + " | Tipo: AND"); return symbol(ParserSym.AND); }
<YYINITIAL> "OR"        { System.out.println("Token: " + yytext() + " | Tipo: OR"); return symbol(ParserSym.OR); }
<YYINITIAL> "NOT"       { System.out.println("Token: " + yytext() + " | Tipo: NOT"); return symbol(ParserSym.NOT); }
<YYINITIAL> "Float"     { System.out.println("Token: " + yytext() + " | Tipo: FLOAT"); return symbol(ParserSym.FLOAT); }
<YYINITIAL> "Int"       { System.out.println("Token: " + yytext() + " | Tipo: INT"); return symbol(ParserSym.INT); }
<YYINITIAL> "String"    { System.out.println("Token: " + yytext() + " | Tipo: STRING"); return symbol(ParserSym.STRING); }
<YYINITIAL> "leer"      { System.out.println("Token: " + yytext() + " | Tipo: READ"); return symbol(ParserSym.READ); }
<YYINITIAL> "escribir"  { System.out.println("Token: " + yytext() + " | Tipo: WRITE"); return symbol(ParserSym.WRITE); }

/* Identifiers */
<YYINITIAL> {
  {Identifier} {
    if (yytext().length() > 50) {
      throw new ExceededLengthException("Identifier too long: " + yytext());
    }
    System.out.println("Token: " + yytext() + " | Tipo: ID");
    addSymbolIfNotExists(new SymbolTableEntry("_".concat(yytext()), null, null, 0));
    return symbol(ParserSym.ID, yytext());
  }
}

/* Constants */
<YYINITIAL> {
  {IntegerConstant} {
    try {
      Integer.parseInt(yytext());
    } catch (NumberFormatException e) {
      throw new InvalidNumericException("Invalid integer constant: " + yytext());
    }
    System.out.println("Token: " + yytext() + " | Tipo: CONST_ENT");
    addSymbolIfNotExists(new SymbolTableEntry("_".concat(yytext()), "Int", yytext(), 0));
    return symbol(ParserSym.CONST_ENT, yytext());
  }

  {FloatConstants} {
    System.out.println("Token: " + yytext() + " | Tipo: CONST_FLT");
    addSymbolIfNotExists(new SymbolTableEntry("_".concat(yytext()), "Float", yytext(), 0));
    return symbol(ParserSym.CONST_FLT, yytext());
  }

  {StringConstant} {
    if (yytext().length() > 50) {
      throw new ExceededLengthException("String constant too long: " + yytext());
    }
    System.out.println("Token: " + yytext() + " | Tipo: CONST_STR");
    addSymbolIfNotExists(new SymbolTableEntry("_".concat(yytext()), "String", yytext(), yytext().length()));
    return symbol(ParserSym.CONST_STR, yytext());
  }
}

/* Whitespace and Comments */
<YYINITIAL> {
  {WhiteSpace}    { /* ignore */ }
  {Comment}       { /* ignore */ }
  {SingleLineComment} { /* ignore */ }
}

/* Error fallback */
[^] { throw new UnrecognizedSymbolException(yytext()); }