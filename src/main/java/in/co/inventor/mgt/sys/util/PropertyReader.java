package in.co.inventor.mgt.sys.util;

import java.util.ResourceBundle;


public class PropertyReader
{

    private static ResourceBundle rb = ResourceBundle.getBundle("in.co.inventor.mgt.sys.bundle.system");

    /**
     * Return value of key
     *
     * @param key
     * @return
     */

    public static String getValue(String key) {

        String val = null;

        try {
            val = rb.getString(key);
        } catch (Exception e) {
            val = key;
        }

        return val;

    }

    /**
     * Gets String after placing parameters values
     *
     * @param key
     * @param param
     * @return String
     */
    public static String getValue(String key, String param) {
        String msg = getValue(key);
        msg = msg.replace("{0}", param);
        return msg;
    }

    /**
     * Gets String after placing parameters values
     *
     * @param key                     
     * @param params
     * @return
     */
    public static String getValue(String key, String[] params) {
        String msg = getValue(key);
        for (int i = 0; i < params.length; i++) {
            msg = msg.replace("{" + i + "}", params[i]);
        }
        return msg;
    }
   
    /**
     * Test method
     *
     * @param args
     */
   
    public static void main(String[] args) {
        String params =  "email" ;
        System.out.println(PropertyReader.getValue("requires", params));
    }
    
}
