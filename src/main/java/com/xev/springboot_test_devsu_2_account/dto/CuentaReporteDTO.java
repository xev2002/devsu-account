package com.xev.springboot_test_devsu_2_account.dto;

import com.xev.springboot_test_devsu_2_account.model.Cuenta;
import com.xev.springboot_test_devsu_2_account.model.Movimiento;
import lombok.Data;

import java.util.List;

@Data
public class CuentaReporteDTO {

    private Long numeroCuenta;
    private String tipoCuenta;
    private Double saldoDisponible;
    private List<MovimientoDTO> movimientos;

    public CuentaReporteDTO(Cuenta cuenta, List<Movimiento> movimientosFiltrados) {
        this.numeroCuenta = cuenta.getNumeroCuenta();
        this.tipoCuenta = cuenta.getTipoCuenta().name();
        if (movimientosFiltrados.isEmpty()) {
            this.saldoDisponible = cuenta.getSaldoInicial();
        } else {
            this.saldoDisponible = movimientosFiltrados
                    .get(movimientosFiltrados.size() - 1)
                    .getSaldo();
        }
        this.movimientos = movimientosFiltrados.stream()
                .map(MovimientoDTO::new)
                .toList();
    }
}