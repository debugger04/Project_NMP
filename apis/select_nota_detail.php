<?php
	error_reporting(E_ERROR | E_PARSE);
	$c = new mysqli("localhost", "root", "", "nmp160418083");
	if($c->connect_errno) {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'Failed to connect DB'));
		die();
	}
	$sql = "SELECT d.qty as qty, p.nama as nama, p.harga as harga, p.image as image from detail d inner join produk p on d.produk_id = p.id where d.nota_id = '".$_POST['no_invoice']."'";
	$result = $c->query($sql);

	$sqll = "SELECT date(n.tgl_order) as tgl_order, n.grandtotal as grandtotal, SUM(d.qty) as jml FROM nota n inner join detail d on d.nota_id = n.id WHERE n.id = '".$_POST['no_invoice']."'";
	$resultt = $c->query($sqll);

	$array = array();
	$arr_data = array();
	if ($result->num_rows > 0) {
		while ($obj = $result->fetch_object()) {
			$array[] = $obj;
		}
		while ($obj = $resultt->fetch_object()) {
			$arr_data[] = $obj;
		}
		echo json_encode(array('result' => 'OK', 'data' => $array, 'data_any' => $arr_data));
	}
	else {
		echo json_encode(array('result'=> 'ERROR', 'message' => 'No data found'));
		die();
	}
?>