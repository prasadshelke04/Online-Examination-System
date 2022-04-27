



<head>

<style>
		select
		{
		    padding : 30px;
		}
		
</style>

	<script>
		
		function display(x)
			{
				// Asynchronous JavaScript with XML
			   //  AJAX Engine Started
			   
				var xmlhttp = new XMLHttpRequest();

				xmlhttp.open("get","getQuestions?subject=" + x.value,true);
				
				xmlhttp.send();
			}
	</script>
</head>


${message}

<body>
	
	<form>
		<select name="selectedSubject" onchange="display(this)">
			<option value="gk"> GeneralKnowldge  </option>
			<option value="maths"> maths </option>
		</select>
		<input type=submit value="startExam" formaction="startExam">
	</form>
	
	
</body>