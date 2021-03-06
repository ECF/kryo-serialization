/*******************************************************************************
* Copyright (c) 2017 GODYO Business Solutions AG and others. All rights reserved. This
* program and the accompanying materials are made available under the terms of
* the Eclipse Public License v1.0 which accompanies this distribution, and is
* available at http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*   Peter Hermsdorf - initial API and implementation
******************************************************************************/

package org.eclipse.ecf.example.kryo.api;

import java.util.Map;

public class MainObject {
	
	String id;
	
	Map<String,Object> data;

	public MainObject(String id, Map<String, Object> data) {
		this.id = id;
		this.data = data;
	}
	public Map<String, Object> getData() {
		return data;
	}
}
