package com.example.picselector.placeholder;

import com.example.picselector.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PlaceholderContent {

    /**
     * An array of picture (placeholder) items.
     */
    public static final List<PlaceholderItem> ITEMS = new ArrayList<>();

    /**
     * A map of sample (placeholder) items, by ID.
     */
    public static final Map<String, PlaceholderItem> ITEM_MAP = new HashMap<>();


    //private static final int COUNT = 15;

    static {
        // Add some sample items.
        addItem(new PlaceholderItem("1","Lion", R.drawable.pic1));
        addItem(new PlaceholderItem("2","Elephant",R.drawable.pic2));
        addItem(new PlaceholderItem("3","Hippopotamus",R.drawable.pic3));
        addItem(new PlaceholderItem("4","Bear",R.drawable.pic4));
        addItem(new PlaceholderItem("5","Cheetah",R.drawable.pic5));
        addItem(new PlaceholderItem("6","Zebra",R.drawable.pic6));
        addItem(new PlaceholderItem("7","Fox",R.drawable.pic7));
        addItem(new PlaceholderItem("8","Mallard",R.drawable.pic8));
        addItem(new PlaceholderItem("9","Chameleon",R.drawable.pic9));
        addItem(new PlaceholderItem("10","Tiger",R.drawable.pic10));
    }

    private static void addItem(PlaceholderItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static PlaceholderItem createPlaceholderItem(int position) {
        return new PlaceholderItem(String.valueOf(position), "Item " + position, position);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A placeholder item representing a piece of content.
     */
    public static class PlaceholderItem {
        public final String id;
        public final String animalName;
        public final int animalImage;


        public PlaceholderItem(String id, String animalName, int details) {
            this.id = id;
            this.animalName = animalName;
            this.animalImage = details;
        }

        @Override
        public String toString() {
            return animalName;
        }

    }
}