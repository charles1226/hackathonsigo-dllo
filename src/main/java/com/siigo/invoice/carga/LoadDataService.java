package com.siigo.invoice.carga;

import org.springframework.web.multipart.MultipartFile;

public interface LoadDataService {

	void uploadFileData(MultipartFile file);

}
