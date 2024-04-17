$("#edit_btn").click(function(){
   let idx = $("#timecapsule_idx").data('idx');

   location.href = "/timecapsule/modify/"+ idx;
});

$("#delete_btn").click(function(){
   let idx = $(this).closest(".comment.card").data("idx");
   let form_data = new FormData;
   form_data.append("idx", idx);

   deleteTimecapsule(form_data);
});

$("#return_btn").click(function(){
   location.replace("/timecapsule");
});

function deleteTimecapsule(form_data, retry = false) {
   $.ajax({
      url: '/timecapsule/delete',
      method: 'post',
      data : form_data,
      contentType: false,
      processData: false,
      success: function (data) {
         const msg = data.msg ?? null;
         if(msg ?? null) {
            alert(msg);
         }

         if(data.code === "200") {
            location.replace("/timecapsule");
         }
      }, error: function () {
         if(!retry) save(form_data, true);
      }
   });
}