<?php


session_start();
?>

<?php
$servername = "localhost";
$username = "*********";
$password = "*********";
$dbname = "TRAVEL_CRM";

if($_SERVER["REQUEST_METHOD"]=="POST")
{

$Phone_no=$_POST["user"];
$Password=$_POST["pwd"];
login($Phone_no,$Password);
	
}
else
{
	echo "Invalid Request";
}
function login($User,$Password){

$conn = new mysqli($servername, $username, $password, $dbname);
if ($conn->connect_error) 
{
    die("Connection failed: " . $conn->connect_error);
} 
$sqlid = "SELECT *FROM admin WHERE username='$User'and password='$Password'";
$resultid = $conn->query($sqlid);
if ($resultid->num_rows > 0)
	{
$_SESSION["flag"] = "1";
echo 1;
    }
 else 
 {
	 
	echo 0;
	
    
 }

$conn->close();
}
?>

