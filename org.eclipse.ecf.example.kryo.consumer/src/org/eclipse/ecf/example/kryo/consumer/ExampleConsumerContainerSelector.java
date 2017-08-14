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

import java.util.Map;

import org.eclipse.ecf.core.ContainerTypeDescription;
import org.eclipse.ecf.core.sharedobject.ISharedObjectContainer;
import org.eclipse.ecf.osgi.services.remoteserviceadmin.ConsumerContainerSelector;
import org.eclipse.ecf.osgi.services.remoteserviceadmin.IConsumerContainerSelector;
import org.eclipse.ecf.osgi.services.remoteserviceadmin.SelectContainerException;
import org.eclipse.ecf.remoteservice.IRemoteServiceContainer;
import org.eclipse.ecf.sharedobject.serializer.kryo.KryoSharedMessageSerializer;
import org.osgi.service.component.annotations.Component;

@Component(service=IConsumerContainerSelector.class)
public class ExampleConsumerContainerSelector extends ConsumerContainerSelector {

	
	public ExampleConsumerContainerSelector() {
		super(true);
	}
	
	@Override
	protected IRemoteServiceContainer createContainer(ContainerTypeDescription containerTypeDescription,
			String containerTypeDescriptionName, Map properties) throws SelectContainerException {
		
		final IRemoteServiceContainer serviceContainer = super.createContainer(containerTypeDescription,
				containerTypeDescriptionName, properties);

		final ISharedObjectContainer container = (ISharedObjectContainer) serviceContainer.getContainer();
		container.setSharedObjectMessageSerializer(new KryoSharedMessageSerializer());
		
		return serviceContainer;
	}
}
