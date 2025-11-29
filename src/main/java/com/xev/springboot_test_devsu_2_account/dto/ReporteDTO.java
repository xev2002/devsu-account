package com.xev.springboot_test_devsu_2_account.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReporteDTO {
    private Long clientId;
    private String clientName;
    private List<CuentaReporteDTO> cuentas;
}