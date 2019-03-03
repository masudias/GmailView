package com.masudias.gmailview;

import java.util.ArrayList;

public class Utilities {

    public static int DEMO_LIST_SIZE = 6;

    public static ArrayList<Integer> populatePositionsWithDummyData() {
        ArrayList<Integer> dummy = new ArrayList<>();

        // Please note that the RecyclerView is showing items in a reverse order.
        // Hence the items are put in a reverse order for the demo

        dummy.add(3); // Just adding last element as expanded so that it looks like an email app
        dummy.add(2);
        dummy.add(2);
        dummy.add(3);
        dummy.add(2);
        dummy.add(1);

        return dummy;
    }
}