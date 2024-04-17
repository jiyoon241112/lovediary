$("#edit_btn").click(function(){
   let idx = $("#timecapsule_idx").data('idx');

   location.href = "/timecapsule/modify/"+ idx;
});