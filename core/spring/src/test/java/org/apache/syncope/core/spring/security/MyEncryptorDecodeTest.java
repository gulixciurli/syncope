package org.apache.syncope.core.spring.security;

import org.apache.syncope.common.lib.types.CipherAlgorithm;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Parameterized.class)
public class MyEncryptorDecodeTest {
    //private final Calendar calendar = Calendar.getInstance();

    //private static Encryptor encryptor;
    private static final Encryptor ENCRYPTOR = Encryptor.getInstance();

    private static final String PASSWORD_VALUE = "password";

    //private String password;

    private boolean expectedResult;
    private String encoded;
    //private String passwdToEncode;
    private CipherAlgorithm cipherAlgorithm;

    public MyEncryptorDecodeTest(boolean expectedResult, String encoded, CipherAlgorithm cipherAlgorithm){
        this.expectedResult = expectedResult;
        this.encoded = encoded;
        this.cipherAlgorithm = cipherAlgorithm;
    }


    public static String encryptPsswd(String value) {
        String encPassword = null;
        System.out.println("ciaoooooooo1");

        try {
            encPassword = ENCRYPTOR.encode(value, CipherAlgorithm.AES);
            System.out.println("ciaoooooooo2");

        } catch (Exception e) {
            System.out.println("ciaoooooooo3");

            e.printStackTrace();
            encPassword = null;
        }
        return encPassword;
    }

    @Parameterized.Parameters
    public static Collection<?> getParameter() {

        Calendar calendar = Calendar.getInstance();

        return Arrays.asList(new Object[][] {


                //output = stringa corretta
                // { true, "9Pav+xl+UyHt02H9ZBytiA==", CipherAlgorithm.AES},
                { true, encryptPsswd("password"), CipherAlgorithm.AES},
                { true, null, CipherAlgorithm.AES}, //se ho null, la funzione decode mi restituisce una decryption = null
                { false, "ciao", CipherAlgorithm.AES}, //javax.crypto.IllegalBlockSizeException: Input length must be multiple of 16 when decrypting with padded cipher
                { false, "ciakfodjfnclo==", CipherAlgorithm.AES}, //java.lang.IllegalArgumentException: Last unit does not have enough valid bits

                // { true, null, CipherAlgorithm.SHA256}, //per la coverage, fare dopo
                // { true, "9Pav+xl+UyHt02H9ZBytiA==", CipherAlgorithm.SHA256}, //per la coverage, fare dopo


                /*
                 //output = stringa corretta
                { true, "9Pav+xl+UyHt02H9ZBytiA==", CipherAlgorithm.AES},
                { true, null, CipherAlgorithm.AES}, //se ho null, la funzione decode mi restituisce una decryption = null
                { false, "ciao", CipherAlgorithm.AES}, //javax.crypto.IllegalBlockSizeException: Input length must be multiple of 16 when decrypting with padded cipher
                { false, "ciakfodjfnclo==", CipherAlgorithm.AES}, //java.lang.IllegalArgumentException: Last unit does not have enough valid bits
                { true, null, CipherAlgorithm.SHA256}, //se ho null, la funzione decode mi restituisce una decryption = null
                { true, "9Pav+xl+UyHt02H9ZBytiA==", CipherAlgorithm.SHA256},
                 */

                /*
                //INVECE DI PASSARE UNA STRINGA A CASO, POSSO PRIMA CRIPTARLA E POI DECRIPTARLA
                {true, "password", CipherAlgorithm.AES},
                {true, "ciao", CipherAlgorithm.AES},
                {true, "", CipherAlgorithm.AES},
                {true, null, CipherAlgorithm.AES}

                 */

                /*
                {true, encryptPsswd(MyEncryptorDecodeTest.passwd), CipherAlgorithm.AES},
                {true, "ciao", CipherAlgorithm.AES},
                {true, "", CipherAlgorithm.AES},
                {true, null, CipherAlgorithm.AES}

                  */

        });
    }

    /*
    //basta farlo una volta sola all'inizio
    @BeforeClass
    public static void configure() {
        MyEncryptorDecodeTest.encryptor = Encryptor.getInstance("123");
        MyEncryptorDecodeTest.passwd = "password";
    }

     */

    /*
    @Before
    public void before(){
        //this.cache = new ReadCache(UnpooledByteBufAllocator.DEFAULT, 10 * 1024);
        encoded = encryptor.encode(passwdToEncode,cipherAlgorithm);
    }

     */

    /*
    @AfterClass
    public static void tearDown(){
        MyEncryptorDecodeTest.encryptor = null;
    }

     */


    @Test
    public void formatDateTest(){
        boolean result;

        //System.out.println("encoded == " + encoded );
        try {
            String prova = ENCRYPTOR.decode(null,cipherAlgorithm);
            System.out.println(("prova ==" + prova));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        try {
            //String encPassword = encryptor.encode(passwdToEncode, cipherAlgorithm);
            if (encoded != null) {
                String decPassword = ENCRYPTOR.decode(encoded,cipherAlgorithm);
                System.out.println("encoded == " + encoded + "\tdecPassw == " + decPassword );
                result = decPassword.equals(PASSWORD_VALUE);
            }
            else {
                String decPassword = ENCRYPTOR.decode(encoded,cipherAlgorithm);
                System.out.println("ooooooooooooo");
                System.out.println("encoded == " + encoded + "\tdecPassw == " + decPassword );
                result = decPassword == null;
            }

            } catch (Exception e ) {
                e.printStackTrace();
                result = false;
            }

        Assert.assertEquals(expectedResult,result);


        /*
        try {
            String decPasswd = encryptor.decode(encoded, cipherAlgorithm);
            System.out.println(decPasswd);
        } catch (Exception e) {
            e.printStackTrace();
        }

         */
    }

}