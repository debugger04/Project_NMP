<?php
	error_reporting(E_ERROR | E_PARSE);
	$c = new mysqli("localhost", "nmp160418083", "ubaya", "nmp160418083");
	if($c->connect_errno) {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'Failed to connect DB'));
		die();
	}
	if ($_POST['id_user']) {
		$sql = "SELECT * from users where id = ".$_POST['id_user'];
		$result = $c->query($sql);
		while ($obj = $result->fetch_assoc()) {
			if ($obj['saldo'] < $_POST['grandtot']) {
				echo json_encode(array('result' => 'NOTENOUGH'));
				break;
			} else {
				$no_invoice = "ORD".$_POST['id_user']."-".date('dmY')."-".rand(1000, 9999);
				$sqll = "INSERT into nota values('".$no_invoice."', '".date('Y-m-d H:i:s')."', ".$_POST['id_user'].", ".$_POST['grandtot'].",'pay')";
				$c->query($sqll);

				$sqll = "SELECT * from cart where users_id = ".$_POST['id_user'];
				$resultt = $c->query($sqll);
				while ($objj = $resultt->fetch_assoc()) {
					$sqlll = "INSERT into detail values(null, '".$no_invoice."', ".$objj['produk_id'].", ".$objj['qty'].")";
					$c->query($sqlll);
				}

				$sqll = "DELETE from cart where users_id = ".$_POST['id_user'];
				$c->query($sqll);

				$grandtot = (int)$_POST['grandtot'];
				$sqll = "UPDATE users set saldo = saldo - ".$grandtot.", poin = poin + ".$_POST['poin']." where id = ".$_POST['id_user'];
				$c->query($sqll);

				$sqll = "SELECT * from users where id = ".$_POST['id_user'];
				$resultt = $c->query($sqll);
				while ($objj = $resultt->fetch_assoc()) {
					if ((int)$objj['poin'] >= 10000 && $objj['member_id'] == 1) {
						$sqlll = "UPDATE users set member_id = 2, poin = 0 where id = ".$_POST['id_user'];
						$c->query($sqlll);
						break;
					} elseif ((int)$objj['poin'] >= 20000 && $objj['member_id'] == 2) {
						$sqlll = "UPDATE users set member_id = 3, poin = 0 where id = ".$_POST['id_user'];
						$c->query($sqlll);
						break;
					} elseif ((int)$objj['poin'] >= 30000 && $objj['member_id'] == 3) {
						$sqlll = "UPDATE users set member_id = 4, poin = 0 where id = ".$_POST['id_user'];
						$c->query($sqlll);
						break;
					}
				}

				echo json_encode(array('result' => 'OK'));
				break;
			}
		}
	}
	else {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'No data found'));
		die();
	}
?>