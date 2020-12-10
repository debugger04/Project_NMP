-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 10, 2020 at 04:09 AM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `nmp160418083`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `users_id` int(11) NOT NULL,
  `produk_id` int(11) NOT NULL,
  `qty` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`id`, `users_id`, `produk_id`, `qty`) VALUES
(14, 2, 9, 2),
(16, 1, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `nama` varchar(200) DEFAULT NULL,
  `image` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `nama`, `image`) VALUES
(1, 'Boots', 'https://www.clifford-james.co.uk/images/products/zoom/z-DMS1204.jpg'),
(2, 'Sneakers', 'https://s4.bukalapak.com/bukalapak-kontenz-production/content_attachments/60024/w-744/46982285_257408208288656_7342863757255023311_n.jpg'),
(3, 'Formals', 'https://i.pinimg.com/originals/dc/96/80/dc9680a20a4d508178e47ff2d3bec985.jpg'),
(4, 'Slip Ons', 'https://cdn.trendhunterstatic.com/thumbs/multilogo.jpeg'),
(5, 'Footballs', 'https://images.solecollector.com/complex/images/c_crop,h_679,w_1152,x_0,y_0/c_fill,dpr_2.0,h_182,q_70,w_328/tee6v0v1g7caqbapsuav/adidas-adizero-5-star-7-emoji-cleats-pack');

-- --------------------------------------------------------

--
-- Table structure for table `detail`
--

