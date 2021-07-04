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
import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(Parameterized.class)
public class MyEncryptorVerifyTest {
    //private final Calendar calendar = Calendar.getInstance();

    //private static Encryptor encryptor;
    private static final Encryptor ENCRYPTOR = Encryptor.getInstance();

    private static final String PASSWORD_VALUE = "password";

    //private String password;

    private boolean expectedResult;
    private String password;
    //private String passwdToEncode;
    private CipherAlgorithm cipherAlgorithm;
    private String encoded;

    public MyEncryptorVerifyTest(boolean expectedResult, String password, CipherAlgorithm cipherAlgorithm, String encoded){
        this.expectedResult = expectedResult;
        this.password = password;
        this.cipherAlgorithm = cipherAlgorithm;
        this.encoded = encoded;
    }


    public static String encryptPsswd(String value,CipherAlgorithm algo ) {
        String encPassword = null;
        System.out.println("ciaoooooooo1");

        try {
            encPassword = ENCRYPTOR.encode(value, algo);
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

                //cambio encoded, ossia la criptatura della psswd
                {true, "password", CipherAlgorithm.AES, encryptPsswd("password", CipherAlgorithm.AES) },    //ENCRYPTED PW == 9Pav+xl+UyHt02H9ZBytiA==
                {false, "password", CipherAlgorithm.AES, encryptPsswd("passworddd", CipherAlgorithm.AES) }, //ENCRYPTED PW == tNHxIa2kL3b7Zbf1fRn23Q==
                //{false, "password", CipherAlgorithm.AES, encryptPsswd("", CipherAlgorithm.AES) }, //non serve. cìe' gia quello sopra
                {false, "password", CipherAlgorithm.AES, null}, //ENCRYPTED PW == null
                {false, "password", CipherAlgorithm.AES, encryptPsswd("password", CipherAlgorithm.SHA256) }, //ENCRYPTED PW == 5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8

                //cambio algoritmo di cifratura
                {true, "password", CipherAlgorithm.BCRYPT, encryptPsswd("password", CipherAlgorithm.BCRYPT) },    //ENCRYPTED PW == $2a$10$wu3QcFg1IaxLZzD6Tlasae2bC7mWFsY2V1HwxPkNK6jxj6VJgBu9u
                {true, "password", CipherAlgorithm.SHA256, encryptPsswd("password", CipherAlgorithm.SHA256) },    //ENCRYPTED PW == $2a$10$wu3QcFg1IaxLZzD6Tlasae2bC7mWFsY2V1HwxPkNK6jxj6VJgBu9u
                {true, "password",null, encryptPsswd("password", null) },    //ENCRYPTED PW == $2a$10$wu3QcFg1IaxLZzD6Tlasae2bC7mWFsY2V1HwxPkNK6jxj6VJgBu9u

                {false, "password_bad", CipherAlgorithm.AES, encryptPsswd("password", CipherAlgorithm.AES) },    //ENCRYPTED PW == 9Pav+xl+UyHt02H9ZBytiA==
                {false, null, CipherAlgorithm.AES, null },    //ENCRYPTED PW == 9Pav+xl+UyHt02H9ZBytiA==
                {false, null, null, null },    //ENCRYPTED PW == 9Pav+xl+UyHt02H9ZBytiA==
                {false, null, CipherAlgorithm.AES, encryptPsswd("password", CipherAlgorithm.AES) },    //ENCRYPTED PW == 9Pav+xl+UyHt02H9ZBytiA==

        });
    }




    @Test
    public void formatDateTest(){

        System.out.println(ENCRYPTOR.verify("password", cipherAlgorithm, "dkdk"));

        boolean result;
        System.out.println("ENCRYPTED PW == " + encoded);
        result = ENCRYPTOR.verify(password, cipherAlgorithm, encoded);
        Assert.assertEquals(expectedResult, result);

    }

}