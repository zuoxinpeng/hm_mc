/**
 * Created with JetBrains PhpStorm.
 * User: 郑国梁
 * Date: 14-10-14
 * Time: 下午3:05
 * To change this template use File | Settings | File Templates.
*
* 这是一个jQuery插件，保存在jQuery的对象中。
* 调用方法：$.tabEffect.effect(option),其中option是一个对象，可以覆盖默认的一些属性，主要是DOM对象或者html标签的类名
*
* option:{
* $eleParent,           //包含tab标签的element父元素的jQuery对象，默认：$('ul.contents-tab')
* eleTag,              //tab项HTML标签，点击切换效果将在这个标签上通过类的切换展示，默认：'li'
* activeClass,        //当前激活状态的类名，默认：'on'
* $contentParent,    //包含内容标签的element父元素的jQuery对象，默认：$(".contents-box")
* eleContentClass,  //与每个tab对应的内容层的类名，默认：'.contents-part'
* }
*/
    (function($){
        $.tabEffect={
            defaultOption:{//默认属性，调用时可以覆盖此属性的值
                $eleParent:$('ul.contents-tab'),
                tabTag:'li',
                activeClass:'on',
                $contentParent:$(".contents-box"),
                tabContentClass:'.contents-part',
                url:["float_iframe.html","float_iframe_modify1.html","float_iframe.html"]
            },
            addEvent:function(opt){//为HTML标签添加event事件方法
                var ep=opt.$eleParent,
                    ele= ep.children(opt.tabTag),
                    aCls=opt.activeClass,
                    cp=opt.$contentParent,
                    ce=cp.children(opt.tabContentClass),
                    frame;
                ele.click(function(){//绑定点击事件，
                    var index=ele.index(this);
                    ele.removeClass(aCls)//删除所有tab标签的一个类
                    $(this).addClass(aCls);//为当前点击tab标签添加一个类，以作醒目展示
                    if(index<=ce.size()){//查找对应需要显示的内容层
                        ce.hide();
                        frame=$(ce.get(index)).children("iframe")
                        if(frame.attr("data-true")=='false'){
                            frame.attr('src',opt.url[index]).attr("data-true","true");
                        }
                        $(ce.get(index)).show();
                    }
                })
            },
            initialize:function(opt){//接受一个用来覆盖默认属性的对象
                opt&& $.extend(this.defaultOption,opt);
                this.addEvent(this.defaultOption);
            }
        }
    })(jQuery)

