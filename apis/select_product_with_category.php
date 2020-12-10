<?php
	error_reporting(E_ERROR | E_PARSE);
	$c = new mysqli("localhost", "root", "", "nmp160418083");
	if($c->connect_errno) {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'Failed to connect DB'));
		die();
	}
	$sql = "SELECT * FROM produk where category_id = ".$_POST['category_id'];
	$result = $c->query($sql);
	$array = array();
	if ($result->num_rows > 0) {
		while ($obj = $result->fetch_object()) {
			$array[] = $obj;
		}
		$sql = "SELECT * FROM category where id = ".$_POST['category_id'];
		$result = $c->query($sql);
		$arr_data = array();
		while ($obj = $result->fetch_object()) {
			$arr_data[] = $obj;
		}

		echo json_encode(array('result' => 'OK', 'data' => $array, 'nama_cat' => $arr_data));
	}
	else {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'No data found'));
		die();
	}
?>