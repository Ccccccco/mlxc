package com.hzht.mlxc.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hzht.mlxc.dao.entity.AppHome;
import com.hzht.mlxc.dao.entity.AppHomeExample;
import com.hzht.mlxc.service.IAppHomeSV;
/**
 * 首页信息展示
 * @author jichao
 *
 */
@Controller
@RequestMapping("/appHome")
@SuppressWarnings("all")
public class AppHomeController {
	@Autowired
	private IAppHomeSV appHomeSV;
	
	private static Logger logger = LoggerFactory.getLogger(AppHomeController.class);  
	/**
	 * 根据主键查询
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value="getAppHomeById")
	public Map getAppHomeById(@RequestBody AppHome apphome) throws IOException{
		Map map = new HashMap();
		List<AppHome> appHomeList = new ArrayList();
		Long id = apphome.getId();
		try {
			appHomeList = appHomeSV.getAppHomeById(id);
			map.put("appHome", appHomeList);
			map.put("result", "success");
			map.put("promptMsg", "查询首页信息成功!");
		} catch (Exception e) {
			map.put("result", "failed");
			map.put("promptMsg", "查询首页信息失败!");
			map.put("errorMsg", e.getMessage());
			logger.error("查询首页信息信息失败。", e);
		}
		return map;
	}
	
	/**
	 * 分页查询
	 * @param appHome
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value="getAppHomesByPage")
	public Map getAppHomes(AppHome appHome, HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map map = new HashMap();
		try {
			/**
			 * 如下为两种分页处理方式（注意分页处理一定要在执行sql语句前进行设置！！）：
			 * 分页处理方式一：假设请求参数中包含分页参数pageNum和pageSize
			 * int pageNum = Integer.valueOf(request.getParameter("pageNum"));
			 * int pageSize = Integer.valueOf(request.getParameter("pageSize"));
			 * Page<Object> page = PageHelper.startPage(pageNum, pageSize);
			 */
			
	        /**
			 * 分页处理方式二：这种方式中请求参数名必须为pageNum,pageSize两个参数
			 * Page<Object> page = PageHelper.startPage(request);
			 */
			Page<Object> page = PageHelper.startPage(request);
			/**
			 * 自行定制，Where条件！！！！！！！！！！
			 */
			AppHomeExample appHomeExample = new AppHomeExample();
			/*appHomeExample.createCriteria().andNameEqualTo("zhangfei").andCodeEqualTo(appHome.getCode());
			appHomeExample.setDistinct(true);
			appHomeExample.setOrderByClause("id desc");*/
			List<AppHome> appHomes = appHomeSV.getAppHomes(appHomeExample);
			
			/**
			 * 1、获取分页信息方式：
			 * System.out.println("符合条件的记录总数："+page.getTotal());
			 * System.out.println("每页记录数："+page.getPageSize());
			 * System.out.println("总页数："+page.getPages());
			 * 
			 * 2、如果希望获取更详细的分页信息可以使用PageInfo的方式，PageInfo中会包含更详细的分页相关信息：
			 * PageInfo<Student> pageInfo = new PageInfo<Student>(students);
			 */
			map.put("appHomes", appHomes);
			map.put("count", page.getTotal());
			map.put("result", "success");
			map.put("promptMsg", "查询首页信息成功!");
		} catch (Exception e) {
			map.put("result", "failed");
			map.put("promptMsg", "查询首页信息失败!");
			map.put("errorMsg", e.getMessage());
			logger.error("查询首页信息信息失败。", e);
		}
		return map;
	}
	/**
	 * 根据主键更新
	 * @param appHome
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value="updateAppHomeById")
	public Map updateAppHomeById(AppHome appHome) throws IOException{
		//appHome对象中封装所有需要update的属性信息。
		Map map = new HashMap();
		int count = 0;
		try {
			count = appHomeSV.updateAppHomeById(appHome);
			map.put("result", "success");
			map.put("promptMsg", "更新首页信息成功!");
			if (count != 1) {
				map.put("result", "failed");
				map.put("promptMsg", "更新首页信息信息失败!");
			}
		} catch (Exception e) {
			map.put("result", "failed");
			map.put("promptMsg", "更新首页信息信息失败!");
			map.put("errorMsg", e.getMessage());
			logger.error("更新首页信息信息失败。", e);
		}
		return map;
	}
	

	@ResponseBody
	@RequestMapping(value="updateAppHome")
	public Map updateAppHome(AppHome appHome) throws IOException{
		//appHome对象中封装所有需要update的属性信息。
		Map map = new HashMap();
		int count = 0;
		try {
			/**
			 * 自行定制，Where条件！！！！！！！！！！
			 */
			AppHomeExample appHomeExample = new AppHomeExample();
			/*
			 * appHomeExample.createCriteria().andNameEqualTo("zhangfei");
			 */
			count = appHomeSV.updateAppHome(appHome, appHomeExample);
			map.put("result", "success");
			map.put("promptMsg", "更新首页信息成功!");
		} catch (Exception e) {
			map.put("result", "failed");
			map.put("promptMsg", "更新首页信息信息失败!");
			map.put("errorMsg", e.getMessage());
			logger.error("更新首页信息信息失败。", e);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="deleteAppHomeById")
	public Map deleteAppHomeById(Long id) throws IOException{
		Map map = new HashMap();
		int count = 0;
		try {
			count = appHomeSV.deleteAppHomeById(id);
			map.put("result", "success");
			map.put("promptMsg", "删除首页信息成功!");
			if (count != 1) {
				map.put("result", "failed");
				map.put("promptMsg", "删除首页信息信息失败!");
			}
		} catch (Exception e) {
			map.put("result", "failed");
			map.put("promptMsg", "删除首页信息信息失败!");
			map.put("errorMsg", e.getMessage());
			logger.error("删除首页信息信息失败。", e);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="deleteAppHome")
	public Map deleteAppHome(AppHome appHome) throws IOException{
		Map map = new HashMap();
		int count = 0;
		try {
			/**
			 * 自行定制，Where条件！！！！！！！！！！
			 */
			AppHomeExample appHomeExample = new AppHomeExample();
			/*
			 * appHomeExample.createCriteria().andNameEqualTo("zhangfei");
			 */
			count = appHomeSV.deleteAppHome(appHomeExample);
			map.put("result", "success");
			map.put("promptMsg", "删除首页信息成功!");
		} catch (Exception e) {
			map.put("result", "failed");
			map.put("promptMsg", "删除首页信息信息失败!");
			map.put("errorMsg", e.getMessage());
			logger.error("删除首页信息信息失败。", e);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="insertAppHome")
	public Map insertAppHome(AppHome appHome) throws IOException{
		//appHome对象中封装所有需要insert的属性信息。
		Map map = new HashMap();
		int count = 0;
		try {
			count = appHomeSV.insertAppHome(appHome);
			map.put("result", "success");
			map.put("promptMsg", "新增首页信息成功!");
		} catch (Exception e) {
			map.put("result", "failed");
			map.put("promptMsg", "新增首页信息信息失败!");
			map.put("errorMsg", e.getMessage());
			logger.error("新增首页信息信息失败。", e);
		}
		return map;
	}
	

}
