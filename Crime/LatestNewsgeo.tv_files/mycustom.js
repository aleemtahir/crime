function open_print_popup(page_id,story_type)
{jQuery('#contentArea').html('');jQuery('html, body').animate({scrollTop:0},500);jQuery('#transparent_div').css('height',jQuery('body').height());jQuery('.printContentArea').css('overflow-y','scroll');jQuery('.printContentArea').css('overflow-x','hidden');jQuery('.printContentArea').css('clear','both');jQuery('body').addClass('OverflowHiddenBody');jQuery('#transparent_div').css('display','block');jQuery('#print_popup').css('display','block');jQuery('.loader_print').css('display','block');jQuery('#printbutton').css('display','none');jQuery.ajax({type:'POST',url:BaseURL+'frontend/home/print_page',data:'id='+page_id+'&story_type='+story_type,cache:false,success:function(response){response=unescape(response);jQuery('.print_page_popup').css('height',jQuery(window).height()-40);jQuery('.printContentArea').css('height',jQuery(window).height()-100);jQuery('#printbutton').css('display','block');jQuery('.loader_print').css('display','none');jQuery('#contentArea').html(response);}});}
function close_print_popup()
{jQuery('#contentArea').html('');jQuery('body').removeClass('OverflowHiddenBody');jQuery('#print_popup').css('height','');jQuery('.printContentArea').css('height','');jQuery('#transparent_div').css('display','none');jQuery('#printbutton').css('display','none');jQuery('#print_popup').css('display','none');}
function print_story(divName){var printContents=document.getElementById('contentArea').innerHTML;var originalContents=document.body.innerHTML;document.body.innerHTML=printContents;window.print();document.body.innerHTML=originalContents;}
jQuery(document).ready(function(){(function($){$.fn.blink=function(options){var defaults={delay:500};var options=$.extend(defaults,options);return $(this).each(function(idx,itm){setInterval(function(){if($(itm).css("color")==="rgb(0, 0, 0)"){$(itm).css('color','#A90909');}
else{$(itm).css('color','#000000');}},options.delay);});}}(jQuery));jQuery('.hotTopicsPanel #secondary-menu li:nth-child(2) a').blink({delay:1200});});var styleAds="<style>#div-gpt-ad-billboard_atf:hover{position: relative;height: 90px !important;}#div-gpt-ad-billboard_atf:hover iframe{height: 450px;position: absolute;left: 50%;top: 0;z-index: 100000;transform: translate(-50%,0);}#div-gpt-ad-halfpage_btf:hover{position: relative;width: 600px !important;height: 600px !important;}#div-gpt-ad-halfpage_btf:hover iframe{width: 600px;position: absolute;left: 15px;top: 0;z-index: 100000;}</style>";jQuery(window).load(function(){var id=jQuery("#div-gpt-ad-billboard_atf iframe").contents().find('div').attr("onmouseover");if(typeof id!=="undefined"&&id=='dclk_show_flexible();'){jQuery("head").append(styleAds);}});