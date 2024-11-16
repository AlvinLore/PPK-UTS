-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 16 Nov 2024 pada 21.17
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pendaftaran_ukm`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `leaders`
--

CREATE TABLE `leaders` (
  `id` bigint(20) NOT NULL,
  `leader_class` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `nim` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `leaders`
--

INSERT INTO `leaders` (`id`, `leader_class`, `name`, `nim`) VALUES
(1, '3SI2', 'Kurniawan', '22224444'),
(2, '3SI1', 'Riduan', '22221111');

-- --------------------------------------------------------

--
-- Struktur dari tabel `registrations`
--

CREATE TABLE `registrations` (
  `id` bigint(20) NOT NULL,
  `registration_date` datetime(6) NOT NULL,
  `status` enum('APPROVED','PENDING','REJECTED') DEFAULT NULL,
  `ukm_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `ukms`
--

CREATE TABLE `ukms` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `nim` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `ukms`
--

INSERT INTO `ukms` (`id`, `description`, `name`, `nim`) VALUES
(1, 'UKM untuk segala permainan Esport', 'Esport', '22224444');

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `nim` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_class` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id`, `email`, `name`, `nim`, `password`, `user_class`) VALUES
(1, 'user@gmail.com', 'userUpdate', '22221234', '$2a$10$1GbIJc3U5KDVXvl72m9boeQgwMX47jXtQ4T.H.VR4LoQI1eVvtEwq', '3SI1');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `leaders`
--
ALTER TABLE `leaders`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKj024y09hyywtwpdbrghtgldji` (`nim`);

--
-- Indeks untuk tabel `registrations`
--
ALTER TABLE `registrations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9n4pwx7a9uoj4kjk5rs259csy` (`ukm_id`),
  ADD KEY `FKl2iby9n9hp8jwkfj8i96pkxpi` (`user_id`);

--
-- Indeks untuk tabel `ukms`
--
ALTER TABLE `ukms`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKrg8pf9wov2vwlwiddoecr8k9w` (`nim`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  ADD UNIQUE KEY `UKh5k5eqgbwmq6sh7k3nqfr1439` (`nim`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `leaders`
--
ALTER TABLE `leaders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT untuk tabel `registrations`
--
ALTER TABLE `registrations`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `ukms`
--
ALTER TABLE `ukms`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `registrations`
--
ALTER TABLE `registrations`
  ADD CONSTRAINT `FK9n4pwx7a9uoj4kjk5rs259csy` FOREIGN KEY (`ukm_id`) REFERENCES `ukms` (`id`),
  ADD CONSTRAINT `FKl2iby9n9hp8jwkfj8i96pkxpi` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Ketidakleluasaan untuk tabel `ukms`
--
ALTER TABLE `ukms`
  ADD CONSTRAINT `FKrg8pf9wov2vwlwiddoecr8k9w` FOREIGN KEY (`nim`) REFERENCES `leaders` (`nim`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
