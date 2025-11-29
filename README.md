# Microservicio Cuentas y Movimientos

Este repositorio contiene ejemplos de payload y endpoints para probar el microservicio **Cuentas y Movimientos** usando Postman.  
Permite realizar pruebas de creación, consulta, actualización y reporte de cuentas y movimientos asociados a un cliente.

---
## `Cuentas`
Contiene la información de una cuenta para pruebas de:

- **Crear cuenta:** `POST /api/cuentas`
- **Actualizar cuenta:** `PUT /api/cuentas/{numeroCuenta}`
- **Actualizar parcialmente:** `PATCH /api/cuentas/{numeroCuenta}`
- **Obtener todas las cuentas de un cliente:** `GET /api/cuentas`
- **Obtener cuenta por número:** `GET /api/cuentas/{numeroCuenta}`

## `Movimientos`
Contiene la información de un movimiento para pruebas de:

- **Crear movimiento:** `POST /api/movimientos/{numeroCuenta}`
- **Actualizar movimiento:** `PUT /api/movimientos/{numeroCuenta}/{id}`
- **Obtener todos los movimientos de una cuenta:** `GET /api/movimientos/{numeroCuenta}`
- **Obtener movimiento por ID:** `GET /api/movimientos/{numeroCuenta}/{id}`
- **Obtener reporte de movimientos:** `GET /api/movimientos/reportes?desde=yyyy-MM-dd&hasta=yyyy-MM-dd`

> **Uso en Postman:**  
> 1. Crear una nueva petición en Postman.  
> 2. Seleccionar el método HTTP correspondiente.  
> 3. Copiar el contenido del JSON en el body de la petición (`raw` → `JSON`) para POST, PUT y PATCH.  
> 4. Para los endpoints protegidos, añadir el token de autorización en **Authorization** → `Bearer Token` el cual es generado por el microservicio cliente.

---
## Uso
-  `POST http://localhost:4444/api/cuentas`  
El archivo para el body se encuentra en:  
\src\test\resources\data\cuenta.json
- `PUT http://localhost:4444/api/cuentas/1001`  
El archivo para el body se encuentra en:  
\src\test\resources\data\cuentaPut.json
- `PATCH http://localhost:4444/api/cuentas/1001`  
El archivo para el body se encuentra en:  
\src\test\resources\data\cuentaPatch.json
- `GET http://localhost:4444/api/cuentas`  
Obtener todas las cuentas, el id del cliente ya está en el token por ende no es necesario colocarlo en el endpoint
- `GET http://localhost:4444/api/cuentas/1001`  
Obtener una cuenta en especifico, el id del cliente ya está en el token por ende no es necesario colocarlo en el endpoint
-  `POST http://localhost:4444/api/movimientos/1001`  
El archivo para el body se encuentra en:  
\src\test\resources\data\movimiento.json
-  `PUT http://localhost:4444/api/movimientos/1001/1`  
El archivo para el body se encuentra en:  
\src\test\resources\data\movimientoPut.json
- `GET http://localhost:4444/api/movimientos/1001`  
Obtener todos los movimientos de una cuenta
- `GET http://localhost:4444/api/movimientos/1001/1`  
Obtener una movimiento en especifico
- `GET http://localhost:4444/api/movimientos/reportes?desde=2025-11-20&hasta=2025-11-30`
Obtener un estado de cuenta

---
## Ejecución con Docker
> 1. Crear carpeta llamada Devsu
> 2. Pegar la carpeta mysql-init
Esta carpeta debe contener el archivo init.sql con la creación del usuario y permisos necesarios.
> 3. Pegar el archivo docker-compose.yml
> 4. Descargar el repositorio devsu-client dentro de la carpeta Devsu
> 5. Descargar el repositorio devsu-account dentro de la carpeta Devsu
> 6. Dentro de la ruta, ejecutar el siguiente comando docker: docker-compose up --build


La estructura para el perfecto funcionamiento debe ser:  
Devsu  
├── docker-compose.yml  
├── mysql-init  
│   └── init.sql  
├── devsu-client  
└── devsu-account  
Es importante tener los puertos 3306, 3333 y 4444 disponibles.
