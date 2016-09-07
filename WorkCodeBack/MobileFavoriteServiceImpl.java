package com.lvmama.pet.mobile.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.lvmama.comm.pet.po.mobile.MobileFavorite;
import com.lvmama.comm.pet.po.mobile.MobileMyFavoriteRequest;
import com.lvmama.comm.pet.po.mobile.FavoritesResult;
import com.lvmama.comm.pet.service.mobile.MobileFavoriteService;
import com.lvmama.pet.mobile.dao.MobileFavoriteDAO;

import org.apache.log4j.Logger;

/**
 * 驴途移动端 from 3.0 我的收藏. 
 * @author qinzubo
 *
 */
public class MobileFavoriteServiceImpl implements MobileFavoriteService {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private MobileFavoriteDAO mobileFavoriteDAO;
	
	
	@Override
	@Deprecated
	public MobileFavorite insertMobileFavorite(MobileFavorite record) {
		return mobileFavoriteDAO.insert(record);
	}

	@Override
	public boolean updateMobileFavorite(MobileFavorite mf) {
		int rows =  mobileFavoriteDAO.updateByPrimaryKey(mf);
		if(rows > 0) {
			return true;
		}
		return false;
	}

	@Override
	public MobileFavorite selectMobileFavoriteById(Long id) {
		return mobileFavoriteDAO.selectByPrimaryKey(id);
	}

	@Override
	public List<MobileFavorite> queryMobileFavoriteList( Map<String, Object> param) {
		return mobileFavoriteDAO.getMobileFavoriteListByPrarms(param);
	}

	@Override
	public Long countMobileFavoriteList(Map<String, Object> param) {
		return mobileFavoriteDAO.countMobileFavorite(param);
	}

	@Override
	public int deleteMobileFavoriteById(Long id) {
		return mobileFavoriteDAO.deleteByPrimaryKey(id);
	}

	
	public void setMobileFavoriteDAO(MobileFavoriteDAO mobileFavoriteDAO) {
		this.mobileFavoriteDAO = mobileFavoriteDAO;
	}

	@Override
	public int deleteMobileFavorite(Long id, Long userId) {
		return mobileFavoriteDAO.deleteByPrimaryKey(id, userId);
	}


	@Override
	public int deleteByObjectIdAndUserId(Long id, Long userId) {
		return mobileFavoriteDAO.deleteByObjectIdAndUserId(id, userId);
	}



	@Override
	public List<MobileFavorite> queryMobileFavoritePlaceListForHome(
			Map<String, Object> param) {
		return mobileFavoriteDAO.getMobileFavoritePlaceListByPrarmsForHome(param);
	}

	@Override
	public List<MobileFavorite> queryMobileFavoriteGuideListForHome(
			Map<String, Object> param) {
		return mobileFavoriteDAO.getMobileFavoriteGuideListByPrarmsForHome(param);
	}

	@Override
    public List<MobileFavorite> queryMobileFavoriteProductListForHome(
            Map<String, Object> param) {
        return mobileFavoriteDAO.getMobileFavoriteProductListByPrarmsForHome(param);
    }

	@Override
    public List<MobileFavorite> queryMobileFavoriteTuangouListForHome(
            Map<String, Object> param) {
        return mobileFavoriteDAO.getMobileFavoriteTuangouListByParamsForHome(param);
    }

	@Override
	@Deprecated
	public int deleteByMultipleOriginalArea(MobileFavorite mf) {
		return mobileFavoriteDAO.deleteByMultipleOriginalArea(mf);
	}
	
	/**
	 * 取消收藏<br>
	 * <h1 style="color:blue;">收藏模块改造，PC/无线接口统一新增接口</h1>
	 */
	public FavoritesResult deleteFavorite(MobileMyFavoriteRequest mobileMyFavoriteRequest) {
		FavoritesResult myFavoritesResult = new FavoritesResult();
		if (null == mobileMyFavoriteRequest || null == mobileMyFavoriteRequest.getObjectId() || mobileMyFavoriteRequest.getUserId() == null) {
			myFavoritesResult.setSuccess(false);
			myFavoritesResult.setMessage("必输项不能为空");
			logger.info("添加收藏必填参数不合法：" + mobileMyFavoriteRequest);
			return myFavoritesResult;
		}
		MobileFavorite mf = new MobileFavorite();
		mf.setObjectId(Long.valueOf(mobileMyFavoriteRequest.getObjectId()));
		mf.setUserId(mobileMyFavoriteRequest.getUserId());
		if (StringUtils.isNotBlank(mobileMyFavoriteRequest.getProductDestId()) && !"0".equals(mobileMyFavoriteRequest.getProductDestId())) {
			mf.setOriginId(Long.valueOf(mobileMyFavoriteRequest.getProductDestId()));
		}
		int count = mobileFavoriteDAO.deleteByMultipleOriginalArea(mf);
		if (count > 0) {
			myFavoritesResult.setSuccess(true);
		} else {
			myFavoritesResult.setSuccess(false);
			myFavoritesResult.setMessage("该产品不存在");
		}
		return myFavoritesResult;
	}
	
