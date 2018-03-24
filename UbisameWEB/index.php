<!DOCTYPE html>
<html lang="es-ES">
 <head>

  
   
     
    <?php 
		require("phpsqlajax_dbinfo.php");  
		 if(isset($_GET["idUsuario"]) && isset($_GET["latitud"]) && isset($_GET["longitud"]) && isset($_GET["device"])){
			$fecha = date("Y-m-d H:i:s");
			 
			 
			$query = "insert into Location (idusuario, latitud, longitud, fecha, device) values ('".$_GET["idUsuario"]."', '".$_GET["latitud"]."', '".$_GET["longitud"]."', '".$fecha."', '".$_GET["device"]."')"; 
			if ( $conn->query($query) === TRUE){
				echo $query;
			} else {
				echo "Error: " . $query . "<br>" . $conn->error;
			}
		}
		
		
 

	?> 
   </head>
 <body >  
   </body>
</html>
