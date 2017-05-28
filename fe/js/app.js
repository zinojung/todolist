(function (window) {
    getAllList();
    showAllList();
    showCompletedList();
    showActiveList();
    insertData();
    deleteData();
    updateData();
    deleteAllCompletedData();
})(window);

function updateData() {
  $(document).on('change', '.toggle', function(){
      if($(this).is(':checked')){
        $(this).parent().parent().addClass("completed");
        $.ajax({
          type : "PUT",
          url : "/todo",
          dataType : "json",
          data : JSON.stringify({id : $(this).attr('id'), completed : 1}),
          contentType : "application/json; charset=UTF-8",
        });
        subOneCountOfActive();
      } else {
        $(this).parent().parent().removeClass("completed");
        $.ajax({
          type : "PUT",
          url : "/todo",
          dataType : "json",
          data : JSON.stringify({id : $(this).attr('id'), completed : 0}),
          contentType : "application/json; charset=UTF-8",
        });
        addOneCountOfActive();
      }
  });
}

function getAllList(){
  $.ajax({
      type : "GET",
      url : "/todo",
      dataType : "json",
      contentType: "application/json; charset=UTF-8",
      success : function(data){
          appendItems(data);
          updateCountOfActive();
      }
  });
}

function showAllList(){
    $('#all').click(function(){
        $(".filters li a").removeClass();
        $("#all").addClass("selected");
        getAllList();
    });
}

function appendItems(data){
    $('.todo-list li').remove();

    $.each(data, function(index, item) {
        if (item['completed'] == 0){
            $('.todo-list').append("<li><div class='view'>"+
                "<input class='toggle' id='"+item['id']+"' type='checkbox'><label>"+item['todo']+"</label>"+
                "<button class='destroy'></button></div></li>");
        } else {
            $('.todo-list').append("<li class='completed'><div class='view'>"+
                "<input class='toggle' id='"+item['id']+"' type='checkbox' checked><label>"+item['todo']+"</label>"+
                "<button class='destroy'></button></div></li>");
        }
    });
}

function showActiveList() {
    $('#active').click(function(){
        $(".filters li a").removeClass();
        $("#active").addClass("selected");
        $.ajax({
            type : "GET",
            url : "/todo/active",
            dataType : "json",
            contentType : "application/json; charset=UTF-8",
            success : function(data) {
                appendItems(data);
            }
        });
    });
}

function showCompletedList() {
    $('#completed').click(function(){
        $(".filters li a").removeClass();
        $("#completed").addClass("selected");
        $.ajax({
            type : "GET",
            url : "/todo/completed",
            dataType : "json",
            contentType : "application/json; charset=UTF-8",
            success : function(data) {
                appendItems(data);
            }
        });
    });
}

function insertData() {
    $(document).keypress(function(e) {
    if(e.which == 13 && $('.new-todo').val() != '') {
        $.ajax({
            type : "POST",
            url : "/todo",
            dataType : "json",
            data : $('.new-todo').val(),
            contentType : "application/json; charset=UTF-8",
            success : function(id){
                prependData(id);
            }
        });
        addOneCountOfActive();
    }
    });
}

function prependData(id) {
  $('.todo-list').prepend("<li><div class='view'><input class='toggle' id='" + id + "' type='checkbox'><label>"+
    $('.new-todo').val()+"</label><button class='destroy'></button></div></li>");
    $('.new-todo').val('');
}

function deleteData() {
    $(document).on('click', '.destroy',function(){
        var id = $(this).siblings('input').attr('id');
        $.ajax({
            type : "DELETE",
            url : "/todo/" + id,
        });
        $('.view [id=' + id + ']').parent().parent().remove();
        updateCountOfActive();
    });

}

function deleteAllCompletedData() {
    $(".clear-completed").click(function(){
        $.ajax({
            type : "DELETE",
            url : "/todo/items",
            success : function(){
            }
        });
        $(".filters li a").removeClass().first().addClass("selected");
        $(".completed").remove();
    });
}

function updateCountOfActive() {
  var count = $('.todo-list li').length - $('.todo-list li.completed').length;
  $('#left-items').text(count);
  console.log(count);
}

function addOneCountOfActive() {
  var temp = parseInt($('#left-items').text()) + 1;
  $('#left-items').text(temp);
}

function subOneCountOfActive() {
  var temp = parseInt($('#left-items').text()) - 1;
  $('#left-items').text(temp);
}
