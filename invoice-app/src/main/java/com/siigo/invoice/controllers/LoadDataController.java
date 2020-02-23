package com.siigo.invoice.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.siigo.invoice.carga.LoadDataService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/load-excel")
public class LoadDataController {
	
	private final LoadDataService loadDataService;

	@PostMapping
	public String upload(@RequestPart(value = "file") MultipartFile file) {
		loadDataService.uploadFileData(file);
		return null;
	}
	
}
