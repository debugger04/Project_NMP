<?php
	error_reporting(E_ERROR | E_PARSE);
	$c = new mysqli("localhost", "root", "", "nmp160418083");
	if($c->connect_errno) {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'Failed to connect DB'));
		die();
	}
	if ($_POST["id_user"]) {
		$id = $_POST["id_user"];
		$sql = "SELECT username, saldo, poin, image, jenis, ifnull((select count(id) from nota where u.id = users_id and status = 'pay' group by users_id),0) as checkout FROM users u inner join member m on u.member_id = m.id where u.id = ".$id;
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
		echo json_encode(array('result'=> 'ERROR', 'message' => 'ID User is not found'));
		die();
	}
?>