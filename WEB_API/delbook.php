<?php
$bid=$_POST["bid"];
$servername = "localhost";
$username = "*********";
$password = "*********";
$dbname = "TRAVEL_CRM";
$conn = new mysqli($servername, $username, $password, $dbname);

$sql = "DELETE FROM Booking WHERE bookiid='$bid'";
if ($conn->query($sql) === TRUE) 
{
 echo 1;
}
else
{
    echo "Error: " . $sql . "<br>" . $conn->error;
}
?>