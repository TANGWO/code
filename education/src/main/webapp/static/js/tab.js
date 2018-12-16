$(function(){

	$(window).resize(function () {          //当浏览器大小变化时
		change();
	});
	change();
})

function change() {
	var windowHeight = $(window).height();
	var documentHeight = $(document.body).height();
	var footerHeight = $('.footer').outerHeight(true);
	var newDocumentHeight = documentHeight + footerHeight;
	if ($('.footer').css('position') == 'fixed' && newDocumentHeight > windowHeight) {
		documentHeight = newDocumentHeight;
	}
	if (windowHeight > documentHeight) {
		$('.footer').css('position', 'fixed');
	} else {
		$('.footer').css('position', 'static');
	}
}



/*$(".tabtitle span").click(function(){
			    $('.tabtitle span').removeClass('on')
                $(this).addClass("on");
               var index = $(".tabtitle span").index($(this));
               $(".tabbox").css("display","none");
               $(".tabbox").eq(index).css("display","block");
           });*/
			







