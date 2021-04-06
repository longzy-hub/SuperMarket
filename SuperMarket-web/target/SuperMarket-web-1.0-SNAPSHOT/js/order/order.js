$(function () {

   var search_status; // 状态

   // 使用mustache模板
   var toBePaidListTemplate = $("#toBePaidListTemplate").html();
   Mustache.parse(toBePaidListTemplate);

   loadToBePaidList();

   function loadToBePaidList() {
        search_status = $("#search_status").val();
        $.ajax({
           url: "/order/toBePaid.json",
           type: "POST",
           data: {
              search_status:search_status
           },
           success: function (result) {
               if (result.status == "success"){
                  if (result.data.length > 0){
                     // var
                  }
               }
           }
        });

   }



});