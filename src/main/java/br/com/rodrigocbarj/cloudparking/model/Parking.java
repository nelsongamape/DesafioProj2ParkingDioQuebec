package br.com.rodrigocbarj.cloudparking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parking {

    private Long id;
    private String license;
    private String state;
    private String model;
    private String color;
    private LocalDateTime entryDate = LocalDateTime.now();
    private LocalDateTime exitDate;
    private Double bill;

    public Parking(Long id, String license, String state, String model, String color) {
        this.id = id;
        this.license = license;
        this.state = state;
        this.model = model;
        this.color = color;
    }
}
