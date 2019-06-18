package com.arakelyan.beerexpert;

import java.util.ArrayList;
import java.util.List;

public class BeerAdvicer {

    List<String> getBrands (String beerColor) {
        List<String> brands = new ArrayList<>();
/*

        if (beerColor.equals("Amber")) {
            brands.add("Jack Amber");
            brands.add("Red Moose");
        }
        else {
            brands.add("Jail Pale Ale");
            brands.add("Gout Stout");
        }
*/

        switch (beerColor) {
            case "Amber" :
                brands.add("Jack amber");
                break;

            case "Light" :
                brands.add("Miller");
                break;

            case "Dark" :
                brands.add("Baltika 9");
                break;

            case "Brown" :
                brands.add("Amstel");
                break;
        }

        return brands;

    }

}
