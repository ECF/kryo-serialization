/*******************************************************************************
* Copyright (c) 2017 GODYO Business Solutions AG and others. All rights reserved. This
* program and the accompanying materials are made available under the terms of
* the Eclipse Public License v1.0 which accompanies this distribution, and is
* available at http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*   Peter Hermsdorf - initial API and implementation
******************************************************************************/

package org.eclipse.ecf.example.kryo.provider;

import java.util.Collections;
import java.util.List;

import org.eclipse.ecf.example.kryo.api.IExampleRemoteService;
import org.eclipse.ecf.example.kryo.api.MainObject;
import org.osgi.service.component.annotations.Component;

@Component(property = {
		"service.exported.interfaces=*",
		"service.exported.configs=ecf.generic.server",
		"ecf.generic.server.port=3288" })
public class ExampleRemoteService  implements IExampleRemoteService {

	@Override
	public String call1(MainObject parameter) {
		List<Integer> integerList = (List<Integer>) parameter.getData().get("key3");
		for (Integer integer : integerList) {
			System.out.println("got value  " + integer);
		}
		return "OK";
	}

	@Override
	public List<MainObject> sort(List<MainObject> data) {
		return Collections.emptyList();
	}
	

}
