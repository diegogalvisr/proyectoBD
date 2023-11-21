-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-11-2023 a las 12:24:17
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdcafeinternet`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `idCliente` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `fechaReg` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`idCliente`, `nombre`, `email`, `fechaReg`) VALUES
(106, 'WILMER ALEXANDER', 'wilmer@admin.com', '2023-11-16'),
(107, 'Pablo Romero', 'pablo@romero.com', '2023-11-19'),
(108, 'pablo romero', 'diego@romero.com', '2023-11-19'),
(109, 'DIEGO GALVI', 'diego@galvis.com', '2023-11-19'),
(110, 'sasas', 'sqasasa', '2023-11-19'),
(111, 'Diego Galvis', 'dasfdjhgf', '2023-11-19'),
(113, 'scgfdh', 'ghgfdhdgfjf', '2023-11-19'),
(114, 'diegog', 'dasgfd', '2023-11-19'),
(115, 'fsdgdfs', 'fgdhgdgh@', '2023-11-19'),
(116, 'sdas', 'sad@', '2023-11-19'),
(117, 'sad@', 'sad@', '2023-11-19'),
(118, 'sad@', 'sad@', '2023-11-19'),
(119, 'sad@', 'sad@', '2023-11-19'),
(121, 'sad@', 'sad@', '2023-11-19'),
(122, 'sad@', 'sad@', '2023-11-19'),
(123, 'sad@', 'sad@', '2023-11-19'),
(124, 'sad@', 'sad@', '2023-11-19'),
(125, 'sad@', 'sad@', '2023-11-19'),
(126, 'sad@', 'sad@', '2023-11-19'),
(127, 'YORGEN', 'diego@galvis.com', '2023-11-19');

--
-- Disparadores `cliente`
--
DELIMITER $$
CREATE TRIGGER `actualizar_fechaRegistro` BEFORE INSERT ON `cliente` FOR EACH ROW BEGIN
    SET NEW.fechaReg = NOW();
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `computador`
--

CREATE TABLE `computador` (
  `idCompu` int(11) NOT NULL,
  `numSerie` varchar(20) NOT NULL,
  `modelo` varchar(50) NOT NULL,
  `estado` varchar(10) NOT NULL,
  `precioHora` decimal(20,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `consola`
--

CREATE TABLE `consola` (
  `idConsola` int(11) NOT NULL,
  `numSerie` varchar(20) NOT NULL,
  `modelo` varchar(50) NOT NULL,
  `estado` varchar(10) NOT NULL,
  `precioHora` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipo`
--

