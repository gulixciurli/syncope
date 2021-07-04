package org.apache.syncope.core.provisioning.api.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class MyFormatDateTest {
    //private final Calendar calendar = Calendar.getInstance();

    private boolean expectedResult;
    private Date date;
    private boolean lenient;
    private String conversionPattern;

    public MyFormatDateTest(boolean expectedResult, Date date, boolean lenient, String conversionPattern){
        this.expectedResult = expectedResult;
        this.date = date;
        this.lenient = lenient;
        this.conversionPattern = conversionPattern;
    }

    @Parameterized.Parameters
    public static Collection<?> getParameter() {

        Calendar calendar = Calendar.getInstance();

        return Arrays.asList(new Object[][] {

                //output = stringa corretta
                { true, calendar.getTime(), true, "dd/MM/yyyy"},
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
        //System.out.println(FormatUtils.format(date, lenient, conversionPattern));
        //System.out.println(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(date));

        //System.out.println(date.toInstant().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ISO_ZONED_DATE_TIME));

        //System.out.println(date.toInstant().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.));


        boolean result = true;
        /*
        try {
            System.out.println(FormatUtils.format(date, lenient, conversionPattern));
        } catch (Exception e ) {
            e.printStackTrace();
        }

         */

        String formatDate;


        if (conversionPattern == null) {
            System.out.println("sono quaaa");
            // se il pattern e' null, la funzione non fallisce ma ritorna 2021-07-03T16:04:21+0200
            formatDate = FormatUtils.format(date, lenient, conversionPattern);
            result = formatDate.equals(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(date));
            //Assert.assertEquals(formatDate, new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(date));
        }
        else {
            try {
                formatDate = FormatUtils.format(date, lenient, conversionPattern);
                result = formatDate.equals(new SimpleDateFormat(conversionPattern).format(date));
            }
            catch (Exception e) {
                e.printStackTrace();
                result = false;
            }

        }
        Assert.assertEquals(expectedResult, result);

    }

}