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
		//把request墙砖为多部件请求对象
		MultipartHttpServletRequest mhr = (MultipartHttpServletRequest) request;
		//根据文件名称获取文件对象
		CommonsMultipartFile cmf = (CommonsMultipartFile) mhr.getFile(fileName);
		//获取文件上传流
		byte[] bytes = cmf.getBytes();
		//文件拓展名
		String originalFilename = cmf.getOriginalFilename();	//123.jpg
		String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")); 	//.jpg
		
		//处理文件名称重复
		String newFileName = "";
		//获取当前时间的字符串
		newFileName = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date());
		for (int i = 0; i < 3; i++) {
			newFileName += new Random().nextInt(10);
		}
		//新文件名
		newFileName += suffix;	//45442154454.jpg
		
		//创建jesy服务器，进行跨服务器上传
		Client client = new Client();
		//把文件关联到远程服务器
		WebResource resource = client.resource(Commons.PIC_HOST+"/upload/"+newFileName);	//URI
		//上传
		resource.put(String.class, bytes);
		
		//返回全路径名
		String fullPath = Commons.PIC_HOST+"/upload/"+newFileName;
		//返回相对路径
		String realPath = "/upload/"+newFileName;
		//构造json格式数据{"fullPath":"123.jpg"}
		String jsonResult = "{\"fullPath\":"+"\""+fullPath+"\","+"\"realPath\":"+"\""+realPath+ "\"}";
		out.print(jsonResult);	
	}
}
