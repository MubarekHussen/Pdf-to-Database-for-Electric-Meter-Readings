package com.dventus.pdfextractor;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class ExtractData {
	public List<List<String>> extractData(List<List<String>> data) {
		List<String> heading = new ArrayList<>();
		List<List<String>> extractedList = new ArrayList<>();

		for (List<String> li:data) {
			if (li.size() > 1) {
				if(li.get(1).equals("Position")) {
				heading = li;
//				System.out.println(li.get(1));
				continue;
			}
			}
			if (heading.size() > 0 && li.size() == heading.size()) {
				for (int i = 1; i < heading.size(); i++) {
					List<String> temp = new ArrayList<>();
					temp.add(li.get(0));
					temp.add(heading.get(i));
					temp.add(li.get(1));
					temp.add(li.get(i));
					extractedList.add(temp);
				}

			}
		}

//		for (String li: heading) {
//		System.out.println(li.toString());
//	}
		System.out.println(heading.size());

		return (extractedList);
	}

	public List<String> getMeterPositions (List<List<String>> extractedList) {
		Set<String> availableMetres = new HashSet<String>();
		for (int i = 0; i < extractedList.size(); i++) {
			availableMetres.add(extractedList.get(i).get(1));
		}

		return (new ArrayList(availableMetres));
	}

}
