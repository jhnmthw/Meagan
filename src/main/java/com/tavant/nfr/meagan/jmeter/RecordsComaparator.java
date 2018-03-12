package com.tavant.nfr.meagan.jmeter;

import java.util.Comparator;

public class RecordsComaparator implements Comparator<Records> {

	@Override
	public int compare(Records o1, Records o2) {
		// TODO Auto-generated method stub
		return o1.getTransaction().compareTo(o2.getTransaction());
	}

}
