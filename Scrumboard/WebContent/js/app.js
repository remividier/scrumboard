// FONCTIONS
function placerScrumzone() {
	var widthZone = ($(window).width()-(5*15)-(4*2))/4;
	var heightZone = $(window).height()-107;

	$('.scrumzone').width(widthZone);
	$('.scrumzone').height(heightZone);

	$('.scrumzone-us').css('left', '15px');
	$('.scrumzone-todo').css('left', (widthZone+17)*1+15+'px');
	$('.scrumzone-wip').css('left', (widthZone+17)*2+15+'px');
	$('.scrumzone-done').css('right', '15px');
}

function afficherScrumzone() {
	$('.scrumzone').fadeIn('slow');
}

function afficherActions() {
	$('.menu-gauche').fadeIn('slow');
}



// LISTENERS
$(window).resize(function() {
	placerScrumzone();
});

$('a.list-group-item').on('click', function(){
	var menu = $(this).attr('data-menu');

	$('a.list-group-item.active').removeClass('active');
	$('a.list-group-item[data-menu='+menu+']').addClass('active');

	$('.menu-content').css('visibility', 'hidden');
	$('.menu-content[data-menu='+menu+']').css('visibility', 'visible');
});

$('#projet-list .item').on('click', function(){

	$.ajax({
  method: "POST",
  url: "some.php",
  data: { name: "John", location: "Boston" }
}).done(function() {
    alert( "success" );
  })
  .fail(function() {
    alert( "error" );
  });

	// TODO Charger les données du projet en question 

	afficherActions();
	afficherScrumzone();
});



// MAIN
$(function() {
	placerScrumzone();
	$('.menu a[data-menu=projets]').click();
    console.log( "ready!" );
});