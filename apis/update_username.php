<?php
	error_reporting(E_ERROR | E_PARSE);
	$c = new mysqli("localhost", "root", "", "nmp160418083");
	if($c->connect_errno) {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'Failed to connect DB'));
		die();
	}
	if ($_POST['new_username']) {
		$sql = "UPDATE users set username = '".$_POST['new_username']."' where id = ".$_POST['id_user'];
		$c->query($sql);
		echo json_encode(array('result' => 'OK'));
	}
	else {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'No data found'));
		die();
	}
?>