-- phpMyAdmin SQL Dump
-- version 4.0.0
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 10, 2020 at 06:58 AM
-- Server version: 10.1.44-MariaDB-0+deb9u1
-- PHP Version: 7.2.32-1+0~20200710.46+debian9~1.gbp625eb5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `nmp160418083`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE IF NOT EXISTS `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `users_id` int(11) NOT NULL,
  `produk_id` int(11) NOT NULL,
  `qty` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cart_users1_idx` (`users_id`),
  KEY `fk_cart_produk1_idx` (`produk_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=66 ;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(200) DEFAULT NULL,
  `image` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `nama`, `image`) VALUES
(1, 'Boots', 'https://i.ebayimg.com/images/g/kBwAAOSwxFVd1-zW/s-l400.jpg'),
(2, 'Sneakers', 'https://s4.bukalapak.com/bukalapak-kontenz-production/content_attachments/60024/w-744/46982285_257408208288656_7342863757255023311_n.jpg'),
(3, 'Formals', 'https://i.pinimg.com/originals/dc/96/80/dc9680a20a4d508178e47ff2d3bec985.jpg'),
(4, 'Slip Ons', 'https://cdn.trendhunterstatic.com/thumbs/multilogo.jpeg'),
(5, 'Footballs', 'https://images.solecollector.com/complex/images/c_crop,h_679,w_1152,x_0,y_0/c_fill,dpr_2.0,h_182,q_70,w_328/tee6v0v1g7caqbapsuav/adidas-adizero-5-star-7-emoji-cleats-pack');

-- --------------------------------------------------------

--
-- Table structure for table `detail`
--

CREATE TABLE IF NOT EXISTS `detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nota_id` varchar(200) NOT NULL,
  `produk_id` int(11) NOT NULL,
  `qty` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_detail_nota1_idx` (`nota_id`),
  KEY `fk_detail_produk1_idx` (`produk_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=50 ;

--
-- Dumping data for table `detail`
--

INSERT INTO `detail` (`id`, `nota_id`, `produk_id`, `qty`) VALUES
(1, 'ORD1-09122020-6545', 1, 1),
(2, 'ORD1-09122020-6545', 8, 1),
(3, 'ORD1-09122020-8120', 9, 20),
(4, 'ORD1-09122020-3592', 16, 2),
(5, 'ORD1-09122020-3592', 6, 2),
(6, 'ORD1-10122020-2649', 7, 1),
(7, 'ORD3-10122020-7962', 3, 1),
(8, 'ORD3-10122020-7962', 1, 1),
(9, 'ORD3-10122020-2287', 15, 1),
(10, 'ORD3-10122020-5139', 10, 1),
(11, 'ORD3-10122020-4647', 14, 1),
(12, 'ORD3-10122020-4647', 12, 1),
(13, 'ORD1-10122020-6575', 1, 1),
(14, 'ORD1-10122020-6575', 11, 2),
(15, 'ORD1-10122020-6762', 16, 2),
(16, 'ORD1-10122020-6762', 13, 1),
(17, 'ORD1-10122020-5235', 14, 1),
(18, 'ORD1-10122020-5235', 9, 1),
(19, 'ORD5-10122020-3906', 8, 1),
(20, 'ORD5-10122020-3906', 13, 2),
(21, 'ORD5-10122020-3906', 9, 1),
(22, 'ORD3-10122020-8519', 15, 1),
(23, 'ORD3-10122020-8519', 8, 1),
(24, 'ORD1-10122020-1486', 13, 10),
(25, 'ORD1-10122020-1486', 5, 8),
(26, 'ORD1-10122020-1486', 6, 6),
(27, 'ORD1-10122020-1486', 16, 9),
(28, 'ORD1-10122020-1486', 10, 8),
(29, 'ORD1-10122020-1486', 7, 6),
(30, 'ORD1-10122020-1486', 9, 13),
(31, 'ORD2-10122020-5622', 3, 1),
(32, 'ORD2-10122020-5622', 1, 1),
(33, 'ORD1-10122020-5181', 1, 2),
(34, 'ORD1-10122020-5181', 13, 1),
(35, 'ORD1-10122020-5181', 7, 2),
(36, 'ORD1-10122020-4515', 10, 2),
(37, 'ORD1-10122020-4515', 14, 2),
(38, 'ORD2-10122020-7664', 7, 1),
(39, 'ORD5-10122020-8032', 10, 1),
(40, 'ORD5-10122020-8032', 8, 2),
(41, 'ORD5-10122020-2482', 8, 1),
(42, 'ORD5-10122020-2482', 6, 1),
(43, 'ORD5-10122020-8114', 8, 1),
(44, 'ORD5-10122020-8114', 13, 1),
(45, 'ORD5-10122020-9213', 7, 2),
(46, 'ORD5-10122020-9213', 10, 2),
(47, 'ORD5-10122020-9213', 4, 1),
(48, 'ORD5-10122020-6086', 15, 1),
(49, 'ORD5-10122020-6086', 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE IF NOT EXISTS `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jenis` varchar(200) DEFAULT NULL,
  `disc` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`id`, `jenis`, `disc`) VALUES
(1, 'Basic', 0),
(2, 'Bronze', 5),
(3, 'Silver', 10),
(4, 'Gold', 20);

-- --------------------------------------------------------

--
-- Table structure for table `nota`
--

CREATE TABLE IF NOT EXISTS `nota` (
  `id` varchar(200) NOT NULL,
  `tgl_order` datetime DEFAULT NULL,
  `users_id` int(11) NOT NULL,
  `grandtotal` int(11) DEFAULT NULL,
  `status` enum('topup','pay') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_nota_users1_idx` (`users_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `nota`
--

INSERT INTO `nota` (`id`, `tgl_order`, `users_id`, `grandtotal`, `status`) VALUES
('ORD1-09122020-3592', '2020-12-09 21:31:32', 1, 681150, 'pay'),
('ORD1-09122020-6545', '2020-12-09 20:03:13', 1, 480000, 'pay'),
('ORD1-09122020-8120', '2020-12-09 20:06:13', 1, 9300000, 'pay'),
('ORD1-10122020-1486', '2020-12-10 04:24:03', 1, 16346175, 'pay'),
('ORD1-10122020-2649', '2020-12-10 02:44:46', 1, 261250, 'pay'),
('ORD1-10122020-4515', '2020-12-10 05:19:39', 1, 1645200, 'pay'),
('ORD1-10122020-5181', '2020-12-10 05:13:52', 1, 1034100, 'pay'),
('ORD1-10122020-5235', '2020-12-10 03:50:18', 1, 1153300, 'pay'),
('ORD1-10122020-6575', '2020-12-10 08:03:48', 1, 360050, 'pay'),
('ORD1-10122020-6762', '2020-12-10 03:47:08', 1, 680200, 'pay'),
('ORD2-10122020-5622', '2020-12-10 05:13:49', 2, 260000, 'pay'),
('ORD2-10122020-7664', '2020-12-10 05:31:23', 2, 275000, 'pay'),
('ORD3-10122020-2287', '2020-12-10 07:47:39', 3, 653000, 'pay'),
('ORD3-10122020-4647', '2020-12-10 07:50:41', 3, 988000, 'pay'),
('ORD3-10122020-5139', '2020-12-10 07:48:43', 3, 165000, 'pay'),
('ORD3-10122020-7962', '2020-12-10 07:40:53', 3, 260000, 'pay'),
('ORD3-10122020-8519', '2020-12-10 04:07:24', 3, 1008000, 'pay'),
('ORD5-10122020-2482', '2020-12-10 06:31:27', 5, 530000, 'pay'),
('ORD5-10122020-3906', '2020-12-10 03:58:53', 5, 1518000, 'pay'),
('ORD5-10122020-6086', '2020-12-10 06:46:26', 5, 903000, 'pay'),
('ORD5-10122020-8032', '2020-12-10 06:20:51', 5, 875000, 'pay'),
('ORD5-10122020-8114', '2020-12-10 06:40:08', 5, 704000, 'pay'),
('ORD5-10122020-9213', '2020-12-10 06:42:01', 5, 1230000, 'pay'),
('TOP1-09122020-1035', '2020-12-09 20:06:06', 1, 10000000, 'topup'),
('TOP1-09122020-3142', '2020-12-09 00:00:00', 1, 25000, 'topup'),
('TOP1-09122020-9690', '2020-12-09 19:56:21', 1, 2000000, 'topup'),
('TOP1-10122020-1278', '2020-12-10 02:44:37', 1, 500000, 'topup'),
('TOP1-10122020-2671', '2020-12-10 03:49:07', 1, 2000000, 'topup'),
('TOP1-10122020-2793', '2020-12-10 03:49:57', 1, 123000, 'topup'),
('TOP1-10122020-3698', '2020-12-10 04:22:52', 1, 17000000, 'topup'),
('TOP1-10122020-4034', '2020-12-10 03:17:51', 1, 5000, 'topup'),
('TOP1-10122020-4424', '2020-12-10 05:19:33', 1, 1000000, 'topup'),
('TOP1-10122020-9533', '2020-12-10 03:46:59', 1, 1000000, 'topup'),
('TOP2-10122020-1651', '2020-12-10 05:30:16', 2, 50000, 'topup'),
('TOP2-10122020-7285', '2020-12-10 02:46:21', 2, 700000, 'topup'),
('TOP3-10122020-3565', '2020-12-10 04:06:36', 3, 1000000, 'topup'),
('TOP3-10122020-5502', '2020-12-10 07:40:47', 3, 300000, 'topup'),
('TOP3-10122020-5655', '2020-12-10 07:47:32', 3, 1000000, 'topup'),
('TOP3-10122020-8384', '2020-12-10 07:49:23', 3, 10000000, 'topup'),
('TOP3-10122020-8839', '2020-12-10 07:49:36', 3, 18000, 'topup'),
('TOP5-10122020-5600', '2020-12-10 03:55:10', 5, 5000000, 'topup'),
('TOP5-10122020-7377', '2020-12-10 06:41:42', 5, 10000000, 'topup');

-- --------------------------------------------------------

--
-- Table structure for table `produk`
--

CREATE TABLE IF NOT EXISTS `produk` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(200) DEFAULT NULL,
  `harga` double DEFAULT NULL,
  `deskripsi` text,
  `likes` int(11) DEFAULT NULL,
  `image` text,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_produk_category1_idx` (`category_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `produk`
--

INSERT INTO `produk` (`id`, `nama`, `harga`, `deskripsi`, `likes`, `image`, `category_id`) VALUES
(1, 'Pinkish Delight', 125000, 'Feminine, warm, and pink !', 0, 'https://www.nativeshoes.com/dw/image/v2/BCXB_PRD/on/demandware.static/-/Sites-nas-master-catalog/default/dw1c7d714b/pdp-images/41108330-5969-1.jpg?sw=550&sh=451&sm=cut', 1),
(2, 'Retro Goods', 250000, 'Bring back old & glory', 0, 'https://images-eu.ssl-images-amazon.com/images/I/4181md5nDfL._SR600%2C315_PIWhiteStrip%2CBottomLeft%2C0%2C35_SCLZZZZZZZ_FMpng_BG255%2C255%2C255.jpg', 1),
(3, 'Shiny Diamond', 135000, 'Clean and white as your future', 0, 'https://bg-look.com/UserFiles/Product/gallery_1/1A7BC252-CC83-14D5-0ACE-53F05E048510.jpg?cache&w=256', 1),
(4, 'Jordan Spizike', 350000, 'This modern pair of shoes is all you need', 0, 'https://thecitrustore.com/data/products/id9788/web_00001-jordan-spizike-bg-white-varsity-red-cement-grey.jpg', 2),
(5, 'Nike Air Max', 250000, 'Wear this shoes and you''ll live to your max !', 0, 'https://www.yousporty.com/img/nike/sneakers/nike+air--AH5222+103--577x433.jpg', 2),
(6, 'Brodo Steel', 175000, 'B to the rescue for your everyday wear', 0, 'https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//95/MTA-7634615/brodo_brodo_sneakers_vantage_hi_-_steel_grey_white_sole_full02_bgyyv00r.jpg', 2),
(7, 'Brown Clarks', 275000, 'Shiny Brown Shoes that are breathtaking', 0, 'https://c1.wallpaperflare.com/preview/60/346/363/still-items-things-footwear.jpg', 3),
(8, 'Oxford Holmes', 355000, 'You can never go wrong wearing these !', 0, 'https://www.beatnikshoes.com/wp-content/uploads/2016/12/Holmes-Deep-Black-5.jpg', 3),
(9, 'Full Brogue', 465000, 'Something old, something borrowed, something brown...', 0, 'https://www.skolyx.se/319-large_default/full-brogue.jpg', 3),
(10, 'Superstar Core', 165000, 'Black and slip is your most perfect combination needs', 0, 'https://cdn.shopify.com/s/files/1/2252/5107/products/Superstar_Slip_on_Shoes_Black_FV3187_01_standard-removebg-preview.png?v=1596463928', 4),
(11, 'New Balance', 127000, 'Simple yet elegant, and you''ll want these one !', 0, 'https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//107/MTA-3849659/new_balance_new_balance_women_nrgize_slip_on_shoes_sepatu_wanita_-newwlnrslp1-_full02_rgjw0fv6.jpg', 4),
(12, 'Dark Rieker', 239000, 'Missing old-school style? Try these !', 0, 'https://www.rieker.co.uk/images/rieker-n0273-14-ladies-dark-blue-slip-on-shoes-p9318-12483_image.jpg', 4),
(13, 'Mercurial Superfly', 349000, 'Blueish to the nine, here we go !', 0, 'https://ecs7.tokopedia.net/img/cache/700/product-1/2019/10/19/6933620/6933620_9a04a6c5-af21-4415-a261-443c07bd42d7.jpg', 5),
(14, 'Nike CR7', 749000, 'Only for Neon addicts. CR7''s limited collection', 0, 'https://ecs7.tokopedia.net/img/cache/700/hDjmkQ/2020/9/2/c1f5f373-4f60-4186-8535-9ef58cd93696.jpg', 5),
(15, 'Coral Phantom', 653000, 'Makes you the star of the field !', 0, 'https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//97/MTA-2999311/nike_nike-phantom-vsn-club-firm-ground-multi-ground-men-football-shoes--aj6959-600-_full02.jpg', 5),
(16, 'Red Vintage', 183500, 'Red and loved by everyone !', 0, 'https://i.stack.imgur.com/WMAkv.jpg', 2);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(200) DEFAULT NULL,
  `username` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `saldo` double DEFAULT NULL,
  `poin` int(11) DEFAULT NULL,
  `image` text,
  `member_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_users_diskon1_idx` (`member_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `username`, `password`, `saldo`, `poin`, `image`, `member_id`) VALUES
(1, 'anasthasyaaverina@gmail.com', 'peyine', '8cb2237d0679ca88db6464eac60da96345513964', 309575, 2977, 'https://scontent.fsub8-1.fna.fbcdn.net/v/t1.0-9/71797330_2318805378385489_280040723921764352_o.jpg?_nc_cat=108&ccb=2&_nc_sid=174925&_nc_ohc=fVH0ZOXo3GwAX-WBDja&_nc_ht=scontent.fsub8-1.fna&oh=f1f01a88637cd505609cf4fd5a4cf977&oe=5FF3D3DE', 3),
(2, 'williamhartanto@gmail.com', 'will', '8cb2237d0679ca88db6464eac60da96345513964', 215000, 535, 'https://scontent.fsub8-1.fna.fbcdn.net/v/t1.0-9/536717_448422605168024_1708997096_n.jpg?_nc_cat=109&ccb=2&_nc_sid=174925&_nc_ohc=gyfMKN-QF2gAX_nev03&_nc_ht=scontent.fsub8-1.fna&oh=8c7988a668fac4cd3d463f19f98db1d4&oe=5FF53B40', 1),
(3, 'matthewnoto@gmail.com', 'matthew', '7c222fb2927d828af22f592134e8932480637c0d', 9244000, 3074, 'https://rimatour.com/wp-content/uploads/2017/09/No-image-found.jpg', 1),
(4, 'pauline@gmail.com', 'pauline', 'ccbb9f311f20699217383de55219831b0be5b1c7', 0, 0, 'https://rimatour.com/wp-content/uploads/2017/09/No-image-found.jpg', 1),
(5, 'gongyoo@gmail.com', 'gongyoo', '8cb2237d0679ca88db6464eac60da96345513964', 8365000, 6635, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS0bwmgmS0yYK-7uw7MWbQ8aO9rJSzTQhuYSg&usqp=CAU', 1),
(6, 'grimreaper@gmail.com', 'grimreaper', '8cb2237d0679ca88db6464eac60da96345513964', 0, 0, 'https://rimatour.com/wp-content/uploads/2017/09/No-image-found.jpg', 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `fk_cart_produk1` FOREIGN KEY (`produk_id`) REFERENCES `produk` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_cart_users1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `detail`
--
ALTER TABLE `detail`
  ADD CONSTRAINT `fk_detail_nota1` FOREIGN KEY (`nota_id`) REFERENCES `nota` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_detail_produk1` FOREIGN KEY (`produk_id`) REFERENCES `produk` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `nota`
--
ALTER TABLE `nota`
  ADD CONSTRAINT `fk_nota_users1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `produk`
--
ALTER TABLE `produk`
  ADD CONSTRAINT `fk_produk_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `fk_users_diskon1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
