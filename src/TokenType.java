//================================================================================
// GRAMÁTICA
// Observe que parte da gramática é processada/avaliada na classe Tokenizer e
// parte é processada/avaliada na classe Parser (<code>, <print> e <sum>).
//================================================================================
// <code>         ::= ((<print> | <sum>)* <blank_line>)*
// <print>        ::= ">" <whitespace>* <string>
// <sum>          ::= "+" <whitespace>* <uint> (<whitespace>+ <uint>)*
// <string>       ::= <char>+
// <char>         ::= <basic_latin> | <latin_1_supp> | <whitespace>
// <basic_latin>  ::= [\u0020-\u007F]  ; Unicode Basic Latin
// <latin_1_supp> ::= [\u00A0-\u00FF]  ; Unicode Latin-1 Supplement
// <uint>         ::= <digit>+
// <digit>        ::= [0-9]
// <blank_line>   ::= <whitespace>* <newline>
// <whitespace>   ::= " " | "\t"
// <newline>      ::= "\n" | "\r" | "\r\n"


public enum TokenType {
    PRINT,
    SUM,
    STRING,
    UINT,
    WHITESPACE,
    NEWLINE,
    EOF
}
