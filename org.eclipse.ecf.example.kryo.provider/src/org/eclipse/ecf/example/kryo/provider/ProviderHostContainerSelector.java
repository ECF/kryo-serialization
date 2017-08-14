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

import java.util.Map;

import org.eclipse.ecf.core.ContainerTypeDescription;
import org.eclipse.ecf.core.sharedobject.ISharedObjectContainer;
import org.eclipse.ecf.osgi.services.remoteserviceadmin.HostContainerSelector;
import org.eclipse.ecf.osgi.services.remoteserviceadmin.IHostContainerSelector;
import org.eclipse.ecf.osgi.services.remoteserviceadmin.SelectContainerException;
import org.eclipse.ecf.remoteservice.IRemoteServiceContainer;
import org.eclipse.ecf.sharedobject.serializer.kryo.KryoSharedMessageSerializer;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Component;

@Component(service=IHostContainerSelector.class)
public class ProviderHostContainerSelector extends HostContainerSelector {

	public ProviderHostContainerSelector() {
		// set the auto create flag to true, so that if the container instance
		// with type specified via SERVICE_EXPORTED_CONFIG
		// does not already exist, then it will be automatically created
		super(null, true);
	}	
	
	@Override
	protected IRemoteServiceContainer createRSContainer(ServiceReference serviceReference,
			Map<String, Object> properties, ContainerTypeDescription containerTypeDescription)
			throws SelectContainerException {
		IRemoteServiceContainer remoteServiceContainer = super.createRSContainer(serviceReference, properties, containerTypeDescription);
		ISharedObjectContainer container = (ISharedObjectContainer) remoteServiceContainer.getContainer();
		
		container.setSharedObjectMessageSerializer(new KryoSharedMessageSerializer());
		
		return remoteServiceContainer;
	}

}
