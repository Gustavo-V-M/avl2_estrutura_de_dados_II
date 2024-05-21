import java.util.List;

public class Parser {
    private List<Token> tokens;
    private Token currentToken;
    private int index;

     public Parser(List<Token> tokens){
        this.tokens = tokens;
        currentToken = null;
        index = -1;
    }

    public void run(){
        while(index < tokens.size()){
            if(currentToken.getType() == TokenType.PRINT){
                print();
            }
            else if(currentToken.getType() == TokenType.SUM){
                sum();
            }
            advance();
        }
    }

    private void advance() {
        index++;
        if (index < tokens.size()) {
            currentToken = tokens.get(index);
        }
    }
    
    private void print(){
        if(currentToken.getType() == TokenType.PRINT){
            index++;
            while (index < tokens.size() && tokens.get(index).getType() == TokenType.WHITESPACE) {
                index++;
            }
            if (index < tokens.size()) {
                String str = tokens.get(index).getValue(); 
                index++; 
                System.out.print(str); 
            }
        }
    }

    private void sum(){
        if(currentToken.getType() == TokenType.SUM) {
            index++;
            while (index < tokens.size() && tokens.get(index).getType() == TokenType.WHITESPACE) {
                index++;
            }
            if (index < tokens.size()) {
                int sum = getIntFromToken(); // Obtém o primeiro número a ser somado
                index++;

                while(index < tokens.size() && tokens.get(index).getType() == TokenType.WHITESPACE){
                    index++;
                    if(index < tokens.size() && tokens.get(index).getType() == TokenType.UINT){
                        sum += getIntFromToken();
                        index++;
                    }
                }

            System.out.print(sum);
            }
        }
    }

    private int getIntFromToken(){
        return Integer.parseInt(currentToken.getValue());
    }
}
