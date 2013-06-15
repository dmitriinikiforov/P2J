package p2j;

public class Variable extends Argument {
    String name;
    public Variable(String text) {
        name=text;
    }

    @Override
    public String toString() {
        return name;
    }
    
    @Override
    public String javaCode() {
        return "new Variable(\""+name+"\")";
}
    
}
