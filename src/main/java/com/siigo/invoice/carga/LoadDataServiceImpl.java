package com.siigo.invoice.carga;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.siigo.invoice.model.Producto;
import com.siigo.invoice.services.IProductoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoadDataServiceImpl implements LoadDataService {
	
	private static final Logger LOGGER = Logger.getLogger(LoadDataServiceImpl.class);
	
	private final IProductoService iProductoService;
	
	@Override
	public void uploadFileData(MultipartFile file) {
		int rowNum = 0;
		LOGGER.info("Inicia el proceso de carga del archivo.");
		int rowActual=1; 
		try(XSSFWorkbook workBook = new XSSFWorkbook(file.getInputStream());) {
			XSSFSheet sheet = workBook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				//preguntar por fila vacia
				rowNum = row.getRowNum();
				if(rowNum-rowActual<=0) {
					LOGGER.info("Cargando fila." + rowNum);
					saveFileDataData(row);
				}
				rowActual++;
			}
		} catch (IOException e) {
			LOGGER.error("Se ignora la fila " + rowNum, e);
			e.printStackTrace();
		}

	}

	@SuppressWarnings("deprecation")
	private void saveFileDataData(Row row) {
		try {
			String nombre = row.getCell(0).getStringCellValue();
			Double precio = row.getCell(1).getNumericCellValue();
			
			Producto producto = new Producto();
			producto.setPrecio(new Double(precio));
			producto.setNombre(nombre);
			producto.setCreateAt( new Date());
			saveProducto(producto);
			
		}catch (Exception e) {
			LOGGER.log(Priority.INFO, "No se encontraron mas datos");
			e.printStackTrace();
		}
	}

	private void saveProducto(Producto producto) {
		if(iProductoService.findByNombre(producto.getNombre()).isEmpty()||iProductoService.findByNombre(producto.getNombre()).size()==0) {
			iProductoService.save(producto);
		}else {
			LOGGER.log(Priority.INFO, "El producto ya existe");
		}
	}
	

}
