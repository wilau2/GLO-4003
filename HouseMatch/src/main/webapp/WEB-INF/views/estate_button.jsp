<script>
$(document).ready(function() {
	 $("#btn_save").hide();
	 $("#btn_cancel").hide();
	 $("#upload_picture").hide();
	 $("#btn_add_picture").hide();
	 $("#dForm input").prop('disabled', true)
	 $("#eForm input").prop('disabled', true)
	 
	$(function() {
	    $("#btn_edit").click(function() {
	        $("#btn_save").toggle("slow");
	        $("#btn_edit").toggle("slow");
	        $("#btn_cancel").toggle("slow");
	        $("#btn_add_picture").toggle("slow");
	        $("#dForm input").prop('disabled', false)
	    });
	});
	
	$(function() {
	    $("#btn_cancel").click(function() {
	        $("#btn_save").toggle("slow");
	        $("#btn_edit").toggle("slow");
	        $("#btn_cancel").toggle("slow");
	        $("#dForm input").prop('disabled', true)
	    });
	});
	
	$(function() {
	    $("#btn_add_picture").click(function() {
	    	$("#upload_picture").toggle("slow");
	    });
	});
});
</script>