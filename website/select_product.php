<?php
	error_reporting(E_ERROR | E_PARSE);
	$c = new mysqli("localhost", "nmp160418083", "ubaya", "nmp160418083");
	if($c->connect_errno) {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'Failed to connect DB'));
		die();
	}
	$sql = "SELECT * FROM produk ORDER BY RAND()";
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