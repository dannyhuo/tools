<!DOCTYPE html>
<#include "/WEB-INF/pages/myspace/base/doctype.ftl"/>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="http://pic.lvmama.com/min/index.php?f=/styles/v5/modules/button.css" >
    <title>我的收藏-驴妈妈旅游网</title>
<#include "/WEB-INF/pages/myspace/base/lv-meta.ftl"/>
<#include "/common/coremetricsHead.ftl">
</head>
<body class = "lv_newhome lvnav_collection">
<#include "/WEB-INF/pages/myspace/base/header.ftl"/>

<div class="newmember_main">
	<!--我的收藏－左则菜单-->
	<#include "/WEB-INF/pages/myspace/base/lv-nav.ftl"/>
	
	<!--我的收藏－右则内容-->
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
                        <li <#if objectType == "all">class="active"</#if>>
                        	<a href="/myspace/favorites.do?objectType=all">全部（${allCount}）</a>
                        	<i class="conmon_icon icon-up"></i>
                        </li>
                        <li <#if objectType == "PLACE">class="active"</#if>>
                        	<a href="/myspace/favorites.do?objectType=PLACE">门票（${placeCount}）</a>
                        	<i class="conmon_icon icon-up"></i>
                        </li>
                        <li <#if objectType == "PRODUCT">class="active"</#if>>
                        	<a href="/myspace/favorites.do?objectType=PRODUCT">旅游线路（${productCount}）</a>
                        	<i class="conmon_icon icon-up"></i>
                        </li>
                        <li <#if objectType == "HOTEL">class="active"</#if>>
                        	<a href="/myspace/favorites.do?objectType=HOTEL">酒店（${hotelCount}）</a>
                        	<i class="conmon_icon icon-up"></i>
                        </li>
                        <li <#if objectType == "SHIP">class="active"</#if>>
                        	<a href="/myspace/favorites.do?objectType=SHIP">邮轮（${shipCount}）</a>
                        	<i class="conmon_icon icon-up"></i>
                        </li>
                        <li <#if objectType == "VISA">class="active"</#if>>
                        	<a href="/myspace/favorites.do?objectType=VISA">签证（${visaCount}）</a>
                        	<i class="conmon_icon icon-up"></i>
                        </li>
                        <li <#if objectType == "WIFI">class="active"</#if>>
                        	<a href="/myspace/favorites.do?objectType=WIFI">WIFI/电话卡（${wifiCount}）</a>
                        	<i class="conmon_icon icon-up"></i>
                        </li>
                        <li <#if objectType == "SECKILL">class="active"</#if>>
                        	<a href="/myspace/favorites.do?objectType=SECKILL">特卖（${temaiCount}）</a>
                        	<i class="conmon_icon icon-up"></i>
                        </li>
                    </ul>
                    
                    <div>
                    	<#if pager?has_content && pager.items?has_content>
	                        <ul class="fav-content js-fav-hover clearfix ">
	                        	<#list pager.items as myFavoriteVo>
	                        		<#assign productDetailUrl =""/><#--默认url为空-->
	                        		<#if myFavoriteVo.isSoldOut == 0>
		                        		<#--拼接产品详情url开始-->
		                        		<#if myFavoriteVo.objectType == "PLACE"><#--拼接门票产品详情url-->
											<#if myFavoriteVo.categoryId==11>
												<#assign productDetailUrl ="http://ticket.lvmama.com/scenic-urlId"/>
										    <#elseif myFavoriteVo.categoryId==12>
										    	<#assign productDetailUrl ="http://ticket.lvmama.com/p-${myFavoriteVo.productId}"/> 
											</#if>
										<#elseif myFavoriteVo.objectType == "PRODUCT"><#--拼接线路产品详情url-->
											<#if myFavoriteVo.categoryId == '15'>
												<#if myFavoriteVo.trafficAupaFlag==1>
													<#assign productDetailUrl = "http://dujia.lvmama.com/fit/group-${myFavoriteVo.productId}"/>
												<#else>
													<#assign productDetailUrl = "http://dujia.lvmama.com/group/${myFavoriteVo.productId}"/>
												</#if>
											<#elseif  myFavoriteVo.categoryId == '18' >
												<#if myFavoriteVo.trafficAupaFlag==1>
													<#assign productDetailUrl = "http://dujia.lvmama.com/fit/freetour-${myFavoriteVo.productId}"/>
												<#else>
													<#assign productDetailUrl = "http://dujia.lvmama.com/freetour/${myFavoriteVo.productId}"/>
												</#if>
											<#elseif  myFavoriteVo.categoryId == '16' >
												<#assign productDetailUrl = "http://dujia.lvmama.com/local/${myFavoriteVo.productId}"/>
											<#elseif  myFavoriteVo.categoryId == '17' >
											 	<#assign productDetailUrl = "http://dujia.lvmama.com/package/${myFavoriteVo.productId}"/>
											</#if>
											<#--多出发地会加上出发地id传到详情页--->
											<#assign isMultDeparture =(myFavoriteVo.multDepartureFlag=="Y")?string("-D"+myFavoriteVo.districtId,"")/>
											<#assign productDetailUrl = productDetailUrl + isMultDeparture/>
											<#--团购渠道108,秒杀渠道110--->
											<#if myFavoriteVo.distributorId?? && myFavoriteVo.distributorId?contains("108")>
												<#--团购-->
												<#assign productDetailUrl = "http://www.lvmama.com/tuangou/deal-${myFavoriteVo.productId}"/>
											<#elseif myFavoriteVo.distributorId?? && myFavoriteVo.distributorId?contains("110")>
												<#--秒杀-->
												<#assign productDetailUrl = "http://www.lvmama.com/tuangou/sale-${myFavoriteVo.productId}"/>
											</#if>
										<#elseif myFavoriteVo.objectType == "HOTEL"><#--拼接酒店产品详情url-->
				                      	
				                      	
										<#elseif myFavoriteVo.objectType == "SHIP"><#--拼接邮轮产品详情url-->
											<#if myFavoriteVo.distributorId?? && myFavoriteVo.distributorId?contains("108")>
												<#--团购-->
												<#assign productDetailUrl = "http://www.lvmama.com/tuangou/deal-${myFavoriteVo.productId}"/>
											<#elseif myFavoriteVo.distributorId?? && myFavoriteVo.distributorId?contains("110")>
												<#--秒杀-->
												<#assign productDetailUrl = "http://www.lvmama.com/tuangou/sale-${myFavoriteVo.productId}"/>
											<#else>
												<#assign productDetailUrl = "http://www.lvmama.com/youlun/cruise-${myFavoriteVo.productId}.html"/>
											</#if>
										<#elseif myFavoriteVo.objectType == "VISA"><#--拼接签证产品详情url-->
											<#assign productDetailUrl = "http://www.lvmama.com/visa/visa-签证规格ID.html"/>
										<#elseif myFavoriteVo.objectType == "WIFI"><#--拼接wifi/电话卡产品详情url-->
				                      	
				                      	
										<#elseif myFavoriteVo.objectType == "SECKILL"><#--拼接特卖产品详情url-->
				                      	
				                      	
										</#if>
									</#if>
	                        		<#--拼接产品详情url结束-->

									<#--绘制每个收藏的产品开始-->
		                            <li>
		                            	<#if myFavoriteVo.isSoldOut != 0>
		                            		<i class="offslef"></i><#--已下架标识-->
		                            	</#if>
		                                <a <#if productDetailUrl?has_content>href="${productDetailUrl}"</#if> class="fav-content-img">
		                                    <i class="fav-content-icon fav-${myFavoriteVo.categoryEnTag}">${myFavoriteVo.categoryCnTag}</i>
		                                    <span class="fav-dele commonFilter">
		                                        <i class="conmon_icon"></i>
		                                    </span>
		                                    <img src="${myFavoriteVo.objectImageUrl}" width="228" height="152">
		                                </a>
		                                <div class="fav-cotent-box">
		                                    <p class="fav-cotent-txt">
		                                    	<a <#if productDetailUrl?has_content>href="${productDetailUrl}"</#if> title="${myFavoriteVo.objectName}">${myFavoriteVo.objectName}</a>
		                                    </p>
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
		                            <#--绘制每个收藏的产品开始-->
	                            </#list>
	                        </ul>
	                        <div class="page_box mart20"><@s.property escape="false" value="@com.lvmama.comm.utils.Pagination@pagination(pager.pageSize,pager.totalPageNum,pager.url,pager.currentPage)"/></div>
						<#else>
							<div class="cashBox">
		                      <span class="fl">
		                         <img src="http://pic.lvmama.com/img/v6/myspace/lv_no.png" width="100" height="100">
		                      </span>
		                      <div class="nomyRecordm">
		                      	<#if objectType == "PLACE">
		                      		<p class="cashTitle noMargin">暂无门票类产品收藏~</p>
		                      	<#elseif objectType == "PRODUCT">
		                      		<p class="cashTitle noMargin">暂无线路类产品收藏~</p>
		                      	<#elseif objectType == "HOTEL">
		                      		<p class="cashTitle noMargin">暂无酒店类产品收藏~</p>
		                      	<#elseif objectType == "SHIP">
		                      		<p class="cashTitle noMargin">暂无邮轮类产品收藏~</p>
		                      	<#elseif objectType == "VISA">
		                      		<p class="cashTitle noMargin">暂无签证类产品收藏~</p>
		                      	<#elseif objectType == "WIFI">
		                      		<p class="cashTitle noMargin">暂无wifi/电话卡类产品收藏~</p>
		                      	<#elseif objectType == "SECKILL">
		                      		<p class="cashTitle noMargin">暂无特卖类产品收藏~</p>
		                      	<#else>
		                      		<p class="cashTitle noMargin">暂无收藏~</p>
		                      	</#if>
		                        <p class="cashHelp"><a href="javascript:;" class="mart10 nomywriteBtn">去看看</a></p>
		                      </div>
		                    </div>
						</#if>
                    </div>
                </div>
            </div>
        </div>
	</div>
</div>

<#include "/WEB-INF/pages/myspace/base/lv-help.ftl"/><!--右侧帮助中心-->
<#include "/WEB-INF/pages/myspace/base/lv-footer.ftl"/>

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