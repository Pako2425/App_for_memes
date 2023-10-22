package com.patryk.app.rest.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class FileData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private String filePath;
}