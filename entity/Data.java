package com.app.entity;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "data")
public class Data {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@CreationTimestamp
	@Column(name = "timeStamp")
	private Timestamp timeStamp;

	@Column(name = "cImage")
	private String cImage;

	@Column(name = "pImage")
	private String pImage;

	@Column(name = "result")
	private String result;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getcImage() {
		return cImage;
	}

	public void setcImage(String cImage) {
		this.cImage = cImage;
	}

	public String getpImage() {
		return pImage;
	}

	public void setpImage(String pImage) {
		this.pImage = pImage;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Data() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Data(int id, Timestamp timeStamp, String cImage, String pImage, String result) {
		super();
		this.id = id;
		this.timeStamp = timeStamp;
		this.cImage = cImage;
		this.pImage = pImage;
		this.result = result;
	}

	@Override
	public String toString() {
		return "Data [id=" + id + ", timeStamp=" + timeStamp + ", cImage=" + cImage + ", pImage=" + pImage + ", result="
				+ result + "]";
	}

}
