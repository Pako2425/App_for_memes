package com.patryk.app.webapp.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class OperationResultDAO {
    private boolean success;
    private String message;
}
