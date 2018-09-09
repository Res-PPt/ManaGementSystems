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
 * �����ߺ�̨
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
	 * ��֤��½
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
			request.setAttribute("error","�û�����������");
		}else if(bts!=null && btss==null){
			request.setAttribute("error","������������");
		}else{
		request.setAttribute("error","�û��������붼������");
		}
		return "devlogin";
	}
}


	@RequestMapping("/list")
	public String list(AppInfo appInfo,HttpServletRequest request){
		
		if(appInfo==null) {
			return "developer/appinfoadd";
		}
		
		String softwareName = request.getParameter("querySoftwareName");//�������
		String flatformId1 = request.getParameter("queryFlatformId");//����ƽ̨��valueֵ
		String categoryLevel11 = request.getParameter("queryCategoryLevel1");//һ������ֵ
		String categoryLevel22 = request.getParameter("queryCategoryLevel2");//��������ֵ
		String categoryLevel33 = request.getParameter("queryCategoryLevel3");//��������ֵ
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
		List<AppCategory> acp = appServiceimpl.queryApp2(null);//��ѯһ���˵�
		//List<AppCategory> acp = appServiceimpl.queryApp1();//��ѯһ���˵�
		List<AppCategory> acct = appServiceimpl.queryApp3();//��ѯ��������
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
		
		List<DataDictionary> acc = appServiceimpl.queryType();//��ѯƽ̨��Ϣ
		request.setAttribute("flatFormList", acc);
		
		request.setAttribute("categoryLevel1List",acp);
		request.setAttribute("queryStatus", Integer.valueOf(queryStatusid));
		request.setAttribute("querySoftwareName",softwareName);//�������
		request.setAttribute("queryFlatformId",flatformId);//����ƽ̨Id
		request.setAttribute("queryCategoryLevel1",categoryLevel1);//һ��Id
		request.setAttribute("queryCategoryLevel2",categoryLevel2);//����Id
		request.setAttribute("queryCategoryLevel3",categoryLevel3);//����Id
		request.setAttribute("statusList", psa);

		System.out.println("categoryLevelList"+categoryLevel2);
		System.out.println("categoryLevel3List"+categoryLevel3);
		
		String pageIndex = request.getParameter("pageIndex");//��ǰҳ��
		int currentPageNo=1;
		if(pageIndex!=null){
			currentPageNo= Integer.valueOf(pageIndex);
		}
		
		List<AppInfo> lists = DevUserService.Arrlist(app);//��ѯAPP��Ϣ
		
		int num = 5;//ÿҳ��ʾ����������
		int totalPageCount = appServiceimpl.AppYe(lists.size(),num);//���ж���ҳ
		System.out.println(totalPageCount);
		pages pages = new pages();
		pages.setCurrentPageNo(currentPageNo);
		pages.setTotalCount(lists.size());//���ж���������
		pages.setTotalPageCount(totalPageCount);
		request.setAttribute("pages", pages);
		currentPageNo = (currentPageNo-1)*num;
		app.setNum(num);
		app.setCurrentPageNo(currentPageNo);
		List<AppInfo> list = DevUserService.ListAPP(app);//��ѯAPP��Ϣ
		request.setAttribute("appInfoList",list);//����ѯ����App��Ϣ�����ҳ��
		return "developer/appinfolist";
	}
//	
	
	/**
	 * ��ת����ѯ�鿴ҳ��
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
	 * �˳�ϵͳ
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		
		
		request.getSession().removeAttribute("devUserSession");
		return "devlogin";
	}
	
	
	/**
	 * ɾ��
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
	    * �����޸�ҳ����д�ֵ
	    */
	   @RequestMapping("/appinfomodify")
	   public String appinfomodify(HttpServletRequest request) {
		   String id= request.getParameter("id");
		   if(id==null) {
			   return "developer/appinfolist";
		   }
		   AppInfo app=DevUserService.queryid(id);//��ѯAPP��Ϣ
		   //List<DataDictionary> acc = appServiceimpl.queryType();//��ѯƽ̨��Ϣ
		   List<AppCategory> acct = appServiceimpl.queryApp2(id);//��ѯ��������
		   request.setAttribute("appInfo",app);
		   //request.setAttribute("",);
		   
		   return "developer/appinfomodify";
	   }
	   /**
	    * �첽��ȡƽ̨��Ϣ
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
	    * �첽����ͼƬ
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
	    * �޸�APP��Ϣ
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
			//�ж��ļ��Ƿ�Ϊ��
			if(!attach.isEmpty()){
				String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
				System.out.println("uploadFile path==============>"+path);
				String oldFileName = attach.getOriginalFilename();//ԭ�ļ���
				System.out.println("uploadFile oldFileName==============>"+oldFileName);
				String prefix = FilenameUtils.getExtension(oldFileName);//ԭ�ļ���׺
				System.out.println("uploadFile prefix==============>"+prefix);
				appInfo.setLogoLocPath(path+"//"+oldFileName);
				//appInfo.setLogoPicPath("/ManaGementSystems/statics/images/"+oldFileName);

				int filesize =500000;
				System.out.println("uploadFile filesize==============>"+filesize);
				
				if(attach.getSize()>filesize){//�ϴ���С���ó���500KB
					request.setAttribute("fileUploadError","* �ϴ���С���ó���200KB");
					out.print("<script>location.href='appinfomodify';</script>");
					//return "developer/appinfomodify";
				}
				File targetFile=new File(path,oldFileName);
				if(!targetFile.exists()){
					targetFile.mkdirs(); //����ļ��в����ڣ����½�
				}
					try {
						attach.transferTo(targetFile);
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute("fileUploadError", "* �ϴ�ʧ��!");
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
					System.out.println("���·��2="+appInfo.getLogoPicPath());
					System.out.println("������·��="+appInfo.getLogoLocPath());
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
				System.out.println("·��="+logoPicPath);
				System.out.println("���·��2="+appInfo.getLogoPicPath());
				System.out.println("getCategoryLevel1="+appInfo.getCategoryLevel1());
				System.out.println("getCategoryLevel2="+appInfo.getCategoryLevel2());
				System.out.println("getCategoryLevel3="+appInfo.getCategoryLevel3());
				System.out.println("flatformId="+appInfo.getFlatformId());
				System.out.println("getAppInfo="+appInfo.getAppInfo());
				System.out.println("������·��="+appInfo.getApkLocPath());
			}
			if(num>0){
				out.print("<script>alert('�޸ĳɹ���');location.href='list';</script>");
			}else{
				out.print("<script>alert('�޸�ʧ�ܣ�');location.href='list';</script>");
			}
	   }
	   /**
	    * �첽��ȡ��������
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
	    * ��ת����ҳ��
	    */
	   @RequestMapping("appinfoadd")
	   public String appinfoadd(AppInfo appInfo,HttpServletRequest request) {
		return "developer/appinfoadd";
	   }
}
