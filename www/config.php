<?php

    if ($_SERVER['REQUEST_METHOD'] === 'GET') {
        
        $name = $_GET[number];
        $url = 'http://127.0.0.1:8080/setthreads;'.$name;
        $data = array('key1' => 'value1', 'key2' => 'value2');

        // use key 'http' even if you send the request to https://...
        $options = array(
            'http' => array(
                'header'  => "Content-type: application/x-www-form-urlencoded\r\n",
                'method'  => 'GET',
                'content' => http_build_query($data)
            )
        );
        $context  = stream_context_create($options);
        $result = file_get_contents($url, false, $context);
        if ($result === FALSE) { /* Handle error */ }

    }
    
    $url = 'http://127.0.0.1:8080/getthreads';
    $data = array('key1' => 'value1', 'key2' => 'value2');

    // use key 'http' even if you send the request to https://...
    $options = array(
        'http' => array(
            'header'  => "Content-type: application/x-www-form-urlencoded\r\n",
            'method'  => 'GET',
            'content' => http_build_query($data)
        )
    );
    $context  = stream_context_create($options);
    $result = file_get_contents($url, false, $context);
    if ($result === FALSE) { /* Handle error */ }

    echo file_get_contents("part1.html");

    echo '<form action="config.php" method="get" enctype="multipart/form-data">
            <div class="form-group">
            <h1>Number of threads</h1>           
            </div>
            <div class="form-group">
            <div class="form-group">
            <input type="number" class="form-control item" min="1" max="20" name="number" value="'.$result.'" id="desc">            
            </div>
            <div class="form-group">
            <input type="submit" class="form-control item" value="Update" name="submit">
            </div>
        </form>';








    echo file_get_contents("part2.html");
    
?>