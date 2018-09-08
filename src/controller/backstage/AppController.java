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
	 * ��ѯȫ����Ҫ��˵�APP��Ϣ
	 * @param request
	 * @return
	 */
	@RequestMapping("/app")
	public String queryList(HttpServletRequest request){
		String softwareName = request.getParameter("querySoftwareName");//�������
		String flatformId1 = request.getParameter("queryFlatformId");//����ƽ̨��valueֵ
		String categoryLevel11 = request.getParameter("queryCategoryLevel1");//һ������ֵ
		String categoryLevel22 = request.getParameter("queryCategoryLevel2");//��������ֵ
		String categoryLevel33 = request.getParameter("queryCategoryLevel3");//��������ֵ
		AppInfo app = new AppInfo();
		int flatformId=0;
		int categoryLevel1=0;
		int categoryLevel2=0;
		int categoryLevel3=0;
		Object pid=null;
		//List<AppCategory> acp = appServiceimpl.queryApp2(pid);//��ѯһ���˵�
		List<AppCategory> acp = appServiceimpl.queryApp1();//��ѯһ���˵�
		List<AppCategory> acct = null;
		if(flatformId1!=null&&!"".equals(flatformId1)){
			flatformId=Integer.valueOf(flatformId1);
		}
		if(categoryLevel11!=null&&!"".equals(categoryLevel11)){
			categoryLevel1=Integer.valueOf(categoryLevel11);
			//request.setAttribute("categoryLevel1List",acp);
			
		}
		if(categoryLevel22!=null&&!"".equals(categoryLevel22)){
			categoryLevel2=Integer.valueOf(categoryLevel22);
			acct = appServiceimpl.queryApp2(categoryLevel1);//��ѯ��������
			request.setAttribute("categoryLevel2List",acct);
			
		}
		if(categoryLevel33!=null&&!"".equals(categoryLevel33)){
			categoryLevel3=Integer.valueOf(categoryLevel33);
			acct = appServiceimpl.queryApp2(categoryLevel2);//��ѯ��������
			request.setAttribute("categoryLevel3List",acct);
			
		}
		app.setSoftwareName(softwareName);
		app.setFlatformId(flatformId);
		app.setCategoryLevel1(categoryLevel1);
		app.setCategoryLevel2(categoryLevel2);
		app.setCategoryLevel3(categoryLevel3);
		
		List<DataDictionary> acc = appServiceimpl.queryType();//��ѯƽ̨��Ϣ
		request.setAttribute("flatFormList", acc);
		
		request.setAttribute("categoryLevel1List",acp);
	
		request.setAttribute("querySoftwareName",softwareName);//�������
		request.setAttribute("queryFlatformId",flatformId);//����ƽ̨Id
		request.setAttribute("queryCategoryLevel1",categoryLevel1);//һ��Id
		request.setAttribute("queryCategoryLevel2",categoryLevel2);//����Id
		request.setAttribute("queryCategoryLevel3",categoryLevel3);//����Id
		

		System.out.println("categoryLevelList"+categoryLevel2);
		System.out.println("categoryLevel3List"+categoryLevel3);
		
		String pageIndex = request.getParameter("pageIndex");//��ǰҳ��
		int currentPageNo=1;
		if(pageIndex!=null){
			currentPageNo= Integer.valueOf(pageIndex);
		}
		
		int num1 = appServiceimpl.queryAPP1(app);//��ѯAPP��Ϣ����
		int num = 5;//ÿҳ��ʾ����������
		int totalPageCount = appServiceimpl.AppYe(num1,num);//���ж���ҳ
		pages pages = new pages();
		pages.setCurrentPageNo(currentPageNo);
		pages.setTotalCount(num1);//���ж���������
		pages.setTotalPageCount(totalPageCount);
		request.setAttribute("pages", pages);
		currentPageNo = (currentPageNo-1)*num;
		app.setNum(num);
		app.setCurrentPageNo(currentPageNo);
		
		List<AppInfo> list = appServiceimpl.queryAPP(app);//��ѯAPP��Ϣ
		request.setAttribute("appInfoList",list);//����ѯ����App��Ϣ�����ҳ��
		return "backend/applist";
	}
	/**
	 * ��ѯ����������
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/categorylevellist")
	public void ssq(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("*************");
		String id =request.getParameter("pid");
		System.out.println("------------------");
		System.out.println(id);
		List<AppCategory> list = appServiceimpl.queryApp2(id);
		System.out.println("******");
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
	 * ��ת���ҳ��
	 */
	@RequestMapping("/check")
	public String check(HttpServletRequest request){
		String vid = request.getParameter("vid");
		String aid = request.getParameter("aid");
		System.out.println("appinfoid"+aid);
		System.out.println("vid="+vid);
		AppInfo app = appServiceimpl.queryVersionid(aid);
		AppVersion acp = appServiceimpl.queryVId(vid);
		request.setAttribute("appVersion",acp);
		request.setAttribute("appInfo",app);
		return "backend/appcheck";
	}
	/**
	 * �޸�APP��Ϣ
	 * @param request
	 * @param response
	 * @throws IOException
	 */
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
			out.print("<script>alert('�޸ĳɹ���');location.href='app';</script>");
		}else{
			out.print("<script>alert('�޸�ʧ�ܣ�');;location.href='app';</script>");
		}
	}
}
