-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-04-2021 a las 16:10:52
-- Versión del servidor: 10.4.18-MariaDB
-- Versión de PHP: 8.0.3

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `Id_Cliente` int(11) NOT NULL,
  `Cedula` int(15) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Direccion` varchar(50) NOT NULL,
  `Estado` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`Id_Cliente`, `Cedula`, `Nombre`, `Direccion`, `Estado`) VALUES
(1, 456214578, 'José Ramirez', 'Cll 66 #45A-53', 'Activo'),
(2, 789526314, 'Emily Hunt', 'Av Manchester 754', 'Activo'),
(3, 222222222, 'Carlos Cabrera', 'Cll 45 #45F-56', 'Activo'),
(4, 777555111, 'Jesica Parker', 'Av Las flores 45', 'Activo'),
(5, 754333214, 'Edwar James', 'Av Lancaster', 'Activo'),
(7, 777444555, 'Hector Saldarriaga', 'Cll Travesias', 'Inactivo'),
(8, 444777999, 'Pablo Perez', 'Calle Las Florez', 'Inactivo'),
(10, 775522777, 'Eric Stone', 'Av newville 458', 'Activo'),
(11, 999111777, 'Emily Vanegas', 'Cll Villa Diego 87', 'Activo'),
(12, 777555666, 'Camilo Vazques', 'Av Villa Nueva 785', 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_ventas`
--

CREATE TABLE `detalle_ventas` (
  `Id_Detalle` int(11) NOT NULL,
  `Id_Venta` int(11) NOT NULL,
  `NroSerie` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `Producto` int(11) NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `PrecUnidad` float NOT NULL,
  `Estado` varchar(15) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `detalle_ventas`
--

INSERT INTO `detalle_ventas` (`Id_Detalle`, `Id_Venta`, `NroSerie`, `Producto`, `Cantidad`, `PrecUnidad`, `Estado`) VALUES
(1, 1, '0001', 5, 1, 650000, 'Cancelada'),
(2, 1, '0001', 3, 2, 270000, 'Cancelada'),
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

CREATE TABLE `productos` (
  `Id_Producto` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Precio` float DEFAULT NULL,
  `Stock` int(11) NOT NULL,
  `Estado` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`Id_Producto`, `Nombre`, `Precio`, `Stock`, `Estado`) VALUES
(1, 'Producto de Prueba 1', 9999.99, 23, 'Existente'),
(2, 'Ram 8G (X2) Corsair Vengance', 35000, 4, 'Existente'),
(3, 'Ram 4G (x2) Tryden-Z RGB DDR4', 270000, 17, 'Existente'),
(5, 'Procesador Intel Core I5 9800 ', 650000, 199, 'Existente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `Id_Vendedor` int(11) NOT NULL,
  `Cedula` varchar(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Telefono` varchar(20) NOT NULL,
  `Estado` varchar(10) NOT NULL,
  `User_2` varchar(15) NOT NULL,
  `Roll` varchar(15) DEFAULT 'No asignado'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`Id_Vendedor`, `Cedula`, `Nombre`, `Telefono`, `Estado`, `User_2`, `Roll`) VALUES
(1, '123456', 'Prueba_1', '555-58-88', 'Activo', 'Empleado_1', 'Vendedor'),
(2, '455122', 'Prueba_2', '888-55-44', 'Inactivo', 'Empleado_2', 'Vendedor'),
(5, '746421', 'Admin_1', '777-77-77', 'Activo', 'Admin_1', 'Administrador'),
(6, '104074', 'AdminBDA', '888-88-88', 'Activo', 'BDA_1', 'No asignado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `Id_venta` int(11) NOT NULL,
  `Cliente` int(11) DEFAULT NULL,
  `Vendedor` int(11) DEFAULT NULL,
  `Serie` int(255) NOT NULL,
  `Fecha_Venta` date NOT NULL,
  `Monto` float NOT NULL,
  `Estado` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`Id_venta`, `Cliente`, `Vendedor`, `Serie`, `Fecha_Venta`, `Monto`, `Estado`) VALUES
(1, 12, 5, 1, '2021-04-06', 1190000, 'Cancelada'),
(2, 8, 5, 2, '2021-04-06', 30000, 'Cancelada'),
(3, 1, 1, 3, '2021-04-06', 810000, 'Efectiva'),
(4, 4, 1, 4, '2021-04-06', 30000, 'Efectiva'),
(5, 4, 1, 5, '2021-04-06', 270000, 'Efectiva'),
(6, 8, 1, 6, '2021-04-06', 2430000, 'Efectiva'),
(7, 3, 1, 7, '2021-04-07', 585000, 'Efectiva');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`Id_Cliente`);

--
-- Indices de la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  ADD PRIMARY KEY (`Id_Detalle`),
  ADD KEY `FK_IDPROUDUCTO_PRODUCTOS` (`Producto`),
  ADD KEY `FK_IDVENTA_VENTA` (`Id_Venta`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`Id_Producto`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`Id_Vendedor`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`Id_venta`),
  ADD KEY `Cliente` (`Cliente`),
  ADD KEY `Vendedor` (`Vendedor`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `Id_Cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  MODIFY `Id_Detalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `Id_Producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `Id_Vendedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `Id_venta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  ADD CONSTRAINT `FK_IDPROUDUCTO_PRODUCTOS` FOREIGN KEY (`Producto`) REFERENCES `productos` (`Id_Producto`),
  ADD CONSTRAINT `FK_IDVENTA_VENTA` FOREIGN KEY (`Id_Venta`) REFERENCES `ventas` (`Id_venta`);

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`Cliente`) REFERENCES `clientes` (`Id_Cliente`),
  ADD CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`Vendedor`) REFERENCES `usuarios` (`Id_Vendedor`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
