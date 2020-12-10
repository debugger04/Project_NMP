<?php
	error_reporting(E_ERROR | E_PARSE);
	$c = new mysqli("localhost", "root", "", "nmp160418083");
	if($c->connect_errno) {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'Failed to connect DB'));
		die();
	}
	$sql = "DELETE from cart where users_id = ".$_POST['id_user']." and produk_id = ".$_POST['id_prod'];
	$c->query($sql);
	echo json_encode(array('result' => 'OK'));
?>