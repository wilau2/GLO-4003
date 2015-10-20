<script>
$(document).ready(function() {
	 $("#btn_save").hide();
	 $("#btn_cancel").hide();
	 $("#dForm input").prop('disabled', true)
	 $("#eForm input").prop('disabled', true)
	
	$(function() {
	    $("#btn_edit").click(function() {
	        $("#btn_save").toggle("slow");
	        $("#btn_edit").toggle("slow");
	        $("#btn_cancel").toggle("slow");
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
});
</script>