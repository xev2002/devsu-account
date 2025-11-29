package com.xev.springboot_test_devsu_2_account.controller;

import com.xev.springboot_test_devsu_2_account.dto.MovimientoDTO;
import com.xev.springboot_test_devsu_2_account.model.Movimiento;
import com.xev.springboot_test_devsu_2_account.security.JwtService;
import com.xev.springboot_test_devsu_2_account.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @PostMapping("{numeroCuenta}")
    public ResponseEntity<?> createMovimiento(
            @PathVariable Long numeroCuenta,
            @RequestBody Movimiento movimiento
    ) {
        Long clientIdFromToken = getClientId();
        Movimiento saved = movimientoService.saveMovimiento(clientIdFromToken, numeroCuenta, movimiento);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("{numeroCuenta}")
    public ResponseEntity<?> getAllMovimientosByNumeroCuenta(@PathVariable Long numeroCuenta) {
        List<MovimientoDTO> movimientos = movimientoService.findAllMovimientosDTOByCuenta(getClientId(),numeroCuenta);
        return new ResponseEntity<>(movimientos, HttpStatus.OK);
    }

    @GetMapping("{numeroCuenta}/{id}")
    public ResponseEntity<?> getMovimientoByNumeroCuenta(@PathVariable Long numeroCuenta, @PathVariable Long id) {
        MovimientoDTO movimiento = movimientoService.findMovimientoDTOById(getClientId(),numeroCuenta, id);
        return new ResponseEntity<>(movimiento, HttpStatus.OK);
    }

    @PutMapping("{numeroCuenta}/{id}")
    public ResponseEntity<?> updateCuenta(@PathVariable Long numeroCuenta, @PathVariable Long id, @RequestBody Movimiento movimiento) {
        return new ResponseEntity<>(movimientoService.updateMovimiento(getClientId(), numeroCuenta, id, movimiento), HttpStatus.OK);
    }

    @GetMapping("/reportes")
    public ResponseEntity<?> generarReporte(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate hasta
    ) {
        return new ResponseEntity<>(
                movimientoService.generarReporte(getClientId(), desde, hasta),
                HttpStatus.OK
        );
    }

    public Long getClientId() {
        return Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
