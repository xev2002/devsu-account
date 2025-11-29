package com.xev.springboot_test_devsu_2_account.service;

import com.xev.springboot_test_devsu_2_account.dto.MovimientoDTO;
import com.xev.springboot_test_devsu_2_account.dto.ReporteDTO;
import com.xev.springboot_test_devsu_2_account.model.Movimiento;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface MovimientoService {

    Movimiento saveMovimiento(Long clientId, Long numeroCuenta, Movimiento movimiento);

    List<MovimientoDTO> findAllMovimientosDTOByCuenta(Long clientId, Long numeroCuenta);

    MovimientoDTO findMovimientoDTOById(Long clientId, Long numeroCuenta, Long Id);

    Movimiento updateMovimiento(Long clientId, Long numeroCuenta, Long id, Movimiento movimientoActualizado);

    ReporteDTO generarReporte(Long clientId, LocalDate desde, LocalDate hasta);
}
