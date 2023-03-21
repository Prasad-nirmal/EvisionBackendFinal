package com.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.entity.Data;
import com.app.service.IService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DataController {

	@Autowired
	IService iservice;
	
	@PostMapping("/upload")
	public String uploadData(@RequestParam("cImage") MultipartFile file1, @RequestParam("pImage") MultipartFile file2, @RequestParam("result") String result) throws IOException {
		iservice.uploadImage(file1, file2, result);
		return "Successfully Uploaded:"+"\n cImage: "+file1.getOriginalFilename()+"\n pImage: "+file2.getOriginalFilename()+"\n result: "+result ;
	}
	
	@GetMapping("/download/{id}")
	public List<Object> downloadData(@PathVariable int id) throws IOException{
		List<Object> list = iservice.displayById(id);
//		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(list);
		return list;
	}
	
	@GetMapping("/downloadall")
	public List<ArrayList<Object>> downloadAll() throws IOException{
		List<ArrayList<Object>> list = iservice.displayAll();
//		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(list);
		return list;
	}
	
	@GetMapping("/downloadbyrange/{id1}-{id2}")
	public List<ArrayList<Object>> displayByIdRange(@PathVariable int id1,@PathVariable int id2) throws IOException{
		List<ArrayList<Object>> list = iservice.displayByIdRange(id1, id2);
		return list;
	}
	
	@GetMapping("/filterbyresult/{result}")
	public List<Data> filterDataByResult(@PathVariable String result) {
		List<Data> data = iservice.filterDataByResult(result);
		return data;
	}
	
	@GetMapping("/filterbydate/{start}_{end}")
	public List<Data> filteDataByDate(@PathVariable String start,@PathVariable String end){ 
	List<Data> data = iservice.filterDataByDate(start, end);
		return data;
	}

}
