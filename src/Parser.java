import java.util.List;

public class Parser {
    private List<Token> tokens;
    private Token currentToken;
    private int index;

    public Parser(){
        tokens = null;
        currentToken = null;
        index = -1;
    }
    //no do kishimoto tem uma função pra parse, que junta outras duas que são o advance e o code, mas n sei se precisa do advance
    //da pra só fazer index++ e se passar do tamanho do token vc da como falso, eu acho q isso funciona
    //tem a função code que meio que lê o codigo msm e vê oq é oq e printa dps
    //tem q terminar o sum, mas eu dps faço isso
    //e tem esse getInt... que da pra fazer tbm, basicamente é transformar o currentToken em int
    //só usar o parseInt q faz isso
    //e no do kishimoto tem RunTimeException em tudo tbm, da pra por isso



    //IMPORTANTE
    //acho q em vez de print tem q mudar pra setNode ou algo assim
    private void print(){
        if(currentToken.getType() == TokenType.PRINT){
            index++;
            while(currentToken.getType() == TokenType.WHITESPACE && index <= tokens.size()){
                index++;
            }
            String str = currentToken.getValue();
            index++;
            System.out.print(str);
        }
    }

    private void sum(){
        if(currentToken.getType() == TokenType.SUM) {
            index++;
            while (currentToken.getType() == TokenType.WHITESPACE && index <= tokens.size()) {
                index++;
            }
            int sum = getIntFromToken();
            index++;

            System.out.print(sum);
        }
        //falta coisa aqui se pa
    }

    private int getIntFromToken(){
        return Integer.parseInt(currentToken.getValue());
    }
}
