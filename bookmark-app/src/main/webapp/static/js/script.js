
$(document).on("click", ".img_thumbnail", function () {	  
	    var image = this.src.substr(this.src.lastIndexOf('/') + 1);
	    $(".modal-body #img").val(image);
	    $(".img_thumbnail").removeClass("selected");
	    $(this).addClass("selected");
});


$(document).on("click", ".open-boardModal", function () {
	var id = $(this).data('id');
	
	if(id=="NEW"){
		// clear any values
	    $(".modal-body #id").val("");
	    $(".modal-body #name").val("");
	    $(".modal-body #description").val("");
	    $(".modal-body #img").val("");
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
	