-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-07-2026 a las 01:05:34
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
-- Base de datos: `articulos2_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulos`
--

CREATE TABLE `articulos` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `categoria_id` bigint(20) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `en_stock` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `articulos`
--

INSERT INTO `articulos` (`id`, `nombre`, `precio`, `categoria_id`, `descripcion`, `en_stock`) VALUES
(1, 'Detergente blanco x1L', 5500, 1, NULL, NULL),
(2, 'Detergente blanco x5L', 2500, 1, NULL, NULL),
(3, 'Lavarropas 12Lts plateada', 850000, 2, NULL, NULL),
(5, 'Lavarropas DREAM 10Lts blanca', 924000, 2, 'Lavarropas con 10 litros de capacidad de color negro.', 15),
(6, 'Secarropas KO-I-NOR 6Lts blanca', 340000, 2, 'Secarropas de 6 litros de capacidad. Color Blanco.', 10),
(7, 'Grasa siliconada PEPITO x100g', 15000, 1, 'Grasa con silicona para impermeabilizar superficies.', 57),
(8, 'Grasa siliconada PEPITO x250g', 40000, 1, 'Grasa con silicona para impermeabilizar superficies.', 40),
(9, 'Silla playera mediana', 35000, 4, 'Silla para exterior de metal reforzado.', 30),
(10, 'Mesa cuadrada de plastico 1x1', 50000, 4, 'Mesa para exterior de 1x1 en plastico blanco.', 21);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id`, `nombre`, `descripcion`) VALUES
(1, 'Limpieza', 'Artículos para la limpieza del hogar/oficina'),
(2, 'Electrodomésticos', 'Producto electricos para el hogar'),
(3, 'Electrónica', 'Artículos electrónicos varios'),
(4, 'Jardinería', 'Productos para el jardín/parque en exterior e interiores.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` bigint(20) NOT NULL,
  `activo` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `fecha_creacion` datetime(6) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `rol` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `activo`, `email`, `fecha_creacion`, `nombre`, `password`, `rol`) VALUES
(1, b'1', 'admin@example.com', '2026-07-10 17:22:14.000000', 'Admin', '$2a$10$5a1NzlMauDNfzPj6om5gyuTxf1MCTVLcivQul50oZocPXw89myHbu', 'ADMIN'),
(2, b'1', 'juan.perez@example.com', '2026-07-10 18:01:08.000000', 'Juan Pérez', '$2a$10$ZAd5tQykg16hKyhoyhe6FOshyCi1mU2exNnQGCFIC.aEgwtrbvrZK', 'USER'),
(3, b'1', 'billy@gmail.com', '2026-07-10 19:54:00.000000', 'Billy Boy', '$2a$10$mNWocRez08ngfAcHNVTWuO6lLFScPBK4tGcEmbZNevZAmgjEX8PkC', 'USER'),
(4, b'1', 'admin@gmail.com.', '2026-07-10 20:47:25.000000', 'Admin', '$2a$10$d2mbxOdfCK9Ytc0f59RaEOFY64yZzZQTA8vxgZXPTi8vTuRcN9m5q', 'ADMIN'),
(5, b'1', 'admin@gmail.com', '2026-07-10 21:38:53.000000', 'Admin', '$2a$10$FcEnKTgRYgScCJ1uX.lJZefYklu/boe.OdyRnHM83Ckbndh5McFRG', 'ADMIN');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `articulos`
--
ALTER TABLE `articulos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKji1k64mta8mfwytpi8f57fvrc` (`categoria_id`);

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKkfsp0s1tflm1cwlj8idhqsad0` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `articulos`
--
ALTER TABLE `articulos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `articulos`
--
ALTER TABLE `articulos`
  ADD CONSTRAINT `FKji1k64mta8mfwytpi8f57fvrc` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
