-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-01-2019 a las 02:27:36
-- Versión del servidor: 10.1.25-MariaDB
-- Versión de PHP: 7.1.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyecto_tienda`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

CREATE TABLE `administrador` (
  `cedula` varchar(10) NOT NULL,
  `nombres` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `usuario` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `administrador`
--

INSERT INTO `administrador` (`cedula`, `nombres`, `apellidos`, `correo`, `telefono`, `usuario`) VALUES
('056156123', 'dkgoidj', 'kskfljd', 'sjfdgi', '0051563', 'user1'),
('1', 'a', 'a', 'a', 'a', 'usuario'),
('11', 'a', 'a', 'a', 'a', 'usuario');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `calificacion_producto`
--

CREATE TABLE `calificacion_producto` (
  `id_calificacion_producto` int(11) NOT NULL,
  `cedula_comprador` varchar(10) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `calificacion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `calificacion_vendedor`
--

CREATE TABLE `calificacion_vendedor` (
  `id_calificacion_vendedor` int(11) NOT NULL,
  `cedula_comprador` varchar(10) NOT NULL,
  `cedula_vendedor` varchar(10) NOT NULL,
  `calificacion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

CREATE TABLE `compra` (
  `id_compra` int(11) NOT NULL,
  `cedulaComprador` varchar(10) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comprador`
--

CREATE TABLE `comprador` (
  `cedula` varchar(10) NOT NULL,
  `nombres` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `usuario` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id_pedido` int(11) NOT NULL,
  `id_compra` int(11) NOT NULL,
  `fecha_envio` date NOT NULL,
  `fecha_entrega` date NOT NULL,
  `estado` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id_producto` int(11) NOT NULL,
  `cedula_vendedor` varchar(10) NOT NULL,
  `calificacion_total` int(11) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `precio` float NOT NULL,
  `nombre_producto` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id_producto`, `cedula_vendedor`, `calificacion_total`, `descripcion`, `precio`, `nombre_producto`) VALUES
(1, '1', 1, '1', 1, '1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `usuario` varchar(20) NOT NULL,
  `contrasenia` varchar(20) NOT NULL,
  `tipo` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`usuario`, `contrasenia`, `tipo`) VALUES
('a', 'a', 'administrador'),
('no', 'no', 'comprador'),
('user1', 'user1', 'administrador'),
('usuario', '15202', 'administrador'),
('usuario1', 'user1', 'vendedor'),
('v', 'v', 'vendedor');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vendedor`
--

CREATE TABLE `vendedor` (
  `cedula` varchar(10) NOT NULL,
  `nombres` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `calificacion_total` int(11) NOT NULL,
  `usuario` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `vendedor`
--

INSERT INTO `vendedor` (`cedula`, `nombres`, `apellidos`, `correo`, `telefono`, `calificacion_total`, `usuario`) VALUES
('1', 'adan', 'n', 'a', 'a', 1, 'user1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--


CREATE TABLE `venta` (
  `vendedor` varchar(15) DEFAULT NULL,
  `comprador` varchar(15) DEFAULT NULL,
  `id` int(100) NOT NULL,
  `cantidad` int(10) NOT NULL,
  `precio` float(10),
  `producto` varchar(20)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `venta`  VALUES
('v', 'abc', 1, 5, 100.1, `agua`);


-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD PRIMARY KEY (`cedula`),
  ADD KEY `usuario` (`usuario`);

--
-- Indices de la tabla `calificacion_producto`
--
ALTER TABLE `calificacion_producto`
  ADD PRIMARY KEY (`id_calificacion_producto`),
  ADD KEY `cedula_comprador` (`cedula_comprador`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indices de la tabla `calificacion_vendedor`
--
ALTER TABLE `calificacion_vendedor`
  ADD PRIMARY KEY (`id_calificacion_vendedor`),
  ADD KEY `cedula_comprador` (`cedula_comprador`),
  ADD KEY `cedula_vendedor` (`cedula_vendedor`);

--
-- Indices de la tabla `compra`
--
ALTER TABLE `compra`
  ADD PRIMARY KEY (`id_compra`),
  ADD KEY `cedulaComprador` (`cedulaComprador`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indices de la tabla `comprador`
--
ALTER TABLE `comprador`
  ADD PRIMARY KEY (`cedula`),
  ADD KEY `usuario` (`usuario`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id_pedido`),
  ADD KEY `id_compra` (`id_compra`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `cedula_vendedor` (`cedula_vendedor`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`usuario`);

--
-- Indices de la tabla `vendedor`
--
ALTER TABLE `vendedor`
  ADD PRIMARY KEY (`cedula`),
  ADD KEY `usuario` (`usuario`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `calificacion_producto`
--
ALTER TABLE `calificacion_producto`
  MODIFY `id_calificacion_producto` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `calificacion_vendedor`
--
ALTER TABLE `calificacion_vendedor`
  MODIFY `id_calificacion_vendedor` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `compra`
--
ALTER TABLE `compra`
  MODIFY `id_compra` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id_pedido` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD CONSTRAINT `administrador_ibfk_1` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`usuario`);

--
-- Filtros para la tabla `calificacion_producto`
--
ALTER TABLE `calificacion_producto`
  ADD CONSTRAINT `calificacion_producto_ibfk_1` FOREIGN KEY (`cedula_comprador`) REFERENCES `comprador` (`cedula`),
  ADD CONSTRAINT `calificacion_producto_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`);

--
-- Filtros para la tabla `calificacion_vendedor`
--
ALTER TABLE `calificacion_vendedor`
  ADD CONSTRAINT `calificacion_vendedor_ibfk_1` FOREIGN KEY (`cedula_comprador`) REFERENCES `comprador` (`cedula`),
  ADD CONSTRAINT `calificacion_vendedor_ibfk_2` FOREIGN KEY (`cedula_vendedor`) REFERENCES `vendedor` (`cedula`);

--
-- Filtros para la tabla `compra`
--
ALTER TABLE `compra`
  ADD CONSTRAINT `compra_ibfk_1` FOREIGN KEY (`cedulaComprador`) REFERENCES `comprador` (`cedula`),
  ADD CONSTRAINT `compra_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`);

--
-- Filtros para la tabla `comprador`
--
ALTER TABLE `comprador`
  ADD CONSTRAINT `comprador_ibfk_1` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`usuario`);

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`id_compra`) REFERENCES `compra` (`id_compra`);

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`cedula_vendedor`) REFERENCES `vendedor` (`cedula`);

--
-- Filtros para la tabla `vendedor`
--
ALTER TABLE `vendedor`
  ADD CONSTRAINT `vendedor_ibfk_1` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`usuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
