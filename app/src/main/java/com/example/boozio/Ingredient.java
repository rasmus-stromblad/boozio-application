package com.example.boozio;

public class Ingredient {

    private String name;
    private String type;

    public Ingredient() {}

    public Ingredient(String _name, String _type){
        name = _name;
        type = _type;
    }

    public String getName() { return name; }

    public String getType() { return type; }
}
