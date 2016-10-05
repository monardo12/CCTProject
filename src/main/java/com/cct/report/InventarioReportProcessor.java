package com.cct.report;

import java.io.InputStream;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cct.dto.ReporteDTO;
import com.cct.model.Inventario;
import com.cct.repo.InventarioRepository;

@Component
public class InventarioReportProcessor extends AbstractReportProcessor<Inventario> {

	@Autowired
	private InventarioRepository inventarioRepository;

	@Override
	JasperReport getFileReport() throws JRException {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("com/cct/report/InventarioReport.jrxml");
		return JasperCompileManager.compileReport(in);
	}

	@Override
	DefaultTableModel fillDataReport(List<Inventario> listData) {
		int i = 0;
    	String[] columnNames = {"Id", "Item", "Fecha", "Estado"};
    	Object[][] data = new Object [listData.size()][4];
     
        for (Inventario inventario : listData) {
      	    data[i][0] = inventario.getIdInventario();
     	    data[i][1] = inventario.getItem().getDescripcion();
     	    data[i][2] = inventario.getFechaCompra().toString();
     	    data[i][3] = inventario.getEstado();
        	i++;
		}
        return new DefaultTableModel(data, columnNames);
	}
	
	List<Inventario> getInfo(ReporteDTO datosConsulta) {
		return inventarioRepository.findAllInventarioByFechaCompraGreaterThanEqualAndFechaCompraLessThanEqual(datosConsulta.getFechaInicial(), datosConsulta.getFechaFinal());
	}


}