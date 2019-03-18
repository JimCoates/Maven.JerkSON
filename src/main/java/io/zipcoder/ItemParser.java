package io.zipcoder;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {

    public List<Item> parseItemList(String valueToParse) {
        List<Item> itemList = new ArrayList<>();
        String[] tempArray = valueToParse.split("#");
        for (String string : tempArray) {
            try {
                itemList.add(parseSingleItem(string + "##"));

            } catch (ItemParseException e) {
            }
        }
        return itemList;
    }

    public Item parseSingleItem(String singleItem) throws ItemParseException {
        Item itemToReturn = new Item(parseName(singleItem), parsePrice(singleItem), parseType(singleItem), parseDate(singleItem));
        return itemToReturn;
    }

    public String parseName(String singleItem) throws ItemParseException {
        Pattern pattern = Pattern.compile(".*name([:|@|^|*|%])(\\w+)", Pattern.CASE_INSENSITIVE);
        return getString(singleItem, pattern);
    }

    public Double parsePrice(String singleItem) throws ItemParseException {
        Double d = 0.0;
        Pattern pattern = Pattern.compile(".*price([:|@|^|*|%])(\\d{1,3}\\.\\d{1,2})", Pattern.CASE_INSENSITIVE);
        Matcher m = pattern.matcher(singleItem);
        if (m.find()) {
            d = Double.parseDouble(m.group(2));
        } else {
            throw new ItemParseException();
        }
        return d;
    }

    public String parseType(String singleItem) throws ItemParseException {

        Pattern pattern = Pattern.compile(".*type([:|@|^|*|%])(\\w+)", Pattern.CASE_INSENSITIVE);
        return getString(singleItem, pattern);
    }

    public String parseDate(String singleItem) throws ItemParseException {
        Pattern pattern = Pattern.compile(".*expiration([:|@|^|*|%])(\\d.*)(##)", Pattern.CASE_INSENSITIVE);
        return getString(singleItem, pattern);
    }

    private String getString(String singleItem, Pattern pattern) throws ItemParseException {
        String s;
        Matcher m = pattern.matcher(singleItem);
        if (m.find()) {
            s = m.group(2).toLowerCase();
        } else {
            throw new ItemParseException();
        }
        return s;
    }
}
