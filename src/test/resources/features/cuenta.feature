Feature: Pruebas de Cuentas y Movimientos

  Background:
    * def baseUrlCliente = 'http://localhost:3333'
    * def baseUrlCuenta = 'http://localhost:4444'
    * def cliente = read('classpath:data/cliente.json')
    * def cuenta = read('classpath:data/cuenta.json')
    * def movimiento = read('classpath:data/movimiento.json')

    Given url baseUrlCliente
    And path '/api/clientes'
    And request cliente
    When method post
    Then status 201

    Given url baseUrlCliente
    And path '/auth/login'
    And request read('classpath:data/clienteLogin.json')
    When method post
    Then status 200
    * def token = response.token
    * def authHeader = 'Bearer ' + token

    Given url baseUrlCuenta
    And path '/api/cuentas'
    And header Authorization = authHeader
    And request cuenta
    When method post
    Then status 201

  Scenario: Obtener todas las cuentas del cliente
    Given url baseUrlCuenta
    And path '/api/cuentas'
    And header Authorization = authHeader
    When method get
    Then status 200

  Scenario: Obtener cuenta por numeroCuenta
    Given url baseUrlCuenta
    And path '/api/cuentas/1001'
    And header Authorization = authHeader
    When method get
    Then status 200

  Scenario: Actualizar cuenta (PUT)
    Given url baseUrlCuenta
    And path '/api/cuentas/1001'
    And header Authorization = authHeader
    And request read('classpath:data/cuentaPut.json')
    When method put
    Then status 200

  Scenario: Actualizar cuenta parcialmente (PATCH)
    Given url baseUrlCuenta
    And path '/api/cuentas/1001'
    And header Authorization = authHeader
    And request read('classpath:data/cuentaPatch.json')
    When method patch
    Then status 200

  Scenario: Crear movimiento
    Given url baseUrlCuenta
    And path '/api/movimientos/1001'
    And header Authorization = authHeader
    And request movimiento
    When method post
    Then status 201

  Scenario: Obtener todos los movimientos de una cuenta
    Given url baseUrlCuenta
    And path '/api/movimientos/1001'
    And header Authorization = authHeader
    When method get
    Then status 200

  Scenario: Obtener movimiento por ID
    Given url baseUrlCuenta
    And path '/api/movimientos/1001/1'
    And header Authorization = authHeader
    When method get
    Then status 200

  Scenario: Actualizar movimiento (PUT)
    Given url baseUrlCuenta
    And path '/api/movimientos/1001/1'
    And header Authorization = authHeader
    And request read('classpath:data/movimientoPut.json')
    When method put
    Then status 200

  Scenario: Obtener reporte de movimientos
    Given url baseUrlCuenta
    And path '/api/movimientos/reportes'
    And header Authorization = authHeader
    And param desde = '2025-11-01'
    And param hasta = '2025-11-30'
    When method get
    Then status 200
