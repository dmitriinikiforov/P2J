
package p2j;


public class ArgString extends Argument{
    String string;
    
    public ArgString(String string){
        String newString = string.substring(1, string.length()-1);
        this.string = newString;
    }

    @Override
    public String toString() {
        return string; 
    }
    
    
}
