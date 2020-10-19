package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Buku;
import com.example.demo.model.BukuInfo;

@Repository
@Transactional(readOnly = true)
public interface BukudaoInt extends JpaRepository<Buku, String>{
	
	@Modifying
	@Query(value = "update buku set harga = :harga where judul = :judul",nativeQuery = true)
	void updateHarga(@Param("judul") String judul ,@Param("harga") int harga);
	
	BukuInfo getOneByJudul(String judul);
	List<BukuInfo> findByJudul(String judul);
	List<BukuInfo> findByJudulContainingIgnoreCase(String judul);
	List<BukuInfo> findByJudulIgnoreCaseStartingWith(String judul);
	List<BukuInfo> findByTahun(String tahun);
	List<BukuInfo> findByGenre(String genre);
}
