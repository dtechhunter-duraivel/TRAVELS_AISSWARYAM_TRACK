<?php
$servername = "localhost";
$username = "*********";
$password = "*********";
$dbname = "TRAVEL_CRM";
if($_SERVER["REQUEST_METHOD"]=="POST")
{	
$Phone_no=$_POST["User_Name"];
$Password=$_POST["Password"];
login($Phone_no,$Password);
}
else
{
	echo "It doesnot support GET Method";
}
function login($Phone_No,$Password){

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) 
{
    die("Connection failed: " . $conn->connect_error);
} 
$sqlid = "SELECT Customer_Name,Customer_ID FROM userdetails WHERE Phone_No='$Phone_No'and Password='$Password'";
$resultid = $conn->query($sqlid);
$id=0;
if ($resultid->num_rows > 0)
	{
  
    while($rowid = $resultid->fetch_assoc())
	{
		http_response_code(200);
     echo $rowid["Customer_ID"]."/".$rowid["Customer_Name"];
	}
    }
 else 
 {
	 
    http_response_code(200);
    echo "Something went wrong";
 }

$conn->close();
}
?>

