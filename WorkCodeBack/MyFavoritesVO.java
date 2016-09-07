package com.lvmama.comm.pet.po.mobile;

import java.io.Serializable;
import java.util.Date;

public class MyFavoritesVO implements Serializable {
	
	private static final long serialVersionUID = -1060381510482548199L;
	
	/** 被收藏对象的id */
	private Long objectId;
	/** 被收藏对象的类型 . PLACE("标的"), PRODUCT("产品"), GUIDE("攻略"); */
	private String objectType;
	/** 收藏时间 . */
	private Date createdTime;
	/** 被收藏对象的图片链接地址 */
	private String objectImageUrl;
	/** 被收藏对象的名称 . */
	private String objectName;
	/** 市场价格 */
	private Float marketPriceYuan;
	/** 驴妈妈销售价格 */
	private Float sellPriceYuan;
	/** 评价分数 */
	private Float avgScore;
	/** 地址 */
	private String address;
	/** 是否今日可定 */
	private boolean todayOrderAble = false;
	/** 对于place 主题 对于router 产品类别 */
	private String subject;
	/** 游玩天数 */
	public String visitDay;
	/** 点评返现金额 */
	private Long maxCashRefund = 0l;
	/** 是否支持多定多惠，早定早惠 */
	private boolean hasBusinessCoupon;
	/** 评论数 */
	private Long cmtNum;
	/** 活动标签 */
	private String[] tagNames;
	/**活动标签描述*/
	private String[] tagMemos;
	/**标签描述对*/
	private TagMemoPair[] tagMemoPairs;
	/** 是否手机独享 product_search_info表中channel只为CLIENT时 */
	private boolean mobileAlone;
	/** 是否支持奖金抵扣或者积分抵用 true：是 ；false：否 */
	private boolean canDeduction;
	/** 团购购买人数 */
	private int orderCount;
	/** 产品类型 */
	private String productType;
	/** 酒店星级 */
	private String hotelType;
	/** 酒店免费wifi */
	private boolean hotelWifi;
	/** 酒店停车场 */
	private boolean hotelPark;
	/** 供应商类型 */
	private String supplierType;
	/** 返现 */
	private Double cashBack;
	/** 景区级别 */
	private String placeStar;
	/** 好评率 */
	private String commentGood;
	/** 线路类型 */
	private String routeType;
	/** 是否有促销 */
	private boolean promotionFlag;
	/** 出发港口 */
	private String departure;
	/** 数据来源（VST迁移后，为了区分新老数据） */
	private String dataFrom;
	/** 团购、秒杀类型 */
	private String branchType;
	/** 团购、秒杀产品ID */
	private Long productId;
	/** 团购、秒杀商品ID */
	private Long suppGoodsId;
	/** 签证范围 */
	private String visaRange;
	/** 多出发地Id */
	private Long originId;
	private int visaAheadDays;
	
	/**是否为下架的产品<br>0：正常在售，1：未查到，2：已下架，默认为未查到*/
	private short isSoldOut = 1;
	
	/**品类标签,中文名称*/
	private String categoryCnTag;
	/**品类标签，英文名称*/
	private String categoryEnTag;
	
