-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Servidor: db
-- Tiempo de generación: 26-08-2026 a las 12:54:12
-- Versión del servidor: 8.1.0
-- Versión de PHP: 8.2.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `akma`
--
CREATE DATABASE IF NOT EXISTS `akma` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `akma`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `KEY`
--

DROP TABLE IF EXISTS `KEY`;
CREATE TABLE `KEY` (
  `id` binary(16) NOT NULL,
  `name` varchar(100) NOT NULL,
  `user_id` binary(16) NOT NULL,
  `key_hash` varchar(255) NOT NULL,
  `key_prefix` varchar(255) NOT NULL,
  `expires_at` date DEFAULT NULL,
  `revoked_at` date DEFAULT NULL,
  `last_used_at` date DEFAULT NULL,
  `created_at` date NOT NULL,
  `updated_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `KEY_PERMISSION`
--

DROP TABLE IF EXISTS `KEY_PERMISSION`;
CREATE TABLE `KEY_PERMISSION` (
  `key_id` binary(16) NOT NULL,
  `permission_id` binary(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PERMISSION`
--

DROP TABLE IF EXISTS `PERMISSION`;
CREATE TABLE `PERMISSION` (
  `id` binary(16) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USER`
--

DROP TABLE IF EXISTS `USER`;
CREATE TABLE `USER` (
  `id` binary(16) NOT NULL,
  `created_at` date NOT NULL,
  `updated_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `KEY`
--
ALTER TABLE `KEY`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `key_hash` (`key_hash`),
  ADD KEY `FK_user` (`user_id`);

--
-- Indices de la tabla `KEY_PERMISSION`
--
ALTER TABLE `KEY_PERMISSION`
  ADD PRIMARY KEY (`key_id`,`permission_id`),
  ADD KEY `FK_Permission` (`permission_id`);

--
-- Indices de la tabla `PERMISSION`
--
ALTER TABLE `PERMISSION`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `USER`
--
ALTER TABLE `USER`
  ADD PRIMARY KEY (`id`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `KEY`
--
ALTER TABLE `KEY`
  ADD CONSTRAINT `FK_user` FOREIGN KEY (`user_id`) REFERENCES `USER` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `KEY_PERMISSION`
--
ALTER TABLE `KEY_PERMISSION`
  ADD CONSTRAINT `FK_Key` FOREIGN KEY (`key_id`) REFERENCES `KEY` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_Permission` FOREIGN KEY (`permission_id`) REFERENCES `PERMISSION` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
