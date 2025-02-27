-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-02-2025 a las 01:14:10
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
-- Base de datos: `bd_ventas`
--
CREATE DATABASE IF NOT EXISTS `bd_ventas` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `bd_ventas`;

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
(1, 456214578, 'José Ramirez', 'Cll 66 #45A-53', 'Activo'),
(2, 789526314, 'Emily Hunt', 'Av Manchester 754', 'Activo'),
(3, 222222222, 'Carlos Cabrera', 'Cll 45 #45F-56', 'Activo'),
(4, 777555111, 'Jesica Parker', 'Av Las flores 45', 'Activo'),
(5, 754333214, 'Edwar James', 'Av Lancaster', 'Activo'),
(7, 777444555, 'Hector Saldarriaga', 'Cll Travesias', 'Inactivo'),
(8, 444777999, 'Pablo Perez', 'Calle Las Florez', 'Inactivo');

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
  `PrecUnidad` float NOT NULL,
  `Estado` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `detalle_ventas`
--

INSERT INTO `detalle_ventas` (`IdDetalleVenta`, `IdVenta`, `NroSerie`, `Producto`, `Cantidad`, `PrecUnidad`, `Estado`) VALUES
(3, 2, '0002', 1, 1, 9999.99, 'Cancelada'),
(4, 2, '0002', 1, 1, 9999.99, 'Cancelada'),
(5, 2, '0002', 1, 1, 9999.99, 'Cancelada'),
(6, 3, '0003', 3, 1, 270000, 'Efectiva'),
(7, 3, '0003', 3, 1, 270000, 'Efectiva'),
(8, 3, '0003', 3, 1, 270000, 'Efectiva'),
(9, 4, '0004', 1, 1, 9999.99, 'Efectiva'),
(10, 4, '0004', 1, 1, 9999.99, 'Efectiva'),
(11, 4, '0004', 1, 1, 9999.99, 'Efectiva'),
(12, 5, '0005', 3, 1, 270000, 'Efectiva'),
(13, 6, '0006', 3, 9, 270000, 'Efectiva'),
(14, 7, '0007', 1, 1, 9999.99, 'Efectiva'),
(15, 7, '0007', 3, 1, 270000, 'Efectiva'),
(16, 7, '0007', 2, 1, 35000, 'Efectiva'),
(17, 7, '0007', 3, 1, 270000, 'Efectiva');

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
(1, 'Producto de Prueba 1', 9999.99, 23, 'Existente'),
(2, 'Ram 8G (X2) Corsair Vengance', 35000, 4, 'Existente'),
(3, 'Ram 4G (x2) Tryden-Z RGB DDR4', 270000, 17, 'Existente'),
(5, 'Procesador Intel Core I5 9800 ', 650000, 199, 'Existente');

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
  `Usuario` varchar(20) NOT NULL,
  `Contrasena` varchar(255) DEFAULT NULL,
  `Role` varchar(20) DEFAULT 'No asignado',
  `Token` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`IdUsuario`, `DNI`, `Nombre`, `Telefono`, `Estado`, `Usuario`, `Contrasena`, `Role`, `Token`) VALUES
(1, '123456', 'Prueba_1', '555-58-88', 'Activo', 'Empleado_1', '', 'Vendedor', NULL),
(5, '746421', 'Admin_1', '777-77-77', 'Activo', 'Admin_1', '', 'Administrador', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

DROP TABLE IF EXISTS `ventas`;
CREATE TABLE `ventas` (
  `Idventa` int(11) NOT NULL,
  `Cliente` int(11) DEFAULT NULL,
  `Vendedor` int(11) DEFAULT NULL,
  `Serie` int(255) NOT NULL,
  `FechaVenta` date NOT NULL,
  `Monto` float NOT NULL,
  `Estado` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`Idventa`, `Cliente`, `Vendedor`, `Serie`, `FechaVenta`, `Monto`, `Estado`) VALUES
(2, 8, 5, 2, '2021-04-06', 30000, 'Cancelada'),
(3, 1, 1, 3, '2021-04-06', 810000, 'Efectiva'),
(4, 4, 1, 4, '2021-04-06', 30000, 'Efectiva'),
(5, 4, 1, 5, '2021-04-06', 270000, 'Efectiva'),
(6, 8, 1, 6, '2021-04-06', 2430000, 'Efectiva'),
(7, 3, 1, 7, '2021-04-07', 585000, 'Efectiva');

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
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_ventas`  AS SELECT `c`.`Nombre` AS `Cliente`, `u`.`Nombre` AS `Vendedor`, `v`.`Idventa` AS `IdVenta`, `v`.`FechaVenta` AS `FechaVenta`, `v`.`Monto` AS `Monto`, `v`.`Estado` AS `Estado` FROM ((`ventas` `v` join `clientes` `c` on(`v`.`Cliente` = `c`.`IdCliente`)) join `usuarios` `u` on(`v`.`Vendedor` = `u`.`IdUsuario`)) ;

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
  ADD KEY `Cliente` (`Cliente`),
  ADD KEY `Vendedor` (`Vendedor`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `IdCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  MODIFY `IdDetalleVenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `IdProducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `IdUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `Idventa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

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
  ADD CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`Cliente`) REFERENCES `clientes` (`IdCliente`),
  ADD CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`Vendedor`) REFERENCES `usuarios` (`IdUsuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
