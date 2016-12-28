package com.lm.ssm.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.lm.ssm.utils.Commons;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

@Controller
@RequestMapping("/upload")
public class UploadPicController {
	
	@RequestMapping("uploadPic")
	public void uploadPic(HttpServletRequest request, String fileName, PrintWriter out) {
		//��requestǽשΪ�ಿ���������
		MultipartHttpServletRequest mhr = (MultipartHttpServletRequest) request;
		//�����ļ����ƻ�ȡ�ļ�����
		CommonsMultipartFile cmf = (CommonsMultipartFile) mhr.getFile(fileName);
		//��ȡ�ļ��ϴ���
		byte[] bytes = cmf.getBytes();
		//�ļ���չ��
		String originalFilename = cmf.getOriginalFilename();	//123.jpg
		String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")); 	//.jpg
		
		//�����ļ������ظ�
		String newFileName = "";
		//��ȡ��ǰʱ����ַ���
		newFileName = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date());
		for (int i = 0; i < 3; i++) {
			newFileName += new Random().nextInt(10);
		}
		//���ļ���
		newFileName += suffix;	//45442154454.jpg
		
		//����jesy�����������п�������ϴ�
		Client client = new Client();
		//���ļ�������Զ�̷�����
		WebResource resource = client.resource(Commons.PIC_HOST+"/upload/"+newFileName);	//URI
		//�ϴ�
		resource.put(String.class, bytes);
		
		//����ȫ·����
		String fullPath = Commons.PIC_HOST+"/upload/"+newFileName;
		//�������·��
		String realPath = "/upload/"+newFileName;
		//����json��ʽ����{"fullPath":"123.jpg"}
		String jsonResult = "{\"fullPath\":"+"\""+fullPath+"\","+"\"realPath\":"+"\""+realPath+ "\"}";
		out.print(jsonResult);	
	}
}
