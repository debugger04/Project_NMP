<?php
	error_reporting(E_ERROR | E_PARSE);
	$c = new mysqli("localhost", "root", "", "nmp160418083");
	if($c->connect_errno) {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'Failed to connect DB'));
		die();
	}
	if ($_POST['username']) {
		$username = $_POST['username'];
		$password = $_POST['password'];
		$pass = sha1($password);
		$sql = "SELECT * FROM users where username = '".$username."' and password = '".$pass."'";
		$result = $c->query($sql);
		if ($result->num_rows > 0) {
			while ($obj = $result->fetch_object()) {
				$array[] = $obj;
			}
			echo json_encode(array('result' => 'OK', 'data' => $array));
		}
		else {
			echo json_encode(array('result'=> 'ERROR'));
			die();
		}
	} else {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'No Username is found'));
		die();
	}
?>