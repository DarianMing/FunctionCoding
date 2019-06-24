package com.lm.function.unit3;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dish {

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    @Override
    public String toString () {
        return name;
    }

    public enum Type {
        MEAT,FISH,OTHER
    }


}
