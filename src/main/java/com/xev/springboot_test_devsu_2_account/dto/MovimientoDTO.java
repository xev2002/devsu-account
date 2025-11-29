package com.xev.springboot_test_devsu_2_account.dto;

import com.xev.springboot_test_devsu_2_account.model.Movimiento;
import com.xev.springboot_test_devsu_2_account.model.TipoMovimiento;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MovimientoDTO {
    private Long id;
    private String nombreCliente;
    private LocalDate fecha;
    private TipoMovimiento tipoMovimiento;
    private Double valor;
    private Double saldo;

    public MovimientoDTO(Movimiento movimiento) {
        this.id = movimiento.getId();
        this.fecha = movimiento.getFecha();
        this.tipoMovimiento = movimiento.getTipoMovimiento();
        this.valor = movimiento.getValor();
        this.saldo = movimiento.getSaldo();
    }
}
