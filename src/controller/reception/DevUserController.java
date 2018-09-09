package controller.reception;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import entity.AppCategory;
import entity.AppInfo;
import entity.AppVersion;
import entity.BackendUser;
import entity.DataDictionary;
import entity.DevUser;
import entity.pages;
import service.AppServiceimpl;
import service.DevUserServiceimpl;
/**
 * 开发者后台
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/dev")
public class DevUserController {
	@Autowired
	private DevUserServiceimpl DevUserService;
	@Autowired
	private AppServiceimpl appServiceimpl;
	
//	private AppInfo appInfo;
	
	@RequestMapping("/login")
	public String login(){
		return "devlogin";
	}

	/**
	 * 验证登陆
	 */
	@RequestMapping("/dologin")
	public String dologin(HttpServletRequest request){
		String devCode = request.getParameter("devCode");
		String devPassword = request.getParameter("devPassword");
		DevUser devUser =new DevUser();
		devUser.setDevCode(devCode);
		devUser.setDevPassword(devPassword);
		DevUser bss = DevUserService.login(devUser);
		DevUser bcct =(DevUser) request.getSession().getAttribute("devUserSession");
		if(bcct!=null){
			return "developer/main";
		}
		if(bss!=null){
		request.getSession().setAttribute("devUserSession",bss);
		return "developer/main";
	}else{ 
		DevUser bts = DevUserService.queryName(devCode);
		DevUser btss = DevUserService.queryPwd(devPassword);
		if(bts==null && btss!=null){
			request.setAttribute("error","用户名输入有误");
		}else if(bts!=null && btss==null){
			request.setAttribute("error","密码输入有误");
		}else{
		request.setAttribute("error","用户名或密码都不存在");
		}
		return "devlogin";
	}
}


	@RequestMapping("/list")
	public String list(AppInfo appInfo,HttpServletRequest request){
		
		if(appInfo==null) {
			return "developer/appinfoadd";
		}
		
		String softwareName = request.getParameter("querySoftwareName");//软件名称
		String flatformId1 = request.getParameter("queryFlatformId");//所属平台的value值
		String categoryLevel11 = request.getParameter("queryCategoryLevel1");//一级分类值
		String categoryLevel22 = request.getParameter("queryCategoryLevel2");//二级分类值
		String categoryLevel33 = request.getParameter("queryCategoryLevel3");//三级分类值
		String queryStatusid=request.getParameter("queryStatus");
		if(queryStatusid==null ||queryStatusid=="") {
			queryStatusid="0";
		}
		
		System.out.println(softwareName);
		AppInfo app = new AppInfo();
		int flatformId=0;
		int categoryLevel1=0;
		int categoryLevel2=0;
		int categoryLevel3=0;
		//Object pid=null;
		
		List<DataDictionary> psa=appServiceimpl.queryTypes();
		List<AppCategory> acp = appServiceimpl.queryApp2(null);//查询一级菜单
		//List<AppCategory> acp = appServiceimpl.queryApp1();//查询一级菜单
		List<AppCategory> acct = appServiceimpl.queryApp3();//查询级别名称
		if(flatformId1!=null&&!"".equals(flatformId1)){
			flatformId=Integer.valueOf(flatformId1);
		}
		if(categoryLevel11!=null&&!"".equals(categoryLevel11)){
			categoryLevel1=Integer.valueOf(categoryLevel11);
			//request.setAttribute("categoryLevel1List",acp);
			
		}
		if(categoryLevel22!=null&&!"".equals(categoryLevel22)){
			categoryLevel2=Integer.valueOf(categoryLevel22);
			request.setAttribute("categoryLevel2List",acct);
			
		}
		if(categoryLevel33!=null&&!"".equals(categoryLevel33)){
			categoryLevel3=Integer.valueOf(categoryLevel33);
			request.setAttribute("categoryLevel3List",acct);
			
		}
		app.setSoftwareName(softwareName);
		app.setFlatformId(flatformId);
		app.setCategoryLevel1(categoryLevel1);
		app.setCategoryLevel2(categoryLevel2);
		appInfo.setStatus(Integer.valueOf(queryStatusid));
		app.setCategoryLevel3(categoryLevel3);
		app.setFlatformId(Integer.valueOf(queryStatusid));
		
		List<DataDictionary> acc = appServiceimpl.queryType();//查询平台信息
		request.setAttribute("flatFormList", acc);
		
		request.setAttribute("categoryLevel1List",acp);
		request.setAttribute("queryStatus", Integer.valueOf(queryStatusid));
		request.setAttribute("querySoftwareName",softwareName);//软件名称
		request.setAttribute("queryFlatformId",flatformId);//所属平台Id
		request.setAttribute("queryCategoryLevel1",categoryLevel1);//一级Id
		request.setAttribute("queryCategoryLevel2",categoryLevel2);//二级Id
		request.setAttribute("queryCategoryLevel3",categoryLevel3);//三级Id
		request.setAttribute("statusList", psa);

		System.out.println("categoryLevelList"+categoryLevel2);
		System.out.println("categoryLevel3List"+categoryLevel3);
		
		String pageIndex = request.getParameter("pageIndex");//当前页数
		int currentPageNo=1;
		if(pageIndex!=null){
			currentPageNo= Integer.valueOf(pageIndex);
		}
		
		List<AppInfo> lists = DevUserService.Arrlist(app);//查询APP信息
		
		int num = 5;//每页显示多少条数据
		int totalPageCount = appServiceimpl.AppYe(lists.size(),num);//共有多少页
		System.out.println(totalPageCount);
		pages pages = new pages();
		pages.setCurrentPageNo(currentPageNo);
		pages.setTotalCount(lists.size());//共有多少条数据
		pages.setTotalPageCount(totalPageCount);
		request.setAttribute("pages", pages);
		currentPageNo = (currentPageNo-1)*num;
		app.setNum(num);
		app.setCurrentPageNo(currentPageNo);
		List<AppInfo> list = DevUserService.ListAPP(app);//查询APP信息
		request.setAttribute("appInfoList",list);//将查询到的App信息保存的页面
		return "developer/appinfolist";
	}
