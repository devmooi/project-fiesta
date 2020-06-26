<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	
	<!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    
    <!-- Icons -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    
    <script>
      $(document).ready(function(){
        $('.modal').modal();
        $('.datepicker').datepicker();
        $('select').formSelect();
      });
    </script>
</head>
<body>
	<!-- https://materializecss.com/grid.html -->
	<div>
		materialize test
		<div class="row">
	      <div class="col s1">1</div>
	      <div class="col s1">2</div>
	      <div class="col s1">3</div>
	      <div class="col s1">4</div>
	      <div class="col s1">5</div>
	      <div class="col s1">6</div>
	      <div class="col s1">7</div>
	      <div class="col s1">8</div>
	      <div class="col s1">9</div>
	      <div class="col s1">10</div>
	      <div class="col s1">11</div>
	      <div class="col s1">12</div>
	  	</div>
	</div>
	
	<div class="container">
      <!-- Page Content goes here -->
      materialize test
      <div class="row">
	      <div class="col s1">1</div>
	      <div class="col s1">2</div>
	      <div class="col s1">3</div>
	      <div class="col s1">4</div>
	      <div class="col s1">5</div>
	      <div class="col s1">6</div>
	      <div class="col s1">7</div>
	      <div class="col s1">8</div>
	      <div class="col s1">9</div>
	      <div class="col s1">10</div>
	      <div class="col s1">11</div>
	      <div class="col s1">12</div>
	  </div>
    </div>
    
    <!-- https://materializecss.com/buttons.html -->
    <a class="waves-effect waves-light btn">button</a>
    <a class="waves-effect waves-light btn-large">Button</a>
    <a class="waves-effect waves-light btn-small">Button</a>
    <a class="btn-large disabled">Button</a>
	<a class="btn disabled">Button</a>
	<a class="btn-flat disabled">Button</a>
	
	<br>
	<br>
	<!-- https://materializecss.com/icons.html -->
	<i class="material-icons">assignment_turned_in</i>
	
	<!-- https://materializecss.com/preloader.html -->
	<div class="progress">
		<div class="determinate" style="width: 25%"></div>
	</div>
	
	<!-- https://materializecss.com/modals.html -->
    <!-- Modal Trigger -->
    <a class="waves-effect waves-light btn modal-trigger" href="#modal1">Modal</a>
    <!-- Modal Structure -->
    <div id="modal1" class="modal">
      <div class="modal-content">
        <h4>Modal Header</h4>
        <p>A bunch of text</p>
      </div>
      <div class="modal-footer">
        <a href="#!" class="modal-close waves-effect waves-green btn-flat">Agree</a>
      </div>
    </div>
    
    <!-- https://materializecss.com/checkboxes.html -->
      <form action="#">
	    <p>
	      <label>
	        <input type="checkbox" />
	        <span>Red</span>
	      </label>
	    </p>
	    <p>
	      <label>
	        <input type="checkbox" checked="checked" />
	        <span>Yellow</span>
	      </label>
	    </p>
	    <p>
	      <label>
	        <input type="checkbox" class="filled-in" checked="checked" />
	        <span>Filled in</span>
	      </label>
	    </p>
	    <p>
	      <label>
	        <input id="indeterminate-checkbox" type="checkbox" />
	        <span>Indeterminate Style</span>
	      </label>
	    </p>
	    <p>
	      <label>
	        <input type="checkbox" checked="checked" disabled="disabled" />
	        <span>Green</span>
	      </label>
	    </p>
	    <p>
	      <label>
	        <input type="checkbox" disabled="disabled" />
	        <span>Brown</span>
	      </label>
	    </p>
	  </form>
	  
	  <!-- https://materializecss.com/pickers.html -->
	  <input type="text" class="datepicker">
	  
	  <!-- https://materializecss.com/radio-buttons.html -->
      <form action="#">
        <p>
          <label>
            <input name="group1" type="radio" checked />
            <span>Red</span>
          </label>
        </p>
        <p>
          <label>
            <input name="group1" type="radio" />
            <span>Yellow</span>
          </label>
        </p>
        <p>
          <label>
            <input class="with-gap" name="group1" type="radio"  />
            <span>Green</span>
          </label>
        </p>
        <p>
          <label>
            <input name="group1" type="radio" disabled="disabled" />
            <span>Brown</span>
          </label>
        </p>
      </form>
      
      <!-- https://materializecss.com/select.html -->
      <div class="input-field col s12">
        <select>
          <optgroup label="team 1">
            <option value="1">Option 1</option>
            <option value="2">Option 2</option>
          </optgroup>
          <optgroup label="team 2">
            <option value="3">Option 3</option>
            <option value="4">Option 4</option>
          </optgroup>
        </select>
        <label>Optgroups</label>
      </div>
	  
	  <!-- https://materializecss.com/text-inputs.html -->
	  <div class="row">
	    <form class="col s12">
	      <div class="row">
	        <div class="input-field col s6">
	          <input placeholder="Placeholder" id="first_name" type="text" class="validate">
	          <label for="first_name">First Name</label>
	        </div>
	        <div class="input-field col s6">
	          <input id="last_name" type="text" class="validate">
	          <label for="last_name">Last Name</label>
	        </div>
	      </div>
	      <div class="row">
	        <div class="input-field col s12">
	          <input disabled value="I am not editable" id="disabled" type="text" class="validate">
	          <label for="disabled">Disabled</label>
	        </div>
	      </div>
	      <div class="row">
	        <div class="input-field col s12">
	          <input id="password" type="password" class="validate">
	          <label for="password">Password</label>
	        </div>
	      </div>
	      <div class="row">
	        <div class="input-field col s12">
	          <input id="email" type="email" class="validate">
	          <label for="email">Email</label>
	        </div>
	      </div>
	      <div class="row">
	        <div class="col s12">
	          This is an inline input field:
	          <div class="input-field inline">
	            <input id="email_inline" type="email" class="validate">
	            <label for="email_inline">Email</label>
	            <span class="helper-text" data-error="wrong" data-success="right">Helper text</span>
	          </div>
	        </div>
	      </div>
	    </form>
	  </div>	  

</body>
</html>