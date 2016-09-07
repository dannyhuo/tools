package com.lvmama.comm.pet.service.mobile;

import java.util.List;
import java.util.Map;

import com.lvmama.comm.pet.po.mobile.MobileFavorite;
import com.lvmama.comm.pet.po.mobile.MobileMyFavoriteRequest;
import com.lvmama.comm.pet.po.mobile.FavoritesResult;

/**
 * 驴途移动端 from 3.0 - 我的收藏.
 * @author qinzubo
 *
 */
public interface MobileFavoriteService {

	/**
	 * 新增我的收藏.
	 * @param mf  推荐我的收藏对象
	 * @return  持久化后的我的收藏对象
	 */
	@Deprecated
	MobileFavorite insertMobileFavorite(MobileFavorite mf);
	
	/**
	 * 更新我的收藏.
	 * @param mf  要更新的对象 
	 * @return   更新后的对象
	 */
	boolean updateMobileFavorite(MobileFavorite mf);
	
	/**
	 * 根据主键查找对象.
	 * @param id  主键
	 * @return  对象
	 */
	MobileFavorite selectMobileFavoriteById(Long id);
	
	/**
     * 查询列表 
     * 如果参数 isPaging 不为null ，表示分页查询. 
     * @param param 
     * @return
     */
	List<MobileFavorite> queryMobileFavoriteList(Map<String,Object> param);
	
	/**
	 * 符合条件的数据量 
	 * @param param
	 * @return long 
	 */
	Long countMobileFavoriteList(Map<String,Object> param);
	
	/**
	 * 删除
	 * @param id
	 * @return 删除的条数
	 */
	int deleteMobileFavoriteById(Long id);
	
	/**
	 * 删除
	 * @param id
	 * @param useId
	 * @return 删除的条数
	 */
	int deleteMobileFavorite(Long id,Long userId);

	
	/**
	 * 删除
	 * @param objectId
	 * @param useId
	 * @return 删除的条数
	 */
	int deleteByObjectIdAndUserId(Long id,Long userId);
	
	/**
	 * 删除
	 * @param mf
	 * @return 删除的条数
	 */
	@Deprecated
	int deleteByMultipleOriginalArea(MobileFavorite mf);

	/**
	 * 景点 (网站首页——我的收藏)
	 * @param param
	 * @return
	 */
	List<MobileFavorite> queryMobileFavoritePlaceListForHome(
			Map<String, Object> param);

	/**
	 * 攻略(网站首页——我的收藏)
	 * @param param
	 * @return
	 */
	List<MobileFavorite> queryMobileFavoriteGuideListForHome(
			Map<String, Object> param);

	/**
	 * 产品(网站首页——我的收藏)
	 * @param param
	 * @return
	 */
	List<MobileFavorite> queryMobileFavoriteProductListForHome(
			Map<String, Object> param);

	/**
	 * 团购(网站首页——我的收藏)
	 * @param param
	 * @return
	 */
	List<MobileFavorite> queryMobileFavoriteTuangouListForHome(
	        Map<String, Object> param);

	/**
	 * 取消收藏<br>
	 * 收藏模块改造，PC/无线接口统一新增接口
	 * @date 2016-6-11 22:04:05
	 * @param param
	 * @return
	 */
	public FavoritesResult deleteFavorite(MobileMyFavoriteRequest request);

	/**
	 * 添加收藏<br>
	 * 收藏模块改造，PC/无线接口统一新增接口
	 * @date 2016-6-11 21:50:16
	 * @param param
	 * @return
	 */
	public FavoritesResult addFavorite(MobileMyFavoriteRequest request);
	
	/**
     * 统计某个产品被收藏的次数
     * @param objectId 产品ID
     * @param collectionFrom 收藏来源 1:PC， 2:APP，null:PC&APP
     * @return
     */
    public Integer favoriteStatistics(String objectId, Short collectionFrom);
    
    /**
     * 分品类count总条数
     * @param param
     * @return
     */
    public Map<String, Integer> countGroupByObjectType(Map<String, Object> param);

}
