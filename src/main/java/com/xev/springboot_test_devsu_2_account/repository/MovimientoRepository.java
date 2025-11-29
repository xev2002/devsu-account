package com.xev.springboot_test_devsu_2_account.repository;

import com.xev.springboot_test_devsu_2_account.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    Optional<Movimiento> findById(Long id);
    List<Movimiento> findAllByCuenta_NumeroCuenta(Long cuentaNumeroCuenta);
    List<Movimiento> findAllByCuenta_NumeroCuentaAndFechaBetween(Long numeroCuenta, LocalDate desde, LocalDate hasta);

}
