<script>
$(document).ready(function() {
	 $("#btn_save").hide();
	 $("#btn_cancel").hide();
	 $("#upload_picture").hide();
	 $("#delete_picture").hide();
	 $("#btn_add_picture").hide();
	 $("#btn_del_picture").hide();
	 $("#dForm input").prop('disabled', true)
	 $("#eForm input").prop('disabled', true)
	 
	$(function() {
	    $("#btn_edit").click(function() {
	        $("#btn_save").toggle("slow");
	        $("#btn_edit").toggle("slow");
	        $("#btn_cancel").toggle("slow");
	        $("#btn_add_picture").toggle("slow");
	        $("#btn_del_picture").toggle("slow");
	        $("#dForm input").prop('disabled', false)
	    });
	});
	
	$(function() {
	    $("#btn_cancel").click(function() {
	        $("#btn_save").toggle("slow");
	        $("#btn_edit").toggle("slow");
	        $("#btn_cancel").toggle("slow");
	        $("#btn_add_picture").toggle("slow");
	        $("#btn_del_picture").toggle("slow");
	        $("#dForm input").prop('disabled', true)
	    });
	});	
	
	$(function() {
	    $("#btn_add_picture").click(function() {
	    	$("#upload_picture").toggle("slow");
	    });
	});
	$(function() {
	    $("#btn_del_picture").click(function() {
	    	$("#delete_picture").toggle("slow");
	    });
	});
  $('#upload_form').on('submit', function(e){
	  window.open("${entryUrl}/approbationWarning",null,
	  "height=100,width=400,status=yes,toolbar=no,menubar=no,location=no");
	});
	
	
});
</script>