<script>
$(document).ready(function() {
	 $("#btn_save_description").hide();
	 $("#btn_cancel_description").hide();
	 $("#dForm input").prop('disabled', true)
	 $("#eForm input").prop('disabled', true)
	
	$(function() {
	    $("#btn_edit_description").click(function() {
	        $("#btn_save_description").toggle("slow");
	        $("#btn_edit_description").toggle("slow");
	        $("#btn_cancel_description").toggle("slow");
	        $("#dForm input").prop('disabled', false)
	    });
	});
	
	$(function() {
	    $("#btn_cancel_description").click(function() {
	        $("#btn_save_description").toggle("slow");
	        $("#btn_edit_description").toggle("slow");
	        $("#btn_cancel_description").toggle("slow");
	        $("#dForm input").prop('disabled', true)
	    });
	});
});
</script>