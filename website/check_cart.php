<?php
	error_reporting(E_ERROR | E_PARSE);
	$c = new mysqli("localhost", "nmp160418083", "ubaya", "nmp160418083");
	if($c->connect_errno) {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'Failed to connect DB'));
		die();
	}
	$sql = "SELECT * from cart where users_id = ".$_POST['id_user'];
	$result = $c->query($sql);
	$array = array();
	if ($result->num_rows > 0) {
		echo json_encode(array('result' => 'OK'));
	}
	else {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'No data found'));
		die();
	}
?>