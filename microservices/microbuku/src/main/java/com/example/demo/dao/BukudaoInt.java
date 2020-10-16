package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Buku;
import com.example.demo.model.BukuInfo;

@Repository
public interface BukudaoInt extends JpaRepository<Buku, String>{
	
	@Query(value = "select judul from buku where judul like :searchquery",nativeQuery = true)
	List<String> searchBuku(@Param("searchquery") String searchquery);
	
	@Modifying
	@Query(value = "update buku set harga = :harga where judul = :judul",nativeQuery = true)
	void updateHarga(@Param("judul") String judul ,@Param("harga") int harga);
	
	BukuInfo getOneByJudul(String judul);
	List<BukuInfo> findByJudul(String judul);
	List<BukuInfo> findByTahun(String tahun);
	List<BukuInfo> findByGenre(String genre);
}
