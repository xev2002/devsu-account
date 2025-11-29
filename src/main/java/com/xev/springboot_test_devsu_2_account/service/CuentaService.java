package com.xev.springboot_test_devsu_2_account.service;

import com.xev.springboot_test_devsu_2_account.dto.CuentaDTO;
import com.xev.springboot_test_devsu_2_account.model.Cuenta;

import java.util.List;
import java.util.Map;

public interface CuentaService {
    Cuenta saveCuenta(Cuenta cuenta);

    List<CuentaDTO> findAllCuentasDTOByClientId(Long clientId);

    CuentaDTO findByCuentaDTOByClientId(Long clientId, Long numeroCuenta);

    Cuenta updateCuenta(Long clientId, Long id, Cuenta cuentaAcutalizada);

    Cuenta patchCuenta(Long clientId, Long id, Map<String, Object> cambios);
}
