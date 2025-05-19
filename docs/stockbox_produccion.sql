-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-05-2025 a las 19:14:44
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
-- Base de datos: `stockbox_produccion`
--
CREATE DATABASE IF NOT EXISTS `stockbox_produccion` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `stockbox_produccion`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `IdCliente` int(11) NOT NULL,
  `DNI` int(15) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Direccion` varchar(50) NOT NULL,
  `Estado` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_ventas`
--

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

CREATE TABLE `productos` (
  `IdProducto` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Precio` float DEFAULT NULL,
  `Stock` int(11) NOT NULL,
  `Estado` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

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

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_detalle_ventas`  AS SELECT `dv`.`IdDetalleVenta` AS `IdDetalleVenta`, `dv`.`IdVenta` AS `IdVenta`, `dv`.`NroSerie` AS `NroSerie`, `dv`.`Producto` AS `Producto`, `p`.`Nombre` AS `Nombre`, `dv`.`Cantidad` AS `Cantidad`, `p`.`Precio` AS `precio`, `dv`.`Estado` AS `Estado` FROM (`stockbox_desarrollo`.`detalle_ventas` `dv` join `stockbox_desarrollo`.`productos` `p` on(`dv`.`Producto` = `p`.`IdProducto`)) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `view_ventas`
--
DROP TABLE IF EXISTS `view_ventas`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_ventas`  AS SELECT `c`.`Nombre` AS `Cliente`, `u`.`Nombre` AS `Vendedor`, `v`.`Idventa` AS `IdVenta`, `v`.`FechaVenta` AS `FechaVenta`, `v`.`Monto` AS `Monto`, `v`.`Estado` AS `Estado` FROM ((`stockbox_desarrollo`.`ventas` `v` join `stockbox_desarrollo`.`clientes` `c` on(`v`.`IdCliente` = `c`.`IdCliente`)) join `stockbox_desarrollo`.`usuarios` `u` on(`v`.`IdUsuario` = `u`.`IdUsuario`)) ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`IdCliente`),
  ADD UNIQUE KEY `DNI` (`DNI`);

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
  MODIFY `IdCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  MODIFY `IdDetalleVenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `IdProducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `IdUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `Idventa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

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
