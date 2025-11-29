package com.xev.springboot_test_devsu_2_account.utils;

import com.xev.springboot_test_devsu_2_account.model.Cuenta;
import com.xev.springboot_test_devsu_2_account.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceUtils {

    @Autowired
    private CuentaRepository cuentaRepository;

    public Cuenta getCuentaValidada(Long clientId, Long numeroCuenta) {
        return cuentaRepository.findByNumeroCuentaAndClientId(numeroCuenta, clientId)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada o no pertenece al cliente"));
    }
}
