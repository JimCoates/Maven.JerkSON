package io.zipcoder;

import io.zipcoder.utils.FileReader;
import io.zipcoder.utils.Item;

import java.util.*;

public class GroceryReporter {
    private final String originalFileText;
    Map<String, Integer> mapOfOutput;
    ItemParser parser;
    List<Item> itemList;

    public GroceryReporter(String jerksonFileName) {
        this.originalFileText = FileReader.readFile(jerksonFileName);
        parser = new ItemParser();
        itemList = parser.parseItemList(originalFileText);
        this.mapOfOutput = new LinkedHashMap<>();
    }

    public void getItemAndPriceCount(String itemToCount, List<Item> items){
        getItemCount(itemToCount, items);
        getPriceCount(itemToCount, items);
    }


    public void getItemCount(String itemToCount, List<Item> items){
        Integer count = 0;
        for (Item item:items) {
            if(item.getName().equals(itemToCount)){
                count++;
            }
        }
        mapOfOutput.put(itemToCount,count);
    }

    public void getPriceCount(String itemToCount, List<Item> items){
        Double tempPrice = 0.0;
        ArrayList<Double> prices = getArrayPrices(itemToCount, items);
        for (Double price: prices) {
            mapOfOutput.put(String.valueOf(price), countAnObjectUtility(price, prices));
        }
    }

    public ArrayList<Double> getArrayPrices(String itemToGetPrices, List<Item> items){
        ArrayList<Double> prices = new ArrayList<>();
        for (Item item:items) {
            if(item.getName().equals(itemToGetPrices)){
                prices.add(item.getPrice());
            }
        }
        return prices;
    }

    public Integer countAnObjectUtility(Double amountToCount, ArrayList<Double> list){
        int counter = 0;
        for (Double amount: list) {
            if(amount == amountToCount){
                counter++;
            }
        }
        return counter;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        getItemAndPriceCount("milk", itemList);
        getItemAndPriceCount("bread", itemList);
        getItemAndPriceCount("cookies", itemList);
        getItemAndPriceCount("apples", itemList);

        for (Map.Entry<String, Integer> entry: mapOfOutput.entrySet()) {
            builder.append("name:"+"    "+entry.getKey() + "        " + "seen: " + entry.getValue() + " times" + "\n" +
                    "=============" + "        "+"=============" + "\n");
        }
        return builder.toString();
    }
}
