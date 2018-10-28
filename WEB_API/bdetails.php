<?php
// Start the session
session_start();
?>
<?php

if(!isset($_SESSION["flag"]))
{
	echo '<script>window.location="index.php";</script>';
}
?>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 
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
      <a class="navbar-brand" href="#" style="color:white">AISSWARYAM TRACK ADMIN PANEL</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
      <li><a href="#" style="color:white" id="logg"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
    </ul>
    </div>
  </div>
</nav>

<div class="container">
 <br>
 <br>
<script type="text/javascript">
$(document).ready(function(){
		$('#show').load('data.php')
	setInterval(function(){
		$('#show').load('data.php')
	},1500);
});
</script>
<div id="show">


</div>
<script>
$("#logg").click(function()
{
	$.ajax(
	{
	
	type:"POST",
	url:'unset.php',
	success:function(data)
	{
	window.location.href="index.php";				
	}
					
	}
	);
}
);
</script>
</body>
</html>