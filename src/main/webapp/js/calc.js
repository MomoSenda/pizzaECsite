$(function(){
	
	$('.size').change(function(){
		alart("size");
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
    	console.log("サイズ："+ str);
    	console.log("トッピング："+ cnt);
    	console.log("枚数："+ quantity);
    	};
});