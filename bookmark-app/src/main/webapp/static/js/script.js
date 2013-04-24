
function getBoards(id, username){
	//make the RESTful call to get the list of boards for this user
	$.ajax( {
		  type:'get', 
		  url:'api/boards/user/'+id,
		  dataType: 'json',
		  success:function(data) {
		     //Here you have 'data' as xml.
		     var boardList = '<ul>';
		     
		     // e.g. {"boardList":[{"id":"b01","name":"Favourite Sites","description":"My Favourite Websites","userId":"001","img":"fav.png"},
		     //                    {"id":"b02","name":"News","description":"News","userId":"001","img":"news.jpg"}]} 
		     
		    
		     var boardItems = "";
		     var boardCount = 0;
		     $.each(data.boardList, function(i, board) 
             {
		    	    boardCount++;
			        boardItems = boardItems+'<li>'+board.name+'</li>';
	         });

			 boardList = boardItems+ '</ul>';		
			 $('#userBoardsModal').modal('show');   
			 $('#userBoardsModal').find('#userboards').html('<p>' +  username + ' has ' + boardCount + ' boards </p>' + boardList);			 
		  } // success
		}); // ajax
}


function checkURL(urlField){

	var url = urlField.val();
	if(/^([a-z]([a-z]|\d|\+|-|\.)*):(\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?((\[(|(v[\da-f]{1,}\.(([a-z]|\d|-|\.|_|~)|[!\$&'\(\)\*\+,;=]|:)+))\])|((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=])*)(:\d*)?)(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*|(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)|((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)|((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)){0})(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(url)) {
	  return true;
	} else {
	  return false;
	}
}

$(document).on("click", ".img_thumbnail", function () {
	//var image = this.src.substr(this.src.lastIndexOf('/') + 1);
	var image = this.id;
	$(".modal-body #img").val(image);
	$(".img_thumbnail").removeClass("selected");
	$(this).addClass("selected");
});

$(document).on("click", ".saveBoard", function () {

	var errors = false;
	var errorText = "";
	if( !$("#saveBoardForm .modal-body #name").val()) {
		 errors = true;
		 errorText += "<li>Name is mandatory!</li>";
	 }
	if(errors){		
	    $('#formErrorsDivBoard').html(errorText);
	    $('#formErrorsDivBoard').show();
		return;
	}
	else{
		$(this).addClass("disabled");
	    document.getElementById("saveBoardForm").submit();
	}
});

$(document).on("click", ".saveLink", function () {	 

	var errors = false;
	var errorText = "";
	var urlField =  $("#saveLinkForm .modal-body #url");
	
	 // Don't have the fields mandatory for now.
	 //$('#saveLinkForm input').each(
	 //           function(){
	 //           	if( !$(this).val() && $(this).attr('type')=="text" ) {
	 //           		errors = true;
	 //           		errorText += "<li>"+$(this).attr('name')+" is mandatory!</li>";
	 //             }
	 //           });  
	
	if (!checkURL(urlField)){
    	errors = true;
		errorText += "<li>Invalid URL! Try: http://google.com</li>";
	}
	
	if(errors){		
	    $('#formErrorsDivLink').html(errorText);
	    $('#formErrorsDivLink').show();
		return;
	}
	else{
		$(this).addClass("disabled");
	    $('#loading-indicator').show();
	    document.getElementById("saveLinkForm").submit();
	}
	
});



$(document).on("click", ".open-boardModal", function () {
	var id = $(this).data('id');
	
	if(id=="NEW"){
		// clear any values
	    $(".modal-body #id").val("");
	    $(".modal-body #name").val("");
	    $(".modal-body #description").val("");
	    $(".modal-body #img").val("icon-star");
	    $(".modal-body #ispublic").val(false);
    	$('input:radio[name=ispublicRadio]:nth(0)').attr('checked',false);
	    $('#boardModal').modal('show');
	    return;
	}
	
    $(".modal-body #id").val( id );
   
    var name = $(this).data('name');
    $(".modal-body #name").val( name );
    
    var description = $(this).data('desc');
    $(".modal-body #description").val( description );
    
    var img = $(this).data('img');
    $(".modal-body #img").val( img );
    
    var isPublic = $(this).data('ispublic');
    if(isPublic){
    	$('input:checkbox[name=ispublicRadio]:nth(0)').attr('checked',true);
	    $(".modal-body #ispublic").val(true);
    }
    else{
    	$('input:checkbox[name=ispublicRadio]:nth(0)').attr('checked',false);
	    $(".modal-body #ispublic").val(false);
    }
    
    $('.img_thumbnail').each(
            function(){
            	if((this.src).indexOf(img) !== -1)
            	    $(this).addClass("selected");
                else
                	 $(this).removeClass("selected");
            });    
    
    $('#boardModal').modal('show');
});

function toggleIsPublic(){
	
	 var selected = $('input:checkbox[name=ispublicRadio]:checked').val();
	 if(selected){
		    $(".modal-body #ispublic").val(true);
	 }
	 else{
		    $(".modal-body #ispublic").val(false);
	 }
}

$(document).on("click", ".open-linkModal", function () {

	$(".modal-footer #saveLink").removeClass("disabled");
    
    var boardId = $(this).data('boardid');
    $(".modal-body #boardId").val( boardId );

    var id = $(this).data('id');
    $(".modal-body #id").val( id );
    
    var name = $(this).data('name');
    $(".modal-body #name").val( name );
    
    var description = $(this).data('desc');
    $(".modal-body #description").val( description );
    
    var url = $(this).data('url');
    $(".modal-body #url").val( url );
    
    var gotolink = $(this).data('gotolink');
    $(".modal-body #gotolink").val( gotolink );
    
    $('#linkModal').modal('show');
});



$(document).on("click", ".open-deleteBoardModal", function () {
     var boardid = $(this).data('boardid');
     $(".modal-body #boardid").val( boardid );
});

$(document).on("click", ".open-deleteLinkModal", function () {
     var linkid = $(this).data('linkid');
     $(".modal-body #linkid").val( linkid );
});

$(document).on("click", ".open-deleteUserModal", function () {
     var userId = $(this).data('userid');
     $(".modal-body #userId").val( userId );
});

$(document).on("click", ".open-lockoutUserModal", function () {
    var userId = $(this).data('userid');
    var enabled = $(this).data('enabled');
    $(".modal-body #userId").val( userId );
    $(".modal-body #enabled").val( enabled );
});

$(document).on("click", ".open-enableUserModal", function () {
    var userId = $(this).data('userid');
    var enabled = $(this).data('enabled');
    $(".modal-body #userId").val( userId );
    $(".modal-body #enabled").val( enabled );
});


/*
$('#linkcontainer').isotope({ 
	filter: '.item', 
	masonry: {
    columnWidth: 240}, 
    }, 
    function( $items ) {
	  var id = this.attr('id'),
	      len = $items.length;
	  console.log( 'Isotope has filtered for ' + len + ' items in #' + id );
	});
	*/
	
	///$(document).ready(function(){
	$(window).load(function(){

	    var $container = $('#linkcontainer');
	    $container.isotope({
	        filter: '.item',
	        animationOptions: {
	            duration: 750,
	            easing: 'linear',
	            queue: false,
	        }
	    });
	});
	