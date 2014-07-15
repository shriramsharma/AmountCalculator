<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
<script>

$("#submit").click(function() {
    $.ajax({
        type: "POST",
        url: "/convert",
        data: {
            txt1: $("#num").val()
        },
        success: function(result) {
            $("#result").html(result);
        }
    });
});

</script>
<head>
	<title>AmountCalculator</title>
</head>
<body>
<h1>
	Convert number to words  
</h1>

<input type="text" name="num" id="num" /><br>
<input type="button" name="submit" id="submit" value="Convert" />
<br/>
<span id="result"></span>
</body>
</html>
