package com.hzht.mlxc.api;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hzht.mlxc.dao.entity.AppCarouselMap;
import com.hzht.mlxc.dao.entity.AppCarouselMapExample;
import com.hzht.mlxc.service.IAppCarouselMapSV;

/**
 * 描述: 首页推荐轮播图
 *
 * @author yxb
 * @createDate 2019-04-04 16:25
 */
@RestController
@RequestMapping("/AppCarouselMap")
public class AppCarouselMapController {
	@Autowired
	private IAppCarouselMapSV appCarouselMapSV;

	private static Logger logger = LoggerFactory.getLogger(AppCarouselMapController.class);

	/**
	 * 根据主键查询
	 *
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "getAppCarouselMapById")
	public Map getAppCarouselMapById(Long id) {
		Map map = new HashMap();
		try {
			AppCarouselMap appCarouselMap = appCarouselMapSV.getAppCarouselMapById(id);
			map.put("AppCarouselMap", appCarouselMap);
			map.put("result", "success");
			map.put("promptMsg", "查询首页推荐轮播图成功!");
		} catch (Exception e) {
			map.put("result", "failed");
			map.put("promptMsg", "查询首页推荐轮播图失败!");
			map.put("errorMsg", e.getMessage());
			logger.error("查询首页推荐轮播图信息失败。", e);
		}
		return map;
	}

	/**
	 * 分页查询
	 *
	 * @param appCarouselMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getAppCarouselMapsByPage")
	public Map getAppCarouselMaps(AppCarouselMap appCarouselMap, HttpServletRequest request) {
		Map map = new HashMap();
		try {
			/**
			 * 如下为两种分页处理方式（注意分页处理一定要在执行sql语句 前进行设置！！）：
			 * 分页处理方式一：假设请求参数中包含分页参数pageNum和 pageSize int pageNum =
			 * Integer.valueOf(request.getParameter ("pageNum")); int pageSize =
			 * Integer.valueOf(request.getParameter ("pageSize")); Page
			 * <Object> page = PageHelper.startPage(pageNum, pageSize);
			 */

			/**
			 * 分页处理方式二：这种方式中请求参数名必须为 pageNum,pageSize两个参数 Page<Object> page =
			 * PageHelper.startPage(request);
			 */
			Page<Object> page = PageHelper.startPage(request);
			/**
			 * 自行定制，Where条件！！！！！！！！！！
			 */
			AppCarouselMapExample AppCarouselMapExample = new AppCarouselMapExample();
			/*
			 * AppCarouselMapExample.createCriteria
			 * ().andNameEqualTo("zhangfei").andCodeEqualTo(AppCarouselMap.
			 * getCode ()); AppCarouselMapExample.setDistinct(true);
			 * AppCarouselMapExample.setOrderByClause("id desc");
			 */
			List<AppCarouselMap> appCarouselMaps = appCarouselMapSV.getAppCarouselMaps(AppCarouselMapExample);
			System.out.println("mybatis 返回的结果集 :" + appCarouselMaps);

