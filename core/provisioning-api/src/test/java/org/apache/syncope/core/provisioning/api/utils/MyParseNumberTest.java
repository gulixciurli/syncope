package org.apache.syncope.core.provisioning.api.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(Parameterized.class)
public class MyParseNumberTest {
    //private final Calendar calendar = Calendar.getInstance();
    private static final Calendar calendar = Calendar.getInstance();

    private static final Date date = calendar.getTime();

    private boolean expectedResult;
    private String source;
    private String conversionPattern;

    public MyParseNumberTest(boolean expectedResult, String source, String conversionPattern){
        this.expectedResult = expectedResult;
        this.source = source;
        this.conversionPattern = conversionPattern;
    }

    @Parameterized.Parameters
    public static Collection<?> getParameter() {

        //Calendar calendar = Calendar.getInstance();

        return Arrays.asList(new Object[][] {


                //output = stringa corretta
                { true, String.valueOf(date.getTime()), "###,###"}, //ok


                { false, "", "###,###"}, //java.text.ParseException: Unparseable number: ""
                { false, "ciao", "###,###"}, //java.text.ParseException: Unparseable number: "ciao"
                { false, null, "###,###"}, //java.lang.NullPointerException
                //{ true, "12", "###,###"},   //ok

                { false, String.valueOf(date.getTime()), "invalidPattern"},  //java.text.ParseException: Unparseable number: "1625394586723"
                //{ true, "", "invalidPattern"}, //java.text.ParseException: Unparseable number: ""
                //{ true, "ciao", "invalidPattern"}, //java.text.ParseException: Unparseable number: "ciao"
                //{ true, null, "invalidPattern"}, //java.lang.NullPointerException --> posso toglierlo, tanto c'e' quello sopra (il 3) che e' uguale
                //{ true, "12", "invalidPattern"}, //java.text.ParseException: Unparseable number: "12"

                //danno tutte NullPointerException, quindi ne basta 1
                { false, String.valueOf(date.getTime()), null},
                //{ true, "", null},
                //{ true, "ciao", null},
                //{ true, null, null},
                //{ true, "12", null},




                /*
                { true, calendar.getTime(), false, "dd/MM/yyyy"},

                //IllegalArgumentException perche' il pattern non e' valido
                { false, calendar.getTime(), true, "invalidPattern"},
                { false, calendar.getTime(), false, "invalidPattern"},

                //output = stringa non parsata, quindi del tipo : 2021-07-03T14:58:31+0200 TODO: cercare un format date che mi stampi questa stringa intera
                //la funzione format non da errore se il pattern e' null, semplicemente non si effettua il format della data
                { true, calendar.getTime(), true, null},
                { true, calendar.getTime(), false, null},


                //NullPointerException a causa della data, quindi ne basta solo uno
                {false, null, true, "dd/MM/yyyy"},
                //{false, null, false, "dd/MM/yyyy"},

                //IllegalArgumentException --> pattern non valido
                {false, null, true, "invalidPattern"},
                {false, null, true, "invalidPattern"},

                //NullPointerException dovuto alla data, perche' pattern null va bene --> non servono questi parametri, perche' testo gia'
                // il caso in cui il pattern e' null
                //{true, null, true, null},
                //{true, null, true, null},

                 */
        });
    }

    @Before
    public void before(){
        //this.cache = new ReadCache(UnpooledByteBufAllocator.DEFAULT, 10 * 1024);
    }
    @After
    public void after(){
        //cache.close();
    }


    @Test
    public void formatDateTest(){
       //System.out.println("source == " + source);
        //System.out.println("date == " + date);
        /*
        try {
            FormatUtils.parseNumber(String.valueOf(date.getTime()),null);
        } catch (ParseException e) {
            e.printStackTrace();
        }

         */
        boolean result;

        try {
            Number number = FormatUtils.parseNumber(source,conversionPattern);
            result = number.equals(Long.valueOf(source));
            System.out.println("number == " + number);
            System.out.println("equals == " + Long.valueOf(source));
        } catch (Exception e) {
            //e.printStackTrace();
            result = false;
        }
        Assert.assertEquals(expectedResult,result);


    }

}