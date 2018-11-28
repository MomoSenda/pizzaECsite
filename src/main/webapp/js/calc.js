$(function(){
	
	$('.size').change(function(){
		calc();
	});
	
	$('.toppingList').change(function() {
		calc();
	});
	
    $('.form-control').change(function() {
    	calc();
    });
 
    function calc() {
    	var str = $('.size:checked').val();
    	var cnt = $('.toppingList:checkbox:checked').length;
    	var quantity = $('.form-control option:selected').val();
    	var total = 0;
    	var priceM = $('#priceM').val();
    	var priceL = $('#priceL').val();
    	var toppingM = $('#toppingM').val();
    	var toppingL = $('#toppingL').val();
    	
    	if( str == 'M' ){
    		console.log("Mサイズの金額は"+ priceM );
    		total = ( Number(priceM) + Number(cnt * toppingM) ) * quantity;
    	}else{
    		console.log("Lサイズの金額は"+ priceL );
    		total = ( Number(priceL) + Number(cnt * toppingL) ) * quantity;
    	}
    	$('#total').text(total.toLocaleString());
    	};
    	
});