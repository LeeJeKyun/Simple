package web.dto;

import java.util.Date;

public class BoardFile {

	private int fileno;
	private int boardno;
	private String originname;
	private String storedname;
	private long filesize;
	private Date writeDate;
	
	public BoardFile() {}

	public BoardFile(int fileno, int boardno, String originname, String storedname, int filesize, Date writeDate) {
		super();
		this.fileno = fileno;
		this.boardno = boardno;
		this.originname = originname;
		this.storedname = storedname;
		this.filesize = filesize;
		this.writeDate = writeDate;
	}

	@Override
	public String toString() {
		return "BoardFile [fileno=" + fileno + ", boardno=" + boardno + ", originname=" + originname + ", storedname="
				+ storedname + ", filesize=" + filesize + ", writeDate=" + writeDate + "]";
	}

	public int getFileno() {
		return fileno;
	}

	public void setFileno(int fileno) {
		this.fileno = fileno;
	}

	public int getBoardno() {
		return boardno;
	}

	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}

	public String getOriginname() {
		return originname;
	}

	public void setOriginname(String originname) {
		this.originname = originname;
	}

	public String getStoredname() {
		return storedname;
	}

	public void setStoredname(String storedname) {
		this.storedname = storedname;
	}

	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	
	
}
