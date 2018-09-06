package controller.backstage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import entity.AppCategory;


import entity.AppInfo;
import entity.AppVersion;
import entity.DataDictionary;
import entity.pages;
import service.AppServiceimpl;
@Controller
@RequestMapping("/manager")
public class AppController {
	@Autowired
	@Qualifier("AppMapper")
	private AppServiceimpl appServiceimpl;
//	@Autowired
//	private pages pages;
	
	/**
	 * 查询全部需要审核的APP信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/app")
	public String queryList(HttpServletRequest request){
		String softwareName = request.getParameter("querySoftwareName");//软件名称
		String flatformId1 = request.getParameter("queryFlatformId");//所属平台的value值
		String categoryLevel11 = request.getParameter("queryCategoryLevel1");//一级分类值
		String categoryLevel22 = request.getParameter("queryCategoryLevel2");//二级分类值
		String categoryLevel33 = request.getParameter("queryCategoryLevel3");//三级分类值
		AppInfo app = new AppInfo();
		int flatformId=0;
		int categoryLevel1=0;
		int categoryLevel2=0;
		int categoryLevel3=0;
		if(flatformId1!=null&&!"".equals(flatformId1)){
			flatformId=Integer.valueOf(flatformId1);
		}
		if(categoryLevel11!=null&&!"".equals(categoryLevel11)){
			categoryLevel1=Integer.valueOf(categoryLevel11);
		}
		if(categoryLevel22!=null&&!"".equals(categoryLevel22)){
			categoryLevel2=Integer.valueOf(categoryLevel22);
		}
		if(categoryLevel33!=null&&!"".equals(categoryLevel33)){
			categoryLevel3=Integer.valueOf(categoryLevel33);
		}
		app.setSoftwareName(softwareName);
		app.setFlatformId(flatformId);
		app.setCategoryLevel1(categoryLevel1);
		app.setCategoryLevel2(categoryLevel2);
		app.setCategoryLevel3(categoryLevel3);
		request.setAttribute("querySoftwareName",softwareName);
		request.setAttribute("queryFlatformId",flatformId);
		request.setAttribute("queryCategoryLevel1",categoryLevel1);	
		request.setAttribute("queryCategoryLevel2",categoryLevel2);
		request.setAttribute("queryCategoryLevel3",categoryLevel3);
		List<DataDictionary> acc = appServiceimpl.queryType();//查询平台信息
		System.out.println(acc.size());
		request.setAttribute("flatFormList", acc);
		List<AppCategory> acp = appServiceimpl.queryApp1();//查询一级菜单
		request.setAttribute("categoryLevel1List",acp);
		
		
		String pageIndex = request.getParameter("pageIndex");//当前页数
		int currentPageNo=1;
		if(pageIndex!=null){
			currentPageNo= Integer.valueOf(pageIndex);
		}
		
		int num1 = appServiceimpl.queryAPP1(app);//查询APP信息条数
		int num = 5;//每页显示多少条数据
		int totalPageCount = appServiceimpl.AppYe(num1,num);//共有多少页
		pages pages = new pages();
		pages.setCurrentPageNo(currentPageNo);
		pages.setTotalCount(num1);//共有多少条数据
		pages.setTotalPageCount(totalPageCount);
		request.setAttribute("pages", pages);
		System.out.println("currentPageNo"+currentPageNo);
		currentPageNo = (currentPageNo-1)*num;
		app.setNum(num);
		app.setCurrentPageNo(currentPageNo);
		System.out.println("currentPageNo"+currentPageNo);
		System.out.println("num+"+num);
		
		/*AppInfo app1 = new AppInfo();
		app1.setSoftwareName(softwareName);
		app1.setFlatformId(flatformId);
		app1.setCategoryLevel1(categoryLevel1);
		app1.setCategoryLevel2(categoryLevel2);
		app1.setCategoryLevel3(categoryLevel3);*/
		List<AppInfo> list = appServiceimpl.queryAPP(app);//查询APP信息
		request.setAttribute("appInfoList",list);//将查询到的App信息保存的页面
		return "backend/applist";
	}
	@ResponseBody
	@RequestMapping("/categorylevellist")
	public void ssq(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("*************");
		String id =request.getParameter("pid");
		List<AppCategory> list = appServiceimpl.queryApp2(id);
		//StringBuffer newsJSON = new StringBuffer("[");
		//AppCategory sb = null;
//			for(int i =0;;){
//				sb = list.get(i);
//				newsJSON.append("{\"id\":"+sb.getId()+",");
//				newsJSON.append("\"categoryName\":\""+sb.getCategoryName().replace("\"","&quot;")+"\"}");
//				if((++i)==list.size()){
//					newsJSON.append("]");
//					break;
//				}else{
//					newsJSON.append(",");
//				}
//			}
		
		String newsJSON  = JSON.toJSONString(list);
			//System.out.println(newsJSON);
			out.print(newsJSON);
	}
	/**
	 * 跳转审核页面
	 */
	@RequestMapping("/check")
	public String check(HttpServletRequest request){
		String vid = request.getParameter("vid");
		AppInfo app = appServiceimpl.queryVersionid(vid);
		AppVersion acp = appServiceimpl.queryVId(vid);
		request.setAttribute("appVersion",acp);
		request.setAttribute("appInfo",app);
		return "backend/appcheck";
	}
	@RequestMapping("/checksave")
	public void checksave(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String status = request.getParameter("status");
		String id = request.getParameter("id");
		int num = appServiceimpl.updapp(id,status);
		System.out.println(status);
		if(num>0){
			out.print("<script>alert('修改成功！');location.href='app';</script>");
		}else{
			out.print("<script>alert('修改失败！');;location.href='app';</script>");
		}
	}
}
