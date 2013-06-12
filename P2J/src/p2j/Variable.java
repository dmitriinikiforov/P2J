package p2j;

class Variable extends Argument {
    String name;
    Variable(String text) {
        name=text;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