//	
	
	/**
	 * 跳转到查询查看页面
	 */
	@RequestMapping("/appview/{id}")
	public String appview(@PathVariable String id,HttpServletRequest resquest) {
		
		System.out.println(id);
		
		List<AppVersion> list=DevUserService.queryids(id);
		
		AppInfo sa=DevUserService.queryid(id);
		
		resquest.setAttribute("appInfo", sa);
		resquest.setAttribute("appVersionList", list);
		
		return "developer/appinfoview";
	}
	
	/**
	 * 退出系统
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		
		
		request.getSession().removeAttribute("devUserSession");
		return "devlogin";
	}
	
	
	/**
	 * 删除
	 */
	   @ResponseBody
	@RequestMapping(value="paginquery",produces = "text/html;charset=UTF-8")
	   public Object Delid(HttpServletRequest reques,HttpServletResponse response) {
		String id= reques.getParameter("id");
		int ida=DevUserService.quertinfoid(id);
		AppInfo appInfo =new AppInfo();
		
		String delResult;
		if(ida<0) {
			appInfo.setAPKName("notexist");
			return JSON.toJSONString(appInfo);
		}
	
		int ia=DevUserService.delappidv(id);
		int ias=DevUserService.delappinfo(id);
		if(ia>0){
			appInfo.setAPKName("true");
		}else {
			appInfo.setAPKName("false");
		}
		String sr=JSON.toJSONString(appInfo);
		
		return sr;
	}
	   
	   /**
	    * 进入修改页面进行传值
	    */
	   @RequestMapping("/appinfomodify")
	   public String appinfomodify(HttpServletRequest request) {
		   String id= request.getParameter("id");
		   if(id==null) {
			   return "developer/appinfolist";
		   }
		   AppInfo app=DevUserService.queryid(id);//查询APP信息
		   //List<DataDictionary> acc = appServiceimpl.queryType();//查询平台信息
		   List<AppCategory> acct = appServiceimpl.queryApp2(id);//查询级别名称
		   request.setAttribute("appInfo",app);
		   //request.setAttribute("",);
		   
		   return "developer/appinfomodify";
	   }
	   /**
	    * 异步获取平台信息
	    * @param request
	    * @param response
	    * @throws IOException
	    */
	   @ResponseBody
	   @RequestMapping("/datadictionarylist")
	   public void datadictionarylist(HttpServletRequest request,HttpServletResponse response) throws IOException{
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			String typeCode =request.getParameter("tcode");
			System.out.println(typeCode);
			List<DataDictionary> daapp = DevUserService.queryCode(typeCode);
			String newsJSON = JSON.toJSONString(daapp);
			out.print(newsJSON);
	   }
	   /**
	    * 异步更换图片
	    * @param request
	    * @param response
	    * @throws IOException
	    */
	   @RequestMapping("/delfile")
	   public void delfile(HttpServletRequest request,HttpServletResponse response) throws IOException{
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			String id = request.getParameter("id");
			String flag = request.getParameter("flag");
			System.out.println("id="+id+"\tflag="+flag);
			String aat = "failed";
			if(id!=null){
				aat ="success";
			}
			out.print(aat);
	   }
	   /**
	    * 修改APP信息
	    * @param request
	    * @param response
	    * @throws IOException
	    */
	   @RequestMapping("appinfomodifysave")
	   public void appinfomodifysave(AppInfo appInfo,
			   HttpServletRequest request,
			   HttpServletResponse response,
			   @RequestParam(value="attach",required=false) MultipartFile attach) throws IOException{
		   response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
		   PrintWriter out = response.getWriter();
		   String logoPicPath = null;
		   DevUser bct =(DevUser) request.getSession().getAttribute("devUserSession");
		   int ii = bct.getId();
		   int num =0;
			//判断文件是否为空
			if(!attach.isEmpty()){
				String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
				System.out.println("uploadFile path==============>"+path);
				String oldFileName = attach.getOriginalFilename();//原文件名
				System.out.println("uploadFile oldFileName==============>"+oldFileName);
				String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
				System.out.println("uploadFile prefix==============>"+prefix);
				appInfo.setLogoLocPath(path+"//"+oldFileName);
				//appInfo.setLogoPicPath("/ManaGementSystems/statics/images/"+oldFileName);

				int filesize =500000;
				System.out.println("uploadFile filesize==============>"+filesize);
				
				if(attach.getSize()>filesize){//上传大小不得超过500KB
					request.setAttribute("fileUploadError","* 上传大小不得超过200KB");
					out.print("<script>location.href='appinfomodify';</script>");
					//return "developer/appinfomodify";
				}
				File targetFile=new File(path,oldFileName);
				if(!targetFile.exists()){
					targetFile.mkdirs(); //如果文件夹不存在，就新建
				}
					try {
						attach.transferTo(targetFile);
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute("fileUploadError", "* 上传失败!");
						out.print("<script>location.href='appinfomodify';</script>");
						//return "developer/appinfomodify";
					}
					logoPicPath = "/ManaGementSystems/statics/uploadfiles/"+oldFileName;
					//logoPicPath = path+File.separator+oldFileName;
					System.out.println("*******************2");
					appInfo.setModifyBy(ii);
					appInfo.setModifyDate(new Date());
					appInfo.setLogoPicPath(logoPicPath);
					 num = DevUserService.AppUpd(appInfo);
					System.out.println("相对路径2="+appInfo.getLogoPicPath());
					System.out.println("服务器路径="+appInfo.getLogoLocPath());
					System.out.println("getCategoryLevel1="+appInfo.getCategoryLevel1());
					System.out.println("getCategoryLevel2="+appInfo.getCategoryLevel2());
					System.out.println("getCategoryLevel3="+appInfo.getCategoryLevel3());
					System.out.println("flatformId="+appInfo.getFlatformId());
					System.out.println("getAppInfo="+appInfo.getAppInfo());
					
			}else{
				System.out.println("*****************1");
				
				appInfo.setModifyBy(ii);
				appInfo.setModifyDate(new Date());
				num = DevUserService.AppUpd(appInfo);
				System.out.println("路径="+logoPicPath);
				System.out.println("相对路径2="+appInfo.getLogoPicPath());
				System.out.println("getCategoryLevel1="+appInfo.getCategoryLevel1());
				System.out.println("getCategoryLevel2="+appInfo.getCategoryLevel2());
				System.out.println("getCategoryLevel3="+appInfo.getCategoryLevel3());
				System.out.println("flatformId="+appInfo.getFlatformId());
				System.out.println("getAppInfo="+appInfo.getAppInfo());
				System.out.println("服务器路径="+appInfo.getApkLocPath());
			}
			if(num>0){
				out.print("<script>alert('修改成功！');location.href='list';</script>");
			}else{
				out.print("<script>alert('修改失败！');location.href='list';</script>");
			}
	   }
	   /**
	    * 异步获取级别名称
	    * @param request
	    * @param response
	    * @throws IOException
	    */
	   @RequestMapping("/categorylevellist")
	   public void categorylevellist(HttpServletRequest request,HttpServletResponse response) throws IOException{
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			String id = request.getParameter("pid");
			
			if("".equals(id)){
				id=null;
			}
			List<AppCategory> acp = appServiceimpl.queryApp2(id);
			String newsJSON = JSON.toJSONString(acp);
			out.print(newsJSON);
			
	   }
	   /**
	    * 跳转新增页面
	    */
	   @RequestMapping("appinfoadd")
	   public String appinfoadd(AppInfo appInfo,HttpServletRequest request) {
		return "developer/appinfoadd";
	   }
}
