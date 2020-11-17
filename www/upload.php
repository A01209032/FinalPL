<?php

ini_set('display_errors',1);
error_reporting(E_ALL);

if(isset($_GET["submit"])){
  $url = 'http://127.0.0.1:8080/delete;'.$_GET["id"];
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
  echo $result;
  echo file_get_contents("index.html");

}
else{
  $threads = 2;
  $target_dir = "uploads/";
  $target_file = $target_dir . basename($_FILES["fileToUpload"]["name"]);
  $uploadOk = 1;
  $imageFileType = strtolower(pathinfo($target_file,PATHINFO_EXTENSION));

  // Check if image file is a actual image or fake image


  if(isset($_POST["submit"])) {
    $check = getimagesize($_FILES["fileToUpload"]["tmp_name"]);
    if($check !== false) {
      echo "File is an image - " . $check["mime"] . ".";
      $uploadOk = 1;
    } else {
      echo "File is not an image.";
      $uploadOk = 0;
    }
  }


  // Check file size
  if ($_FILES["fileToUpload"]["size"] > 500000) {
    echo "Sorry, your file is too large.";
    $uploadOk = 0;
  }

  // Allow certain file formats
if($imageFileType != "jpg" && $imageFileType != "png" && $imageFileType != "jpeg"
&& $imageFileType != "gif" ) {
  echo "Sorry, only JPG, JPEG, PNG & GIF files are allowed.";
  $uploadOk = 0;
}

  $url = 'http://127.0.0.1:8080/size';
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

  //var_dump($result);

  $target_file = "test".$imageFileType;

  // Check if $uploadOk is set to 0 by an error
  if ($uploadOk == 0) {
    echo "Sorry, your file was not uploaded.";
  // if everything is ok, try to upload file
  } else {
    if (move_uploaded_file($_FILES["fileToUpload"]["tmp_name"], $target_file)) {
      echo "The file ". htmlspecialchars( basename( $_FILES["fileToUpload"]["name"])). " has been uploaded.";
    } else {
      echo "Sorry, there was an error uploading your file.";
    }
  }

  $url = 'http://127.0.0.1:8080/check;'.realpath($target_file).";".$threads;
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

  //var_dump($result);
  

  $result = explode(",",$result);
  echo $result[1];
  $name = str_replace('%20', ' ', $result[2]);
  $desc = str_replace('%20', ' ', $result[3]);
  $desc = str_replace('__', '<br/>', $desc);
  $new_image = $result[0].".jpg";
  if($result[1]<$result[4]){
    $percent = $result[1]/$result[4];
  }
  else{
    $percent = $result[4]/$result[1];
  }
  $percent = round((float)$percent * 100 ) . '%';
  echo file_get_contents("part1.html");
  echo '<img style='."'height: 100%; width: 100%; object-fit: contain'".'src="'. $new_image . '">';
  //echo '<h5>'.$result[1].' and '.$result[4].'</h5>';
  echo '<h3>'.$percent.'</h3>';
  echo '<h3>'.$name.'</h3>';
  echo '<h3>'.$desc.'</h3>';
  echo '<form action="delete.php" method="get" enctype="multipart/form-data">
  <div class="form-group">
  <div class="form-group">
  <input type="hidden" class="form-control item" name="id" value="'.$result[0].'" id="desc">            
  </div>
  <div class="form-group">
  <input type="submit" class="btn btn-danger" value="Delete" name="submit">
  </div>
  </form>';
  echo file_get_contents("part2.html");
  }
?>