	public Long getObjectId() {
		return objectId;
	}
	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getObjectImageUrl() {
		return objectImageUrl;
	}
	public void setObjectImageUrl(String objectImageUrl) {
		this.objectImageUrl = objectImageUrl;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public Float getMarketPriceYuan() {
		return marketPriceYuan;
	}
	public void setMarketPriceYuan(Float marketPriceYuan) {
		this.marketPriceYuan = marketPriceYuan;
	}
	public Float getSellPriceYuan() {
		return sellPriceYuan;
	}
	public void setSellPriceYuan(Float sellPriceYuan) {
		this.sellPriceYuan = sellPriceYuan;
	}
	public Float getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(Float avgScore) {
		this.avgScore = avgScore;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isTodayOrderAble() {
		return todayOrderAble;
	}
	public void setTodayOrderAble(boolean todayOrderAble) {
		this.todayOrderAble = todayOrderAble;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getVisitDay() {
		return visitDay;
	}
	public void setVisitDay(String visitDay) {
		this.visitDay = visitDay;
	}
	public Long getMaxCashRefund() {
		return maxCashRefund;
	}
	public void setMaxCashRefund(Long maxCashRefund) {
		this.maxCashRefund = maxCashRefund;
	}
	public boolean isHasBusinessCoupon() {
		return hasBusinessCoupon;
	}
	public void setHasBusinessCoupon(boolean hasBusinessCoupon) {
		this.hasBusinessCoupon = hasBusinessCoupon;
	}
	public Long getCmtNum() {
		return cmtNum;
	}
	public void setCmtNum(Long cmtNum) {
		this.cmtNum = cmtNum;
	}
	public String[] getTagNames() {
		return tagNames;
	}
	public void setTagNames(String[] tagNames) {
		this.tagNames = tagNames;
	}
	public String[] getTagMemos() {
		return tagMemos;
	}
	public void setTagMemos(String[] tagMemos) {
		this.tagMemos = tagMemos;
	}
	public TagMemoPair[] getTagMemoPairs() {
		if(null == tagMemoPairs){
			int len = 0;
			if(null != tagNames && (len = tagNames.length) > 0){
				this.tagMemoPairs = new TagMemoPair[len];
				for (int i = 0; i < tagNames.length; i++) {
					TagMemoPair tmp = new TagMemoPair();
					tmp.setTagName(tagNames[i]);
					if(null != tagMemos && tagMemos.length > i){
						tmp.setTagMemo(tagMemos[i]);
					}
					this.tagMemoPairs[i] = tmp;
				}
			}
		}
		return tagMemoPairs;
	}
	public void setTagMemoPairs(TagMemoPair[] tagMemoPairs) {
		this.tagMemoPairs = tagMemoPairs;
	}
	public boolean isMobileAlone() {
		return mobileAlone;
	}
	public void setMobileAlone(boolean mobileAlone) {
		this.mobileAlone = mobileAlone;
	}
	public boolean isCanDeduction() {
		return canDeduction;
	}
	public void setCanDeduction(boolean canDeduction) {
		this.canDeduction = canDeduction;
	}
	public int getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getHotelType() {
		return hotelType;
	}
	public void setHotelType(String hotelType) {
		this.hotelType = hotelType;
	}
	public boolean isHotelWifi() {
		return hotelWifi;
	}
	public void setHotelWifi(boolean hotelWifi) {
		this.hotelWifi = hotelWifi;
	}
	public boolean isHotelPark() {
		return hotelPark;
	}
	public void setHotelPark(boolean hotelPark) {
		this.hotelPark = hotelPark;
	}
	public String getSupplierType() {
		return supplierType;
	}
	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}
	public Double getCashBack() {
		return cashBack;
	}
	public void setCashBack(Double cashBack) {
		this.cashBack = cashBack;
	}
	public String getPlaceStar() {
		return placeStar;
	}
	public void setPlaceStar(String placeStar) {
		this.placeStar = placeStar;
	}
	public String getCommentGood() {
		return commentGood;
	}
	public void setCommentGood(String commentGood) {
		this.commentGood = commentGood;
	}
	public String getRouteType() {
		return routeType;
	}
	public void setRouteType(String routeType) {
		this.routeType = routeType;
	}
	public boolean isPromotionFlag() {
		return promotionFlag;
	}
	public void setPromotionFlag(boolean promotionFlag) {
		this.promotionFlag = promotionFlag;
	}
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	public String getDataFrom() {
		return dataFrom;
	}
	public void setDataFrom(String dataFrom) {
		this.dataFrom = dataFrom;
	}
	public String getBranchType() {
		return branchType;
	}
	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getSuppGoodsId() {
		return suppGoodsId;
	}
	public void setSuppGoodsId(Long suppGoodsId) {
		this.suppGoodsId = suppGoodsId;
	}
	public String getVisaRange() {
		return visaRange;
	}
	public void setVisaRange(String visaRange) {
		this.visaRange = visaRange;
	}
	public int getVisaAheadDays() {
		return visaAheadDays;
	}
	public void setVisaAheadDays(int visaAheadDays) {
		this.visaAheadDays = visaAheadDays;
	}
	public Long getOriginId() {
		return originId;
	}
	public void setOriginId(Long originId) {
		this.originId = originId;
	}
	public short getIsSoldOut() {
		return isSoldOut;
	}
	public void setIsSoldOut(short isSoldOut) {
		this.isSoldOut = isSoldOut;
	}
	
	public String getCategoryCnTag() {
		return categoryCnTag;
	}
	public void setCategoryCnTag(String categoryCnTag) {
		this.categoryCnTag = categoryCnTag;
	}
	public String getCategoryEnTag() {
		return categoryEnTag;
	}
	public void setCategoryEnTag(String categoryEnTag) {
		this.categoryEnTag = categoryEnTag;
	}
	
}
