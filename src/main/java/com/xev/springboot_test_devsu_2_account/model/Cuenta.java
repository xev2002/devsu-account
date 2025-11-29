package com.xev.springboot_test_devsu_2_account.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="cuentas")
public class Cuenta {
    @Id
    @Column(name = "numero_cuenta")
    private Long numeroCuenta;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cuenta", nullable = false)
    private TipoCuenta tipoCuenta;
    @Column(name = "saldo_inicial", nullable = false)
    private Double saldoInicial;
    @Column(name = "estado", nullable = false)
    private boolean estado;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Movimiento> movimientos = new ArrayList<>();

}
