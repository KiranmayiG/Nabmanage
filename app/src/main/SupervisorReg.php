<?php
	$con = mysqli_connect('localhost', 'root', '', 'nabmanage') or die(mysqli_error($con));

	$name = $_POST["name"];
    $location = $_POST["location"];
    $username = $_POST["username"];
    $password = $_POST["password"];

    $statement = mysqli_prepare($con, "INSERT INTO supervisor (s_name, s_location, s_username, s_password) VALUES (?, ?, ?, ?)");

    mysqli_stmt_bind_param($statement, "ssss", $name, $location, $username, $password);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>