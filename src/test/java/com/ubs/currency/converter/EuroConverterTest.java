package com.ubs.currency.converter;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.stream.Collectors;

public class EuroConverterTest {

    private  static EuroConverter converter;

    @BeforeClass
    public static void setUpData()
    {
        String fileName = "src/test/resources/FILE.DAT";
        converter = new EuroConverter();
        converter.processTransactions(fileName);

        converter.getData().entrySet()
                .stream()
                .flatMap(l-> l.getValue().keySet().stream())
                .collect(Collectors.toSet())
                .forEach(System.out::println);
    }

    @Test
    public void testWhenCountryNotAvailable(){
        Assert.assertTrue("As country not available then LONDON city must be present ",
                converter.getData().containsKey("London"));
    }

    @Test
    public void testIgnoreCaseCreditRating(){
        Assert.assertEquals("As country UK has only single credit rating ",
                1, converter.getData().get("UK").size());
    }

    @Test
    public void testValidCreditRating(){
        Assert.assertFalse(" credit rating should contain valid ratings",
                converter.getData().entrySet()
                        .stream()
                        .flatMap(l-> l.getValue().keySet().stream())
                        .collect(Collectors.toSet())
                        .contains("-"));

    }
}
