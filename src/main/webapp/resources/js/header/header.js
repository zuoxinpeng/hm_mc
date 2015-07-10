/**
 * Created with JetBrains PhpStorm.
 * User: YK
 * Date: 14-10-16
 * Time: 下午2:36
 * To change this template use File | Settings | File Templates.
 */
!window.ivObj&&(window.ivObj={})
var iv=window.ivObj;
    iv.onMenuEvent=function(tar,activeClass,subMenuClass){
        var aC=activeClass||"hover",subC=subMenuClass||"ul"
        tar=tar||".JScrollableBar ul li";
        $(tar).hover(function(){
            $(this).addClass(aC);
            $(this).children(subC).show();
        },function(){
            $(this).removeClass(aC);
            $(this).children(subC).hide();
        })
    }
$(function(){
    ivObj&&(ivObj.onMenuEvent());
    $(".sitemenu1st li.first").click(function(){
           $("#m1_menu_color").removeClass("iamorder").removeClass("iamdistribution").addClass("iamsales");
           $(".menuBarItem").hide();
           $("#menu_sales").show();
    })
    $(".sitemenu1st li.middle").click(function(){
        $("#m1_menu_color").removeClass("iamsales").removeClass("iamdistribution").addClass("iamorder");
        $(".menuBarItem").hide();
        $("#menu_order").show();
    })
    $(".sitemenu1st li.last").click(function(){
        $("#m1_menu_color").removeClass("iamorder").removeClass("iamsales").addClass("iamdistribution");
        $(".menuBarItem").hide();
        $("#menu_distribution").show();
    })


})