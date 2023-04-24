package org.capitole.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Size {

    private Integer id;

    private Integer productId;

    private Boolean backSoon;

    private Boolean special;

    private Integer stock;
}
