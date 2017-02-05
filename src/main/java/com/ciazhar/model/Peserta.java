package com.ciazhar.model;

import javax.persistence.*;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Peserta{
  /*id menggunakan string karena nanti akan menggunakan UUID,
  sehingga id digenerate secara unique.*/
  @Id @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  /*
    not empty just for string
    not null, not empty, size buat validasi
  */
  @Column(nullable=false)
  @NotNull
  @NotEmpty
  @Size(min = 1, max = 150)
  private String nama;

  @Column(nullable=false,unique=true)
  @Email
  @NotNull
  @NotEmpty
  private String email;

  @Column(nullable=false,unique=true)
  @NotNull
  @NotEmpty
  private String noHp;

  @Version
  @Column(columnDefinition= "integer DEFAULT 0")
  private Integer version;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNama() {
    return nama;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNoHp() {
    return noHp;
  }

  public void setNoHp(String noHp) {
    this.noHp = noHp;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }
}
