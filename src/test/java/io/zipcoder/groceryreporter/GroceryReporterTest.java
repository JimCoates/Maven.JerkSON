package io.zipcoder.groceryreporter;

import io.zipcoder.GroceryReporter;
import io.zipcoder.utils.FileReader;
import io.zipcoder.utils.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GroceryReporterTest {
    GroceryReporter reporter;
    ArrayList<Item> items;

   @Before
   public void setUp(){
       String fileName = "RawInput.JerkSON";
       reporter = new GroceryReporter(fileName);
       Item item1 = new Item("milk", 3.00, "food", "1/22/2222");
       Item item2 = new Item("bread", 2.00, "food", "1/22/2222");
       Item item3 = new Item("egg", 4.00, "food", "1/22/1111");
       Item item4 = new Item("milk", 1.00, "food", "1/22/2222");
       Item item5 = new Item("bread", 6.00, "food", "1/22/2222");
       Item item6 = new Item("egg", 7.00, "food", "1/22/1111");

       this.items = new ArrayList<>();
       items.add(item1);
       items.add(item2);
       items.add(item3);
       items.add(item4);
       items.add(item5);
       items.add(item6);
   }


    @Test
    public void getItemAndPriceCountTest() {
    }

    @Test
    public void getItemCountTest() {
    }

    @Test
    public void getPriceCountTest() {
    }

    @Test
    public void getArrayPricesTest() {
       ArrayList<Double> actual = reporter.getArrayPrices("milk", items);
        System.out.println(actual);
    }

    @Test
    public void countAnObjectUtilityTest() {
        //Given
        Double valueToCount = 2.0;
        Integer expected = 3;

        ArrayList<Double> list = new ArrayList<>();
        list.add(1.0);
        list.add(valueToCount);
        list.add(3.0);
        list.add(valueToCount);
        list.add(4.0);
        list.add(valueToCount);
        //When

        Integer actual = reporter.countAnObjectUtility(valueToCount,list);

        Assert.assertEquals(expected,actual);
    }
}