package org.capitole.model.csv;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SizeCSV {

    @CsvBindByPosition(position = 0)
    private Integer id;

    @CsvBindByPosition(position = 1)
    private Integer productId;

    @CsvBindByPosition(position = 2)
    private Boolean backSoon;

    @CsvBindByPosition(position = 3)
    private Boolean special;
}
