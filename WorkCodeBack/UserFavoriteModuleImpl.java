package com.lvmama.pet.mobile.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.mortbay.log.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.lvmama.cmt.api.service.CmtLatitudeScoreService;
import com.lvmama.cmt.comment.service.VstCmtTitleStatistisService;
import com.lvmama.comm.bee.service.GroupDreamService;
import com.lvmama.comm.bee.service.prod.UnityProductService;
import com.lvmama.comm.pet.po.mobile.FavoritesResult;
import com.lvmama.comm.pet.po.mobile.MobileFavorite;
import com.lvmama.comm.pet.po.mobile.MobileMyFavoriteRequest;
import com.lvmama.comm.pet.po.mobile.MyFavoritesVO;
import com.lvmama.comm.pet.service.businessCoupon.VstProdSalesService;
import com.lvmama.comm.pet.service.mobile.IUserFavoriteModule;
import com.lvmama.comm.pet.service.mobile.MobileFavoriteService;
import com.lvmama.comm.pet.service.search.PlaceSearchInfoService;
import com.lvmama.comm.pet.service.search.ProductSearchInfoService;
import com.lvmama.comm.pet.vo.Page;
import com.lvmama.comm.pet.vo.businessCoupon.VstProdSalesVO;
import com.lvmama.comm.search.vst.vo.HotelBean;
import com.lvmama.comm.search.vst.vo.RouteBean;
import com.lvmama.comm.search.vst.vo.TicketBean;
import com.lvmama.comm.search.vst.vo.VstHotelSearchVO;
import com.lvmama.comm.search.vst.vo.VstRouteSearchVO;
import com.lvmama.comm.search.vst.vo.VstTicketSearchVO;
import com.lvmama.comm.utils.MemcachedUtil;
import com.lvmama.comm.utils.StringUtil;
import com.lvmama.comm.vo.Constant.COLLECTION_FROM;
import com.lvmama.comm.vo.Constant.MY_FAVORITE_OBJECT_TYPE;
import com.lvmama.tnt.partner.comm.RequestVO;
import com.lvmama.tnt.partner.comm.ResponseVO;
import com.lvmama.tnt.partner.compship.service.CompShipService;
import com.lvmama.vst.api.biz.service.VstDistrictService;
import com.lvmama.vst.api.biz.vo.DistrictVo;
import com.lvmama.vst.api.compship.prod.vo.CompShipProductVo;
import com.lvmama.vst.api.compship.prod.vo.ProdGroupDateVo;
import com.lvmama.vst.api.prod.service.VstSuppGoodsService;
import com.lvmama.vst.api.visa.prod.service.VstVisaService;
import com.lvmama.vst.api.visa.prod.vo.VisaProductVo;
import com.lvmama.vst.api.vo.prod.GoodsBaseVo;
import com.lvmama.vst.api.vo.prod.ProdProductBranchPropBaseVo;
import com.lvmama.vst.api.vo.prod.ProductBranchBaseVo;
import com.lvmama.vst.newsearch.service.LvmamaClientHotelService;
import com.lvmama.vst.newsearch.service.LvmamaClientService;
import com.lvmama.vst.search.api.vo.PageConfigVo;
import com.lvmama.vst.search.api.vo.ResultHandleT;
import com.lvmama.vst.search.api.vo.SearchResultVo;

public class UserFavoriteModuleImpl implements IUserFavoriteModule {

	@Autowired
	private MobileFavoriteService mobileFavoriteService;
	@Autowired
	private LvmamaClientHotelService lvmamaClientHotelService;
	@Autowired
	private LvmamaClientService lvmamaClientService;
	@Autowired
	private PlaceSearchInfoService placeSearchInfoService;
	@Autowired
	private CompShipService tntCompShipService; // 邮轮组合产品
	@Autowired
	private VstSuppGoodsService vstSuppGoodsService;
	@Autowired
	private VstVisaService vstVisaService;
	@Autowired
	private VstDistrictService vstDistrictService;
	@Autowired
	private VstCmtTitleStatistisService vstCmtTitleStatistisService;
	/** 产品服务 */
	@Autowired
	private ProductSearchInfoService productSearchInfoService;
	@Autowired
	private CmtLatitudeScoreService cmtLatitudeScoreService;
	@Autowired
	private UnityProductService unityProductService; // 迁移后的统一商品接口
	@Autowired
	private GroupDreamService groupDreamService;
	
	private VstProdSalesService vstProdSalesService;
	
	/**
	 * 日志打印工具
	 */
	private final Logger logger = Logger.getLogger(this.getClass());
	/**
	 * 日志打印前缀
	 */
	private final String LOG_PREFIX = "User favorite interface's implements. ";
	/**
	 * 无线和PC的渠道ID
	 */
	private final String distributorsOfPcAndApp = "3,107,10000";
	
	/**
	 * memcache缓存时间
	 */
	private final int CACHED_SECONDS = 60 * 15;
	