CREATE TABLE `equipo` (
  `idEquipo` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `idTorneo` int(11) NOT NULL,
  `idCliente` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inscripcion`
--

CREATE TABLE `inscripcion` (
  `idInscripcion` int(11) NOT NULL,
  `idTorneo` int(11) NOT NULL,
  `idEquipo` int(11) NOT NULL,
  `iEqpGAN` int(11) DEFAULT NULL,
  `idComputador` int(11) DEFAULT NULL,
  `idConsola` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Disparadores `inscripcion`
--
DELIMITER $$
CREATE TRIGGER `check_inscripcion_trigger` BEFORE INSERT ON `inscripcion` FOR EACH ROW BEGIN
    IF NOT ((NEW.idComputador IS NOT NULL AND NEW.idConsola IS NULL) OR
            (NEW.idComputador IS NULL AND NEW.idConsola IS NOT NULL)) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Debe haber exactamente una columna con datos y la otra nula.';
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `juego`
--

CREATE TABLE `juego` (
  `idJuego` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `genero` varchar(50) NOT NULL,
  `plataforma` varchar(50) NOT NULL,
  `clasificacion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `idReserva` int(11) NOT NULL,
  `idCliente` int(11) NOT NULL,
  `idComputador` int(11) DEFAULT NULL,
  `idConsola` int(11) DEFAULT NULL,
  `fechaHoraI` datetime NOT NULL,
  `fechaHoraF` datetime NOT NULL,
  `monto` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Disparadores `reserva`
--
DELIMITER $$
CREATE TRIGGER `check_inscripcion_reserva_trigger` BEFORE INSERT ON `reserva` FOR EACH ROW BEGIN
    IF NOT ((NEW.idComputador IS NOT NULL AND NEW.idConsola IS NULL) OR
            (NEW.idComputador IS NULL AND NEW.idConsola IS NOT NULL)) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Debe haber exactamente una columna con datos y la otra nula.';
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `torneo`
--

CREATE TABLE `torneo` (
  `idTorneo` int(11) NOT NULL,
  `idJuego` int(11) NOT NULL,
  `fechaHoraI` datetime NOT NULL,
  `fechaHoraF` datetime NOT NULL,
  `numParticipantes` int(11) NOT NULL,
  `premio` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`idCliente`);

--
-- Indices de la tabla `computador`
--
ALTER TABLE `computador`
  ADD PRIMARY KEY (`idCompu`);

--
-- Indices de la tabla `consola`
--
ALTER TABLE `consola`
  ADD PRIMARY KEY (`idConsola`);

--
-- Indices de la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD PRIMARY KEY (`idEquipo`),
  ADD KEY `TORNEO_EQUIPO_FK` (`idTorneo`),
  ADD KEY `CTE_EQP_FK` (`idCliente`);

--
-- Indices de la tabla `inscripcion`
--
ALTER TABLE `inscripcion`
  ADD PRIMARY KEY (`idInscripcion`),
  ADD KEY `TORNEO_INSCRIPCION_FK` (`idTorneo`),
  ADD KEY `EQUIPO_INSCRIPCION_FK` (`idEquipo`),
  ADD KEY `EQPG_INS_fK` (`iEqpGAN`),
  ADD KEY `CTD_INS_FK` (`idComputador`),
  ADD KEY `CNA_INS_FK` (`idConsola`);

--
-- Indices de la tabla `juego`
--
ALTER TABLE `juego`
  ADD PRIMARY KEY (`idJuego`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`idReserva`),
  ADD KEY `CLIENTE_RESERVA_FK` (`idCliente`),
  ADD KEY `COMPUTADOR_RESERVA_FK` (`idComputador`),
  ADD KEY `CONSOLA_RESERVA_FK` (`idConsola`);

--
-- Indices de la tabla `torneo`
--
ALTER TABLE `torneo`
  ADD PRIMARY KEY (`idTorneo`),
  ADD KEY `JUEGO_TORNEO_FK` (`idJuego`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=130;

--
-- AUTO_INCREMENT de la tabla `computador`
--
ALTER TABLE `computador`
  MODIFY `idCompu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=103;

--
-- AUTO_INCREMENT de la tabla `consola`
--
ALTER TABLE `consola`
  MODIFY `idConsola` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=103;

--
-- AUTO_INCREMENT de la tabla `equipo`
--
ALTER TABLE `equipo`
  MODIFY `idEquipo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- AUTO_INCREMENT de la tabla `inscripcion`
--
ALTER TABLE `inscripcion`
  MODIFY `idInscripcion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- AUTO_INCREMENT de la tabla `juego`
--
ALTER TABLE `juego`
  MODIFY `idJuego` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=103;

--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `idReserva` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `torneo`
--
ALTER TABLE `torneo`
  MODIFY `idTorneo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=105;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD CONSTRAINT `CTE_EQP_FK` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`),
  ADD CONSTRAINT `TRO_EQP_FK` FOREIGN KEY (`idTorneo`) REFERENCES `torneo` (`idTorneo`);

--
-- Filtros para la tabla `inscripcion`
--
ALTER TABLE `inscripcion`
  ADD CONSTRAINT `CNA_INS_FK` FOREIGN KEY (`idConsola`) REFERENCES `consola` (`idConsola`),
  ADD CONSTRAINT `CPR_INS_FK` FOREIGN KEY (`idComputador`) REFERENCES `computador` (`idCompu`),
  ADD CONSTRAINT `EQPG_INS_FK` FOREIGN KEY (`iEqpGAN`) REFERENCES `equipo` (`idEquipo`),
  ADD CONSTRAINT `EQP_INS_FK` FOREIGN KEY (`idEquipo`) REFERENCES `equipo` (`idEquipo`),
  ADD CONSTRAINT `TRO_INS_FK` FOREIGN KEY (`idTorneo`) REFERENCES `torneo` (`idTorneo`);

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `CLIENTE_RESERVA_FK` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`),
  ADD CONSTRAINT `COMPUTADOR_RESERVA_FK` FOREIGN KEY (`idComputador`) REFERENCES `computador` (`idCompu`),
  ADD CONSTRAINT `CONSOLA_RESERVA_FK` FOREIGN KEY (`idConsola`) REFERENCES `consola` (`idConsola`);

--
-- Filtros para la tabla `torneo`
--
ALTER TABLE `torneo`
  ADD CONSTRAINT `JUEGO_TORNEO_FK` FOREIGN KEY (`idJuego`) REFERENCES `juego` (`idJuego`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
