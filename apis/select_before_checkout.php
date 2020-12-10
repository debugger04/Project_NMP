<?php
	error_reporting(E_ERROR | E_PARSE);
	$c = new mysqli("localhost", "root", "", "nmp160418083");
	if($c->connect_errno) {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'Failed to connect DB'));
		die();
	}
	$sql = "SELECT sum(c.qty * p.harga) as gtot, sum(c.qty) as jml, m.jenis as member, m.disc as diskon from cart c inner join produk p on c.produk_id = p.id inner join users u on c.users_id = u.id inner join member m on m.id = u.member_id WHERE c.users_id = ".$_POST['id_user'];
	$result = $c->query($sql);
	$array = array();
	if ($result->num_rows > 0) {
		while ($obj = $result->fetch_object()) {
			$array[] = $obj;
		}
		echo json_encode(array('result' => 'OK', 'data' => $array));
	}
	else {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'No data found'));
		die();
	}
?>