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

import java.util.List;

public interface IExampleRemoteService {
	
	
	String call1(MainObject parameter);
	
	List<MainObject> sort(List<MainObject> data);

}
