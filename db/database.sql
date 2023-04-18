-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Počítač: 127.0.0.1
-- Vytvořeno: Úte 18. dub 2023, 20:16
-- Verze serveru: 10.4.28-MariaDB
-- Verze PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáze: `vypis_prace_database`
--

-- --------------------------------------------------------

--
-- Struktura tabulky `vypis_prace`
--

CREATE TABLE `vypis_prace` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `timeFrom` time NOT NULL,
  `timeTo` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `vypis_prace`
--

INSERT INTO `vypis_prace` (`id`, `date`, `timeFrom`, `timeTo`) VALUES
(16, '2023-04-15', '08:00:00', '14:00:00'),
(18, '2023-04-18', '09:45:00', '18:25:00'),
(19, '2023-04-19', '01:00:00', '23:00:00');

--
-- Indexy pro exportované tabulky
--

--
-- Indexy pro tabulku `vypis_prace`
--
ALTER TABLE `vypis_prace`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pro tabulky
--

--
-- AUTO_INCREMENT pro tabulku `vypis_prace`
--
ALTER TABLE `vypis_prace`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