			/**
			 * 1、获取分页信息方式： System.out.println("符合条件的记录总 数："+page.getTotal());
			 * System.out.println("每页记录数："+page.getPageSize());
			 * System.out.println("总页数："+page.getPages());
			 *
			 * 2、如果希望获取更详细的分页信息可以使用PageInfo的方式， PageInfo中会包含更详细的分页相关信息： PageInfo
			 * <Student> pageInfo = new PageInfo<Student> (students);
			 */
			map.put("AppCarouselMaps", appCarouselMaps);
			map.put("count", page.getTotal());
			map.put("result", "success");
			map.put("promptMsg", "查询首页推荐轮播图成功!");
		} catch (Exception e) {
			map.put("result", "failed");
			map.put("promptMsg", "查询首页推荐轮播图失败!");
			map.put("errorMsg", e.getMessage());
			logger.error("查询首页推荐轮播图信息失败。", e);
		}
		return map;
	}

	/**
	 * 修改根据主键
	 *
	 * @param appCarouselMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateAppCarouselMapById")
	public Map updateAppCarouselMapById(AppCarouselMap appCarouselMap) {
		// AppCarouselMap对象中封装所有需要update的属性信息。
		Map map = new HashMap();
		int count = 0;
		try {
			count = appCarouselMapSV.updateAppCarouselMapById(appCarouselMap);
			map.put("result", "success");
			map.put("promptMsg", "更新首页推荐轮播图成功!");
			if (count != 1) {
				map.put("result", "failed");
				map.put("promptMsg", "更新首页推荐轮播图信息失败!");
			}
		} catch (Exception e) {
			map.put("result", "failed");
			map.put("promptMsg", "更新首页推荐轮播图信息失败!");
			map.put("errorMsg", e.getMessage());
			logger.error("更新首页推荐轮播图信息失败。", e);
		}
		return map;
	}

	/**
	 * 自制条件修改
	 *
	 * @param appCarouselMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateAppCarouselMap")
	public Map updateAppCarouselMap(AppCarouselMap appCarouselMap) {
		// AppCarouselMap对象中封装所有需要update的属性信息。
		Map map = new HashMap();
		int count = 0;
		try {
			/**
			 * 自行定制，Where条件！！！！！！！！！！
			 */
			AppCarouselMapExample AppCarouselMapExample = new AppCarouselMapExample();
			/*
			 * AppCarouselMapExample.createCriteria().andNameEqualTo
			 * ("zhangfei");
			 */
			count = appCarouselMapSV.updateAppCarouselMap(appCarouselMap, AppCarouselMapExample);
			map.put("result", "success");
			map.put("promptMsg", "更新首页推荐轮播图成功!");
		} catch (Exception e) {
			map.put("result", "failed");
			map.put("promptMsg", "更新首页推荐轮播图信息失败!");
			map.put("errorMsg", e.getMessage());
			logger.error("更新首页推荐轮播图信息失败。", e);
		}
		return map;
	}

	/**
	 * 删除 根据主键
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deleteAppCarouselMapById")
	public Map deleteAppCarouselMapById(Long id) {
		Map map = new HashMap();
		int count = 0;
		try {
			count = appCarouselMapSV.deleteAppCarouselMapById(id);
			map.put("result", "success");
			map.put("promptMsg", "删除首页推荐轮播图成功!");
			if (count != 1) {
				map.put("result", "failed");
				map.put("promptMsg", "删除首页推荐轮播图信息失败!");
			}
		} catch (Exception e) {
			map.put("result", "failed");
			map.put("promptMsg", "删除首页推荐轮播图信息失败!");
			map.put("errorMsg", e.getMessage());
			logger.error("删除首页推荐轮播图信息失败。", e);
		}
		return map;
	}

	/**
	 * 拼接条件删除
	 *
	 * @param appCarouselMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deleteAppCarouselMap")
	public Map deleteAppCarouselMap(AppCarouselMap appCarouselMap) {
		Map map = new HashMap();
		int count = 0;
		try {
			/**
			 * 自行定制，Where条件！！！！！！！！！！
			 */
			AppCarouselMapExample AppCarouselMapExample = new AppCarouselMapExample();
			/*
			 * AppCarouselMapExample.createCriteria().andNameEqualTo
			 * ("zhangfei");
			 */
			count = appCarouselMapSV.deleteAppCarouselMap(AppCarouselMapExample);
			map.put("result", "success");
			map.put("promptMsg", "删除首页推荐轮播图成功!");
		} catch (Exception e) {
			map.put("result", "failed");
			map.put("promptMsg", "删除首页推荐轮播图信息失败!");
			map.put("errorMsg", e.getMessage());
			logger.error("删除首页推荐轮播图信息失败。", e);
		}
		return map;
	}

	/**
	 * 新增首页推荐轮播图
	 *
	 * @param appCarouselMap
	 *            传入参数
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "insertAppCarouselMap")
	public Map insertAppCarouselMap(AppCarouselMap appCarouselMap) {
		// AppCarouselMap对象中封装所有需要insert的属性信息。
		Map map = new HashMap();
		int count = 0;
		try {
			// 创建时间
			appCarouselMap.setImageCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(DateFormat.getDateTimeInstance().format(new Date())));
			// 定义图片的展示顺序
			List<AppCarouselMap> shunxuList = new ArrayList<>();
			shunxuList.add(appCarouselMap);
			for (int i = 0; i < shunxuList.size(); i++) {
				i++;
				appCarouselMap.setImageSorting(i);
			}
			count = appCarouselMapSV.insertAppCarouselMap(appCarouselMap);
			map.put("result", "success");
			map.put("promptMsg", "新增首页推荐轮播图成功!");
		} catch (Exception e) {
			map.put("result", "failed");
			map.put("promptMsg", "新增首页推荐轮播图信息失败!");
			map.put("errorMsg", e.getMessage());
			logger.error("新增首页推荐轮播图信息失败。", e);
		}
		return map;
	}

}
