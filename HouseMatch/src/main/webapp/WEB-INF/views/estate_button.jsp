<script>
$(document).ready(function() {
	 $("#type").val("${estate.type}");
	 $("#btn_save_estate").hide();
	 $("#btn_cancel_estate").hide();
	 $("#btn_save_description").hide();
	 $("#btn_cancel_description").hide();
	 $("#upload_picture").hide();
	 $("#btn_add_picture").hide();
	 $("#dForm input").prop('disabled', true)
	 $("#eForm input").prop('disabled', true)
	 $("#eForm select").prop('disabled', true)
	 
	 $(function() {
	    $("#btn_edit_estate").click(function() {
	        $("#btn_save_estate").toggle("slow");
	        $("#btn_edit_estate").toggle("slow");
	        $("#btn_cancel_estate").toggle("slow");

	        $("#btn_add_picture").toggle("slow");
	        
	        $("#eForm input").prop('disabled', false)
	        $("#eForm select").prop('disabled', false)
	    });
	});
	
	$(function() {
	    $("#btn_cancel_estate").click(function() {
	        $("#btn_save_estate").toggle("slow");
	        $("#btn_edit_estate").toggle("slow");
	        $("#btn_cancel_estate").toggle("slow");
	        
	        $("#btn_add_picture").toggle("slow");
	        
	        $("#eForm input").prop('disabled', true)
	        $("#eForm select").prop('disabled', true)
	    });
	});
	 
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
	
	$(function() {
	    $("#btn_add_picture").click(function() {
	    	$("#upload_picture").toggle("slow");
	    });
	});

});
</script>