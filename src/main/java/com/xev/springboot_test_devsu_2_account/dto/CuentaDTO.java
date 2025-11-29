package com.xev.springboot_test_devsu_2_account.dto;

import com.xev.springboot_test_devsu_2_account.model.Cuenta;
import com.xev.springboot_test_devsu_2_account.model.TipoCuenta;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CuentaDTO {
    private Long numeroCuenta;
    private TipoCuenta tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;
    private Long clientId;
    private List<MovimientoDTO> movimientos;

    public CuentaDTO(Cuenta cuenta) {
        this.numeroCuenta = cuenta.getNumeroCuenta();
        this.tipoCuenta = cuenta.getTipoCuenta();
        this.saldoInicial = cuenta.getSaldoInicial();
        this.estado = cuenta.isEstado();
        this.clientId = cuenta.getClientId();

        if (cuenta.getMovimientos() != null) {
            this.movimientos = cuenta.getMovimientos().stream()
                    .map(MovimientoDTO::new)
                    .collect(Collectors.toList());
        }
    }
}