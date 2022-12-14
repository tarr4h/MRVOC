package com.wigo.voc.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wigo.voc.common.util.Utilities;
import com.wigo.voc.sys.dao.CrmEmpBaseDao;
import com.wigo.voc.sys.dao.CrmUserBaseDao;
import com.wigo.voc.sys.dao.ICrmDao;
import com.wigo.voc.sys.model.CrmJadeHrVo;
import com.wigo.voc.sys.model.CrmLoginUserVo;
import com.wigo.voc.sys.model.CrmUserBaseVo;

@Service
public class CrmEmpBaseService extends AbstractCrmService {
	@Autowired
	CrmEmpBaseDao dao;
	@Autowired
	CrmUserBaseDao userDao;

	@Autowired
	CrmJadeService jadeService;

	@Override
	public ICrmDao getDao() {
		return dao;
	}

	public void saveSyncEmp() throws Exception {
		List<CrmLoginUserVo> list = jadeService.getCeragemHrList();
		list.addAll(jadeService.getCeragemCnsHrList());
		insertList(list);
	}

	@Override
	public int insert(Object param) throws Exception {
		if (param instanceof CrmLoginUserVo) {

			CrmLoginUserVo v = (CrmLoginUserVo) param;
			if (Utilities.isEmpty(v.getOrgId()))
				return 0;
			if (Utilities.isEmpty(v.getEmailAddr()))
				v.setLoginId(v.getEmpId());
			else
				v.setLoginId(v.getEmailAddr());
			String phone = v.getMobileNo();
			v.setMphonNoEncVal(Utilities.encrypt(phone));
			v.setMobileNo("");
			v.setDelYn("30".equals(v.getStatusCd()) ? "Y" : "N");
		}

		CrmUserBaseVo usr = userDao.select(param);

		if (usr == null) {

			userDao.insert(param);
		} else {
			userDao.updateEmp(param);
		}
		CrmJadeHrVo vo = get(param);
		if (vo == null)
			return super.insert(param);
		else
			return super.update(param);
	}
}
