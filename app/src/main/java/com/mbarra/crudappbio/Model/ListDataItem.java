package com.mbarra.crudappbio.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ListDataItem implements Serializable {

	@SerializedName("nama_siswa")
	private String namaSiswa;

	@SerializedName("email_siswa")
	private String emailSiswa;

	@SerializedName("kelas_siswa")
	private String kelasSiswa;

	@SerializedName("id_siswa")
	private String idSiswa;

	public void setNamaSiswa(String namaSiswa){
		this.namaSiswa = namaSiswa;
	}

	public String getNamaSiswa(){
		return namaSiswa;
	}

	public void setEmailSiswa(String emailSiswa){
		this.emailSiswa = emailSiswa;
	}

	public String getEmailSiswa(){
		return emailSiswa;
	}

	public void setKelasSiswa(String kelasSiswa){
		this.kelasSiswa = kelasSiswa;
	}

	public String getKelasSiswa(){
		return kelasSiswa;
	}

	public void setIdSiswa(String idSiswa){
		this.idSiswa = idSiswa;
	}

	public String getIdSiswa(){
		return idSiswa;
	}
}