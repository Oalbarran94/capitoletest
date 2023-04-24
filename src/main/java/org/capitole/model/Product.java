package org.capitole.model;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

    private Integer id;
    private Integer sequence;
    private Map<Integer, Size> mapSize;
}
