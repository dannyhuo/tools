package com.lvmama.comm.pet.service.mobile;

import java.util.Map;

import com.lvmama.comm.pet.po.mobile.FavoritesResult;
import com.lvmama.comm.pet.po.mobile.MobileMyFavoriteRequest;

public interface IUserFavoriteModule {

	/**
	 * 收藏列表
	 * @param mobileMyFavoriteRequest
	 * @return
	 */
	public FavoritesResult queryFavoriteList(MobileMyFavoriteRequest mobileMyFavoriteRequest);

	/**
	 * 根据条件查询收藏数量
	 * @param mobileMyFavoriteRequest
	 * @return
	 */
	public Long countFavoriteList(MobileMyFavoriteRequest mobileMyFavoriteRequest);
	
	/**
	 * 添加收藏
	 * @param mobileMyFavoriteRequest
	 * @return
	 */
	public FavoritesResult addFavorite(MobileMyFavoriteRequest mobileMyFavoriteRequest);

	/**
	 * 取消收藏
	 * @param mobileMyFavoriteRequest
	 * @return
	 */
	public FavoritesResult deleteFavorite(MobileMyFavoriteRequest mobileMyFavoriteRequest);

	/**
	 * 是否被收藏过
	 * @param mobileMyFavoriteRequest
	 * @return
	 */
	public boolean isFavorite(MobileMyFavoriteRequest mobileMyFavoriteRequest);
	
	/**
	 * 通过产品或商品ID统计产品在全站被收藏过的次数<br>
	 * 根据收藏来源统计单个商品的统计次数<br>
	 * 全站所有收藏总数数，从PC收藏过的次数，从无线收藏过的次数<br>
	 * @param id	产品or商品ID
	 * @param collectionFrom 收藏来源<br>
	 * 		  null:统计全站所有；1：统计PC被收藏过的次数；2：统计无线被收藏过的次数
	 * @return 收藏的次数
	 */
	public Integer favoriteStatistics(String id, Short collectionFrom);
	
	/**
	 * 分品类count总条数
	 * @param mobileMyFavoriteRequest
	 * @return
	 */
	public Map<String, Integer> countGroupByObjectType(MobileMyFavoriteRequest mobileMyFavoriteRequest);
}
