<?php
$servername = "localhost";
$username = "*********";
$password = "*********";
$dbname = "TRAVEL_CRM";
if($_SERVER["REQUEST_METHOD"]=="POST")
{

$Phone_no=$_POST["Phoneno"];
$Password=$_POST["Password"];
login($Phone_no,$Password);
}
else
{
	echo "It doesnot support GET Method";
}
function login($Phone_No,$Password){

$dbname = "TRAVEL_CRM";
$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) 
{
    die("Connection failed: " . $conn->connect_error);
} 
$sqlid = "SELECT Customer_ID FROM userdetails WHERE Phone_No='$Phone_No'and Password='$Password'";
$resultid = $conn->query($sqlid);
$id=0;
if ($resultid->num_rows > 0)
	{
  
      http_response_code(200);
    }
 else 
 {
	 http_response_code(401);
	 echo $Phone_No." ".$Password;
	echo "Incorrect Username or Password";
	
    
 }

$conn->close();
}
?>

