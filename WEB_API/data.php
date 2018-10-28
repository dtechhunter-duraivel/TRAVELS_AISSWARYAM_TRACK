<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 
</head>

<div class="container">
 <center> <h3>Booking Details</h3></center>

<br> 
  <div class="table-responsive">          
  <table class="table table-bordered table-striped" style="font-size:15px;height:20px;">
  
    <tbody >
	  <tr style="width:90px;background-color:#1D839C;color:white;">
        <th>UID</th>
		<th>BID</th>
        <th>Name</th>
        <th>Mobile</th>
        <th>Pick Up</th>
        <th>Drop</th>
        <th style="  width: 145px;">Booking Date</th>
		<th>Pickup Date</th>
		<th style="  width: 185px;" >Action</th>
		</tr> 
<?php
$servername = "localhost";
$username = "duraivel1198";
$password = "abcde12345";
$dbname = "TRAVEL_CRM";
$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error)
	{
    die("Connection failed: " . $conn->connect_error);
    } 
$sql = "SELECT *
FROM   Booking
WHERE  NOT EXISTS
  (SELECT *
   FROM   CONFIRM_BOOKING
   WHERE  CONFIRM_BOOKING.bid = Booking.bookiid)";
$result = $conn->query($sql);
if ($result->num_rows > 0)
	{
    while($row = $result->fetch_assoc())
	{
     echo "<tr><td>".$row["userid"]."</td><td>".$row["bookiid"]."</td><td>".$row["cusname"]."</td><td>".$row["phone"]."</td><td>".$row["frm"]."</td><td>".$row["tol"]."</td><td>".$row["bdate"]."</td><td>".$row["pdate"]."</td><td><button  value='".$row["bookiid"]."'"." type='button' class='confirm btn btn-primary'>Confirm</button>&nbsp;&nbsp;<button  value='".$row["bookiid"]."'"." type='button' width='200px' class='btn btn-danger reject'>Reject</button></td>
 </tr>";
    }
    }
 else 
 {
   echo '<tr><td colspan="9"><center>No Booking
   </td></tr>';
 }
$conn->close();
?>
  
		    
    </tbody>
  </table>
  </div>
</div>
<script>
$(document).ready(function()
{
$(".confirm").click(function()
{

	var id=$(this).val();
	var varData='bid='+id;
$.ajax(
	{
	
	type:"POST",
	data:varData,
	url:'conbook.php',
	success:function(data)
	{
	alert("Booking Confirmed");			
	}
					
	}
	);
});
$(".reject").click(function()
{
	var r = confirm("Do you confirm to reject the booking?");
if (r == true) 
{
  
	var id=$(this).val();
	var varData='bid='+id;
$.ajax(
	{
	
	type:"POST",
	data:varData,
	url:'delbook.php',
	success:function(data)
	{
	alert("Booking Rejected");		
	}				
	}
	); 
} 

else {
  
}
	
});
});

</script>
<style>
</style>
