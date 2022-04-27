
<style>
	textarea
	{
		border:none;
	}
</style>

<script>

	function getResponse()
	{
		var qno = document.questionForm.qno.value;
		var question = document.questionForm.question.value;
		var submittedAnswer = document.questionForm.option.value;
		
		alert(qno + " " + question + submittedAnswer);

		var xmlhttp = new XMLHttpRequest();

		var data = "?qno="+qno+"&question="+question+"&submittedAnswer="+submittedAnswer;

		xmlhttp.open("get","storeResponse" + data,true);
		
		xmlhttp.send();
	}
</script>

<body>

${message}

<form name="questionForm">

	<input  style="border:none" type="text" name="qno" value="${question.qno}"><br><br>
	
	<textarea  rows=4 cols=50 name="question"> ${question.question} </textarea><br><br>
		
	<input  type="radio" name="option" value="${question.option1}" onclick="getResponse()"> <span> ${question.option1} </span><br><br>
	
	<input  type="radio" name="option" value="${question.option2}" onclick="getResponse()">  <span> ${question.option2} </span> <br><br>
		
	<input  type="radio" name="option" value="${question.option3}" onclick="getResponse()"> <span> ${question.option3} </span> <br><br>
	
	<input  type="radio" name="option" value="${question.option4}" onclick="getResponse()"> <span> ${question.option4} </span> <br><br>
		
	<input type="submit" value="next" formaction="next">
	<input type="submit" value="previous" formaction="previous">
	<input type="submit" value="end exam" formaction="endexam">

</form>

 

</body>