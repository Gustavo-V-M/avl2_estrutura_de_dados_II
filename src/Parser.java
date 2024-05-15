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
}
