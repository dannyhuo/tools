<!DOCTYPE html>
<#include "/WEB-INF/pages/myspace/base/doctype.ftl"/>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="http://pic.lvmama.com/min/index.php?f=/styles/v6/header_new.css,/styles/v4/modules/dialog.css,/styles/v5/modules/tip.css,/styles/v5/modules/button.css" >
	<link rel="stylesheet" href="http://pic.lvmama.com/styles/v6/myspace/newmember.css" >
	<link rel="stylesheet" href="http://pic.lvmama.com/styles/v6/myspace/newcommon.css" >
	<link rel="stylesheet" href="http://pic.lvmama.com/styles/v6/myspace/integral.css" >
	<link href="http://s3.lvjs.com.cn/styles/new_v/ob_common/ui-components.css" rel="stylesheet" />
    <title>我的收藏-驴妈妈旅游网</title>
<#include "/WEB-INF/pages/myspace/base/lv-meta.ftl"/>
<#include "/common/coremetricsHead.ftl">
</head>
<body class = "lv_newhome lvnav_collection">
<#include "/WEB-INF/pages/myspace/base/header.ftl"/>

<!--会员中心页面开始-->
<div class="newmember_main">
	<#include "/WEB-INF/pages/myspace/base/lv-nav.ftl"/>
    <div class="newmember_rightside">
    	<div class="mod_box shadow_down">
            <div class="mod_content p20_4">
            	<div class="password_edit_title">
                  <a href="javascript:;" class="fav-bat-btn fr">批量管理</a>
            	  <h4>我的收藏</h4>
                  <div class="favorite_action_operation">
                  <label><input type="checkbox" class="js_check_all">全选</label>
                  <a href="javascript:;" class="fav-dele-btn">删除（<span id="del_checked_num">4</span>）</a></div>
                </div>
                <div class="integral_content">
                    <ul class="fav-tab">
                        <li class="active">
                        	<a href="/myspace/favorites.do?objectType=all">全部（${allCount}）
                        	<i class="conmon_icon icon-up"></i></a>
                        </li>
                        <li>
                        	<a href="/myspace/favorites.do?objectType=PLACE">门票（${placeCount}）
                        	<i class="conmon_icon icon-up"></i></a>
                        </li>
                        <li>
                        	<a href="/myspace/favorites.do?objectType=PRODUCT">旅游线路（${productCount}）
                        	<i class="conmon_icon icon-up"></i></a>
                        </li>
                        <li>
                        	<a href="/myspace/favorites.do?objectType=HOTEL">酒店（${hotelCount}）
                        	<i class="conmon_icon icon-up"></i></a>
                        </li>
                        <li>
                        	<a href="/myspace/favorites.do?objectType=SHIP">邮轮（${shipCount}）
                        	<i class="conmon_icon icon-up"></i></a>
                        </li>
                        <li>
                        	<a href="/myspace/favorites.do?objectType=VISA">签证（${visaCount}）
                        	<i class="conmon_icon icon-up"></i></a>
                        </li>
                        <li>
                        	<a href="/myspace/favorites.do?objectType=WIFI">WIFI/电话卡（${wifiCount}）
                        	<i class="conmon_icon icon-up"></i></a>
                        </li>
                        <li>
                        	<a href="/myspace/favorites.do?objectType=SECKILL">特卖（${temaiCount}）
                        	<i class="conmon_icon icon-up"></i></a>
                        </li>
                    </ul>
                    
                    <div class="js_con">
                    	<#if pager.items?has_content>
	                        <ul class="fav-content js-fav-hover clearfix ">
	                        	<#list pager.items as myFavoriteVo>
	                        		<#if myFavoriteVo.isSoldOut == 0>
			                            <li>
			                                <a href="#" class="fav-content-img">
			                                    <i class="fav-content-icon fav-${myFavoriteVo.categoryEnTag}">${myFavoriteVo.categoryCnTag}</i>
			                                    <span class="fav-dele commonFilter">
			                                        <i class="conmon_icon"></i>
			                                    </span>
			                                    <img src="${myFavoriteVo.objectImageUrl}" width="228" height="152">
			                                </a>
			                                <div class="fav-cotent-box">
			                                    <p class="fav-cotent-txt"><a href="#" title="${myFavoriteVo.objectName}">${myFavoriteVo.objectName}</a></p>
			                                    <#if myFavoriteVo.tagMemoPairs?has_content>
				                                    <p class="fav-tag-box">
				                                    	<#list myFavoriteVo.tagMemoPairs as tag>
				                                        	<i class="fav-icon-bg tag" tip-content="<#if tag.tagMemo?has_content>${tag.tagMemo}</#if>">${tag.tagName}</i>
				                                    	</#list>
				                                    </p>
			                                    </#if>
												<p class="fav-cotent-date ellipsis">主题乐园，主题体验、门票</p>
			                                    <div class="fav-price-box">
			                                        <p class="fav-price"><em>¥</em>${myFavoriteVo.sellPriceYuan}<span>起</span></p>
			                                    </div>
			                                </div>
			                            </li>
			                        <#else>
			                        	<li>
			                                <a href="#" class="fav-content-img">
			                                    <i class="fav-content-icon fav-${myFavoriteVo.categoryEnTag}">${myFavoriteVo.categoryCnTag}</i>
			                                    <span class="fav-dele commonFilter">
			                                        <i class="conmon_icon"></i>
			                                    </span>
			                                    <img src="${myFavoriteVo.objectImageUrl}" width="228" height="152">
			                                </a>
			                                <div class="fav-cotent-box">
			                                    <p class="fav-cotent-txt"><a href="#" title="${myFavoriteVo.objectName}">${myFavoriteVo.objectName}</a></p>
			                                    <#if myFavoriteVo.tagMemoPairs?has_content>
				                                    <p class="fav-tag-box">
				                                    	<#list myFavoriteVo.tagMemoPairs as tag>
				                                        	<i class="fav-icon-bg tag" tip-content="<#if tag.tagMemo?has_content>${tag.tagMemo}</#if>">${tag.tagName}</i>
				                                    	</#list>
				                                    </p>
			                                    </#if>
												<p class="fav-cotent-date ellipsis">下架了。。。。</p>
			                                    <div class="fav-price-box">
			                                        <p class="fav-price"><em>¥</em>${myFavoriteVo.sellPriceYuan}<span>起</span></p>
			                                    </div>
			                                </div>
			                            </li>
		                            </#if>
	                            </#list>
	                        </ul>
	                        <div class="page_box mart20"><@s.property escape="false" value="@com.lvmama.comm.utils.Pagination@pagination(pager.pageSize,pager.totalPageNum,pager.url,pager.currentPage)"/></div>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
	</div>
