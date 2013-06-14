package p2j;

public class Number extends Argument {
    double value;
    Number(String decode) {
        String ints = "";
        int i=0;
        char c = decode.charAt(i);
        while ((decode.length() < i)&&((c!='e')||(c!='E')||(c!='.'))) {
            c = decode.charAt(i);
            ints = ints + c;
            i++;
        } 
        if (ints.isEmpty()) {
            value = 0;
        } else {
            value = Double.parseDouble(ints);
        }
        
        c = decode.charAt(i);
        if (c=='.') {
            String doubles = "";
            i++;
            while ((c!='e')||(c!='E')) {
                c = decode.charAt(i);
                doubles = doubles + c;
                i++;
            }
            if (!doubles.isEmpty()) {
                value = value + Double.parseDouble("0."+doubles);
            }
        }
        c = decode.charAt(i);
        if (c == 'x') {
            i++;
            i++;
        }
        
        
        if (decode.length() < i) {
            int power = Integer.parseInt(decode.substring(i, decode.length()));
            value = value * Math.pow(10, power);
        }
        //value=decode.intValue();
    }

    @Override
    public String toString() {
        return ""+value;
    }
    
}
