<?php
// Start the session
session_start();
?>
<?php

if(isset($_SESSION["flag"]))
{
	echo '<script>window.location="bdetails.php";</script>';
}
?>
<html lang="en">
<head>
  <title>AISSWARYAM TRACK BOOKING</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function()
	{
        
    });
</script>
</head>
<body style="background-color:#fafafa">

<nav class="navbar navbar-default" style=" background-color:#1D839C">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#" style="color:white">AISSWARYAM TRACK ADMIN</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
   
       
    
      <ul class="nav navbar-nav navbar-right">
        
        <li><a href="#" style="color:white"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </ul>
    </div>
  </div>
</nav>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<div class="container">
<div class="row">
<div class="col-sm-12">

<center><form  class="lform"  >


<table style="width:100%;background-color:white">
<tr>
<th>
<h5>ADMIN LOGIN</h5>
</th>
</tr>
</table>
<br>
<table style="width:70%;">
<br>
<tr>
<td>
<input id="ip" class="form-control" type="text" placeholder="USERNAME" name="eid" required /><br>
</td>
</tr>
<tr>
<td>
<input id="ip1" class="form-control" type="password" placeholder="PASSWORD" name="mno" required /><br>
</td>
</tr>
<tr>
<td>
<input id="sub"  value="LOGIN" type="button" class="btn btn-default" name="su"><br><br>
</td>
</tr>
<tr>
<td id="ip2">
<center><span id="erl" style="color:maroon;">Incorrect Username or Password</span>
<br><br>
</td>
</tr>
<tr>
</table>

</form></center>

<br>
<br>
</div>
</div>


</div>
<br>
<script>

$("#sub").click(function()
{
	var uid=$("#ip").val();
	var pass=$("#ip1").val();
	var varData='user='+uid+'&pwd='+pass;
	console.log(varData);	
					$.ajax(
				{
					type:"POST",
					data:varData,
					url:'adminlog.php',
					success:function(data)
					{
						if(data==0)
						{
							$("#erl").fadeIn();
						}
						else if(data==1)
						{
						window.location.href = "http://aisswaryamtrack.xyz/bdetails.php";
						
							$("#erl").hide();
						}
					}
					
				
				}
				);
				
}
);
</script>


<style>

.nbar2
{
display:none;
}

#erl
{
	display:none;
}
.lform
{
	
box-shadow:0px 0px 1px 0px;
background-color:white;
width:30%;
height:300px;
}
li a.lin1
{
display:none;
}
#ip
{
width:100%;
height:40px;

}
#ip1
{
width:100%;
height:40px;

}
#sub:hover
{
box-shadow:black 0px 0px 4px 0px;
}
#sub
{
width:100%;
height:40px;
background-color:#1D839C;
color:white;
border:0;
}
th {
    text-align: center;
	background-color:#1D839C;
	color:white;
	height:45px;


} 


.ico
{
display:none;
}
@media screen and (max-width: 780px)
 {
.nbar2
{
display:block;
}
.nbar1
{
display:none;
}
.lform
 {
 width:100%;
 }
 #image
{
height:200px;
}

 .con1
 {
 margin-top:15px;
 }

}


</style>
</html>


