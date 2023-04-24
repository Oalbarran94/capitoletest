package org.capitole.model;

import lombok.*;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InventoryStock {

    Map<Integer, Product> mapProducts;
}