	/**表示正常在售的产品*/
	private final short NONE_SOLD_OUT = 0;
	
	
	/**
	 * 取消收藏<br>
	 * <h1 style="color:blue;">收藏模块改造，PC/无线接口统一新增接口</h1>
	 */
	@Override
	public FavoritesResult deleteFavorite(MobileMyFavoriteRequest mobileMyFavoriteRequest) {
		return mobileFavoriteService.deleteFavorite(mobileMyFavoriteRequest);
	}

	/**
	 * 添加收藏<br>
	 * <h1 style="color:red;">收藏模块改造，PC/无线接口统一新增接口</h1>
	 */
	@Override
	public FavoritesResult addFavorite(MobileMyFavoriteRequest mobileMyFavoriteRequest) {
		return mobileFavoriteService.addFavorite(mobileMyFavoriteRequest);
	}
	
	/**
	 * 判断是否被收藏过
	 */
	@Override
	public boolean isFavorite(MobileMyFavoriteRequest mobileMyFavoriteRequest) {
		if(null == mobileMyFavoriteRequest || 
				StringUtils.isEmpty(mobileMyFavoriteRequest.getObjectId()) ||
				null == mobileMyFavoriteRequest.getUserId()){
			return false;
		}
		//2、 判断是否已收藏
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("objectId", mobileMyFavoriteRequest.getObjectId());
		p.put("objectType", mobileMyFavoriteRequest.getObjectType());
		p.put("userId", mobileMyFavoriteRequest.getUserId());
		p.put("dataFrom", "VST");
		// 出发地ID
		if (StringUtils.isNotBlank(mobileMyFavoriteRequest.getProductDestId()) && !"0".equals(mobileMyFavoriteRequest.getProductDestId())) {
			p.put("originId", mobileMyFavoriteRequest.getProductDestId());
		}
		List<MobileFavorite> mfList = mobileFavoriteService.queryMobileFavoriteList(p);
		if (null != mfList && mfList.size() > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 统计产品收藏过的次数
	 */
	@Override
	public Integer favoriteStatistics(String id, Short collectionFrom) {
		//1、产品ID为空，返回空
		if(StringUtils.isBlank(id)){
			return null;
		}
		
		if(null != collectionFrom && !(//如果收藏来源不为空时，来源不是app,也不是pc则无效，直接返回
				collectionFrom.equals(COLLECTION_FROM.from_app.getCode()) || 
				collectionFrom.equals(COLLECTION_FROM.from_pc.getCode())) ){
			
			this.logger.info(this.LOG_PREFIX + "favoriteStatistics(String id, Short collectionFrom), "
					+ "received the invalid @param collectionFrom :" + collectionFrom);
			return null;
		}
		
		//2、构造memcached key，如：favorite_statistics_from_app_343443
		String key = "favorite_statistics_" + (null == collectionFrom ? "all" : 
			COLLECTION_FROM.getName(collectionFrom.toString())) + "_" + id;
		
		//3、从缓存中取统计该商品收藏的次数
		MemcachedUtil memcashedUtil = MemcachedUtil.getInstance();
		Integer fromCached = (Integer) memcashedUtil.get(key);
		if(fromCached != null){
			return fromCached;
		}
		
		//4、缓存中没有，查库统计
		Integer fromDb = mobileFavoriteService.favoriteStatistics(id, collectionFrom);
		if(null != fromDb){
			memcashedUtil.set(key, CACHED_SECONDS, fromDb);
		}
		
		return fromDb;
	}
	
	
	/**
	 * 查询数量，用于分页显示时
	 * @param mobileMyFavoriteRequest
	 * @return
	 */
	public Long countFavoriteList(MobileMyFavoriteRequest mobileMyFavoriteRequest){
		if(null == mobileMyFavoriteRequest){
			return null;
		}
		Map<String, Object> param = this.getQueryCondition(mobileMyFavoriteRequest, null);
		return this.mobileFavoriteService.countMobileFavoriteList(param);
	}
	
	public Map<String, Integer> countGroupByObjectType(MobileMyFavoriteRequest mobileMyFavoriteRequest){
		if(null == mobileMyFavoriteRequest){
			return null;
		}
		Map<String, Object> param = this.getQueryCondition(mobileMyFavoriteRequest, null);
		return this.mobileFavoriteService.countGroupByObjectType(param);
	}
	
	/**
	 * 个人中心-收藏列表<br>
	 * 收藏模块改造，PC/无线接口统一新增接口
	 * 
	 * @date 2016-6-11 22:26:20
	 * @param mobileMyFavoriteRequest
	 * @return
	 */
	@Override
	public FavoritesResult queryFavoriteList(MobileMyFavoriteRequest mobileMyFavoriteRequest) {
		FavoritesResult mobileMyFavoritesResult = new FavoritesResult();
		//1、初始化分页信息
		int pageSize = mobileMyFavoriteRequest.getPageSize() == 0 ? 10 : mobileMyFavoriteRequest.getPageSize();
		int pageNum = mobileMyFavoriteRequest.getPageNum() == 0 ? 1 : mobileMyFavoriteRequest.getPageNum();
		Page<MyFavoritesVO> page = new Page<MyFavoritesVO>(pageSize, pageNum);
		
		//2、构造搜索条件
		Map<String, Object> param = getQueryCondition(mobileMyFavoriteRequest, page);
		this.logger.info(LOG_PREFIX + "The query condition is " + this.printMapStr(param));
		
		//3、从收藏表中查询收藏记录
		List<MobileFavorite> mfList = mobileFavoriteService.queryMobileFavoriteList(param);
		this.logger.info(LOG_PREFIX + "Call the mobileFavoriteService.queryMobileFavoriteList , the result(List<MobileFavorite>) of size is " + (null == mfList? 0 : mfList.size()));
		if (null == mfList || mfList.isEmpty()) {
			return mobileMyFavoritesResult;//未查询到收藏信息，直接返回empty对象
		}
		
		//4、按品类进行分组，key：品类，value:收藏的产品List<MobileFavorite>
		Map<String, List<MobileFavorite>> favGroups = this.groupByObjectType(mfList);
		Set<Entry<String, List<MobileFavorite>>> groupEntry = favGroups.entrySet();
		Iterator<Entry<String, List<MobileFavorite>>> iter = groupEntry.iterator();
		
		//5、循环处理每个类别的产品，分别去调用对应品类的接口获取产品信息
		List<MyFavoritesVO> result = new ArrayList<MyFavoritesVO>();
		while(iter.hasNext()){
			Entry<String, List<MobileFavorite>> entry = iter.next();
			String objectType = entry.getKey();
			List<MobileFavorite> branchFavoritesList = entry.getValue();
			if(MY_FAVORITE_OBJECT_TYPE.HOTEL.getCode().equals(objectType)){//酒店
				result.addAll(this.queryHotelFavorities(branchFavoritesList));
			}else if(MY_FAVORITE_OBJECT_TYPE.PRODUCT.getCode().equals(objectType)){//线路
				result.addAll(this.queryVstRouteInfo(branchFavoritesList));
			}else if(MY_FAVORITE_OBJECT_TYPE.PLACE.getCode().equals(objectType)){//门票
				result.addAll(this.queryVstTicketInfo(branchFavoritesList));
			}else if(MY_FAVORITE_OBJECT_TYPE.TUANGOU.getCode().equals(objectType) || 
					MY_FAVORITE_OBJECT_TYPE.SECKILL.getCode().equals(objectType)){//团购秒杀
				for (MobileFavorite mobileFavorite : branchFavoritesList) {
					if(null != mobileFavorite){
						MyFavoritesVO mfVo = this.queryVstTuangouSeckillInfo(mobileFavorite);
						if(null == mfVo){
							mfVo = new MyFavoritesVO();
							BeanUtils.copyProperties(mobileFavorite,mfVo);
						}else{
							mfVo.setIsSoldOut(NONE_SOLD_OUT);//找到，则设置为正常在售
						}
						result.add(mfVo);
					}
				}
			}else if(MY_FAVORITE_OBJECT_TYPE.SHIP.getCode().equals(objectType)){//邮轮
				for (MobileFavorite mobileFavorite : branchFavoritesList) {
					if(null != mobileFavorite){
						MyFavoritesVO mfVo = this.queryShipFavorities(mobileFavorite);
						if(null == mfVo){
							mfVo = new MyFavoritesVO();
							BeanUtils.copyProperties(mobileFavorite,mfVo);
						}else{
							mfVo.setIsSoldOut(NONE_SOLD_OUT);//找到，则设置为正常在售
						}
						result.add(mfVo);
					}
				}
			}else if(MY_FAVORITE_OBJECT_TYPE.VISA.getCode().equals(objectType)){//签证
				for (MobileFavorite mobileFavorite : branchFavoritesList) {
					if(null != mobileFavorite){
						MyFavoritesVO mfVo = this.queryVisaFavorities(mobileFavorite);
						if(null == mfVo){
							mfVo = new MyFavoritesVO();
							BeanUtils.copyProperties(mobileFavorite,mfVo);
						}else{
							mfVo.setIsSoldOut(NONE_SOLD_OUT);//找到，则设置为正常在售
						}
						result.add(mfVo);
					}
				}
			}else{
				//其它
				
			}
		}
		
		if(result.size() < mfList.size()){
			this.logger.warn(LOG_PREFIX + "Call the interface to query the product info, exists product not found! Real is " + 
					mfList.size() + ", query return is " + result.size());//调用查询产品信息的接口，存在未找到的产品
		}else if(result.size() > mfList.size()){
			this.logger.warn(LOG_PREFIX + "Call the interface to query the product info, return more product than real!  Real is " + 
					mfList.size() + ", query return is " + result.size());//调用查询产品信息的接口，返回的产品比实际的产品多
		}else{
			this.logger.info(LOG_PREFIX + "Call the interface return the same number of product, okay!");//收藏表中的产品都一一查到产品信息
		}

		//6、对结果进行排序
		Collections.sort(result, new Comparator<MyFavoritesVO>() {
			@Override
			public int compare(MyFavoritesVO o1, MyFavoritesVO o2) {
				if (o1.getCreatedTime() == null) {
					return -1;
				}
				if (o2.getCreatedTime() == null) {
					return 1;
				}
				if (o1.getCreatedTime().before(o2.getCreatedTime())) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		
		//7、设置结果信息
		mobileMyFavoritesResult.setFavorities(result);
		if (pageNum > 0) {
			mobileMyFavoritesResult.setHasNext(page.hasNext());// 是否最后一页
		}
		mobileMyFavoritesResult.setSuccess(true);
		return mobileMyFavoritesResult;
	}
	
	/**
	 * 
	 * @param mobileFavorites
	 * @param keyWords ID拼接的搜索关键字
	 * @param categoryTag 品类标签数组，第0个中文标签，第1个英文标签
	 * @return
	 */
	private List<MyFavoritesVO> prepare(List<MobileFavorite> mobileFavorites, 
			StringBuffer keyWords, String[] categoryTag){
		List<MyFavoritesVO> myFavoritesVoList = new ArrayList<MyFavoritesVO>();
		
		for (MobileFavorite mf : mobileFavorites) {
			keyWords.append(mf.getObjectId()).append(",");
			
			MyFavoritesVO mfVo = new MyFavoritesVO();
			BeanUtils.copyProperties(mf,mfVo);
			mfVo.setCategoryCnTag(categoryTag[0]);
			mfVo.setCategoryEnTag(categoryTag[1]);
			myFavoritesVoList.add(mfVo);
		}
		return myFavoritesVoList;
	}
	
	/**
	 * 批量查询并填充酒店产品信息
	 * @param mfList
	 * @return
	 */
	private List<MyFavoritesVO> queryHotelFavorities(List<MobileFavorite> mobileFavorites) {
		StringBuffer keyWords = new StringBuffer();
		List<MyFavoritesVO> hotelResult = prepare(mobileFavorites, keyWords, new String[]{"酒店","hotel"});
		
		VstHotelSearchVO vo = new VstHotelSearchVO();
		vo.setCancelFlag("y");// 必传
		vo.setDistributors(distributorsOfPcAndApp);// 必传
		vo.setSaleFlag("y"); // 必传
		vo.setPage(1);
		vo.setPageSize(100);
		vo.setKeyword(keyWords.toString());
		vo.setUuid(UUID.randomUUID().toString());
		vo.setCityDistrictId("9");
		ResultHandleT<SearchResultVo> handleT = lvmamaClientHotelService.getHotelIndexBeans(vo);
		if (handleT == null || handleT.getReturnContent() == null || 
				handleT.getReturnContent().getPageConfig() == null || 
				handleT.getReturnContent().getPageConfig().getItems() == null) {
			return hotelResult;
		}
		PageConfigVo<?> pageConfig = handleT.getReturnContent().getPageConfig();
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = (List<Map<String, Object>>) pageConfig.getItems();
		for (Map<String, Object> item : list) {
			HotelBean bean = (HotelBean) item.get("product");
			for (MyFavoritesVO mfVo : hotelResult) {
				if (mfVo.getObjectId().longValue() != Long.valueOf(bean.getProductId()).longValue()) {
					continue;
				}
				
				mfVo.setIsSoldOut(NONE_SOLD_OUT);//找到，则标记为正常在售产品
				
				//酒店类型
				String hotelType = "";
				if ("100".equals(bean.getStarId()) || "101".equals(bean.getStarId())) {
					hotelType = "豪华酒店";
				} else if ("102".equals(bean.getStarId()) || "103".equals(bean.getStarId())) {
					hotelType = "品质酒店";
				} else if ("104".equals(bean.getStarId()) || "105".equals(bean.getStarId())) {
					hotelType = "舒适酒店";
				} else if ("106".equals(bean.getStarId()) || "107".equals(bean.getStarId())) {
					hotelType = "简约酒店";
				}
				mfVo.setHotelType(hotelType);
				
				//地址
				String address = bean.getAddress();
				if (StringUtils.isNotBlank(address)) {
					if (address.indexOf("(") > -1 && address.indexOf(")") > -1) {// 英文括号
						address = address.substring(address.indexOf("(") + 1, address.indexOf(")"));
					} else if (address.indexOf("（") > -1 && address.indexOf("）") > -1) {// 中文括号
						address = address.substring(address.indexOf("（") + 1, address.indexOf("）"));
					}
				}
				mfVo.setAddress(address);
				
				//免费wifi和免费停车
				if(bean.getFacilities() != null) {
					if (bean.getFacilities().contains("460")) {
						// 免费wifi
						mfVo.setHotelWifi(true);
					}
					if (bean.getFacilities().contains("464")) {
						// 免费停车
						mfVo.setHotelPark(true);
					}
				}
				
				//其它信息设置
				mfVo.setObjectName(bean.getProductName());
				mfVo.setSellPriceYuan(bean.getSellPrice());
				mfVo.setMarketPriceYuan(bean.getMarketPrice());
				mfVo.setObjectImageUrl(bean.getPhotoUrl());
				mfVo.setCashBack(bean.getMaxRebateAmountMobile());
				
				Log.info("ProductId:" + bean.getProductId() + " CommentGood:" + String.valueOf(bean.getCommentGood()));
				Log.info("ProductId:" + bean.getProductId() + " PromotionFlag:" + bean.getPromotionFlag());
				
				//好评率默认100%
				if(bean.getCommentGood()==null||bean.getCommentGood()==0){
					mfVo.setCommentGood("100%");
				}else{
					int CommentGood = (new Double(bean.getCommentGood())).intValue();
					mfVo.setCommentGood(String.valueOf(CommentGood) + "%");
				}
				
				//促销标签
				mfVo.setPromotionFlag("1".equals(bean.getPromotionFlag()));
				
				// 处理标签，最多显示两个并且每个标签最多显示5个字
				Map<String, String[]> tags = this.handleTags(bean.getTagName(), bean.getTagMemo());
				mfVo.setTagNames(tags.get("tagNames"));
				mfVo.setTagMemos(tags.get("tagMemos"));
			}
		}
		return hotelResult;
	}
	
	/**
	 * 批量查询并填充线路产品信息
	 * @param mobileFavorites
	 * @return
	 */
	private List<MyFavoritesVO> queryVstRouteInfo(List<MobileFavorite> mobileFavorites) {
		StringBuffer keyWords = new StringBuffer();
		List<MyFavoritesVO> routeResult = prepare(mobileFavorites, keyWords, new String[]{"旅游线路","line"});
		VstRouteSearchVO vo = new VstRouteSearchVO();
		vo.setKeyword(keyWords.toString());
		vo.setDistributors(distributorsOfPcAndApp);
		// VST线路搜索
		ResultHandleT<SearchResultVo> handleT = lvmamaClientService.getRouteIndexBeans("ROUTE", vo);
		if (handleT == null || handleT.getReturnContent() == null || handleT.getReturnContent().getPageConfig() == null) {
			return null;
		}
		
		@SuppressWarnings("unchecked")
		PageConfigVo<RouteBean> pageConfigVo = (PageConfigVo<RouteBean>) handleT.getReturnContent().getPageConfig();
		List<RouteBean> list = pageConfigVo.getItems();
		if (list == null || list.isEmpty()) {
			return null;
		}
		
		//循环将查询到的产品信息set到对应的MyFavoriteVO中
		for (RouteBean routeBean : list) {
			String productId = routeBean.getProductId();
			for (MyFavoritesVO mfVo : routeResult) {
				if(mfVo.getObjectId().toString().equals(productId)){
					mfVo.setIsSoldOut(NONE_SOLD_OUT);//找到，则标记为正常在售产品
					
					//设置价格
					mfVo.setSellPriceYuan(routeBean.getSellPrice());
					mfVo.setMarketPriceYuan(routeBean.getMarketPrice());
					// 好评率
					Double commentGood = routeBean.getCommentGood();
					if (commentGood != null && commentGood > 0) {
						String str = String.valueOf(commentGood);
						if (!str.contains("0.")) {
							mfVo.setCommentGood(String.valueOf(commentGood) + "%");
						}
					} else {
						mfVo.setCommentGood("100%");
					}
					// 类型
					mfVo.setRouteType(routeBean.getCategoryName());
					if ("酒店套餐".equals(routeBean.getCategoryName())) {
						mfVo.setRouteType("自由行");
					}
					// 游玩天数
					mfVo.setVisitDay(routeBean.getRouteNum());
					// 返现
					mfVo.setCashBack(routeBean.getMaxRebateAmountMobile());
					// 处理标签，最多显示两个并且每个标签最多显示5个字
					Map<String, String[]> tags = this.handleTags(routeBean.getTagName(), routeBean.getTagMemo());
					mfVo.setTagNames(tags.get("tagNames"));
					mfVo.setTagMemos(tags.get("tagMemos"));
					/*if (routeBean.getTagNameArr() != null && routeBean.getTagNameArr().length > 0) {
						String tags[] = routeBean.getTagNameArr();
						String result[] = new String[tags.length];
						for (int k = 0; k < tags.length; k++) {
							if (k < 2) {
								String tag = tags[k].replaceAll("\\s*", "");
								if (tag.length() > 5) {
									tag = tag.substring(0, 5);
								}
								result[k] = tag;
							}
						}
						mfVo.setTagNames(result);
					}*/

					//设置是否有促销
					mfVo.setPromotionFlag("1".equals(routeBean.getPromotionFlag()));
					
					//设置线路的品类
					if(StringUtil.isNotEmptyString(routeBean.getCategoryName())){
						mfVo.setCategoryCnTag(routeBean.getCategoryName());
						if("自由行".equals(routeBean.getCategoryName().trim())){
							mfVo.setCategoryEnTag("domestic");
						}else if("跟团游".equals(routeBean.getCategoryName().trim())){
							mfVo.setCategoryEnTag("outbound");
						}else if("当地游".equals(routeBean.getCategoryName().trim())){
							
						}else if("酒店套餐".equals(routeBean.getCategoryName().trim())){
							
						}
					}
					
					break;//找到，则跳出内层循环
				}
			}
		}
		return routeResult;
	}


	/**
	 * 批量查询并填充门票产品信息
	 * @param mobileFavorites
	 * @return
	 */
	private List<MyFavoritesVO> queryVstTicketInfo(List<MobileFavorite> mobileFavorites) {
		StringBuffer keyWords = new StringBuffer();
		List<MyFavoritesVO> ticketResult = prepare(mobileFavorites, keyWords, new String[]{"门票","tick"});
		VstTicketSearchVO vo = new VstTicketSearchVO();
		vo.setKeyword(keyWords.toString());
		vo.setDistributors(distributorsOfPcAndApp);
		
		// VST搜索接口查询产品信息
		ResultHandleT<SearchResultVo> handleT = lvmamaClientService.getTicketIndexBeans(vo);
		if (handleT != null && handleT.getReturnContent() == null || handleT.getReturnContent().getPageConfig() == null) {
			return null;
		}
		PageConfigVo<?> pageConfigVo = handleT.getReturnContent().getPageConfig();
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = (List<Map<String, Object>>) pageConfigVo.getItems();
		if (list == null || list.isEmpty()) {
			return ticketResult;
		}
		
		for (Map<String, Object> ticketMap : list) {
			if (ticketMap.get("product") == null) {
				continue;
			}
			TicketBean product = (TicketBean) ticketMap.get("product");
			for (MyFavoritesVO mfVo : ticketResult) {
				if(mfVo.getObjectId().toString().equals(product.getProductId())){
					mfVo.setIsSoldOut(NONE_SOLD_OUT);//找到，则标记为正常在售产品
					
					//设置价格
					mfVo.setSellPriceYuan(product.getSellPrice());
					mfVo.setMarketPriceYuan(product.getMarketPrice());
					
					// 主题
					if (StringUtils.isNotBlank(product.getSubjectName())) {
						mfVo.setSubject(product.getSubjectName());
					}
					// 是否今日可定
					mfVo.setTodayOrderAble("1".equals(product.getTodayOnlineFlag()));
					// 星级
					if (StringUtils.isNotBlank(product.getStar())) {
						if ("1".equals(product.getStar())) {
							mfVo.setPlaceStar("A景区");
						} else if ("2".equals(product.getStar())) {
							mfVo.setPlaceStar("AA景区");
						} else if ("3".equals(product.getStar())) {
							mfVo.setPlaceStar("AAA景区");
						} else if ("4".equals(product.getStar())) {
							mfVo.setPlaceStar("AAAA景区");
						} else if ("myFavoritesVO".equals(product.getStar())) {
							mfVo.setPlaceStar("AAAAA景区");
						}
					}
					// 好评率
					Double commentGood = product.getCommentGood();
					if (commentGood != null && commentGood > 0) {
						String str = String.valueOf(commentGood);
						if (!str.contains("0.")) {
							mfVo.setCommentGood(String.valueOf(commentGood) + "%");
						}
					} else {
						mfVo.setCommentGood("100%");
					}
					mfVo.setPromotionFlag("1".equals(product.getPromotionFlag()));
					// 处理标签，最多显示两个并且每个标签最多显示5个字
					Map<String, String[]> tags = this.handleTags(product.getTagName(), product.getTagMemo());
					mfVo.setTagNames(tags.get("tagNames"));
					mfVo.setTagMemos(tags.get("tagMemos"));
					/*if (product.getTagNameArr() != null && product.getTagNameArr().length > 0) {
						String tags[] = product.getTagNameArr();
						String result[] = new String[tags.length];
						for (int k = 0; k < tags.length; k++) {
							if (k < 2) {
								String tag = tags[k].replaceAll("\\s*", "");
								if (tag.length() > 5) {
									tag = tag.substring(0, 5);
								}
								result[k] = tag;
							}
						}
						mfVo.setTagNames(result);
					}*/
					
					break;//找到，则调处内存循环
				}
			}
		}
		return ticketResult;
	}
	
	/**
	 * 特卖会产品信息查询并填充
	 * 团购/秒杀 品类
	 * @param mobileFavorite
	 * @return
	 */
	private MyFavoritesVO queryVstTuangouSeckillInfo(MobileFavorite mobileFavorite) {
		String branchType = mobileFavorite.getBranchType();
		MyFavoritesVO mfVo = new MyFavoritesVO();
		BeanUtils.copyProperties(mobileFavorite, mfVo);
		
		VstProdSalesVO salesVo = vstProdSalesService.querySalesProductBySalesIdAndFromPlaceId(null,mobileFavorite.getObjectId());
		
		if (salesVo == null) {
			return mfVo;
		}
		mfVo.setIsSoldOut(NONE_SOLD_OUT);//查到，标记为正常在售产品
		
		mfVo.setOrderCount(salesVo.getOrderCount());
		mfVo.setProductType(salesVo.getProductType());
		//设置价格
		if(null != salesVo.getSellPrice()){
			mfVo.setSellPriceYuan(salesVo.getSellPrice().floatValue() / 100f);
		}
		if(null != salesVo.getSellPrice()){
			mfVo.setMarketPriceYuan(salesVo.getMarketPrice().floatValue() / 100f);
		}
		
		/*UnityProductBranch unityBranch = null;
		if ("BRANCH".equalsIgnoreCase(branchType)) {
			for (UnityProductBranch unityBranchInLoop : unityProductBranchList) {
				if (unityBranchInLoop.getSuppGoodsId().equals(mobileFavorite.getObjectId())) {
					unityBranch = unityBranchInLoop;
				}
			}
		} else if ("PROD".equalsIgnoreCase(branchType)) {
			unityBranch = unityProductBranchList.get(0);
		}
		if (unityBranch == null) {
			return null;
		}
		MyFavoritesVO myFavoritesVO = new MyFavoritesVO();
		BeanUtils.copyProperties(mobileFavorite, myFavoritesVO);
		long orderCount = unityBranch.getOrderCount();
		
		myFavoritesVO.setOrderCount((int) orderCount);
		myFavoritesVO.setProductType(unityBranch.getProductType());
		// ProdSeckillRuleVst prodSeckillRuleVst =
		// vstSeckillService.getSeckillRuleVstWithFromPlace(mobileFavorite.getObjectId(),branchType,79L);
		myFavoritesVO.setCommentGood(getCommentGood(unityBranch, mobileFavorite));
		//设置价格
		myFavoritesVO.setSellPriceYuan(unityBranch.getSellPriceYuan());
		myFavoritesVO.setMarketPriceYuan(unityBranch.getMarketPriceYuan())*/;
		return mfVo;
	}

	/**
	 * 邮轮数据
	 * 
	 * @param favoritesResponse
	 */
	private MyFavoritesVO queryShipFavorities(MobileFavorite mobileFavorite) {
		Long productId = mobileFavorite.getObjectId();
		RequestVO<Long> compShipRequestVo = new RequestVO<Long>();
		compShipRequestVo.setBody(productId);
		ResponseVO<CompShipProductVo> compShipProdResp = tntCompShipService.findProductDetail(compShipRequestVo);
		if (compShipProdResp == null || compShipProdResp.getHeader() == null || compShipProdResp.getHeader().isFail() || compShipProdResp.getBody() == null) {
			return null;
		}
		MyFavoritesVO myFavoritesVO = new MyFavoritesVO();
		BeanUtils.copyProperties(mobileFavorite, myFavoritesVO);
		// 产品所属行政区划
		Long districtId = compShipProdResp.getBody().getBizDistrictId();
		com.lvmama.vst.api.vo.ResultHandleT<DistrictVo> districtHandle = vstDistrictService.findDistrictDetail(districtId);
		if (districtHandle == null || districtHandle.isFail() || districtHandle.getBusinessException() != null || districtHandle.getReturnContent() == null) {
			myFavoritesVO.setDeparture("");
		}
		myFavoritesVO.setDeparture(districtHandle.getReturnContent().getDistrictName());
		List<ProdGroupDateVo> groupDateVos = compShipProdResp.getBody().getProdGroupDates();
		if (groupDateVos != null && !groupDateVos.isEmpty() && groupDateVos.get(0).getSpecDate() != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(groupDateVos.get(0).getSpecDate());
			String visitDay = (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.DAY_OF_MONTH);
			myFavoritesVO.setVisitDay(visitDay);
		}

		return myFavoritesVO;
	}

	/**
	 * 签证数据
	 * @param favoritesResponse
	 */
	private MyFavoritesVO queryVisaFavorities(MobileFavorite mobileFavorite) {
		Long objectId = mobileFavorite.getObjectId();
		// 根据商品id反查规格
		com.lvmama.vst.api.vo.ResultHandleT<GoodsBaseVo> goodshandle = vstSuppGoodsService.findSuppGoodsByGoodsId(objectId);
		// 商品信息为空，则继续遍历
		if (goodshandle == null || goodshandle.getBusinessException() != null || goodshandle.getReturnContent() == null) {
			return null;
		}
		// 规格编号
		Long productBranchId = goodshandle.getReturnContent().getProductBranchId();
		// 产品编号
		Long productId = goodshandle.getReturnContent().getProductId();
		// 查询产品详情
		com.lvmama.vst.api.vo.ResultHandleT<VisaProductVo> productHandle = vstVisaService.findProductDetail(productId);
		// 产品信息为空，则继续遍历
		if (productHandle == null || productHandle.getBusinessException() != null || productHandle.getReturnContent() == null) {
			return null;
		}
		// 产品规格列表
		List<ProductBranchBaseVo> branchVoList = productHandle.getReturnContent().getProductBranchVoList();
		// 产品规格为空，则继续遍历
		if (branchVoList == null) {
			return null;
		}
		// 遍历【当前产品】的规格列表，获取【当前规格】的相关信息
		for (ProductBranchBaseVo branchBaseVo : branchVoList) {
			if (!productBranchId.equals(branchBaseVo.getProductBranchId())) {
				continue;
			}
			MyFavoritesVO myFavoritesVO = new MyFavoritesVO();
			BeanUtils.copyProperties(mobileFavorite, myFavoritesVO);
			myFavoritesVO.setObjectName(branchBaseVo.getBranchName());
			// myFavoritesVO.setCashBack(0);
			// 遍历规格属性信息
			List<ProdProductBranchPropBaseVo> list = branchBaseVo.getProductBranchPropList();
			for (int i = 0; i < list.size(); i++) {
				String code = list.get(i).getCode();
				String value = list.get(i).getValue();
				// 签证范围
				if (StringUtils.equals(code, "visa_range")) {
					String visaRange = (value == null ? "" : String.valueOf(value));
					myFavoritesVO.setVisaRange(visaRange);
					continue;
				}
				if (StringUtils.equals(code, "visa_ahead_days")) {
					int visaAheadDays = (value == null ? 0 : Integer.parseInt(value));
					myFavoritesVO.setVisaAheadDays(visaAheadDays);
				}
			}
			return myFavoritesVO;
		}
		return null;
	}

	
	/**
	 * 拼接查询条件
	 * 
	 * @date 2016-6-11 22:45:01
	 */
	private Map<String, Object> getQueryCondition(MobileMyFavoriteRequest request, Page<?> page) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", request.getUserId());
		// 收藏类型
		String objectTypes = request.getObjectType();
		if(!"ALL".equalsIgnoreCase(objectTypes)){//不是全部类型时，则指定收藏类型
			if (StringUtils.isBlank(objectTypes)) {
				objectTypes = "PLACE";// 默认景点
			}
			List<String> objectTypeList = new ArrayList<String>();
			if ("TUANGOU".equals(objectTypes) || "SECKILL".equals(objectTypes)) {
				// 团购跟秒杀合并显示
				objectTypeList.add("TUANGOU");
				objectTypeList.add("SECKILL");
			} else {
				objectTypeList.add(objectTypes);
			}
			param.put("objectTypeList", objectTypeList);
		}
		
		// 多出发地Id
		if (StringUtils.isNotBlank(request.getProductDestId()) && !"0".equals(request.getProductDestId())) {
			param.put("originId", Long.valueOf(request.getProductDestId()));
		}
		
		if(null != page){
			param.put("isPaging", "true"); // 是否使用分页
			param.put("startRows", page.getStartRows());
			param.put("endRows", page.getEndRows());
			page.setTotalResultSize(mobileFavoriteService.countMobileFavoriteList(param));
		}
		return param;
	}
	
	/**
	 * 对收藏的产品按品类进行分组
	 * @param mfList
	 * @return
	 */
	private Map<String, List<MobileFavorite>> groupByObjectType(List<MobileFavorite> mfList){
		Map<String, List<MobileFavorite>> groups = new HashMap<String, List<MobileFavorite>>();
		for (MobileFavorite mobileFavorite : mfList) {
			if(null == mobileFavorite){
				continue;
			}
			
			List<MobileFavorite> curGroup = groups.get(mobileFavorite.getObjectType());
			if(null == curGroup){
				curGroup = new ArrayList<MobileFavorite>();
				groups.put(mobileFavorite.getObjectType(), curGroup);
			}
			
			curGroup.add(mobileFavorite);
		}
		return groups;
	}

	/**
	 * 处理搜索返回的标签及标签描述
	 * 搜索返回的标签是字符串，以逗号隔开，标签描述同样
	 * @param tagName	标签名称，逗号隔开
	 * @param tagMemo	标签描述，逗号隔开
	 * @return <tagNames, String[]>,<tagMemos, String[]>
	 */
	private Map<String, String[]> handleTags(String tagName, String tagMemo){
		Map<String, String[]> tagsResult = new HashMap<String, String[]>();
		int maxShowTags = 2;//最多显示2个标签
		//处理标签名称
		if(StringUtil.isNotEmptyString(tagName)){
			String[] tagNames = tagName.split(",");
			int tagLen = tagNames.length;
			String result[] = new String[tagLen > maxShowTags ? maxShowTags : tagLen];
			for (int k = 0; k < tagLen; k++) {
				if (k < maxShowTags) {
					String tag = tagNames[k].replaceAll("\\s*", "");
					if (tag.length() > 5) {//每个标签最多显示5个字符
						tag = tag.substring(0, 5);
					}
					result[k] = tag;
				}else{
					break;
				}
			}
			tagsResult.put("tagNames", result);
			
			//处理标签名称对应的标签描述
			if(StringUtil.isNotEmptyString(tagMemo)){
				String[] tagMemos = tagMemo.split(",");
				int memoLen = tagMemos.length;
				String[] memoResult = new String[memoLen > maxShowTags ? maxShowTags : memoLen];
				for(int i = 0; i < memoLen; i++){
					if(i < maxShowTags){
						memoResult[i] = tagMemos[i];
					}else{
						break;
					}
				}
				tagsResult.put("tagMemos", memoResult);
			}
		}
		return tagsResult;
	}
	
	
	/**
	 * 获取map的打印输出格式字符串
	 * @param params
	 * @return
	 */
	private String printMapStr(Map<String, Object> params){
		if(null == params || params.isEmpty()){
			return null;
		}
		StringBuffer result = new StringBuffer("{");
		Set<Entry<String, Object>> entries = params.entrySet();
		for (Entry<String, Object> entry : entries) {
			result.append(entry.getKey());
			result.append(":");
			result.append(entry.getValue());
			result.append(",");
		}
		result.append("}");
		return result.toString();
	}
}
