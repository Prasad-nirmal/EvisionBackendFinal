package com.app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.app.entity.Data;
import com.app.entity.User;
import com.app.exception.ResourceNotFoundException;
import com.app.repo.DataRepository;
import com.app.repo.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServiceImpl implements IService {

	@Autowired
	DataRepository datarepo;
	@Autowired
	UserRepository userrepo;

	private final String PATH = "C:\\meg-nxt\\evision_image_data\\";

	@Override
	public Data uploadImage(MultipartFile cImageFile, MultipartFile pImageFile, @RequestParam("result") String result)
			throws IOException {

		String fileName = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss-SSSS ").format(new Date());
		String fullPathC = PATH + fileName + cImageFile.getOriginalFilename();
		String fullPathP = PATH + fileName + pImageFile.getOriginalFilename();
		Data d = new Data();
		d.setcImage(fullPathC);
		d.setpImage(fullPathP);
		d.setResult(result);
		cImageFile.transferTo(new File(fullPathC));
		pImageFile.transferTo(new File(fullPathP));
		return datarepo.save(d);
	}

	@Override
	public List<Object> displayById(int id) throws IOException {
		Optional<Data> imageObject = datarepo.findById(id);
		List<Object> list = new ArrayList<>();

		int Id = imageObject.get().getId();
		Timestamp ts = imageObject.get().getTimeStamp();
		String res = imageObject.get().getResult();

		String fullPathC = imageObject.get().getcImage();
		String fullPathP = imageObject.get().getpImage();

		list.add(Id);
		list.add(ts);
		list.add(res);
		list.add(Files.readAllBytes(new File(fullPathC).toPath()));
		list.add(Files.readAllBytes(new File(fullPathP).toPath()));
		return list;
	}

	@Override
	public List<ArrayList<Object>> displayAll() throws IOException {
		List<Integer> idList = datarepo.Id();

		List<ArrayList<Object>> list1 = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> list;

		for (Integer id : idList) {
			Optional<Data> imageObject = datarepo.findById(id);
			int Id = imageObject.get().getId();
			Timestamp ts = imageObject.get().getTimeStamp();
			String res = imageObject.get().getResult();

			String fullPathC = imageObject.get().getcImage();
			String fullPathP = imageObject.get().getpImage();

			list = new ArrayList<Object>();
			list.add(Id);
			list.add(ts);
			list.add(res);
			list.add(Files.readAllBytes(new File(fullPathC).toPath()));
			list.add(Files.readAllBytes(new File(fullPathP).toPath()));

			list1.add(list);
		}

		return list1;
	}

	@Override
	public List<ArrayList<Object>> displayByIdRange(int id1, int id2) throws IOException {
//		List<Data> idList = datarepo.findAllInIdRange(id1 ,id2);
//		System.out.println(idList);

		List<ArrayList<Object>> list1 = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> list = new ArrayList<>();

		Integer a = id1;
		Integer b = id2;

		for (Integer i = a; i <= b; i++) {
			Optional<Data> imageObject = datarepo.findById(i);

			int Id = imageObject.get().getId();
			Timestamp ts = imageObject.get().getTimeStamp();
			String res = imageObject.get().getResult();

			String fullPathC = imageObject.get().getcImage();
			String fullPathP = imageObject.get().getpImage();

			list = new ArrayList<>();
			list.add(Id);
			list.add(ts);
			list.add(res);
			list.add(Files.readAllBytes(new File(fullPathC).toPath()));
			list.add(Files.readAllBytes(new File(fullPathP).toPath()));
			list1.add(list);
		}

		return list1;
	}

	@Override
	public User fetchUserByEmailIdAndPassword(User request) throws ResourceNotFoundException {
		PasswordEncoder encodePass = new BCryptPasswordEncoder();
		User user = userrepo.findUserByEmailIdAndRole(request.getEmailId());
		if (encodePass.matches(request.getPassword(), user.getPassword())) {
			return user;
		} else {
			throw new ResourceNotFoundException("Invalid Credentials");
		}
	}

	@Override
	public void addUser(String email, String pass) {
		User u = new User();
		PasswordEncoder encodePass = new BCryptPasswordEncoder();
		String password = encodePass.encode(pass);
		u.setEmailId(email);
		u.setPassword(password);
		userrepo.save(u);
	}

	@Override
	public List<Data> filterDataByResult(String result) {
		List<Data> data = datarepo.filterByResult(result);
		return data;
	}

	@Override
	public List<Data> filterDataByDate(String start, String end) {
		List<Data> data = datarepo.filterByDate(start, end);
		return data;
	}
	
}
