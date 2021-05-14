/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import domain.Security;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author juanp
 */
public class Utility {

    public static int random() {
        return 1 + (int) Math.floor(Math.random() * (50 - 40 + 1) + 40);
    }

    public static int random(int bound) {
        //return 1+random.nextInt(bound);
        return 1 + (int) Math.floor(Math.random() * bound);
    }

    public static String format(double value) {
        return new DecimalFormat("###,###,###,###.##")
                .format(value);
    }

    public static String $format(double value) {
        return new DecimalFormat("$###,###,###,###.##")
                .format(value);
    }

    public static String perFormat(double value) {
        //#,##0.00 '%'
        return new DecimalFormat("#,##0.00'%'")
                .format(value);
    }

    public static String dateFormat(Date birthday) {
        return new SimpleDateFormat("dd-MM-yyyy")
                .format(birthday);
    }

    public static String hhmmss(long start, long end) {
        long milisegundos = end - start;
        long hora = milisegundos / 3600000;
        long restohora = milisegundos % 3600000;
        long minuto = restohora / 60000;
        long restominuto = restohora % 60000;
        long segundo = restominuto / 1000;
        long restosegundo = restominuto % 1000;

        return hora + ":" + minuto + ":" + segundo + "." + restosegundo;
    }//tnHHMMSS

    public static boolean equals(Object a, Object b) {
        switch (instanceOf(a, b)) {
            case "integer":
                Integer x = (Integer) a;
                Integer y = (Integer) b;
                return x.equals(y);
            case "string":
                String s1 = (String) a;
                String s2 = (String) b;
                return s1.compareTo(s2) == 0;
            case "security":
                Security secu1 = (Security) a;
                Security secu2 = (Security) b;
                return secu1.getUser().compareTo(secu2.getUser()) == 0;
        }
        return false; // en cualquier otro caso 
    }

    private static String instanceOf(Object a, Object b) {
        if (a instanceof Integer && b instanceof Integer) {
            return "integer";
        }
        if (a instanceof String && b instanceof String) {
            return "string";
        }
        if (a instanceof Security && b instanceof Security) {
            return "security";
        }

        return "Unknown";
    }

    public static boolean lessT(Object a, Object b) {//MENOR
        switch (instanceOf(a, b)) {
            case "integer":
                Integer x = (Integer) a;
                Integer y = (Integer) b;
                return x < y;
            case "string":
                String s1 = (String) a;
                String s2 = (String) b;
                return s1.compareTo(s2) < 0;
        }
        return false; // en cualquier otro caso 
    }

    public static boolean greaterT(Object a, Object b) {//MAYOR
        switch (instanceOf(a, b)) {
            case "integer":
                Integer x = (Integer) a;
                Integer y = (Integer) b;
                return x > y;
            case "string":
                String s1 = (String) a;
                String s2 = (String) b;
                return s1.compareTo(s2) > 0;

        }
        return false; // en cualquier otro caso 
    }

}//end class
