<?php
	error_reporting(E_ERROR | E_PARSE);
	$c = new mysqli("localhost", "nmp160418083", "ubaya", "nmp160418083");
	if($c->connect_errno) {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'Failed to connect DB'));
		die();
	}
	$sql = "SELECT p.id as id, p.nama as nama, p.harga as harga, p.deskripsi as deskripsi, p.likes as likes, p.image as image, c.qty as qty FROM cart c inner join produk p on p.id = c.produk_id where c.users_id = ".$_POST['id_user'];
	$result = $c->query($sql);
	$array = array();
	if ($result->num_rows > 0) {
		while ($obj = $result->fetch_object()) {
			$array[] = $obj;
		}

		$sql = "SELECT ifnull(sum(c.qty), 0) as jml_barang, ifnull(sum(p.harga * c.qty), 0) as grand_total FROM cart c inner join produk p on p.id = c.produk_id where c.users_id = ".$_POST['id_user'];
		$result = $c->query($sql);
		$arr_data = array();
		while ($obj = $result->fetch_object()) {
			$arr_data[] = $obj;
		}

		echo json_encode(array('result' => 'OK', 'data' => $array, 'data_any' => $arr_data));
	}
	else {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'No data found'));
		die();
	}
?>