
package p2j;


public class ArgString extends Argument{
    String string;
    
    public ArgString(String string){
        this.string = string;
    }

    @Override
    public String toString() {
        return string; 
    }
    
    
}
