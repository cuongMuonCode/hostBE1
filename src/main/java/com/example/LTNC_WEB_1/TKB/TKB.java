package com.example.LTNC_WEB_1.TKB;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "TKB")
public class TKB {
    private Integer personId;
    private List<String> ca1;
    private List<String> ca2;

}
