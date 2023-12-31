package com.tenniswing.project.attach.service.Impl;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	public List<AttachVO> attachList(String tableDiv, int attachTablePk ) {
		System.out.println(attachTablePk);
		return attachMapper.attachListAll(tableDiv,attachTablePk);
	}

	@Override
	public int saveAttach( String tableDiv, int tablePk, List<AttachVO> files ) {
		if(CollectionUtils.isEmpty(files)) {
			return 0;
		}
		
		for (AttachVO file : files) {
			file.setAttachTableDiv(tableDiv);
			file.setAttachTablePk(tablePk);
		}
		
		return attachMapper.saveAttachAll(files);
	}
	

	
	/*
	 * // 순서 추가 - 코트
	 * 
	 * @Override public int saveAttachTurn( String tableDiv, int tablePk,
	 * List<AttachVO> files ) { if(CollectionUtils.isEmpty(files)) { return 0; }
	 * 
	 * int index = 1; for (AttachVO file : files) {
	 * file.setAttachTableDiv(tableDiv); file.setAttachTablePk(tablePk);
	 * file.setAttachTurn(index); index++; }
	 * 
	 * return attachMapper.saveAttachTurn(files); }
	 */


	/*
	 * @Override public List<AttachVO> attachListAllSns() { return
	 * attachMapper.attachListAllSns(); }
	 */

	@Override
	public int updateAttach(String tableDiv, int tablePk, List<AttachVO> files) {
		if(CollectionUtils.isEmpty(files)) {
			return 0;
		}
		
		for (AttachVO file : files) {
			file.setAttachTableDiv(tableDiv);
			file.setAttachTablePk(tablePk);
		}
		
		return attachMapper.updateAttach(files);
		
	}

	@Override
	public List<AttachVO> attachListAllSns() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * //sns 첨부파일 삭제
	 * 
	 * @Override public boolean deleteAttachSns(int tablePk) { int result =
	 * attachMapper.deleteAttachSns(tablePk);
	 * 
	 * if(result == 1) { return true; }else { return false; } }
	 */
	
}
