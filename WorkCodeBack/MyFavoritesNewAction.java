package com.lvmama.front.web.myspace;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.lvmama.comm.pet.service.mobile.IUserFavoriteModule;
import com.lvmama.comm.pet.service.mobile.MobileFavoriteService;
import com.lvmama.comm.pet.vo.Page;
import com.lvmama.comm.vo.Constant.COLLECTION_FROM;
import com.lvmama.comm.vo.Constant.MY_FAVORITE_OBJECT_TYPE;
import com.lvmama.comm.pet.po.mobile.FavoritesResult;
import com.lvmama.comm.pet.po.mobile.MobileFavorite;
import com.lvmama.comm.pet.po.mobile.MobileMyFavoriteRequest;
import com.lvmama.comm.pet.po.mobile.MyFavoritesVO;
import com.lvmama.comm.pet.po.user.UserUser;
import com.lvmama.front.web.BaseAction;

/**
 * 我的收藏页Action－2016 08新版
 * @author huoqiang
 * @date 2016/08/24
 */
@Results({
	@Result(name = "success", location = "/WEB-INF/pages/myspace/sub/favorites.ftl", type = "freemarker")
})
public class MyFavoritesNewAction extends BaseAction{

	/**
	 * Serial versionUID
	 */
	private static final long serialVersionUID = -8728952341321988885L;
	
	@Autowired
	private IUserFavoriteModule userFavoriteModule;
	
	@Autowired//测试service
	private MobileFavoriteService mobileFavoriteService;
	
	/**收藏类型，【PRODUCT(度假-线路)，PLACE(门票)，SHIP(游轮)，HOTEL(酒店)，
	  VISA(签证)，SECKILL(特卖、秒杀),GUIDE(攻略)】*/
	//见MY_FAVORITE_OBJECT_TYPE
	private String objectType;
	
	/**分页*/
	private Page<MyFavoritesVO> pager = null;
	
	/**页码，默认第一页*/
	private Integer pageNo = 1;
	
	/**页大小*/
	private final Integer pageSize = 12;
	
	/**分品类查询总条数*/
	private Map<String, Integer> eachObjectTypeCount;
	
	private int allCount = 0;
	private int productCount = 0;
	private int placeCount = 0;
	private int shipCount = 0;
	private int hotelCount = 0;
	private int visaCount = 0;
	private int temaiCount = 0;
	private int wifiCount = 0;
	
	/**
	 * 我的收藏首页
	 * @return
	 */
	@Action("/myspace/favorites")
	public String index(){
		//1、登录信息判断
		if(!this.isLogin()){
			return LOGIN;
		}
		UserUser user = this.getUser();
		if(null == user){
			return ERROR;
		}

		//2、构造查询入参
		MobileMyFavoriteRequest favoriteRequest = new MobileMyFavoriteRequest();
		favoriteRequest.setUserId(user.getId());

		favoriteRequest.setUserId(13257L);//测试代码
		
		
		favoriteRequest.setObjectType("ALL");//按品类分组，查询所有品类的分数量
		this.eachObjectTypeCount = this.userFavoriteModule.countGroupByObjectType(favoriteRequest);
		this.calEachTypeCount(eachObjectTypeCount);//计算各品类的数量
		
		//3、当前条件下，总记录数查询
		long totalCount = 0;
		if(null == objectType || "all".equalsIgnoreCase(objectType.trim())){
			objectType = "all";//默认不分品类展示，既显示全部
			totalCount = this.allCount;
		}else{
			Integer curTypeCount = this.eachObjectTypeCount.get(objectType);
			totalCount = (null == curTypeCount) ? 0 : curTypeCount.longValue();
		}
		
		if(totalCount > 0){//存在数据，则根据当前条件取回数据到页面展示
			//4、设置分页信息
			pager = new Page<MyFavoritesVO>(totalCount, Long.parseLong(pageNo.toString()));
			pager.setUrl("/myspace/share/favorites.do?objectType="+this.objectType+"&pageNo="+this.pageNo);
			pager.setPageSize(pageSize);
			
			//5、查询当页数据
			favoriteRequest.setPageNum(pageNo);
			favoriteRequest.setPageSize(pageSize);
			favoriteRequest.setObjectType(objectType);//设置为当前品类，只获取当前品类的数据
			
			//FavoritesResult favorites = userFavoriteModule.queryFavoriteList(favoriteRequest);
			//pager.setItems(favorites.getFavorities());
			
			//测试代码start
			int pageSize = favoriteRequest.getPageSize() == 0 ? 10 : favoriteRequest.getPageSize();
			int pageNum = favoriteRequest.getPageNum() == 0 ? 1 : favoriteRequest.getPageNum();
			Page<MyFavoritesVO> page = new Page<MyFavoritesVO>(pageSize, pageNum);
			Map<String, Object> param = getQueryCondition(favoriteRequest, page);
			List<MobileFavorite> result = mobileFavoriteService.queryMobileFavoriteList(param);
			List<MyFavoritesVO> favoriteVoList = new ArrayList<>();
			//FavoritesResult favoritesResult = new FavoritesResult();
			int i = 0;
			for (MobileFavorite mobileFavorite : result) {
				MyFavoritesVO mfVo = new MyFavoritesVO();
				BeanUtils.copyProperties(mobileFavorite,mfVo);
				mfVo.setTagNames(new String[]{"返现","促销","特价"});
				mfVo.setTagMemos(new String[]{"购买后返现，你觉得呢","此产品有促销，赶快购买吧","特价产品哦你心动了么"});
				if(i % 3 == 0){
					mfVo.setCategoryCnTag("门票");
					mfVo.setCategoryEnTag("tick");
				}else if(i % 3 == 1){
					mfVo.setCategoryCnTag("旅游线路");
					mfVo.setCategoryEnTag("line");
				}else{
					mfVo.setCategoryCnTag("酒店");
					mfVo.setCategoryEnTag("hotel");
				}
				
				if(i % 2 == 0){
					mfVo.setIsSoldOut((short)0);
				}
				favoriteVoList.add(mfVo);
				mfVo.setHotelWifi(true);
				mfVo.setAddress("true");
				i++;
				
			}
			//favoritesResult.setFavorities(favoriteVoList);
			//测试代码end
			
			pager.setItems(favoriteVoList);
		}
		
		return SUCCESS;
	}

