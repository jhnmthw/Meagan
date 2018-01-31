//package com.tavant.nfr.meagan.jmeter;
//
//import org.jxls.command.CellDataUpdater;
//import org.jxls.common.CellData;
//import org.jxls.common.CellRef;
//import org.jxls.common.Context;
//
//public class TotalCellUpdater implements CellDataUpdater {
//	
//	 public void updateCellData(CellData cellData, CellRef targetCell, Context context) {
//	        if( cellData.isFormulaCell() && cellData.getFormula().equals("SUM(E2)")){
//	            String resultFormula = String.format("SUM(E2:E%d)", targetCell.getRow());
//	            cellData.setEvaluationResult(resultFormula);
//	        }
//	    }
//	
//
//}
