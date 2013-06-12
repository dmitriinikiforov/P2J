package p2j;

public class Number extends Argument {
    int value;
    Number(Integer decode) {
        value=decode.intValue();
    }

    @Override
    public String toString() {
        return ""+value;
    }
    
}
