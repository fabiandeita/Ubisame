<!DOCTYPE html>
<html>
<head>
	
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDVjj6dECwFoAH6hBaWVhez8vxPNJEOEGc" type="text/javascript"></script>	
    <script type="text/javascript">
    //<![CDATA[

    function initMap() {
	  var myLatLng = {lat: 19.423251, lng: -99.137252};

	  var map = new google.maps.Map(document.getElementById('map'), {
		zoom: 9,
		center: myLatLng,
				mapTypeId: 'roadmap'
	  }); 
	   
	  
	  <?php
			require("phpsqlajax_dbinfo.php");
			$query = "select * from Location "; 
			
			if ( $result = $conn->query($query)){
				while ($row = $result->fetch_assoc()) {	 
					echo 'var myLatLng' . $row['idLocation'] . ' = {lat: ' . $row['latitud'] . ', lng: ' . $row['longitud'] . '};
						var marcadorVertical' . $row['idLocation'] . '  = new google.maps.Marker({
							position: myLatLng' . $row['idLocation'] . ',
							map: map,
							title: \'' . $row['fecha'] . '\' });';
					/*echo "google.maps.event.addListener(marcadorVertical" . $row['idLocation'] . ", 'click', function(h) {	";	 
					echo "infoWindow.setContent('<table> <tr> <td><span>Descripción: </span></td><td>" . $row['fecha'] . " </td> </tr><tr> <td><span>Observaciones: </span></td><td>" . $row['fecha'] . " </td> </tr><tr> <td><span>Cadenamiento: </span></td><td>" . $row['fecha'] . " </td> </tr><tr> <td><span>Latitud: </span></td><td>" . $row['latitud'] . " </td> </tr><tr> <td><span>Longitud: </span></td><td>" . $row['longitud'] . " </td> </tr> </table> <br />'); ";
					echo "infoWindow.setPosition(h.latLng);	";
					echo "infoWindow.open(map);	"; 			 
					echo '});';*/
				}
			} else {
				echo "Error: " . $query . "<br>" . $conn->error;
			} 
		?>
	}
	</script>
	</head>
	<body onLoad="initMap()">

		<h1>My First Google Map</h1>
		<div style="width:100%; height:100%">
			<div id="map" style="margin:auto; width:90%;height:500px	"></div>
		</div>
	</body>
<html>