	/**
	 * 添加收藏<br>
	 * <h1 style="color:red;">收藏模块改造，PC/无线接口统一新增接口</h1>
	 */
	public FavoritesResult addFavorite(MobileMyFavoriteRequest mobileMyFavoriteRequest) {
		//1、判断参数是否合法
		FavoritesResult mobileMyFavoritesResult = new FavoritesResult();
		if(mobileMyFavoriteRequest == null || null == mobileMyFavoriteRequest.getObjectId() 
				|| null == mobileMyFavoriteRequest.getObjectType() || mobileMyFavoriteRequest.getUserId() == null) {
			mobileMyFavoritesResult.setSuccess(false);
			mobileMyFavoritesResult.setMessage("非法参数");
			logger.info("添加收藏必填参数不合法：" + mobileMyFavoriteRequest);
			return mobileMyFavoritesResult;
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
		List<MobileFavorite> mfList = mobileFavoriteDAO.getMobileFavoriteListByPrarms(p);
		if (null != mfList && mfList.size() > 0) {
			mobileMyFavoritesResult.setSuccess(false);
			mobileMyFavoritesResult.setMessage("该产品已经收藏过");
			return mobileMyFavoritesResult;//已收藏，直接返回消息
		}
		
		//3、添加收藏
		MobileFavorite mf = new MobileFavorite();
		mf.setCreatedTime(new Date());
		mf.setIsValid(mobileMyFavoriteRequest.getIsValid());
		mf.setObjectId(Long.valueOf(mobileMyFavoriteRequest.getObjectId()));
		mf.setObjectImageUrl(mobileMyFavoriteRequest.getObjectImageUrl());
		mf.setObjectName(mobileMyFavoriteRequest.getObjectName());
		mf.setObjectType(mobileMyFavoriteRequest.getObjectType());
		mf.setUserId(mobileMyFavoriteRequest.getUserId());
		mf.setSellPrice(Float.valueOf(StringUtils.isBlank(mobileMyFavoriteRequest.getSellPrice()) ? "0" : mobileMyFavoriteRequest.getSellPrice()));
		mf.setMarketSellPrice(Float.valueOf(StringUtils.isBlank(mobileMyFavoriteRequest.getMarketSellPrice()) ? "0" : mobileMyFavoriteRequest.getMarketSellPrice()));
		mf.setCurrentVersion(String.valueOf(mobileMyFavoriteRequest.getAppVersion()));
		mf.setBranchType(mobileMyFavoriteRequest.getBranchType());
		mf.setDataFrom("VST");
		mf.setCollectionFrom(mobileMyFavoriteRequest.getCollectionFrom());//设置收藏来源
		if (StringUtils.isNotBlank(mobileMyFavoriteRequest.getProductDestId()) && !"0".equals(mobileMyFavoriteRequest.getProductDestId())) {
			mf.setOriginId(Long.valueOf(mobileMyFavoriteRequest.getProductDestId()));
		}
		mobileFavoriteDAO.insert(mf);
		mobileMyFavoritesResult.setSuccess(true);
		return mobileMyFavoritesResult;
	}
	
	/**
     * 统计某个产品被收藏的次数
     * @param objectId 产品ID
     * @param collectionFrom 收藏来源 1:PC， 2:APP
     * @return
     */
    public Integer favoriteStatistics(String objectId, Short collectionFrom){
    	return mobileFavoriteDAO.favoriteStatistics(objectId, collectionFrom);
    }
    
    /**
     * 分品类count总条数
     * @param param
     * @return
     */
    public Map<String, Integer> countGroupByObjectType(Map<String, Object> param){
    	return mobileFavoriteDAO.countGroupByObjectType(param);
    }
}
