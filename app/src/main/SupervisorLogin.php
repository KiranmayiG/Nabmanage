<?php
    $con = mysqli_connect("localhost", "root", "", "nabmanage") or die(mysqli_error($con));

    $username = $_POST["username"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM supervisor WHERE s_username = ? AND s_password = ?");
    mysqli_stmt_bind_param($statement, "ss", $username, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $s_id, $name, $location, $username, $password);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response["s_name"] = $name;
        $response["s_location"] = $location;
        $response["s_username"] = $username;
        $response["s_password"] = $password;
    }
    
    echo json_encode($response);
?>
