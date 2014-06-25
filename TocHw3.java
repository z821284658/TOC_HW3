/** Author: 許成家
 * 	Student ID: F74006365
 */
/** Program: 
 * This program is for parse data from the web find the average of all sale
 * prices matching the condition as the arguments(鄉鎮市區, Road_Name, Year).
 * 
 * Your input should be giving extra four argument "URl", 鄉鎮市區, Road_Name, Year.
 * 2014.6.25
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.InputStream;
import org.json.JSONException;
import org.json.JSONObject;

public class TocHw3 {

    public static void main(String[] args) throws JSONException, IOException, InterruptedException {
        getPageData(args[0], args[1], args[2], args[3]);
        
    }
    
public static String getPageData(String URL, String Block, String Road_name, String Year){
        int sum = 0;
        int num = 0;
        URL u = null;
        InputStream in = null;
        InputStreamReader r = null;
        BufferedReader br = null;
        StringBuffer message = null;
        try {
           u = new URL(URL);
           in = u.openStream();
           r = new InputStreamReader(in, "UTF-8");
           br = new BufferedReader(r);
           String tempstr = null;
           message = new StringBuffer();
           while ((tempstr = br.readLine()) != null) {
               //message.append(tempstr);
        	   if(tempstr.length() > 1) {
        		   JSONObject jsonObjectJackyFromString = new JSONObject(
        				   tempstr);
        		   if(jsonObjectJackyFromString.get("鄉鎮市區").toString().matches(Block)) {
        			   if(jsonObjectJackyFromString.get("土地區段位置或建物區門牌").toString().matches(".*" + Road_name + ".*")) {
        					   int afterConvert1 = Integer.parseInt(jsonObjectJackyFromString.get("交易年月").toString().substring(0, 3));
        					   int afterConvert2 = Integer.parseInt(Year);
                			   if(afterConvert2 <= afterConvert1) {
                				    int afterConvert3 = Integer.parseInt(jsonObjectJackyFromString.get("總價元").toString());
                				   	sum += afterConvert3;
                				   	num ++;
                			   }
        			   }
        		   } 
        		   
        	   }
        	   
           }
           System.out.println((int)sum/num);
        } catch (Exception e) {
           e.getStackTrace();
           System.out.println(e.getMessage());
        } finally {
           try {
              u = null;
              in.close();
              r.close();
              br.close();
           } catch (Exception e) {
           }

    }
    
        return message.toString();
    }
        
    
    
}
