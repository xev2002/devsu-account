package com.xev.springboot_test_devsu_2_account.controller;

import com.xev.springboot_test_devsu_2_account.dto.CuentaDTO;
import com.xev.springboot_test_devsu_2_account.model.Cuenta;
import com.xev.springboot_test_devsu_2_account.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping
    public ResponseEntity<?> createCuenta(@RequestBody Cuenta cuenta) {
        return new ResponseEntity<>(cuentaService.saveCuenta(cuenta), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getCuentas() {
        return ResponseEntity.ok(cuentaService.findAllCuentasDTOByClientId(getClientId()));
    }

    @GetMapping("{cuentaId}")
    public ResponseEntity<?> getCuentaDTOByClientId(@PathVariable Long cuentaId) {
        CuentaDTO cuentaDTO = cuentaService.findByCuentaDTOByClientId(getClientId(), cuentaId);
        return new ResponseEntity<>(cuentaDTO, HttpStatus.OK);
    }

    @PutMapping("{numeroCuenta}")
    public ResponseEntity<?> updateCuenta(@PathVariable Long numeroCuenta, @RequestBody Cuenta cuenta) {
        return new ResponseEntity<>(cuentaService.updateCuenta(getClientId(), numeroCuenta, cuenta), HttpStatus.OK);
    }

    @PatchMapping("{numeroCuenta}")
    public ResponseEntity<?> patchCuenta(@PathVariable Long numeroCuenta, @RequestBody Map<String, Object> cambios) {
        return new ResponseEntity<>(cuentaService.patchCuenta(getClientId(), numeroCuenta, cambios), HttpStatus.OK);
    }

    private Long getClientId(){
        return (Long) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }

}
