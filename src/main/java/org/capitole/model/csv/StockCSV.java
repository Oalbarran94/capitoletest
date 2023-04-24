package org.capitole.model.csv;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockCSV {

    @CsvBindByPosition(position = 0)
    private Integer sizeId;

    @CsvBindByPosition(position = 1)
    private Integer quantity;
}
