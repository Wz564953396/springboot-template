package com.wz.example.template.designPattern.bridge.implementor;

import com.wz.example.template.designPattern.bridge.abstraction.Colour;

public abstract class Bag {
    private Colour colour;

    public abstract String getName();

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

}
