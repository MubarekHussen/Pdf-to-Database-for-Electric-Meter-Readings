package com.dventus;
// Generated Dec 8, 2022, 4:50:42 PM by Hibernate Tools 6.1.3.Final

import java.math.BigInteger;

/**
 * TestFile generated by hbm2java
 */
public class TestFile implements java.io.Serializable {

	private String fileName;
	private byte[] fileData;
	private BigInteger fileLength;
	private String fileProp;
	private String fileId;

	public TestFile() {
	}

	public TestFile(String fileName) {
		this.fileName = fileName;
	}

	public TestFile(String fileName, byte[] fileData, BigInteger fileLength, String fileProp, String fileId) {
		this.fileName = fileName;
		this.fileData = fileData;
		this.fileLength = fileLength;
		this.fileProp = fileProp;
		this.fileId = fileId;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileId() {
		return this.fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public byte[] getFileData() {
		return this.fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public BigInteger getFileLength() {
		return this.fileLength;
	}

	public void setFileLength(BigInteger fileLength) {
		this.fileLength = fileLength;
	}

	public String getFileProp() {
		return this.fileProp;
	}

	public void setFileProp(String fileProp) {
		this.fileProp = fileProp;
	}

}
