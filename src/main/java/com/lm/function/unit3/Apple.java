package com.lm.function.unit3;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Apple {

    private int weight;

    public Apple (int weight) {
        this.weight = weight;
    }

}
