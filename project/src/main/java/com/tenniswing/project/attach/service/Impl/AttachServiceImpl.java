package com.tenniswing.project.attach.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.tenniswing.project.attach.mapper.AttachMapper;
import com.tenniswing.project.attach.service.AttachService;
import com.tenniswing.project.attach.service.AttachVO;

@Service
public class AttachServiceImpl implements AttachService {
	
	@Autowired
	AttachMapper attachMapper;

	@Override
	public List<AttachVO> attachList(AttachVO attachVO) {		
		return attachMapper.attachListAll(attachVO);
	}

	@Override
	public int saveAttach( String tableDiv, int tablePk, List<AttachVO> files ) {
		if(CollectionUtils.isEmpty(files)) {
			return 0;
		}
		
		for (AttachVO file : files) {
			file.setAttachTableDiv(tableDiv);
			file.setAttacTablePk(tablePk);
		}
		
		return attachMapper.saveAttachAll(files);
	}
	
	
}