	@Action("/myspace/testCountByObjType")
	public void test(){
		MobileMyFavoriteRequest mobileMyFavoriteRequest = new MobileMyFavoriteRequest();
		mobileMyFavoriteRequest.setObjectType("ALL");
		Map<String, Integer> result = this.userFavoriteModule.countGroupByObjectType(mobileMyFavoriteRequest);
		System.out.println(result.size());
		
		mobileMyFavoriteRequest.setObjectType("PLACE");
		result = this.userFavoriteModule.countGroupByObjectType(mobileMyFavoriteRequest);
		System.out.println("PLACE" + result.get("PLACE"));
	}
	
	@Action("/myspace/testStatistics")
	public void testStatistics(){
		int count = this.userFavoriteModule.favoriteStatistics("100697", null);
		System.out.println(count);
		
		count = this.userFavoriteModule.favoriteStatistics("100697", COLLECTION_FROM.from_app.getCode());
		System.out.println("APP:" + count);
		
		count = this.userFavoriteModule.favoriteStatistics("100697", COLLECTION_FROM.from_pc.getCode());
		System.out.println("PC:" + count);
	}
	
	
	/**
	 * 拼接查询条件
	 * copy //test code
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
	
	private void calEachTypeCount(Map<String, Integer> eachObjectTypeCount){
		Integer place = eachObjectTypeCount.get(MY_FAVORITE_OBJECT_TYPE.PLACE.getCode());
		if(null != place){
			this.placeCount = place;
			this.allCount += place;
		}
		
		Integer product = eachObjectTypeCount.get(MY_FAVORITE_OBJECT_TYPE.PRODUCT.getCode());
		if(null != product){
			this.productCount = product;
			this.allCount += product;
		}
		
		Integer hotel = eachObjectTypeCount.get(MY_FAVORITE_OBJECT_TYPE.HOTEL.getCode());
		if(null != hotel){
			this.hotelCount = hotel;
			this.allCount += hotel;
		}
		
		Integer ship = eachObjectTypeCount.get(MY_FAVORITE_OBJECT_TYPE.SHIP.getCode());
		if(null != ship){
			this.shipCount = ship;
			this.allCount += ship;
		}
		
		Integer visa = eachObjectTypeCount.get(MY_FAVORITE_OBJECT_TYPE.VISA.getCode());
		if(null != visa){
			this.visaCount = visa;
			this.visaCount += visa;
		}
		
		Integer wifi = eachObjectTypeCount.get(MY_FAVORITE_OBJECT_TYPE.WIFI.getCode());
		if(null != wifi){
			this.wifiCount = wifi;
			this.allCount += wifi;
		}
		
		Integer seckill = eachObjectTypeCount.get(MY_FAVORITE_OBJECT_TYPE.SECKILL.getCode());
		if(null != seckill){
			this.temaiCount += seckill;
			this.allCount += seckill;
		}
		Integer tuangou = eachObjectTypeCount.get(MY_FAVORITE_OBJECT_TYPE.TUANGOU.getCode());
		if(null != tuangou){
			this.temaiCount += tuangou;
			this.allCount += tuangou;
		}
	}
	
	
	
	
	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}


	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Page<MyFavoritesVO> getPager() {
		return pager;
	}

	public void setPager(Page<MyFavoritesVO> pager) {
		this.pager = pager;
	}

	public Map<String, Integer> getEachObjectTypeCount() {
		return eachObjectTypeCount;
	}

	public void setEachObjectTypeCount(Map<String, Integer> eachObjectTypeCount) {
		this.eachObjectTypeCount = eachObjectTypeCount;
	}
	
	
	
	public int getAllCount() {
		return allCount;
	}

	public int getProductCount() {
		return productCount;
	}

	public int getPlaceCount() {
		return placeCount;
	}

	public int getShipCount() {
		return shipCount;
	}

	public int getHotelCount() {
		return hotelCount;
	}

	public int getVisaCount() {
		return visaCount;
	}

	public int getTemaiCount() {
		return temaiCount;
	}

	public int getWifiCount() {
		return wifiCount;
	}
	
	
}
