package sample;

import javafx.application.Platform;

import java.lang.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class StockFetcher {
    Content content = new Content();

    public String stock(String stockOrCrypto, String nameOfStockCrypto) throws Exception {

        String prices = "";


            URL url = new URL("https://robinhood.com/" + stockOrCrypto +"/" + nameOfStockCrypto);//https://docs.oracle.com/javase/7/docs/api/java/net/URL.html
            URLConnection urlConnection = url.openConnection();//https://docs.oracle.com/javase/8/docs/api/java/net/URLConnection.html
            //An InputStreamReader is a bridge from byte streams to character streams: It reads bytes and decodes them into characters using a specified charset .
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            if(content.isAlreadyCheckedURL() || connection.getResponseCode() == 200){
                InputStreamReader inStream = new InputStreamReader(urlConnection.getInputStream());//https://docs.oracle.com/javase/7/docs/api/java/io/InputStreamReader.html#:~:text=An%20InputStreamReader%20is%20a%20bridge,default%20charset%20may%20be%20accepted.
                BufferedReader buffer = new BufferedReader(inStream);//https://www.tutorialspoint.com/bufferedreader-class-in-java
                String line = buffer.readLine();
                while (line != null) {

                    if (line.contains("aria-label=\"$")) {
                        int target = line.indexOf("aria-label=\"$");
                        int deci = line.indexOf("\"", target);
                        int start = deci;
                        while (line.charAt(start) != '.') {
                            start--;
                        }//while not point
                        //The regex ("=\"\\$") is used because I am getting the html prices, so I found the logic where does the price stand in the html
                        String[] data = line.substring(start, deci+20).split("=\"\\$");
                        //What I wanted to do at first was that to but the 2 lines below in 1, but it didn't work, so I had to do that lol...
                        data[1] = data[1].replaceAll("[a-z]|[A-Z]|\"|-|\\s+|,|=", "");
                        prices = data[1];

                    }//if contains close
                    line = buffer.readLine();
                }//while(line != null) close

            }//if connection.getResponse close
            else{
                content.wrongStock.setText("WrongStock");
                return "incorrect stock/crypto";
            }


//        }catch (FileNotFoundException inputE) {
//            System.out.println("Mistake");
//            inputE.printStackTrace();
//            Platform.exit();
//            System.exit(0);
//        }

        return prices;
    }
}
