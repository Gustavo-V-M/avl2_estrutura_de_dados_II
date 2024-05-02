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

package parser;

import java.util.List;
import java.util.regex.*;

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


public class Parser {

	private List<Token> initial_tokens;
	private List<Token> tokens;
	private int curId;

	public Parser() {
		tokens = null;
		curId = 0;
	}
	
	public void run(List<String> contents) {
		Tokenizer tokenizer = new Tokenizer();
		initial_tokens = tokenizer.tokenize(contents);

		// Descomente o código abaixo para ver a lista de tokens gerada pelo Tokenizer.
//		System.out.println("==================== TOKENS ====================");
//		for (var token : tokens) {
//			System.out.println(token);
//		}
//		System.out.println("==================== TOKENS ====================");
		
		parse();
	}

	private parse(){
		for (Token currToken: initial_tokens){
			// caso EOF
			if (currToken.getType() == TokenType.EOF){
				return;
			}
			// caso NEWLINE
			else if (currToken.getType() == TokenType.NEWLINE){
				continue;
			}

			// caso STRING
			else {
				String currString = currToken.getValue();

				// sera usado REGEX para verificar o padrao da gramatica

				// caso COMMENT
				Pattern comment = Pattern.compile("^#.*");
				Matcher commentMatcher = comment.matcher(currString);

				// Caso KEY
				Pattern key = Pattern.compile("[^\\s=]+=[^\\s=]+$");
				Matcher keyMatcher = key.matcher(currString);
				
				// Caso SCOPE
				Pattern scope = Pattern.compile("^[^\\s]+\\s*\\(\\n(?:.*\\n)*\\)$");
				Matcher scopeMatcher = scope.matcher(currString); // FIXME concertar regex para o scope

				// TODO fazer logica de criar a lista/arvore com os tokens
				if (commentMatcher){
					// Comentarios sao pulados
					continue;
				}
				else if(keyMatcher){
					// debug
					System.out.println(currString);
				}
				else if (scopeMatcher) {
					// debug
					System.out.println(currString);
				}

			}

		}
	}


}
