<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
<head>
	<title>AmountCalculator</title>
</head>
<body>
<h1>
	Convert number to words  
</h1>
<span style="display: inline;">
<input type="text" name="num" id="num" />
<input type="button" name="submit" id="sub" value="Convert" />
</span>
<br/>
<br/>
<div><span>Result: </span><span id="result" style="color:blue;"></span></div>


<script>

$("#sub").click(function() {
	var val = $("#num").val();
	console.log(val);
    $.ajax({
        type: "GET",
        url: "convert/"+val,
        success: function(result) {
            $("#result").html(result);
        }
    });
});

</script>
</body>
</html>
