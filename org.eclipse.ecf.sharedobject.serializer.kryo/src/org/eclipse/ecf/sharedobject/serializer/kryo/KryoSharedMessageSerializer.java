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
import com.esotericsoftware.kryo.pool.KryoFactory;
import com.esotericsoftware.kryo.pool.KryoPool;

public class KryoSharedMessageSerializer implements ISharedObjectMessageSerializer {

	KryoPool pool;

	public KryoSharedMessageSerializer() {
		KryoFactory factory = new KryoFactory() {
			public Kryo create() {
				Kryo kryo = new Kryo();
				kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
				kryo.setClassLoader(getClass().getClassLoader());
				return kryo;
			}
		};
		pool = new KryoPool.Builder(factory).build();

		// TODO
		// kryo.addDefaultSerializer(Throwable.class, new JavaSerializer());
	}

	@Override
	public byte[] serializeMessage(final ID sharedObjectID, final Object message) throws IOException {
		try (Output output = new Output(new ByteArrayOutputStream())) {
			return pool.run(kryo -> {
				kryo.writeClassAndObject(output, message);
				return new ByteArrayOutputStream().toByteArray();
			});
		}
	}

	@Override
	public Object deserializeMessage(final byte[] data) throws IOException, ClassNotFoundException {
		return pool.run(kryo -> kryo.readClassAndObject(new Input(data)));
	}
}
