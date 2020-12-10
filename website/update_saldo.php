<?php
	error_reporting(E_ERROR | E_PARSE);
	$c = new mysqli("localhost", "nmp160418083", "ubaya", "nmp160418083");
	if($c->connect_errno) {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'Failed to connect DB'));
		die();
	}
	if ($_POST['saldo']) {
		$saldo = (int)$_POST['saldo'];
		$sql = "UPDATE users set saldo = saldo + ".$saldo." where id = ".$_POST['id_user'];
		$c->query($sql);
		$no_invoice = "TOP".$_POST['id_user']."-".date('dmY')."-".rand(1000, 9999);
		$sql = "INSERT into nota values('".$no_invoice."', '".date('Y-m-d H:i:s')."', ".$_POST['id_user'].", ".$saldo.",'topup')";
		$c->query($sql);
		echo json_encode(array('result' => 'OK'));
	}
	else {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'No data found'));
		die();
	}
?>