<?php
	error_reporting(E_ERROR | E_PARSE);
	$c = new mysqli("localhost", "nmp160418083", "ubaya", "nmp160418083");
	if($c->connect_errno) {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'Failed to connect DB'));
		die();
	}
	if ($_POST['username']) {
		$username = $_POST['username'];
		$email = $_POST['email'];
		$password = $_POST['password'];
		$pass = sha1($password);
		$sql = "SELECT * FROM users where username = '".$username."' or email = '".$email."'";
		$result = $c->query($sql);

		if ($result->num_rows > 0) {
			echo json_encode(array('result' => 'ERROR'));
			die();
		}
		else {
			if ($_POST['img'] != "") {
				$sql = "INSERT INTO users VALUES (NULL, '".$email."', '".$username."', '".$pass."', 0, 0, '".$_POST['img']."', 1)";
			} else {
				$sql = "INSERT INTO users VALUES (NULL, '".$email."', '".$username."', '".$pass."', 0, 0, 'https://rimatour.com/wp-content/uploads/2017/09/No-image-found.jpg', 1)";
			}
			$c->query($sql);
			echo json_encode(array('result'=> 'OK'));
		}
	} else {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'No Username is found'));
		die();
	}
?>