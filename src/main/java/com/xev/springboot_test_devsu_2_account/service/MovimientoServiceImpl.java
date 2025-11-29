package com.xev.springboot_test_devsu_2_account.service;

import com.xev.springboot_test_devsu_2_account.dto.CuentaReporteDTO;
import com.xev.springboot_test_devsu_2_account.dto.MovimientoDTO;
import com.xev.springboot_test_devsu_2_account.dto.ReporteDTO;
import com.xev.springboot_test_devsu_2_account.model.Cuenta;
import com.xev.springboot_test_devsu_2_account.model.Movimiento;
import com.xev.springboot_test_devsu_2_account.model.TipoMovimiento;
import com.xev.springboot_test_devsu_2_account.repository.CuentaRepository;
import com.xev.springboot_test_devsu_2_account.repository.MovimientoRepository;
import com.xev.springboot_test_devsu_2_account.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;
    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private ServiceUtils serviceUtils;

    @Override
    public Movimiento saveMovimiento(Long clientId, Long numeroCuenta, Movimiento movimiento) {
        Cuenta cuenta = serviceUtils.getCuentaValidada(clientId, numeroCuenta);
        movimiento.setCuenta(cuenta);
        validateTransaction(cuenta, movimiento);
        movimiento.setSaldo(cuenta.getSaldoInicial());
        cuentaRepository.save(cuenta);
        return movimientoRepository.save(movimiento);
    }
    @Override
    public List<MovimientoDTO> findAllMovimientosDTOByCuenta(Long clientId, Long numeroCuenta) {
        serviceUtils.getCuentaValidada(clientId, numeroCuenta);
        List<Movimiento> movimientos =
                movimientoRepository.findAllByCuenta_NumeroCuenta(numeroCuenta);

        return movimientos.stream()
                .map(MovimientoDTO::new)
                .toList();
    }

    @Override
    public MovimientoDTO findMovimientoDTOById(Long clientId, Long numeroCuenta, Long id) {
        Cuenta cuenta = serviceUtils.getCuentaValidada(clientId, numeroCuenta);
        Movimiento movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
        movimiento.setCuenta(cuenta);
        if (!movimiento.getCuenta().getNumeroCuenta().equals(numeroCuenta)) {
            throw new RuntimeException("El movimiento no pertenece a la cuenta indicada");
        }
        return new MovimientoDTO(movimiento);
    }

    @Override
    public Movimiento updateMovimiento(Long clientId, Long numeroCuenta, Long id, Movimiento movimientoActualizado) {
        Cuenta cuenta = serviceUtils.getCuentaValidada(clientId, numeroCuenta);
        Movimiento movimientoExistente = movimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
        movimientoExistente.setTipoMovimiento(movimientoActualizado.getTipoMovimiento());
        movimientoExistente.setFecha(movimientoActualizado.getFecha());
        movimientoExistente.setValor(movimientoActualizado.getValor());
        movimientoExistente.setSaldo(movimientoActualizado.getSaldo());
        validateTransaction(cuenta, movimientoExistente);
        return movimientoRepository.save(movimientoExistente);
    }

    private void validateTransaction(Cuenta cuenta, Movimiento movimiento) {
        Double valor = movimiento.getValor();
        switch (movimiento.getTipoMovimiento()) {
            case DEPOSITO -> {
                if (valor <= 0) throw new RuntimeException("Un depósito debe ser positivo");
                cuenta.setSaldoInicial(cuenta.getSaldoInicial() + valor);
            }
            case RETIRO -> {
                if (valor == 0) throw new RuntimeException("El retiro no puede ser cero");
                double retiro = Math.abs(valor);
                if (retiro > cuenta.getSaldoInicial()) throw new RuntimeException("Saldo insuficiente");
                cuenta.setSaldoInicial(cuenta.getSaldoInicial() - retiro);
                movimiento.setValor(-retiro);
            }
            default -> throw new RuntimeException("Tipo de movimiento no válido");
        }
    }

    @Override
    public ReporteDTO generarReporte(Long clientId, LocalDate desde, LocalDate hasta) {
        List<Cuenta> cuentas = cuentaRepository.findAllByClientId(clientId);
        if (cuentas.isEmpty()) {
            throw new RuntimeException("El cliente no tiene cuentas registradas");
        }
        List<CuentaReporteDTO> cuentasReporte = new ArrayList<>();
        for (Cuenta cuenta : cuentas) {
            List<Movimiento> movimientos = movimientoRepository
                    .findAllByCuenta_NumeroCuentaAndFechaBetween(
                            cuenta.getNumeroCuenta(),
                            desde, hasta
                    );

            CuentaReporteDTO reporteCuenta = new CuentaReporteDTO(cuenta, movimientos);
            cuentasReporte.add(reporteCuenta);
        }
        ReporteDTO reporte = new ReporteDTO();
        reporte.setClientId(clientId);
        reporte.setCuentas(cuentasReporte);

        return reporte;
    }
}
