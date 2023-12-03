-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 02-Dez-2023 às 21:04
-- Versão do servidor: 10.4.27-MariaDB
-- versão do PHP: 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `componente`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `categoria`
--

CREATE TABLE `categoria` (
  `Id _Categoria` int(11) NOT NULL,
  `Descrição` varchar(300) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `Id_Cliente` int(11) NOT NULL,
  `Nome` varchar(200) NOT NULL,
  `DataNasc` date NOT NULL,
  `Usuário` varchar(300) NOT NULL,
  `Senha` varchar(40) NOT NULL,
  `Email` varchar(200) NOT NULL,
  `CPF` varchar(16) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`Id_Cliente`, `Nome`, `DataNasc`, `Usuário`, `Senha`, `Email`, `CPF`) VALUES
(1, 'Yasmin', '2006-06-21', 'Yasmin', '123', 'yasmin123@gmail.com', 'ervqer'),
(2, 'Leticia', '2006-10-25', 'Leticia', '123', 'leticia123@gmail.com', 'ervqer');

-- --------------------------------------------------------

--
-- Estrutura da tabela `componente`
--

CREATE TABLE `componente` (
  `Id_Comp` int(11) NOT NULL,
  `Nome_Comp` varchar(200) NOT NULL,
  `Categoria_Num` int(11) NOT NULL,
  `Preço` float NOT NULL,
  `Estoque` int(11) NOT NULL,
  `Descrição` varchar(80) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `componente`
--

INSERT INTO `componente` (`Id_Comp`, `Nome_Comp`, `Categoria_Num`, `Preço`, `Estoque`, `Descrição`) VALUES
(1, 'Computador MSI', 2, 10, 12, 'Computador gamer MSI'),
(2, 'Teclado MSI', 5, 13, 20, 'Teclado gamer MSI'),
(3, 'Mouse MSI', 1, 5, 50, ' Mouse gamer MSI'),
(4, 'PC MSI', 3, 20, 15, 'PC gamer MSI');

-- --------------------------------------------------------

--
-- Estrutura da tabela `controle`
--

CREATE TABLE `controle` (
  `ID_Func` int(11) NOT NULL,
  `ID_Componente` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionário`
--

CREATE TABLE `funcionário` (
  `ID_Func` int(11) NOT NULL,
  `Nome_Func` varchar(50) NOT NULL,
  `Usuário` varchar(50) NOT NULL,
  `Nivel_ID` int(11) NOT NULL,
  `Senha` varchar(30) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Data_Nasc` date NOT NULL,
  `CPF` varchar(14) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `funcionário`
--

INSERT INTO `funcionário` (`ID_Func`, `Nome_Func`, `Usuário`, `Nivel_ID`, `Senha`, `Email`, `Data_Nasc`, `CPF`) VALUES
(1, 'Yasmin', 'Yasmin', 1, '123', 'yasmin123@gmail.com', '2006-06-21', '12341234213');

-- --------------------------------------------------------

--
-- Estrutura da tabela `nível-restrição`
--

CREATE TABLE `nível-restrição` (
  `Nivel_ID` int(11) NOT NULL,
  `Descrição` varchar(80) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `registro_compra`
--

CREATE TABLE `registro_compra` (
  `Id_Componente` int(11) NOT NULL,
  `Id_Cliente` int(11) NOT NULL,
  `Data_compra` date NOT NULL,
  `Quantidade` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `registro_compra`
--

INSERT INTO `registro_compra` (`Id_Componente`, `Id_Cliente`, `Data_compra`, `Quantidade`) VALUES
(2, 2, '2023-10-30', 1);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`Id _Categoria`);

--
-- Índices para tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`Id_Cliente`);

--
-- Índices para tabela `componente`
--
ALTER TABLE `componente`
  ADD PRIMARY KEY (`Id_Comp`);

--
-- Índices para tabela `funcionário`
--
ALTER TABLE `funcionário`
  ADD PRIMARY KEY (`ID_Func`);

--
-- Índices para tabela `nível-restrição`
--
ALTER TABLE `nível-restrição`
  ADD PRIMARY KEY (`Nivel_ID`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `categoria`
--
ALTER TABLE `categoria`
  MODIFY `Id _Categoria` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `cliente`
--
ALTER TABLE `cliente`
  MODIFY `Id_Cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `componente`
--
ALTER TABLE `componente`
  MODIFY `Id_Comp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `funcionário`
--
ALTER TABLE `funcionário`
  MODIFY `ID_Func` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `nível-restrição`
--
ALTER TABLE `nível-restrição`
  MODIFY `Nivel_ID` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