</div>
<!--会员中心页面结束-->

<#include "/WEB-INF/pages/myspace/base/lv-help.ftl"/><!--右侧帮助中心-->
<#include "/WEB-INF/pages/myspace/base/lv-footer.ftl"/>

<!-- 公用js--> 
<script src="http://pic.lvmama.com/min/index.php?f=js/new_v/jquery-1.7.2.min.js,/js/v5/modules/pandora-dialog.js,/js/v5/modules/pandora-poptip.js"></script>
<script src="http://pic.lvmama.com/js/v6/myspace/lvmember.js"></script>
<script>
//调用方法
$(".tag").poptip();

//可配置参数
$(".tag").poptip({
    templete : 1,
    skin: "default",    //默认皮肤
    tiptitle: "",       //可统一设置标题
    place : 7,          //点钟方位，默认7点钟方向
    offsetX :-18,        //偏移修正
    offsetY : 0, 
    trigger : "mouseenter",     // mouseenter or click
    bindevent : "live",         // bind or live
    hovershow : 100       // 300 or undefined
});
</script>
<script type="text/javascript">
$(function(){
    $('.fav-bat-btn').toggle(function() {
        $(this).text('完成')
        $('.favorite_action_operation').show();
        $('.js_con').each(function(){
           $(this).find('ul').removeClass('js-fav-hover');
           $(this).find('li').addClass('js-fav-select');

        });
   },function(){
        $(this).text('批量管理')
        $('.favorite_action_operation').hide();
        $('.js_con').each(function(){
           $(this).find('ul').addClass('js-fav-hover');
           $(this).find('li').removeClass('js-fav-select');
           $('.fav-content li').removeClass('js-fav-action');
        });
   });

    $(".fav-dele").click(function () {
    var that = this;
    $.confirm("您确认删除所选收藏吗？", function () {
        $(that).parent().parent().remove();
        });
    });

    $('.fav-content li').click(function() {
        if($(this).hasClass('js-fav-action')){
            $(this).removeClass('js-fav-action');
        }else if($(this).hasClass('js-fav-select')){
            $(this).addClass('js-fav-action');
        }
        if($(this).hasClass('js-fav-select')){
            return false;
        }
    });

    $('.js_check_all').change(function() {
        if($(this).is(':checked')){
            $('.fav-content li').addClass('js-fav-action');
        }else{
            $('.fav-content li').removeClass('js-fav-action');
        }
    });


   
})
</script>

<script>
    cmCreatePageviewTag("手机收藏", "D0001", null, null,"-_--_--_--_--_-其他页面");
</script>
</body>
</html>