/*
 * Copyright (c) 2011-2017 Pivotal Software Inc, All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package reactor.core.publisher;

import java.time.Duration;
import java.util.Objects;

import org.reactivestreams.Subscriber;
import reactor.core.Fuseable;

/**
 * @see <a href="https://github.com/reactor/reactive-streams-commons">Reactive-Streams-Commons</a>
 */
final class MonoJust<T> 
extends Mono<T>
		implements Fuseable.ScalarCallable<T>, Fuseable {

	final T value;

	MonoJust(T value) {
		this.value = Objects.requireNonNull(value, "value");
	}

	@Override
	public T call() {
		return value;
	}

	@Override
	public T block(Duration m) {
		return value;
	}

	@Override
	public T block() {
		return value;
	}

	@Override
	public void subscribe(Subscriber<? super T> s) {
		s.onSubscribe(Operators.scalarSubscription(s, value));
	}
}