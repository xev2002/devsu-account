package com.xev.springboot_test_devsu_2_account.repository;

import com.xev.springboot_test_devsu_2_account.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    List<Cuenta> findAllByClientId(Long clientId);

    Optional<Cuenta> findByNumeroCuentaAndClientId(Long numeroCuenta, Long clientId);
}
