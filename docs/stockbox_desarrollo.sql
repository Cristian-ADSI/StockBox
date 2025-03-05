-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-03-2025 a las 00:10:02
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `stockbox_desarrollo`
--
CREATE DATABASE IF NOT EXISTS `stockbox_desarrollo` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `stockbox_desarrollo`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE `clientes` (
  `IdCliente` int(11) NOT NULL,
  `DNI` int(15) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Direccion` varchar(50) NOT NULL,
  `Estado` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`IdCliente`, `DNI`, `Nombre`, `Direccion`, `Estado`) VALUES
(1, 1234567890, 'Carlos Pérez', 'Calle Falsa 123', 'Activo'),
(2, 2147483647, 'Ana Gómez', 'Avenida Libertad 456', 'Inactivo'),
(3, 2147483647, 'Luis Rodríguez', 'Calle Mayor 789', 'Activo'),
(4, 2147483647, 'Marta Sánchez', 'Paseo del Sol 321', 'Activo'),
(5, 2147483647, 'Pedro Fernández', 'Calle de la Luna 654', 'Inactivo'),
(6, 2147483647, 'Laura Martínez', 'Avenida de la Paz 987', 'Activo'),
(7, 2147483647, 'José García', 'Calle del Río 135', 'Activo'),
(8, 2147483647, 'Sara López', 'Calle del Mar 246', 'Inactivo'),
(9, 2147483647, 'Javier Díaz', 'Avenida Europa 369', 'Activo'),
(10, 1234567890, 'Carla Torres', 'Calle de la Esperanza 456', 'Activo'),
(11, 2147483647, 'Raúl Jiménez', 'Calle del Bosque 753', 'Activo'),
(12, 2147483647, 'Elena Martínez', 'Calle de las Flores 852', 'Inactivo'),
(13, 2147483647, 'Andrés Ruiz', 'Calle del Sol 147', 'Activo'),
(14, 2147483647, 'Patricia Pérez', 'Calle del Viento 258', 'Activo'),
(15, 2147483647, 'Miguel Alonso', 'Avenida Central 369', 'Inactivo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_ventas`
--

DROP TABLE IF EXISTS `detalle_ventas`;
CREATE TABLE `detalle_ventas` (
  `IdDetalleVenta` int(11) NOT NULL,
  `IdVenta` int(11) NOT NULL,
  `NroSerie` varchar(255) NOT NULL,
  `Producto` int(11) NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `PrecioUnidad` float NOT NULL,
  `Estado` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

DROP TABLE IF EXISTS `productos`;
CREATE TABLE `productos` (
  `IdProducto` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Precio` float DEFAULT NULL,
  `Stock` int(11) NOT NULL,
  `Estado` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`IdProducto`, `Nombre`, `Precio`, `Stock`, `Estado`) VALUES
(1, 'Martillo Stanley', 15.5, 100, 'Existente'),
(2, 'Destornillador Bosch', 8.99, 150, 'Existente'),
(3, 'Alicates Klein Tools', 12.75, 75, 'Existente'),
(4, 'Sierra manual Black & Decker', 22.3, 50, 'Existente'),
(5, 'Cinta métrica Makita', 5.99, 200, 'Existente'),
(6, 'Llave inglesa Crescent', 14.2, 120, 'Existente'),
(7, 'Taladro inalámbrico DeWalt', 99.99, 30, 'Existente'),
(8, 'Pistola de silicona 3M', 9.49, 85, 'Existente'),
(9, 'Cuchillo multiusos Victorinox', 19.99, 55, 'Existente'),
(10, 'Escalera de aluminio Werner', 79.5, 60, 'Existente'),
(11, 'Guantes de trabajo Carhartt', 15.75, 100, 'Existente'),
(12, 'Brocas para metal Irwin', 12.4, 90, 'Existente'),
(13, 'Sierra caladora Milwaukee', 45.6, 40, 'Existente'),
(14, 'Lijadora orbital Bosch', 67, 50, 'Existente'),
(15, 'Manguera de jardín Gardena', 19.8, 110, 'Existente'),
(16, 'Pico Estwing', 25.9, 30, 'Existente'),
(17, 'Hacha Fiskars', 35.75, 75, 'Existente'),
(18, 'Rollo de cinta aislante 3M', 3.5, 200, 'Existente'),
(19, 'Nivel láser Stabila', 89, 45, 'Existente'),
(20, 'Linterna LED Streamlight', 29.99, 90, 'Existente'),
(21, 'Cinta de medir Stanley', 7.99, 120, 'Existente'),
(22, 'Lima de metal Bahco', 6.5, 150, 'Existente'),
(23, 'Martillo de goma Estwing', 18, 100, 'Existente'),
(24, 'Cinta métrica digital Makita', 32.5, 60, 'Existente'),
(25, 'Tijeras de podar Felco', 42, 50, 'Existente'),
(26, 'Cargador de batería DeWalt', 25, 80, 'Existente'),
(27, 'Manguera para aire comprimido Flexzilla', 22.99, 70, 'Existente'),
(28, 'Pala de jardín Fiskars', 16.5, 130, 'Existente'),
(29, 'Pistola de clavos Makita', 159.99, 20, 'Existente'),
(30, 'Cerradura de seguridad Yale', 55, 50, 'Existente'),
(31, 'Grasa lubricante WD-40', 6.5, 180, 'Existente'),
(32, 'Sierra para corte de madera Bosch', 23.9, 90, 'Existente'),
(33, 'Sargento de carpintero Irwin', 14.99, 70, 'Existente'),
(34, 'Cinta de embalaje Scotch', 2, 250, 'Existente'),
(35, 'Tornillos para madera ScrewsPro', 4.5, 300, 'Existente'),
(36, 'Pinzas de presión Irwin', 22.8, 85, 'Existente'),
(37, 'Soporte para taladro Bessey', 50, 30, 'Existente'),
(38, 'Gatos hidráulicos Torin', 99.9, 40, 'Existente'),
(39, 'Compresor de aire Ingersoll Rand', 250, 15, 'Existente'),
(40, 'Pala de mano Fiskars', 10.99, 120, 'Existente'),
(41, 'Cutter Stanley', 8.9, 110, 'Existente'),
(42, 'Extensión de cable Masterplug', 12.5, 140, 'Existente'),
(43, 'Serrucho Bahco', 17.25, 65, 'Existente'),
(44, 'Taladro percutor Bosch', 120, 50, 'Existente'),
(45, 'Cinta antideslizante 3M', 7, 100, 'Existente'),
(46, 'Cadenas para neumáticos Tirechains', 45, 0, 'Agotado'),
(47, 'Lámpara de trabajo LED Milwaukee', 49.95, 80, 'Existente'),
(48, 'Cutter de precisión Olfa', 6.25, 150, 'Existente'),
(49, 'Escoba de jardín Gardena', 14, 90, 'Existente'),
(50, 'Kit de reparación de electrodomésticos Dremel', 25.99, 0, 'Agotado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `IdUsuario` int(11) NOT NULL,
  `DNI` varchar(20) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Telefono` varchar(20) NOT NULL,
  `Estado` enum('Activo','Inactivo') NOT NULL,
  `Usuario` varchar(50) NOT NULL,
  `Contrasena` varchar(255) DEFAULT NULL,
  `Role` enum('Administrador','Vendedor') DEFAULT 'Vendedor',
  `Token` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`IdUsuario`, `DNI`, `Nombre`, `Telefono`, `Estado`, `Usuario`, `Contrasena`, `Role`, `Token`) VALUES
(1, '12345678A', 'Carlos Pérez', '987654321', 'Activo', 'carlos.perez', 'contraseña_encriptada_1', 'Administrador', 'token_123456'),
(2, '23456789B', 'Ana Gómez', '987654322', 'Inactivo', 'ana.gomez', 'contraseña_encriptada_2', 'Administrador', 'token_234567'),
(3, '34567890C', 'Luis Rodríguez', '987654323', 'Activo', 'luis.rodriguez', 'contraseña_encriptada_3', 'Administrador', 'token_345678'),
(4, '45678901D', 'Marta Sánchez', '987654324', 'Activo', 'marta.sanchez', 'contraseña_encriptada_4', 'Vendedor', 'token_456789'),
(5, '56789012E', 'Pedro Fernández', '987654325', 'Inactivo', 'pedro.fernandez', 'contraseña_encriptada_5', 'Vendedor', 'token_567890'),
(6, '67890123F', 'Laura Martínez', '987654326', 'Activo', 'laura.martinez', 'contraseña_encriptada_6', 'Vendedor', 'token_678901'),
(7, '78901234G', 'José García', '987654327', 'Activo', 'jose.garcia', 'contraseña_encriptada_7', 'Vendedor', 'token_789012'),
(8, '89012345H', 'Sara López', '987654328', 'Inactivo', 'sara.lopez', 'contraseña_encriptada_8', 'Vendedor', 'token_890123'),
(9, '90123456I', 'Javier Díaz', '987654329', 'Activo', 'javier.diaz', 'contraseña_encriptada_9', 'Vendedor', 'token_901234'),
(10, '01234567J', 'Carla Torres', '987654330', 'Activo', 'carla.torres', 'contraseña_encriptada_10', 'Vendedor', 'token_012345'),
(11, '12345678K', 'Raúl Jiménez', '987654331', 'Inactivo', 'raul.jimenez', 'contraseña_encriptada_11', 'Vendedor', 'token_123457'),
(12, '23456789L', 'Elena Martínez', '987654332', 'Activo', 'elena.martinez', 'contraseña_encriptada_12', 'Vendedor', 'token_234568'),
(13, '34567890M', 'Andrés Ruiz', '987654333', 'Activo', 'andres.ruiz', 'contraseña_encriptada_13', 'Vendedor', 'token_345679'),
(14, '45678901N', 'Patricia Pérez', '987654334', 'Activo', 'patricia.perez', 'contraseña_encriptada_14', 'Vendedor', 'token_456790'),
(15, '56789012O', 'Miguel Alonso', '987654335', 'Inactivo', 'miguel.alonso', 'contraseña_encriptada_15', 'Vendedor', 'token_567891');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

DROP TABLE IF EXISTS `ventas`;
CREATE TABLE `ventas` (
  `Idventa` int(11) NOT NULL,
  `IdCliente` int(11) DEFAULT NULL,
  `IdUsuario` int(11) DEFAULT NULL,
  `NroSerie` int(255) NOT NULL,
  `FechaVenta` date NOT NULL,
  `Monto` float NOT NULL,
  `Estado` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `view_detalle_ventas`
-- (Véase abajo para la vista actual)
--
DROP VIEW IF EXISTS `view_detalle_ventas`;
CREATE TABLE `view_detalle_ventas` (
`IdDetalleVenta` int(11)
,`IdVenta` int(11)
,`NroSerie` varchar(255)
,`Producto` int(11)
,`Nombre` varchar(50)
,`Cantidad` int(11)
,`precio` float
,`Estado` varchar(15)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `view_ventas`
-- (Véase abajo para la vista actual)
--
DROP VIEW IF EXISTS `view_ventas`;
CREATE TABLE `view_ventas` (
`Cliente` varchar(50)
,`Vendedor` varchar(50)
,`IdVenta` int(11)
,`FechaVenta` date
,`Monto` float
,`Estado` varchar(10)
);

-- --------------------------------------------------------

--
-- Estructura para la vista `view_detalle_ventas`
--
DROP TABLE IF EXISTS `view_detalle_ventas`;

DROP VIEW IF EXISTS `view_detalle_ventas`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_detalle_ventas`  AS SELECT `dv`.`IdDetalleVenta` AS `IdDetalleVenta`, `dv`.`IdVenta` AS `IdVenta`, `dv`.`NroSerie` AS `NroSerie`, `dv`.`Producto` AS `Producto`, `p`.`Nombre` AS `Nombre`, `dv`.`Cantidad` AS `Cantidad`, `p`.`Precio` AS `precio`, `dv`.`Estado` AS `Estado` FROM (`detalle_ventas` `dv` join `productos` `p` on(`dv`.`Producto` = `p`.`IdProducto`)) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `view_ventas`
--
DROP TABLE IF EXISTS `view_ventas`;

DROP VIEW IF EXISTS `view_ventas`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_ventas`  AS SELECT `c`.`Nombre` AS `Cliente`, `u`.`Nombre` AS `Vendedor`, `v`.`Idventa` AS `IdVenta`, `v`.`FechaVenta` AS `FechaVenta`, `v`.`Monto` AS `Monto`, `v`.`Estado` AS `Estado` FROM ((`ventas` `v` join `clientes` `c` on(`v`.`IdCliente` = `c`.`IdCliente`)) join `usuarios` `u` on(`v`.`IdUsuario` = `u`.`IdUsuario`)) ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`IdCliente`);

--
-- Indices de la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  ADD PRIMARY KEY (`IdDetalleVenta`),
  ADD KEY `FK_IDPROUDUCTO_PRODUCTOS` (`Producto`),
  ADD KEY `FK_IDVENTA_VENTA` (`IdVenta`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`IdProducto`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`IdUsuario`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`Idventa`),
  ADD KEY `Cliente` (`IdCliente`),
  ADD KEY `Vendedor` (`IdUsuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `IdCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  MODIFY `IdDetalleVenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `IdProducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `IdUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `Idventa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  ADD CONSTRAINT `FK_IDPROUDUCTO_PRODUCTOS` FOREIGN KEY (`Producto`) REFERENCES `productos` (`IdProducto`),
  ADD CONSTRAINT `FK_IDVENTA_VENTA` FOREIGN KEY (`IdVenta`) REFERENCES `ventas` (`Idventa`);

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`IdCliente`) REFERENCES `clientes` (`IdCliente`),
  ADD CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`IdUsuario`) REFERENCES `usuarios` (`IdUsuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
