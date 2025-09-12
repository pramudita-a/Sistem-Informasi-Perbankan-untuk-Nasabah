-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 09, 2025 at 08:01 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bank_2211500919`
--

-- --------------------------------------------------------

--
-- Table structure for table `jenis_2211500919`
--

CREATE TABLE `jenis_2211500919` (
  `Kode_Rekening` char(2) NOT NULL,
  `Jenis_Rekening` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `jenis_2211500919`
--

INSERT INTO `jenis_2211500919` (`Kode_Rekening`, `Jenis_Rekening`) VALUES
('01', 'Tabungan'),
('02', 'Deposito'),
('03', 'Giro');

-- --------------------------------------------------------

--
-- Table structure for table `nasabah_2211500919`
--

CREATE TABLE `nasabah_2211500919` (
  `No_Rekening` char(12) NOT NULL,
  `Nama_Nasabah` varchar(50) DEFAULT NULL,
  `Jenis_Rekening` varchar(10) DEFAULT NULL,
  `Saldo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `jenis_2211500919`
--
ALTER TABLE `jenis_2211500919`
  ADD PRIMARY KEY (`Kode_Rekening`);

--
-- Indexes for table `nasabah_2211500919`
--
ALTER TABLE `nasabah_2211500919`
  ADD PRIMARY KEY (`No_Rekening`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
