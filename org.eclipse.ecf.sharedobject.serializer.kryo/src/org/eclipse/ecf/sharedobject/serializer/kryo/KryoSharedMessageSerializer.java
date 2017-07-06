/*******************************************************************************
* Copyright (c) 2017 GODYO Business Solutions AG and others. All rights reserved. This
* program and the accompanying materials are made available under the terms of
* the Eclipse Public License v1.0 which accompanies this distribution, and is
* available at http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*   Peter Hermsdorf - initial API and implementation
******************************************************************************/

package org.eclipse.ecf.sharedobject.serializer.kryo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.ecf.core.identity.ID;
import org.eclipse.ecf.core.sharedobject.util.ISharedObjectMessageSerializer;
import org.objenesis.strategy.StdInstantiatorStrategy;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class KryoSharedMessageSerializer implements ISharedObjectMessageSerializer {

	Kryo kryo = new Kryo();
	final ByteArrayOutputStream baos = new ByteArrayOutputStream();

	public KryoSharedMessageSerializer() {
		kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
		kryo.setClassLoader(this.getClass().getClassLoader());

		//TODO
//		kryo.addDefaultSerializer(Throwable.class, new JavaSerializer());
	}

	@Override
	public byte[] serializeMessage(final ID sharedObjectID, final Object message) throws IOException {
		try {
			baos.reset();
			final Output output = new Output(baos);
			kryo.writeClassAndObject(output, message);
			output.close();
			return baos.toByteArray();
		} catch (final Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Object deserializeMessage(final byte[] data) throws IOException, ClassNotFoundException {
		try {
			return kryo.readClassAndObject(new Input(data));
		} catch (final Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
