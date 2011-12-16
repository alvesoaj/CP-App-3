$( document ).ready( function(){
	$( ".crypt" ).click( function(){
		Android.update( $(this).attr( "app_id" ), $(this).attr( "password" ) );
	});
});