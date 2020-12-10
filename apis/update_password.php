<?php
	error_reporting(E_ERROR | E_PARSE);
	$c = new mysqli("localhost", "root", "", "nmp160418083");
	if($c->connect_errno) {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'Failed to connect DB'));
		die();
	}
	if ($_POST['new_pass']) {
		$pass_old = sha1($_POST['old_pass']);
		$sql = "SELECT * from users where password = '".$pass_old."' and id = ".$_POST['id_user'];
		$result = $c->query($sql);
		if ($result->num_rows > 0) {
			$pass_new = sha1($_POST['new_pass']);
			$sql = "UPDATE users set password = '".$pass_new."' where id = ".$_POST['id_user'];
			$c->query($sql);
			echo json_encode(array('result' => 'OK'));
		} else {
			echo json_encode(array('result' => 'ERROR'));
		}
		
	}
	else {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'No data found'));
		die();
	}
?>