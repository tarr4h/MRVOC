package com.wigo.voc.common.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.wigo.voc.common.model.EzMap;
import com.wigo.voc.common.util.Utilities;
import com.wigo.voc.sys.service.CrmMenuService;

/**
 * 
* <pre>
* com.wigo.crm.common.taglib
*	- MenuNameTag.java
* </pre>
*
* @ClassName	: MenuNameTag 
* @Description	: 메뉴이름태그 
* @author 		: 김성태
* @date 		: 2021. 1. 5.
* @Version 		: 1.0 
* @Company 		: Copyright ⓒ wigo.ai. All Right Reserved
 */

public class MenuNameTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6129581713388769101L;
    private String menuCd;
    
    private CrmMenuService service = Utilities.getBean("menuService");
	
	@Override
    public int doStartTag() throws JspException {
    	
    	EzMap param = new EzMap();
    	param.put("menuCd",getMenuCd());
    	try {
    		EzMap menu  = service.get(param);
    		if(menu!=null)
    			pageContext.getOut().print(menu.getString("menuNm"));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return SKIP_BODY;
    }
	public String getMenuCd() {
		return menuCd;
	}
	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
	}
	
	
}
