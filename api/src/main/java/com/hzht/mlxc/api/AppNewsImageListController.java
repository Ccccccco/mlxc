package com.hzht.mlxc.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hzht.mlxc.dao.entity.AppNewsImageList;
import com.hzht.mlxc.dao.entity.AppNewsImageListExample;
import com.hzht.mlxc.service.IAppNewsImageListSV;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/AppNewsImageList")
public class AppNewsImageListController {
	@Autowired
	private IAppNewsImageListSV AppNewsImageListSV;
	
	private static Logger logger = LoggerFactory.getLogger(AppNewsImageListController.class);  
	
	@ResponseBody
	@RequestMapping(value="getAppNewsImageListById")
	public Map getAppNewsImageListById(AppNewsImageList appNewsImageList, HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map map = new HashMap();
		try {
			AppNewsImageList AppNewsImageList = AppNewsImageListSV.getAppNewsImageListById(appNewsImageList);
			map.put("AppNewsImageList", AppNewsImageList);
			map.put("result", "success");
			map.put("promptMsg", "查询新闻列表图片表成功!");
		} catch (Exception e) {
			map.put("result", "failed");
			map.put("promptMsg", "查询新闻列表图片表失败!");
			map.put("errorMsg", e.getMessage());
			logger.error("查询新闻列表图片表信息失败。", e);
		}
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping(value="getAppNewsImageListsByPage")
	public Map getAppNewsImageLists(AppNewsImageList AppNewsImageList, HttpServletRequest request, HttpServletResponse response) throws IOException{
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
			AppNewsImageListExample AppNewsImageListExample = new AppNewsImageListExample();
			/*AppNewsImageListExample.createCriteria().andNameEqualTo("zhangfei").andCodeEqualTo(AppNewsImageList.getCode());
			AppNewsImageListExample.setDistinct(true);
			AppNewsImageListExample.setOrderByClause("id desc");*/
			List<AppNewsImageList> AppNewsImageLists = AppNewsImageListSV.getAppNewsImageLists(AppNewsImageListExample);
			
			/**
			 * 1、获取分页信息方式：
			 * System.out.println("符合条件的记录总数："+page.getTotal());
			 * System.out.println("每页记录数："+page.getPageSize());
			 * System.out.println("总页数："+page.getPages());
			 * 
			 * 2、如果希望获取更详细的分页信息可以使用PageInfo的方式，PageInfo中会包含更详细的分页相关信息：
			 * PageInfo<Student> pageInfo = new PageInfo<Student>(students);
			 */
			map.put("AppNewsImageLists", AppNewsImageLists);
			map.put("count", page.getTotal());
			map.put("result", "success");
			map.put("promptMsg", "查询新闻列表图片表成功!");
		} catch (Exception e) {
			map.put("result", "failed");
			map.put("promptMsg", "查询新闻列表图片表失败!");
			map.put("errorMsg", e.getMessage());
			logger.error("查询新闻列表图片表信息失败。", e);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="updateAppNewsImageListById")
	public Map updateAppNewsImageListById(AppNewsImageList AppNewsImageList, HttpServletRequest request, HttpServletResponse response) throws IOException{
		//AppNewsImageList对象中封装所有需要update的属性信息。
		Map map = new HashMap();
		int count = 0;
		try {
			count = AppNewsImageListSV.updateAppNewsImageListById(AppNewsImageList);
			map.put("result", "success");
			map.put("promptMsg", "更新新闻列表图片表成功!");
			if (count != 1) {
				map.put("result", "failed");
				map.put("promptMsg", "更新新闻列表图片表信息失败!");
			}
		} catch (Exception e) {
			map.put("result", "failed");
			map.put("promptMsg", "更新新闻列表图片表信息失败!");
			map.put("errorMsg", e.getMessage());
			logger.error("更新新闻列表图片表信息失败。", e);
		}
		return map;
	}
	

	@ResponseBody
	@RequestMapping(value="updateAppNewsImageList")
	public Map updateAppNewsImageList(AppNewsImageList AppNewsImageList, HttpServletRequest request, HttpServletResponse response) throws IOException{
		//AppNewsImageList对象中封装所有需要update的属性信息。
		Map map = new HashMap();
		int count = 0;
		try {
			/**
			 * 自行定制，Where条件！！！！！！！！！！
			 */
			AppNewsImageListExample AppNewsImageListExample = new AppNewsImageListExample();
			/*
			 * AppNewsImageListExample.createCriteria().andNameEqualTo("zhangfei");
			 */
			count = AppNewsImageListSV.updateAppNewsImageList(AppNewsImageList, AppNewsImageListExample);
			map.put("result", "success");
			map.put("promptMsg", "更新新闻列表图片表成功!");
		} catch (Exception e) {
			map.put("result", "failed");
			map.put("promptMsg", "更新新闻列表图片表信息失败!");
			map.put("errorMsg", e.getMessage());
			logger.error("更新新闻列表图片表信息失败。", e);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="deleteAppNewsImageListById")
	public Map deleteAppNewsImageListById(AppNewsImageList appNewsImageList, HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map map = new HashMap();
		int count = 0;
		try {
			count = AppNewsImageListSV.deleteAppNewsImageListById(appNewsImageList);
			map.put("result", "success");
			map.put("promptMsg", "删除新闻列表图片表成功!");
			if (count != 1) {
				map.put("result", "failed");
				map.put("promptMsg", "删除新闻列表图片表信息失败!");
			}
		} catch (Exception e) {
			map.put("result", "failed");
			map.put("promptMsg", "删除新闻列表图片表信息失败!");
			map.put("errorMsg", e.getMessage());
			logger.error("删除新闻列表图片表信息失败。", e);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="deleteAppNewsImageList")
	public Map deleteAppNewsImageList(AppNewsImageList AppNewsImageList, HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map map = new HashMap();
		int count = 0;
		try {
			/**
			 * 自行定制，Where条件！！！！！！！！！！
			 */
			AppNewsImageListExample AppNewsImageListExample = new AppNewsImageListExample();
			/*
			 * AppNewsImageListExample.createCriteria().andNameEqualTo("zhangfei");
			 */
			count = AppNewsImageListSV.deleteAppNewsImageList(AppNewsImageListExample);
			map.put("result", "success");
			map.put("promptMsg", "删除新闻列表图片表成功!");
		} catch (Exception e) {
			map.put("result", "failed");
			map.put("promptMsg", "删除新闻列表图片表信息失败!");
			map.put("errorMsg", e.getMessage());
			logger.error("删除新闻列表图片表信息失败。", e);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="insertAppNewsImageList")
	public Map insertAppNewsImageList(AppNewsImageList AppNewsImageList, HttpServletRequest request, HttpServletResponse response) throws IOException{
		//AppNewsImageList对象中封装所有需要insert的属性信息。
		Map map = new HashMap();
		int count = 0;
		try {
			count = AppNewsImageListSV.insertAppNewsImageList(AppNewsImageList);
			map.put("result", "success");
			map.put("promptMsg", "新增新闻列表图片表成功!");
		} catch (Exception e) {
			map.put("result", "failed");
			map.put("promptMsg", "新增新闻列表图片表信息失败!");
			map.put("errorMsg", e.getMessage());
			logger.error("新增新闻列表图片表信息失败。", e);
		}
		return map;
	}
	

}
