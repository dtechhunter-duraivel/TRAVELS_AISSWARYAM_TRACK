<?php

$servername = "localhost";
$username = "*******";
$password = "*******";
$dbname = "TRAVEL_CRM";
if($_SERVER["REQUEST_METHOD"]=="POST")
{
$name=$_POST["CusName"];	
$pass=$_POST["Passw"];	
$mailid=$_POST["EmailID"];
$gender=$_POST["Gend"];	
$Phone_no=$_POST["Phoneno"];
$Address=$_POST["Address"];
$DOB=$_POST["DOB"];
signup($name,$DOB,$pass,$mailid,$Phone_no,$Address);
}
else{
	echo "It doesnot support GET Method";
}
function signup($Customer_Name,$DOB,$CPassword,$Email_ID,$Phone_no,$Address){
$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) 
{
    die("Connection failed: " . $conn->connect_error);
} 
$phon=0;
$sqlphone = "SELECT Phone_No FROM userdetails WHERE Phone_No='$Phone_no' ";
$sqlid = "SELECT Customer_ID FROM userdetails ORDER BY Customer_ID DESC LIMIT 1;";
$resultid = $conn->query($sqlid);
$resultphone = $conn->query($sqlphone);
if ($resultphone->num_rows > 0)
	{
  
    while($rowphone = $resultphone->fetch_assoc())
	{
     
	 $phon=$rowphone["Phone_No"];
	}
    }
$id=0;

if ($resultid->num_rows > 0)
	{
  
    while($rowid = $resultid->fetch_assoc())
	{
     $id=$rowid["Customer_ID"]+1;  
	 
	}
    }
 else 
 {
   http_response_code(500);
 }
$sql = "INSERT INTO `userdetails`(`Customer_ID`, `Customer_Name`, `DOB`, `Password`, `Email_ID`, `Phone_No`, `Address`) VALUES ($id,'$Customer_Name','$DOB','$CPassword','$Email_ID','$Phone_no','$Address')";

if(strcmp($phon,$Phone_no))
{
	if ($conn->query($sql) === TRUE) 
	{
	http_response_code(200);
    echo "Registered Successfully";
	} 
else 
{
	http_response_code(500);
   echo "Invalid Input: Don't Use Special Characters like @'$%^&*=-_)( !";
}
}
else
{
	http_response_code(500);
	echo $Phone_no;
	echo " Mobile Number Already Registered";	
}
$conn->close();
}


?>

