<?php
	error_reporting(E_ERROR | E_PARSE);
	$c = new mysqli("localhost", "root", "", "nmp160418083");
	if($c->connect_errno) {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'Failed to connect DB'));
		die();
	}
	if ($_POST['id_user']) {
		$saldo = (int)$_POST['saldo'];
		$sql = "UPDATE cart set qty = qty+1 where users_id = ".$_POST['id_user']." and produk_id = ".$_POST['id_prod'];
		$c->query($sql);

		$sql = "SELECT ifnull(sum(p.harga * c.qty), 0) as grand_total FROM cart c inner join produk p on p.id = c.produk_id where c.users_id = ".$_POST['id_user'];
		$result = $c->query($sql);
		$arr_data = array();
		while ($obj = $result->fetch_object()) {
			$arr_data[] = $obj;
		}

		echo json_encode(array('result' => 'OK', 'data' => $arr_data));
	}
	else {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'No data found'));
		die();
	}
?>