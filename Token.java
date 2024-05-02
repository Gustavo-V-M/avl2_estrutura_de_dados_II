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

public class Token {
	
	private int id; 
	private TokenType type;
	private String value;

	public Token(TokenType type, String value){
		this.id = -1; // inicializado sem ID
		this.type = type; 
		this.value = value;
	}
	
	public Token(int id, TokenType type, String value) {
		this.id = id;
		this.type = type;
		this.value = value;
	}
	
	public TokenType getType() {
		return type;
	}
	
	public String getValue() {
		return value;
	}

	public int getId() {
		return id;
	}

	
	@Override
	public String toString() {
		return id + " -> " + type + ": " + value;
	}

}
