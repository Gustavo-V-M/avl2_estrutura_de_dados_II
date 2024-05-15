import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private int pos;
    private List<String> contents;
    public Tokenizer(){
        pos = 0;
    }

    private TokenType getType(String word){
        switch (word) {
            case "print" -> {
                return TokenType.PRINT;
            }
            case "+" -> {
                return TokenType.SUM;
            }
            case " ", "\t" -> {
                return TokenType.WHITESPACE;
            }
            case "\n" -> {
                return TokenType.NEWLINE;
            }
            case "" -> {
                return TokenType.EOF;
            }
        }
        if (word.matches("\\d+")){
          return TokenType.UINT;
        }
        else{
            return TokenType.STRING;
        }
    }
    private List<Token> getLineToken(String line) {
        List<Token> tokens = new ArrayList<>();
        String[] words = line.split("\\s+");
        for(int i = 0; i < words.length; i++){
            String Character = words[i];
            TokenType type = getType(Character);
            tokens.add(new Token(type, Character));
        }
        return tokens;
    }
    public List<Token> tokenize(List<String> contents){
        this.contents = contents;
        List<Token> token = new ArrayList<>();

        for(int i = 0; i < contents.size(); i++) {
            String line = contents.get(i);
            token.addAll(getLineToken(line));
        }
        return token; //acho q ta errado
    }
}
