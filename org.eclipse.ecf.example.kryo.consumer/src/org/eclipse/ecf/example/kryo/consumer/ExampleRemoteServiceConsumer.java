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

import org.eclipse.ecf.example.kryo.api.IExampleRemoteService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;

@Component
public class ExampleRemoteServiceConsumer {
	
	@Reference(policy=ReferencePolicy.DYNAMIC)
	void bind(IExampleRemoteService service) {
		System.out.println("Got remote Service " + service);
		
		String result = service.call1(ExampleData.obj1);
		System.out.println(result);
		
		
	}
	void unbind(IExampleRemoteService service) {
		System.out.println("Service " + service + " gone");
	}
}
