package com.wigo.voc.sys.controller;

import java.util.ArrayList;
import java.util.Map;

import com.wigo.voc.common.model.EzMap;
import com.wigo.voc.common.util.Utilities;
import com.wigo.voc.sys.model.CrmComnCdBaseVo;
import com.wigo.voc.sys.service.CrmComnCdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
* <pre>
* com.wigo.crm.common.ExampleController
*	- ExampleController.java
* </pre>
*
* @ClassName	: ExampleController 
* @Description	: CrmComnCdController 컨트롤러 
* @author 		: 김성태
* @date 		: 2022. 1. 8.
* @Version 		: 1.0 
* @Company 		: Copyright ⓒ wigo.ai. All Right Reserved
 */

@Controller
@RequestMapping( value = { "commCode", "{menuCd}/commCode" } )
public class CrmComnCdController {
	@Autowired
    CrmComnCdService service;

    @PostMapping( value = { "getComboCode" } )
    public @ResponseBody Object getComboCode( @RequestBody EzMap param ) throws Exception {
    	if(Utilities.isEmpty(param.getString("codeCd")) && Utilities.isEmpty(param.getString("codeType")))
    		return new ArrayList<CrmComnCdBaseVo>();
        return service.getComboCode( param );
    }
    
    @GetMapping( value = { "getCode" } )
    public @ResponseBody Object getCode(@RequestParam Map<String,Object> rparam ) throws Exception {
    	EzMap param = new EzMap(rparam);
    	return  getComboCode(param);
    }

	
}
