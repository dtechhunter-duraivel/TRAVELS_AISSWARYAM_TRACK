<?php
$servername = "localhost";
$username = "********";
$password = "********";
$dbname = "TRAVEL_CRM";
function insert($name,$from,$to,$bdate,$pdate,$phone,$cusname)
{

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) 
{
    die("Connection failed: " . $conn->connect_error);
} 

$sqli="SELECT bookiid from Booking ORDER BY bookiid DESC LIMIT 1";
$resultid = $conn->query($sqli);
if ($resultid->num_rows > 0)
{
    while($rowid = $resultid->fetch_assoc())
	{
     $a=$rowid["bookiid"];
     $id=substr($a,2);
	 echo $id;
     $id=$id+1;
	 $id="AT".$id;
	 echo $id;
	}
}
 else 
 {
	/* $sqlinit="SELECT bid from CONFIRM_BOOKING ORDER BY bid DESC LIMIT 1";
$resultinit = $conn->query($sqlinit);
if ($resultinit->num_rows > 0)
{
    while($rowiinit = $resultinit->fetch_assoc())
	{
     $a=$rowiinit["bid"];
     $id=substr($a,2);
	 echo $id;
     $id=$id+1;
	 $id="AT".$id;
	 echo $id;
	}
}
else
{
	
}*/
$id="AT10001";
 }
 
$sql = "INSERT INTO Booking (userid,bookiid,frm,tol,bdate,pdate,phone,cusname)
VALUES ($name,'$id','$from', '$to','$bdate','$pdate','$phone','$cusname')";

if ($conn->query($sql) === TRUE) {
    echo "Your cab is booked successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
$conn->close();
}

if($_SERVER["REQUEST_METHOD"]=="POST")
{
$name=$_POST["uid"];	
$from=$_POST["from"];	
$to=$_POST["to"];	
$bdate=$_POST["bdate"];	
$pdate=$_POST["pdate"];
$phone=$_POST["phone"];
$cusname=$_POST["cusname"];
insert($name,$from,$to,$bdate,$pdate,$phone,$cusname);
}

else
{
	echo "It doesnot support GET Method";
}

?>