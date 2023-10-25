package it.dedagroup.project_cea.service.def;

import java.util.List;

import it.dedagroup.project_cea.model.Scan;

public interface ScanServiceDef {
	public List<Scan> findAll();

	public void insertScan(Scan scan);
	
	Scan findById(long id);

	public List<Scan> findAllScanByCondominiumId(long condominiumId);

}
