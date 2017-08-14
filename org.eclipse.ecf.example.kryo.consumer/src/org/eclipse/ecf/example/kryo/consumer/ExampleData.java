/*******************************************************************************
* Copyright (c) 2017 GODYO Business Solutions AG and others. All rights reserved. This
* program and the accompanying materials are made available under the terms of
* the Eclipse Public License v1.0 which accompanies this distribution, and is
* available at http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*   Peter Hermsdorf - initial API and implementation
******************************************************************************/

package org.eclipse.ecf.example.kryo.consumer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.ecf.example.kryo.api.MainObject;

public class ExampleData {
	
	
	private static List<Integer> integerList = new ArrayList<>();
	static {
		for(int i=0; i < 10000; i++) {
			integerList.add(Integer.valueOf(i));
		}
	}
	
	private static Map<String, Object> map1 = new HashMap<>();
	static {
		map1.put("key1", new ArrayList<String>());
		map1.put("key2", new Integer(1));
		map1.put("key3", integerList);
	}

	private ExampleData() {
		// not used
	}
	
	static final MainObject obj1 = new MainObject("obj1", map1);

}