CREATE TABLE `detail` (
  `id` int(11) NOT NULL,
  `nota_id` varchar(200) NOT NULL,
  `produk_id` int(11) NOT NULL,
  `qty` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `detail`
--

INSERT INTO `detail` (`id`, `nota_id`, `produk_id`, `qty`) VALUES
(1, 'ORD1-09122020-6545', 1, 1),
(2, 'ORD1-09122020-6545', 8, 1),
(3, 'ORD1-09122020-8120', 9, 20),
(4, 'ORD1-09122020-3592', 16, 2),
(5, 'ORD1-09122020-3592', 6, 2),
(6, 'ORD1-10122020-2649', 7, 1);

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `id` int(11) NOT NULL,
  `jenis` varchar(200) DEFAULT NULL,
  `disc` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

CREATE TABLE `nota` (
  `id` varchar(200) NOT NULL,
  `tgl_order` datetime DEFAULT NULL,
  `users_id` int(11) NOT NULL,
  `grandtotal` int(11) DEFAULT NULL,
  `status` enum('topup','pay') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `nota`
--

INSERT INTO `nota` (`id`, `tgl_order`, `users_id`, `grandtotal`, `status`) VALUES
('ORD1-09122020-3592', '2020-12-09 21:31:32', 1, 681150, 'pay'),
('ORD1-09122020-6545', '2020-12-09 20:03:13', 1, 480000, 'pay'),
('ORD1-09122020-8120', '2020-12-09 20:06:13', 1, 9300000, 'pay'),
('ORD1-10122020-2649', '2020-12-10 02:44:46', 1, 261250, 'pay'),
('TOP1-09122020-1035', '2020-12-09 20:06:06', 1, 10000000, 'topup'),
('TOP1-09122020-3142', '2020-12-09 00:00:00', 1, 25000, 'topup'),
('TOP1-09122020-9690', '2020-12-09 19:56:21', 1, 2000000, 'topup'),
('TOP1-10122020-1278', '2020-12-10 02:44:37', 1, 500000, 'topup'),
('TOP2-10122020-7285', '2020-12-10 02:46:21', 2, 700000, 'topup');

-- --------------------------------------------------------

--
-- Table structure for table `produk`
--

CREATE TABLE `produk` (
  `id` int(11) NOT NULL,
  `nama` varchar(200) DEFAULT NULL,
  `harga` double DEFAULT NULL,
  `deskripsi` text DEFAULT NULL,
  `likes` int(11) DEFAULT NULL,
  `image` text DEFAULT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `produk`
--

INSERT INTO `produk` (`id`, `nama`, `harga`, `deskripsi`, `likes`, `image`, `category_id`) VALUES
(1, 'Pinkish Delight', 125000, 'Feminine, warm, and pink !', 0, 'https://www.nativeshoes.com/dw/image/v2/BCXB_PRD/on/demandware.static/-/Sites-nas-master-catalog/default/dw1c7d714b/pdp-images/41108330-5969-1.jpg?sw=550&sh=451&sm=cut', 1),
(2, 'Retro Goods', 250000, 'Bring back old & glory', 0, 'https://images-eu.ssl-images-amazon.com/images/I/4181md5nDfL._SR600%2C315_PIWhiteStrip%2CBottomLeft%2C0%2C35_SCLZZZZZZZ_FMpng_BG255%2C255%2C255.jpg', 1),
(3, 'Shiny Diamond', 135000, 'Clean and white as your future', 0, 'https://bg-look.com/UserFiles/Product/gallery_1/1A7BC252-CC83-14D5-0ACE-53F05E048510.jpg?cache&w=256', 1),
(4, 'Jordan Spizike', 350000, 'This modern pair of shoes is all you need', 0, 'https://thecitrustore.com/data/products/id9788/web_00001-jordan-spizike-bg-white-varsity-red-cement-grey.jpg', 2),
(5, 'Nike Air Max', 250000, 'Wear this shoes and you\'ll live to your max !', 0, 'https://www.yousporty.com/img/nike/sneakers/nike+air--AH5222+103--577x433.jpg', 2),
(6, 'Brodo Steel', 175000, 'B to the rescue for your everyday wear', 0, 'https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//95/MTA-7634615/brodo_brodo_sneakers_vantage_hi_-_steel_grey_white_sole_full02_bgyyv00r.jpg', 2),
(7, 'Brown Clarks', 275000, 'Shiny Brown Shoes that are breathtaking', 0, 'https://c1.wallpaperflare.com/preview/60/346/363/still-items-things-footwear.jpg', 3),
(8, 'Oxford Holmes', 355000, 'You can never go wrong wearing these !', 0, 'https://www.beatnikshoes.com/wp-content/uploads/2016/12/Holmes-Deep-Black-5.jpg', 3),
(9, 'Full Brogue', 465000, 'Something old, something borrowed, something brown...', 0, 'https://www.skolyx.se/319-large_default/full-brogue.jpg', 3),
(10, 'Superstar Core', 165000, 'Black and slip is your most perfect combination needs', 0, 'https://cdn.shopify.com/s/files/1/2252/5107/products/Superstar_Slip_on_Shoes_Black_FV3187_01_standard-removebg-preview.png?v=1596463928', 4),
(11, 'New Balance', 127000, 'Simple yet elegant, and you\'ll want these one !', 0, 'https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//107/MTA-3849659/new_balance_new_balance_women_nrgize_slip_on_shoes_sepatu_wanita_-newwlnrslp1-_full02_rgjw0fv6.jpg', 4),
(12, 'Dark Rieker', 239000, 'Missing old-school style? Try these !', 0, 'https://www.rieker.co.uk/images/rieker-n0273-14-ladies-dark-blue-slip-on-shoes-p9318-12483_image.jpg', 4),
(13, 'Mercurial Superfly', 349000, 'Blueish to the nine, here we go !', 0, 'https://ecs7.tokopedia.net/img/cache/700/product-1/2019/10/19/6933620/6933620_9a04a6c5-af21-4415-a261-443c07bd42d7.jpg', 5),
(14, 'Nike CR7', 749000, 'Only for Neon addicts. CR7\'s limited collection', 0, 'https://ecs7.tokopedia.net/img/cache/700/hDjmkQ/2020/9/2/c1f5f373-4f60-4186-8535-9ef58cd93696.jpg', 5),
(15, 'Coral Phantom', 653000, 'Makes you the star of the field !', 0, 'https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//97/MTA-2999311/nike_nike-phantom-vsn-club-firm-ground-multi-ground-men-football-shoes--aj6959-600-_full02.jpg', 5),
(16, 'Red Vintage', 183500, 'Red and loved by everyone !', 0, 'https://i.stack.imgur.com/WMAkv.jpg', 2);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` varchar(200) DEFAULT NULL,
  `username` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `saldo` double DEFAULT NULL,
  `poin` int(11) DEFAULT NULL,
  `image` text DEFAULT NULL,
  `member_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `username`, `password`, `saldo`, `poin`, `image`, `member_id`) VALUES
(1, 'anasthasyaaverina@gmail.com', 'peyine', '8cb2237d0679ca88db6464eac60da96345513964', 400600, 992, 'https://scontent.fsub8-1.fna.fbcdn.net/v/t1.0-9/71797330_2318805378385489_280040723921764352_o.jpg?_nc_cat=108&ccb=2&_nc_sid=174925&_nc_ohc=fVH0ZOXo3GwAX-WBDja&_nc_ht=scontent.fsub8-1.fna&oh=f1f01a88637cd505609cf4fd5a4cf977&oe=5FF3D3DE', 2),
(2, 'williamhartanto@gmail.com', 'wihar', '348162101fc6f7e624681b7400b085eeac6df7bd', 700000, 0, 'https://scontent.fsub8-1.fna.fbcdn.net/v/t1.0-9/536717_448422605168024_1708997096_n.jpg?_nc_cat=109&ccb=2&_nc_sid=174925&_nc_ohc=gyfMKN-QF2gAX_nev03&_nc_ht=scontent.fsub8-1.fna&oh=8c7988a668fac4cd3d463f19f98db1d4&oe=5FF53B40', 1),
(3, 'matthewnoto@gmail.com', 'matthew', 'b1b3773a05c0ed0176787a4f1574ff0075f7521e', 0, 0, 'https://rimatour.com/wp-content/uploads/2017/09/No-image-found.jpg', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_cart_users1_idx` (`users_id`),
  ADD KEY `fk_cart_produk1_idx` (`produk_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `detail`
--
ALTER TABLE `detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_detail_nota1_idx` (`nota_id`),
  ADD KEY `fk_detail_produk1_idx` (`produk_id`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `nota`
--
ALTER TABLE `nota`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_nota_users1_idx` (`users_id`);

--
-- Indexes for table `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_produk_category1_idx` (`category_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_users_diskon1_idx` (`member_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `detail`
--
ALTER TABLE `detail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `produk`
--
ALTER TABLE `produk`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
