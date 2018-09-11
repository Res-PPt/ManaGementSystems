package controller.reception;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.annotations.Param;
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
		List<AppCategory> acct=null;//查询级别名称
		if(flatformId1!=null&&!"".equals(flatformId1)){
			flatformId=Integer.valueOf(flatformId1);
		}
		if(categoryLevel11!=null&&!"".equals(categoryLevel11)){
			categoryLevel1=Integer.valueOf(categoryLevel11);
			
			//request.setAttribute("categoryLevel1List",acp);
			
		}
		if(categoryLevel22!=null&&!"".equals(categoryLevel22)){
			categoryLevel2=Integer.valueOf(categoryLevel22);
			acct = appServiceimpl.queryApp2(categoryLevel1);
			request.setAttribute("categoryLevel2List",acct);
			
		}
		if(categoryLevel33!=null&&!"".equals(categoryLevel33)){
			categoryLevel3=Integer.valueOf(categoryLevel33);
			acct = appServiceimpl.queryApp2(categoryLevel2);
			request.setAttribute("categoryLevel3List",acct);
			
		}
		app.setSoftwareName(softwareName);
		app.setFlatformId(flatformId);
		app.setCategoryLevel1(categoryLevel1);
		app.setCategoryLevel2(categoryLevel2);
//		appInfo.setStatus(Integer.valueOf(queryStatusid));
		app.setCategoryLevel3(categoryLevel3);
		app.setStatus(Integer.valueOf(queryStatusid));
		
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
		AppVersion idas=DevUserService.queryver(id);
		AppInfo appInfo =new AppInfo();
		
			
		
		String delResult;
		if(ida<0) {
			appInfo.setAPKName("notexist");
			return JSON.toJSONString(appInfo);
		}
		if(idas==null)
		{
			appInfo.setAPKName("true");
			int ias=DevUserService.delappinfo(id);
			return JSON.toJSONString(appInfo);
		}
		int ia=DevUserService.delappidv(id);
		int ias=DevUserService.delappinfo(id);
		if(ia>0 || ias>0){
			appInfo.setAPKName("true");
		}else {
			appInfo.setAPKName("false");
		}
		String sr=JSON.toJSONString(appInfo);
		System.out.println(sr);
		
		return sr;
	}
	   
	   /**
	    * 进入修改页面进行传值
	    */
	   @RequestMapping("/appinfomodify")
	   public String appinfomodify(HttpServletRequest request) {
		   String id= request.getParameter("id");
		  /* if(id==null ||) {
			   return "developer/appinfolist";
		   }*/
		   AppInfo app = new AppInfo();
			app.setLogoPicPath("111");
			
		   AppInfo app1=DevUserService.queryid(id);//查询APP信息
		   //List<DataDictionary> acc = appServiceimpl.queryType();//查询平台信息
		   List<AppCategory> acct = appServiceimpl.queryApp2(id);//查询级别名称
		   System.out.println("app.getStatus()"+app.getStatus());
		   if(request.getAttribute("appInfo")==null && id!=null){
				
		   request.getSession().setAttribute("appInfo",app1);
		   }
		   AppInfo app2 = new AppInfo();
			app.setLogoPicPath("112");
			request.getSession().setAttribute("app",app2);
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
		   System.out.println("appInfo.getStatus()"+appInfo.getStatus());
		   int ii = bct.getId();
		   int num =0;
			//判断文件是否为空
		   System.out.println("appInfo.getLogoPicPath()===="+appInfo.getLogoPicPath());
		   System.out.println("attach==="+attach);
			if(!attach.isEmpty()){
				System.out.println("**************1成功");
				String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
				//System.out.println("uploadFile path==============>"+path);
				String oldFileName = attach.getOriginalFilename();//原文件名
				//System.out.println("uploadFile oldFileName==============>"+oldFileName);
				String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
				//System.out.println("uploadFile prefix==============>"+prefix);
				appInfo.setLogoLocPath(path+"//"+oldFileName);
				//appInfo.setLogoPicPath("/ManaGementSystems/statics/images/"+oldFileName);
				int filesize =500000;
				//int filesize =90000;
				//System.out.println("uploadFile filesize==============>"+filesize);
				if(attach.getSize()>filesize){//上传大小不得超过500KB
					System.out.println("***********************3成功");
					request.getSession().setAttribute("fileUploadError","* 上传大小不得超过500KB");
					out.print("<script>location.href='appinfomodify';</script>");
				}else if(prefix.equalsIgnoreCase("jpg")
						||prefix.equalsIgnoreCase("png")
						||prefix.equalsIgnoreCase("jpeg")
						||prefix.equalsIgnoreCase("pneg")){//上传图片格式不正确
					System.out.println("************2成功");
					//String fileName = System.currentTimeMillis()+RandomUtils.nextInt(10000000)+"_Personal.jpg";
					
				File targetFile=new File(path,oldFileName);
				if(!targetFile.exists()){
					targetFile.mkdirs(); //如果文件夹不存在，就新建
				}
					try {
						System.out.println("******************4成功");
						attach.transferTo(targetFile);
					} catch (Exception e) {
						//e.printStackTrace();
						request.getSession().setAttribute("fileUploadError", "* 上传失败!");
						out.print("<script>location.href='appinfomodify';</script>");
					}
					//System.out.println("dddddddddddddddddd1111111111"+oldFileName);
					logoPicPath = "/ManaGementSystems/statics/uploadfiles/"+oldFileName;
					//logoPicPath = path+File.separator+oldFileName;
					appInfo.setModifyBy(ii);
					appInfo.setModifyDate(new Date());
					appInfo.setLogoPicPath(logoPicPath);
					 num = DevUserService.AppUpd(appInfo);
					/*System.out.println("相对路径2="+appInfo.getLogoPicPath());
					System.out.println("服务器路径="+appInfo.getLogoLocPath());
					System.out.println("getCategoryLevel1="+appInfo.getCategoryLevel1());
					System.out.println("getCategoryLevel2="+appInfo.getCategoryLevel2());
					System.out.println("getCategoryLevel3="+appInfo.getCategoryLevel3());
					System.out.println("flatformId="+appInfo.getFlatformId());
					System.out.println("getAppInfo="+appInfo.getAppInfo());*/
			}else{
				System.out.println("*********************5成功");
				request.getSession().setAttribute("fileUploadError","上传图片格式不正确");
				out.print("<script>location.href='appinfomodify';</script>");
				// return "developer/appinfomodify";
			}
			}else{
				System.out.println("**********************6成功");
				AppInfo app =(AppInfo) request.getSession().getAttribute("app");
				System.out.println("app.getLogoPicPath()"+app.getLogoPicPath());
				String sts =app.getLogoPicPath();
				
				if("112".equals(sts)){
					System.out.println("1111111111111"+sts+"22222222222");
					appInfo.setLogoLocPath(null);
					appInfo.setLogoPicPath(null);
				}
				System.out.println("**************7成功");
				appInfo.setModifyBy(ii);
				appInfo.setModifyDate(new Date());
				num = DevUserService.AppUpd(appInfo);
				/*System.out.println("路径="+logoPicPath);
				System.out.println("相对路径2="+appInfo.getLogoPicPath());
				System.out.println("getCategoryLevel1="+appInfo.getCategoryLevel1());
				System.out.println("getCategoryLevel2="+appInfo.getCategoryLevel2());
				System.out.println("getCategoryLevel3="+appInfo.getCategoryLevel3());
				System.out.println("flatformId="+appInfo.getFlatformId());
				System.out.println("getAppInfo="+appInfo.getAppInfo());
				System.out.println("服务器路径="+appInfo.getApkLocPath());*/
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
	   
	   @RequestMapping("addingos")
	   public void appinfomodifysaves(AppInfo appInfo,
			   HttpServletRequest request,
			   HttpServletResponse response,
			   MultipartFile a_logoPicPath) throws IOException{
		   response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
		   PrintWriter out = response.getWriter();
		   String logoPicPath = null;
		   DevUser bct =(DevUser) request.getSession().getAttribute("devUserSession");
		   
		   appInfo.setDevld(bct.getId());
		   int num =0;
			//判断文件是否为空
			if(!a_logoPicPath.isEmpty()){
				String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
				System.out.println("uploadFile path==============>"+path);
				String oldFileName = a_logoPicPath.getOriginalFilename();//原文件名
				System.out.println("uploadFile oldFileName==============>"+oldFileName);
				String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
				System.out.println("uploadFile prefix==============>"+prefix);
				System.out.println(path);
				appInfo.setLogoLocPath(path+"//"+oldFileName);
				appInfo.setLogoPicPath("/ManaGementSystems/statics/images/"+oldFileName);
				
				int filesize =500000;
				System.out.println("uploadFile filesize==============>"+filesize);
				
				if(a_logoPicPath.getSize()>filesize){//上传大小不得超过500KB
					request.setAttribute("fileUploadError","* 上传大小不得超过200KB");
					out.print("<script>location.href='appinfomodify';</script>");
					//return "developer/appinfomodify";
				}
				File targetFile=new File(path,oldFileName);
				if(!targetFile.exists()){
					targetFile.mkdirs(); //如果文件夹不存在，就新建
				}
					try {
						a_logoPicPath.transferTo(targetFile);
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute("fileUploadError", "* 上传失败!");
						out.print("<script>location.href='appinfomodify';</script>");
						//return "developer/appinfomodify";
					}
					logoPicPath = "/ManaGementSystems/statics/uploadfiles/"+oldFileName;
					//logoPicPath = path+File.separator+oldFileName;
					System.out.println("*******************2");
					appInfo.setCreatedBy(bct.getId());
					appInfo.setCreationDate(new Date());
					appInfo.setLogoPicPath(logoPicPath);
					System.out.println("222222222222222");
					 num = DevUserService.addinfo(appInfo);
					System.out.println("相对路径2="+appInfo.getLogoPicPath());
					System.out.println("服务器路径="+appInfo.getLogoLocPath());
					System.out.println("getCategoryLevel1="+appInfo.getCategoryLevel1());
					System.out.println("getCategoryLevel2="+appInfo.getCategoryLevel2());
					System.out.println("getCategoryLevel3="+appInfo.getCategoryLevel3());
					System.out.println("flatformId="+appInfo.getFlatformId());
					System.out.println("getAppInfo="+appInfo.getAppInfo());
					
			}
			if(num>0){
				out.print("<script>alert('新增成功！');location.href='list';</script>");
			}else{
				out.print("<script>alert('新增失败！');location.href='list';</script>");
			}
	   }
	   
	   @ResponseBody
	   @RequestMapping("/datadictionarylista")
	   public Object  datadictionarylista(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
			String APKName =request.getParameter("APKName");
			AppInfo daapp =  DevUserService.apkname(APKName);
			String APKNames;
			
			 if(APKName ==null ||APKName=="") {
				APKNames="empty";
				String json=JSON.toJSONString(APKNames);
				return json;
			}
			if(daapp!=null) {
				APKNames="exist";
			}else {
				APKNames="noexist";
			}
			String json=JSON.toJSONString(APKNames);
			System.out.println(json);
			return json;
	   
	   }
	   /**
	    * 
	    * 跳转到修改版本页面
	    * @return
	    */
	   @RequestMapping("appversionmodify")
	   public String dp(HttpServletRequest request) {
		   String id= request.getParameter("aid");
		   String vid= request.getParameter("vid");
		   System.out.println("000000000000000000000000000000000000000000000s"+vid);
		   if(vid.equals("0")) {
			   return "developer/main";
		   }
		   List<AppVersion> list=DevUserService.queryids(id);
		   AppVersion Version=DevUserService.queryVersion(vid);
		   request.setAttribute("appVersionList", list);
		   request.setAttribute("appVersion", Version);
		   return "developer/appversionmodify";
		   
	   }
	   
	   
	   /**
	    * 提交页面
	 * @throws IOException 
	    */
	   @RequestMapping("appversionmodifysave")
	   public void appversionmodifysave(AppVersion appVersion,HttpServletRequest request,
			   HttpServletResponse response,
			   @RequestParam(value="attach",required=false) MultipartFile attach) throws IOException {
		   
		   if(attach==null) {
			   request.setAttribute("fileUploadError", "不能为空");
		   }
		   
	   response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
	   PrintWriter out = response.getWriter();
	   String logoPicPath = null;
	   DevUser bct =(DevUser) request.getSession().getAttribute("devUserSession");
	   
	   int num =0;
		//判断文件是否为空
		if(!attach.isEmpty()){
			String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
			System.out.println("uploadFile path==============>"+path);
			String oldFileName = attach.getOriginalFilename();//原文件名
			System.out.println("uploadFile oldFileName==============>"+oldFileName);
			String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
			System.out.println("uploadFile prefix==============>"+prefix);
			System.out.println(path);
			
			int filesize =9254515;
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
				appVersion.setModifyBy(bct.getId());
				appVersion.setCreationDate(new Date());
				appVersion.setDownloadLink("/ManaGementSystems/statics/images/"+oldFileName);
				appVersion.setApkLocPath(path);
				appVersion.setApkFileName(oldFileName);
				System.out.println("222222222222222");
				 num = DevUserService.moder(appVersion);
				
		}else{
			System.out.println("*****************1");
			appVersion.setModifyBy(bct.getId());
			appVersion.setCreationDate(new Date());
			num = DevUserService.moder(appVersion);
		}
		if(num>0){
			out.print("<script>alert('修改成功！');location.href='list';</script>");
		}else{
			out.print("<script>alert('修改失败！');location.href='list';</script>");
		}
		   
		   
		   
		   
		   
		   
		   
		   
	   }
	   /**
	    * 跳转到新增页面
	    * @return
	    */
	   @RequestMapping("appversionadd")
	   public String dsp(HttpServletRequest request) {
		  String id= request.getParameter("id");
		   
		  	List<AppVersion> list=DevUserService.queryids(id);
		  	AppInfo all=DevUserService.queryidsf(id);
		   request.setAttribute("appVersionList", list);
		   request.setAttribute("appinfList", all);
		return "developer/appversionadd";
	   }
	   /**
	    * 进行新增操作
	    * @return
	 * @throws IOException 
	    */
	   @RequestMapping("/addversionsavea")
	   public void ssd(HttpServletRequest request,
			   HttpServletResponse response,
			   MultipartFile a_downloadLink) throws IOException{
		   String versionNo=request.getParameter("versionNo");//版本
		   String versionSize=request.getParameter("versionSize");//版本大小
		   String publishStatus=request.getParameter("publishStatus");//版本
		   String versionInfo=request.getParameter("versionInfo");//版本简介
		   String appid=request.getParameter("id");//版本简介
		   
		   
		   System.out.println(appid);
		   AppVersion aAppVersion =new AppVersion();
		   response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
		   PrintWriter out = response.getWriter();
		   String logoPicPath = null;
		   DevUser bct =(DevUser) request.getSession().getAttribute("devUserSession");
		   int num =0;
			//判断文件是否为空
			if(!a_downloadLink.isEmpty()){
				String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
				System.out.println("uploadFile path==============>"+path);
				String oldFileName = a_downloadLink.getOriginalFilename();//原文件名
				System.out.println("uploadFile oldFileName==============>"+oldFileName);
				String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
				System.out.println("uploadFile prefix==============>"+prefix);
				System.out.println(path);
				int filesize =9251415;
				System.out.println("uploadFile filesize==============>"+filesize);
				
				if(a_downloadLink.getSize()>filesize){//上传大小不得超过500KB
					request.setAttribute("fileUploadError","* 上传大小不得超过200KB");
					out.print("<script>location.href='appinfomodify';</script>");
				}
				File targetFile=new File(path,oldFileName);
				if(!targetFile.exists()){
					targetFile.mkdirs(); //如果文件夹不存在，就新建
				}
					try {
						a_downloadLink.transferTo(targetFile);
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute("fileUploadError", "* 上传失败!");
						out.print("<script>location.href='appinfomodify';</script>");
					}
					logoPicPath = "/ManaGementSystems/statics/uploadfiles/"+oldFileName;
					aAppVersion.setCreatedBy(bct.getId());
					aAppVersion.setCreationDate(new Date());
					aAppVersion.setApkLocPath(path);
					aAppVersion.setApkFileName(oldFileName);
					aAppVersion.setDownloadLink("/ManaGementSystems/statics/images/"+oldFileName);
					aAppVersion.setAppId(Integer.valueOf(appid));
					aAppVersion.setVersionInfo(versionInfo);
					aAppVersion.setPublishStatus(Integer.valueOf(publishStatus));
					aAppVersion.setVersionSize(Integer.valueOf(versionSize));
					aAppVersion.setVersionNo(versionNo);
					num=DevUserService.appver(aAppVersion);
					AppVersion asp=DevUserService.queryver(appid);
					
					if(asp==null) {
						return;
					}
					AppInfo appInfo =new AppInfo();
					appInfo.setVersionId(asp.getId());
					appInfo.setId(Integer.valueOf(appid));
					DevUserService.infovid(appInfo);
			}
			
			if(num>0){
				out.print("<script>alert('新增成功！');location.href='list';</script>");
			}else{
				out.print("<script>alert('新增失败！');location.href='list';</script>");
			}
			
	   }
	   
	   @ResponseBody
	   @RequestMapping("/sale")
	   public String sale(AppInfo appInfo,HttpServletRequest request,HttpServletResponse response){
		   System.out.println("1111111");
		  HashMap<Object,Object> map = new HashMap<Object,Object>();
		  	  Integer id =0;
			  String appId = request.getParameter("appId");
			  System.out.println("appId"+appId);
			  DevUser bct =(DevUser) request.getSession().getAttribute("devUserSession");//登录者对象
			   int ii = bct.getId();
			  if(appId!=null){
			  id = Integer.valueOf(appId);
			  }
			  System.out.println("status"+id);
			 
			  if(id>0){
				  map.put("errorCode","0");
				  try { 
					  appInfo.setId(id);
					  AppInfo acpt = DevUserService.queryid(String.valueOf(id));
					  acpt.setModifyBy(ii);
					  acpt.setModifyDate(new Date());
					  System.out.println("fdafasfadsf"+acpt.getStatus());
					  System.out.println(DevUserService.AppUpd1(acpt));
					  if(DevUserService.AppUpd1(acpt)){
						 
						  map.put("resultMsg","success");
					  }else{
						  map.put("resultMsg","failed");
					  }
				} catch (Exception e) {
					map.put("errorCode","exception000001");
				}
				 
			  }else{
				  request.setAttribute("param000001","0");
			  }
			return JSON.toJSONString(map);
	   }
	  
}
