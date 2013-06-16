package p2j;

public class Number extends Argument {
    double value;
    public Number (double val) {
        value=val;
    }
    Number(String decode) {
        System.out.println(decode);
        String ints = "";
        int i=0;
        char c = decode.charAt(i);
        while ((decode.length() > i)&&((c!='e')||(c!='E')||(c!='.'))) {
            c = decode.charAt(i);
            ints = ints + c;
            i++;
        } 
        if (ints.isEmpty()) {
            value = 0;
        } else {
            value = Double.parseDouble(ints);
        }
        if (i == decode.length()) return;
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
            i++;
        }
        
        
        if (decode.length() < i) {
            int power = Integer.parseInt(decode.substring(i, decode.length()));
            value = value * Math.pow(10, power);
        }
        System.out.println(value);
        //value=decode.intValue();
    }

    @Override
    public String toString() {
        return ""+value;
    }
    
    @Override
    public String javaCode() {
        return "new p2j.Number("+value+")";
}
    
}
