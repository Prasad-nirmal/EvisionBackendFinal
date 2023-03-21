package com.app.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.entity.Data;
import com.app.entity.User;
import com.app.exception.ResourceNotFoundException;

public interface IService {
public Data uploadImage(MultipartFile cImage, MultipartFile pImage,String result) throws IOException;
public List<Object> displayById (int id) throws IOException ;
public List<ArrayList<Object>> displayAll() throws IOException ;
public List<ArrayList<Object>> displayByIdRange(int id1, int id2) throws IOException;
public User fetchUserByEmailIdAndPassword(User request) throws ResourceNotFoundException;
public void addUser(String email, String pass);
public List<Data> filterDataByResult(String result);
public List<Data> filterDataByDate(String start, String end);
}
