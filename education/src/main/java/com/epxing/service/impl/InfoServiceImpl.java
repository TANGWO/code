package com.epxing.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.epxing.entity.ContactUs;
import com.epxing.entity.Pagination;
import com.epxing.entity.WebsiteInformationContent;
import com.epxing.mapper.InfoMapper;
import com.epxing.service.InfoService;
import com.epxing.util.JsonUtils;

@Service
public class InfoServiceImpl implements InfoService {

	@Resource
	private InfoMapper infoMapper;
	
	@Override
	public int getCount(String column) {

		return infoMapper.findCount(column);
	}

	@Override
	public String findInfoByPageAndOwnModule(Pagination<WebsiteInformationContent> p, String ownModule) {
		List<WebsiteInformationContent> list = infoMapper.findInfoByPageAndOwnModule(p, ownModule);
		Map<String, Object> map = new HashMap<String, Object>();
		ContactUs linkUs = infoMapper.getLinkUs();
		String div = "";
		for (int i = 0; i < list.size(); i++) {
			String title =list.get(i).getTitle().length()>40?list.get(i).getTitle().substring(0, 40)+"....":list.get(i).getTitle();
			String div1 = " <li class='clearfix'>";
			String div2 = " <a href='noticeTwo?code=" + list.get(i).getWicid() + "&modle=" + ownModule
					+ "' class='weui-fl'>" +title+ "</a>";
			String div3 = " <span class='weui-fr'>" + list.get(i).getReleaseDate() + "</span> ";
			String div4 = " </li>";
			div = div + div1 + div2 + div3 + div4;
		}
		map.put("info", div);
		
		String s ="";
		if(null !=linkUs){
			String s0="<h4 class='weui-f20  weui-pb10'>联系我们</h4>";
			String s1="<p>电话："+linkUs.getPhone()+"</p>";
			String s2="<p>邮箱： "+linkUs.getEmail()+"</p>";
			String s3="<p>地址："+linkUs.getAddress()+"</p>";
			s =s+s0+s1+s2+s3;
		}
		map.put("link", s);
		
		return  JsonUtils.objectToJson(map);
	}

	@Override
	public String findDetailInfo(String id, String ownModule) {
		WebsiteInformationContent detaileInfo = infoMapper.findDetailInfo(id, ownModule);
		Map<String, Object> map = new HashMap<String, Object>();
		String div = "";
		if (null != detaileInfo) {
			
			if (null != ownModule && ownModule.equals("introduce")) {
				String div1 = "<p><img src='down?id=" + detaileInfo.getPhoto().getID() + "' width='100%'></p>";
				String div2 = "<p class='weui-td2'>" + detaileInfo.getContent() + "</p>";
				div = div1 + div2;
			} else {
				String div1 = "<h3 class='weui-f22 weui-t_c'>" + detaileInfo.getTitle() + "</h3>";
				String div2 = "<p class='weui-t_c weui-f14 weui-c_9'>发布日期：" + detaileInfo.getReleaseDate()
						+ " <span class='weui-pl30'>浏览：" + detaileInfo.getReadingQuantity() + "</span></p>";
				String div3 = " <div class='weui-pl15 weui-pr15'>" + detaileInfo.getContent() + "</div>";
				div = div1 + div2 + div3;
			}
		}
		
		map.put("info", div);
		ContactUs linkUs = infoMapper.getLinkUs();
		String s ="";
		if(null !=linkUs){
			String s0="<h4 class='weui-f20  weui-pb10'>联系我们</h4>";
			String s1="<p>电话："+linkUs.getPhone()+"</p>";
			String s2="<p>邮箱： "+linkUs.getEmail()+"</p>";
			String s3="<p>地址："+linkUs.getAddress()+"</p>";
			s =s+s0+s1+s2+s3;
		}
		map.put("link", s);
		return  JsonUtils.objectToJson(map);
	}

	@Override
	public String pageGetInfo(Pagination<WebsiteInformationContent> p, String context) {
		Map<String, Object> map = new HashMap<String, Object>();
		String preButton = "";
		if (p.isHasPrev()) {
			preButton = "onclick='getInfoByPage(" + p.getPrevPage() + ");'";
		} else {
			preButton = "onclick='return false;'";
		}
		String nextButton = "";
		if (p.isHasNext()) {
			nextButton = "onclick='getInfoByPage(" + p.getNextPage() + ");'";
		} else {
			nextButton = "onclick='return false;'";
		}

		String div = "";
		String div1 = "<div class='form-group weui-pl15 weui-pr15'><a href='javascript:void(0);'  onclick='getInfoByPage("
				+ p.getFirstPage() + ");'>首页</a></div>";
		String div2 = "<div class='form-group weui-pl15 weui-pr15'><a href='javascript:void(0);'  " + preButton
				+ ">上一页</a></div>";
		String div3 = "<div class='form-group weui-pl15 weui-pr15'>第" + p.getPage() + "/" + p.getTotalPage()
				+ "页</div>";
		String div4 = "<div class='form-group weui-pl15 weui-pr15'><a href='javascript:void(0);' " + nextButton
				+ ">下一页</a></div>";
		String div5 = "<div class='form-group weui-pl15 weui-pr15'><a href='javascript:void(0);' onclick='getInfoByPage("
				+ p.getTotalPage() + ");'>末页</a></div>";
		String div6 = "<div class='form-group weui-pl15 weui-pr15'><input type='number' id='inputPage' min='1' max='"
				+ p.getTotalPage() + "' class='form-control' value='' style='width:40px;'></div>";
		String div7 = "<div class='form-group weui-pl15 weui-pr15' onclick='getInfoByInputPage();'>跳转到</div>";
		div = div1 + div2 + div3 + div4 + div5 + div6 + div7;
		map.put("pageLine", div);
		map.put("context", context);
		return JsonUtils.objectToJson(map);
	}

}
