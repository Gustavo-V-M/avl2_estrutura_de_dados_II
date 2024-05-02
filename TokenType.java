//
// Exemplo de tokenizer (lexer) e parser.
// Copyright (C) 2024 André Kishimoto
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <https://www.gnu.org/licenses/>.
//

// GRAMATICA
// <data>         ::= ((<scope> | <key> | <comment>)* <blank_line>)*
// <scope>        ::= <identifier> <blank_line>* "(" <blank_line>+ <data>* <blank> ")"
// <key>          ::= <identifier> "=" <value>
// <identifier>   ::= <string>
// <value>        ::= <string>
// <comment>      ::= "#" <string>

// <string>       ::= <char>+
// <char>         ::= <basic_latin> | <latin_1_supp> | <whitespace>
// <basic_latin>  ::= [\u0020-\u007F]  ; Unicode Basic Latin
// <latin_1_supp> ::= [\u00A0-\u00FF]  ; Unicode Latin-1 Supplement

// <blank_line>   ::= <blank> <newline>
// <blank>        ::= <whitespace>*
// <whitespace>   ::= " " | "\t"
// <newline>      ::= "\n" | "\r" | "\r\n"

package parser;

// Possíveis tokens reconhecidos pela classe Tokenizer.
public enum TokenType {

	STRING,
	DATA,
	SCOPE,
	KEY,
	IDENTIFIER,
	VALUE, 
	WHITESPACE, 
	NEWLINE, 
	EOF


}
