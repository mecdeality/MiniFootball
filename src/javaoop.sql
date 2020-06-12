-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Хост: localhost:3306
-- Время создания: Июн 11 2020 г., 21:55
-- Версия сервера: 8.0.18
-- Версия PHP: 7.3.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `javaoop`
--

-- --------------------------------------------------------

--
-- Структура таблицы `allcoaches`
--

CREATE TABLE `allcoaches` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `surname` varchar(250) NOT NULL,
  `exp` int(11) NOT NULL,
  `salary` int(11) NOT NULL,
  `team_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `allcoaches`
--

INSERT INTO `allcoaches` (`id`, `name`, `surname`, `exp`, `salary`, `team_id`) VALUES
(4, 'Tofik', 'Asala', 5, 65, 7),
(5, 'James', 'Bond', 4, 69, 8),
(6, 'Test', 'Test', 4, 54, 9);

-- --------------------------------------------------------

--
-- Структура таблицы `alldirectors`
--

CREATE TABLE `alldirectors` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `surname` varchar(250) NOT NULL,
  `budget` int(11) NOT NULL,
  `salary` int(250) NOT NULL,
  `team_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `alldirectors`
--

INSERT INTO `alldirectors` (`id`, `name`, `surname`, `budget`, `salary`, `team_id`) VALUES
(4, 'Asylzhan', 'Utegen', 5000, 100, 7),
(5, 'John', 'Capral', 5000, 78, 8),
(6, 'Test', 'Test', 5000, 54, 9);

-- --------------------------------------------------------

--
-- Структура таблицы `allplayers`
--

CREATE TABLE `allplayers` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `surname` varchar(250) NOT NULL,
  `position` varchar(250) NOT NULL,
  `salary` int(11) NOT NULL,
  `defence` int(11) NOT NULL,
  `attack` int(11) NOT NULL,
  `team_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `allplayers`
--

INSERT INTO `allplayers` (`id`, `name`, `surname`, `position`, `salary`, `defence`, `attack`, `team_id`) VALUES
(9, 'Play', 'Mario', 'defender', 45, 45, 45, 7),
(10, 'Aseke', 'Hokage', 'forward', 65, 69, 69, 7),
(11, 'Leo', 'Messi', 'forward', 100, 78, 78, 7),
(12, 'Ter', 'Stegen', 'goalkeeper', 65, 89, 89, 7),
(13, 'Kio', 'Ranger', 'defender', 35, 4, 4, 7),
(14, 'Captain', 'Sparklez', 'defender', 78, 98, 98, 8),
(15, 'Matt ', 'Davella', 'forward', 65, 78, 78, 8),
(16, 'Leonel', 'Ronaldo', 'forward', 69, 7, 7, 8),
(17, 'Vratar', 'Top', 'goalkeeper', 69, 89, 89, 8),
(18, 'Ala', 'Iol', 'forward', 69, 78, 78, 8),
(19, 'Test', 'Test', 'defender', 54, 54, 54, 9),
(20, 'Test', 'Test', 'goalkeeper', 54, 54, 54, 9),
(21, 'Test', 'Test', 'defender', 54, 54, 54, 9),
(22, 'Test', 'Test', 'defender', 54, 54, 54, 9),
(23, 'Test', 'Test', 'defender', 54, 54, 54, 9);

-- --------------------------------------------------------

--
-- Структура таблицы `allteams`
--

CREATE TABLE `allteams` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `players` int(11) NOT NULL DEFAULT '0',
  `budget` int(11) NOT NULL,
  `league_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `allteams`
--

INSERT INTO `allteams` (`id`, `name`, `players`, `budget`, `league_id`) VALUES
(7, 'Barsa', 5, 10000, 15),
(8, 'Arsenal', 5, 10000, 15),
(9, 'Test', 5, 10000, 17);

-- --------------------------------------------------------

--
-- Структура таблицы `leagues`
--

CREATE TABLE `leagues` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `teams` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `leagues`
--

INSERT INTO `leagues` (`id`, `name`, `teams`) VALUES
(15, 'LaLiga', 2),
(16, 'Premiere', 0),
(17, 'Test', 1);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `allcoaches`
--
ALTER TABLE `allcoaches`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `alldirectors`
--
ALTER TABLE `alldirectors`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `allplayers`
--
ALTER TABLE `allplayers`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `allteams`
--
ALTER TABLE `allteams`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `leagues`
--
ALTER TABLE `leagues`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `allcoaches`
--
ALTER TABLE `allcoaches`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT для таблицы `alldirectors`
--
ALTER TABLE `alldirectors`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT для таблицы `allplayers`
--
ALTER TABLE `allplayers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT для таблицы `allteams`
--
ALTER TABLE `allteams`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT для таблицы `leagues`
--
ALTER TABLE `leagues`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
