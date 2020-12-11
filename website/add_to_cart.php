<?php
	error_reporting(E_ERROR | E_PARSE);
	$c = new mysqli("localhost", "nmp160418083", "ubaya", "nmp160418083");
	if($c->connect_errno) {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'Failed to connect DB'));
		die();
	}
	if ($_POST['id_prod']) {
		$id_prod = (int)$_POST['id_prod'];
		$id_user = (int)$_POST['id_user'];
		$sql = "SELECT * FROM cart where users_id = ".$id_user." and produk_id = ".$id_prod;
		$result = $c->query($sql);
		if ($result->num_rows > 0) {
			$sql = "UPDATE cart set qty = qty+1 where users_id = ".$id_user." and produk_id = ".$id_prod;
			$c->query($sql);
		} else {
			$sql = "INSERT into cart values(null, ".$id_user.", ".$id_prod.", 1)";
			$c->query($sql);
		}
		echo json_encode(array('result' => 'OK', 'message' => 'Product has been added to cart'));
	} else {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'No ID is found'));
		die();
	}
?>