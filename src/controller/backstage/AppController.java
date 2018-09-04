package controller.backstage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import entity.AppInfo;
import service.AppServiceimpl;
@Controller
@RequestMapping("/manager")
public class AppController {
	@Autowired
	@Qualifier("AppMapper")
	private AppServiceimpl appServiceimpl;
	
	/**
	 * ��ѯȫ����Ҫ��˵�APP��Ϣ
	 * @param request
	 * @return
	 */
	@RequestMapping("/app")
	public String queryList(HttpServletRequest request){
		String softwareName = request.getParameter("querySoftwareName");//�������
		String flatformId1 = request.getParameter("queryFlatformId");//�������
		String categoryLevel11 = request.getParameter("queryCategoryLevel1");//�������
		String categoryLevel22 = request.getParameter("queryCategoryLevel2");//�������
		String categoryLevel33 = request.getParameter("queryCategoryLevel3");//�������
		AppInfo app = new AppInfo();
		app.setSoftwareName(softwareName);
		int flatformId=0;
		int categoryLevel1=0;
		int categoryLevel2=0;
		int categoryLevel3=0;
		if(flatformId1!=null){
			flatformId=Integer.valueOf(flatformId);
		}
		if(categoryLevel11!=null){
			categoryLevel1=Integer.valueOf(categoryLevel11);
		}
		if(categoryLevel22!=null){
			categoryLevel2=Integer.valueOf(categoryLevel22);
		}
		if(categoryLevel33!=null){
			categoryLevel3=Integer.valueOf(categoryLevel33);
		}
		app.setFlatformId(flatformId);
		app.setCategoryLevel1(categoryLevel1);
		app.setCategoryLevel2(categoryLevel2);
		app.setCategoryLevel3(categoryLevel3);
		List<AppInfo> list = appServiceimpl.queryAPP(app);
		request.setAttribute("appInfoList",list);
		return "backend/applist";
	}
}
