<?php
$username="everis_escolar";
$password="escolares";
$database="Ubisame";
$host = "tecnologiasdenegocio.com";
 
$conn = new mysqli($host, $username, $password, $database); 
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}  	
		
?>