package com.ubs.calculation.model;

import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class CalculationPdfDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private LocalDateTime localDateTime;
    private String path;
}
