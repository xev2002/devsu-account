package com.xev.springboot_test_devsu_2_account.service;

import com.xev.springboot_test_devsu_2_account.dto.CuentaDTO;
import com.xev.springboot_test_devsu_2_account.model.Cuenta;
import com.xev.springboot_test_devsu_2_account.model.TipoCuenta;
import com.xev.springboot_test_devsu_2_account.repository.CuentaRepository;
import com.xev.springboot_test_devsu_2_account.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CuentaServiceImpl implements CuentaService{

    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private ServiceUtils serviceUtils;

    @Override
    public Cuenta saveCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }
    @Override
    public List<CuentaDTO> findAllCuentasDTOByClientId(Long clientId) {
        List<Cuenta> cuentas = cuentaRepository.findAllByClientId(clientId);
        return cuentas.stream()
                .map(CuentaDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public CuentaDTO findByCuentaDTOByClientId(Long clientId, Long numeroCuenta) {
        Cuenta cuenta = serviceUtils.getCuentaValidada(clientId, numeroCuenta);
        return new CuentaDTO(cuenta);
    }

    @Override
    public Cuenta updateCuenta(Long clientId, Long numeroCuenta, Cuenta cuentaAcutalizada) {
        Cuenta cuentaExistente = serviceUtils.getCuentaValidada(clientId, numeroCuenta);
        cuentaExistente.setTipoCuenta(cuentaAcutalizada.getTipoCuenta());
        cuentaExistente.setEstado(cuentaAcutalizada.isEstado());
        cuentaExistente.setSaldoInicial(cuentaAcutalizada.getSaldoInicial());
        return cuentaRepository.save(cuentaExistente);
    }
    @Override
    public Cuenta patchCuenta(Long clientId, Long numeroCuenta, Map<String, Object> cambios) {
        Cuenta cuenta = serviceUtils.getCuentaValidada(clientId, numeroCuenta);
        cambios.forEach((key, value) -> {
            switch (key) {
                case "tipoCuenta" -> cuenta.setTipoCuenta(TipoCuenta.valueOf((String) value));
                case "saldoInicial" -> cuenta.setSaldoInicial((Double) value);
                case "estado" -> cuenta.setEstado((boolean) value);
            }
        });
        return cuentaRepository.save(cuenta);
    }
}
