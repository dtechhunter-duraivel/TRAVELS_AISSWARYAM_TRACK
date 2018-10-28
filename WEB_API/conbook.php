<?php
$servername = "localhost";
$username = "*********";
$password = "*********";
$dbname = "TRAVEL_CRM";
function select($uid)
{

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) 
{
    die("Connection failed: " . $conn->connect_error);
} 
$sqlid = "SELECT * FROM Booking WHERE bookiid='$uid'";
echo $uid;
$resultid = $conn->query($sqlid);
if ($resultid->num_rows > 0)
	{
    while($rowid = $resultid->fetch_assoc())
	{
     $bid=$rowid["bookiid"];
$userid=$rowid["userid"]; 
$from=$rowid["frm"];
$tol=$rowid["tol"];
$bdate=$rowid["bdate"];
$pdate=$rowid["pdate"];
$cusname=$rowid["cusname"];
$phone=$rowid["phone"];
insert($bid,$userid,$from,$tol,$bdate,$pdate,$cusname,$phone);
	}
    }
 else 
 {
echo "error";
 }

$conn->close();
}
function insert($bid,$name,$from,$to,$bdate,$pdate,$phone,$cusname)
{
$conn = new mysqli($servername, $username, $password, $dbname);

	$sql = "INSERT INTO CONFIRM_BOOKING (bid,userid,frm,droploc,bdate,pdate,phone,cusname)
VALUES ('$bid','$name','$from','$to','$bdate','$pdate','$phone','$cusname')";
if ($conn->query($sql) === TRUE) 
{
 echo "Your cab is booked successfully";
 #deletion($bid);
}
else
{
    echo "Error: " . $sql . "<br>" . $conn->error;
}
}

if($_SERVER["REQUEST_METHOD"]=="POST")
{
$bid=$_POST["bid"];	
select($bid);
}
else
{
echo "It doesnot support GET Method";
}
function deletion($bid)
{
$conn = new mysqli($servername, $username, $password, $dbname);

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
}
?>