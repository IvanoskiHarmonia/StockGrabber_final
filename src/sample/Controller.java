package sample;

public class Controller {

    public String stringToHexInverted(String str){
        str = str.substring(1) + str.substring(0,1);
        str = str.substring(str.length()/2) + str.substring(0, str.length()/2);

        StringBuffer sb = new StringBuffer();
        //Converting string to character array
        char ch[] = str.toCharArray();
        for(int i = 0; i < ch.length; i++) {
            String hexString = Integer.toHexString(ch[i]);
            sb.append(hexString);
        }
        String result = sb.toString();

        return result.substring(result.length()/2) + result.substring(0, result.length()/2);
    }



    public String hexToStringReverted(String str) throws NumberFormatException{

        str = str.substring(str.length()/2) + str.substring(0, str.length()/2);
        String result = new String();
        char[] charArray = str.toCharArray();
        for(int i = 0; i < charArray.length; i=i+2) {
            String st = ""+charArray[i]+""+charArray[i+1];
            char ch = (char)Integer.parseInt(st, 16);
            result = result + ch;
        }
        result = result.substring(result.length()/2) + result.substring(0, result.length()/2);
        if(result.length() % 2 == 0)
            result = result.substring(result.length()-1) + result.substring(0, result.length()-1);

        return result;
    }

}
