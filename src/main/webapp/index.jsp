<head>
 <link rel="stylesheet" href="index.css">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
   <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
      crossorigin="anonymous"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
   <script>
      $(document).ready(function () {
      	/* Get the checkboxes values based on the class attached to each check box */
      	$("#buttonClass").click(function() {
      	    getValueUsingClass();
      	    
      	});
      });
      function getValueUsingClass(){
      	/* declare an checkbox array */
      	var chkArray = [];
      	  var carType =  $('.selectpicker option:selected').val();
      	/* look for all checkboes that have a class 'chk' attached to it and check if it was checked */
      	$(".chk:checked").each(function() {
      		chkArray.push($(this).val());
      	});
      	
      	/* we join the array separated by the comma */
      	var selected;
      	selected = chkArray.join(',') ;
      	
      	/* check if there is selected checkboxes, by default the length is 1 as it contains one single comma */
      	if(carType != ""){
      		
      		//alert("You have selected " + selected+carType);	
      console.log(window.location.protocol+"//"+window.location.host+"/carcostcalculation/cars/"+carType+"/"+selected);
      		$.get(window.location.protocol+"//"+window.location.host+"/carcostcalculation/cars/"+carType+"/"+selected, function(data, status){
    			
            document.getElementById("output").innerHTML = data;
        });
      	}else{
      		alert("Please select the car type");	
      	}
      }
         
   </script>
   <style>
body {
    background-repeat: no-repeat, repeat;
    background-size:cover;
}

h1 {
    color: maroon;
    margin-left: 40px;
    text-align: center;
} 
.heading{
    text-align: center;
    width: 400px;
    margin-bottom: 32px;
}
.list-group{
    width: 400px;
}
.btn-Calculate{
    margin: 32px 0;
    text-align: center;
}
.selector, .checkbox-class{
    display: flex;
    justify-content: center;
}
.calculator{
    background-color: antiquewhite;
    width: 500px;
    margin: 50px auto 0 auto;
    padding: 40px 0 40px;
}
.cal-btn{
    width: 200px;
    height: 40px;
}
</style>
</head>
<body>

    

 <div class="calc">

  <div class="header">

    

  </div>

  <h1>Car Cost Calculator</h1>

    <p>

<select class="selectpicker" value="select">
	<option value="" selected disabled hidden>Choose Car Type</option>
	<optgroup label="Standard">
  <option value="coupe">coupe</option>
  </optgroup>
  <optgroup label="SUV">
  <option value="suv">suv</option>
  </optgroup>
   <optgroup label="Pick up">
   	
  <option value="truck">truck</option>
  </optgroup>
  <optgroup label="Economy">
  <option value="luxary_sedan">Luxary_sedan</option>
  </optgroup>
</select>



    </p>

    <p>

    <div class="container">

    <div class="row">

        <div class="col-xs-12 col-sm-6 col-md-4 col-sm-offset-3 col-md-offset-4">

            <div class="panel panel-default">

                <!-- Default panel contents -->

                <div class="panel-heading">please select options</div>

            

                <!-- List group -->

                <ul class="list-group">

                    <li class="list-group-item">

                        V8

                        <div class="material-switch pull-right">

                            <input id="someSwitchOptionDefault" name="someSwitchOption001" value="v8" class="chk" type="checkbox"/>

                            <label for="someSwitchOptionDefault" class="label-default"></label>

                        </div>

                    </li>

                    <li class="list-group-item">

                        Automatic

                        <div class="material-switch pull-right">

                            <input id="someSwitchOptionPrimary" name="someSwitchOption001" value="automatic" class="chk" type="checkbox"/>

                            <label for="someSwitchOptionPrimary" class="label-primary"></label>

                        </div>

                    </li>

                    <li class="list-group-item">

                        Premiumaudio

                        <div class="material-switch pull-right">

                            <input id="someSwitchOptionSuccess" name="someSwitchOption001"  value="premiumaudio"  class="chk" type="checkbox"/>

                            <label for="someSwitchOptionSuccess" class="label-success"></label>

                        </div>

                    </li>

                    <li class="list-group-item">

                        Sunroof

                        <div class="material-switch pull-right">

                            <input id="someSwitchOptionInfo" name="someSwitchOption001"  value="sunroof"  class="chk" type="checkbox"/>

                            <label for="someSwitchOptionInfo" class="label-info"></label>

                        </div>

                    </li>

                    <li class="list-group-item">

                        Navigation

                        <div class="material-switch pull-right">

                            <input id="someSwitchOptionWarning" name="someSwitchOption001"  value="navigation" class="chk" type="checkbox"/>

                            <label for="someSwitchOptionWarning" class="label-warning"></label>

                        </div>

                    </li>

                    <li class="list-group-item">

                        Towpackage

                        <div class="material-switch pull-right">

                            <input id="someSwitchOptionDanger" name="someSwitchOption001" value="towpackage" class="chk" type="checkbox"/>

                            <label for="someSwitchOptionDanger" class="label-danger"></label>

                        </div>

                    </li>

                </ul>

            </div>            

        </div>

    </div>

</div>

    </p>

    <p>

        <button class="btn" id="buttonClass">Calculate</button>

    </p>

    <p id="output">Monthly Payment</p>

</div>

        </body>