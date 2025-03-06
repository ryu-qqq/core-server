package com.ryuqq.core.api.config;


import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import com.ryuqq.core.logging.MDCThreadContextHandler;

@Configuration
public class VirtualThreadConfig implements AsyncConfigurer {

	private final MDCThreadContextHandler threadContextHandler;

	public VirtualThreadConfig(MDCThreadContextHandler threadContextHandler) {
		this.threadContextHandler = threadContextHandler;
	}

	@Bean(name = "virtualThreadExecutor")
	public ExecutorService virtualThreadExecutor() {
		ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
		return new ExecutorServiceWrapper(executor, threadContextHandler);
	}

	private static class ExecutorServiceWrapper extends AbstractExecutorService {
		private final ExecutorService delegate;
		private final MDCThreadContextHandler threadContextHandler;

		public ExecutorServiceWrapper(ExecutorService delegate, MDCThreadContextHandler threadContextHandler) {
			this.delegate = delegate;
			this.threadContextHandler = threadContextHandler;
		}

		@Override
		public void execute(Runnable command) {
			delegate.execute(threadContextHandler.propagateToChildThread(command));
		}

		@Override
		public <T> Future<T> submit(Callable<T> task) {
			return delegate.submit(threadContextHandler.propagateToChildThread(task));
		}

		@Override
		public <T> Future<T> submit(Runnable task, T result) {
			return delegate.submit(threadContextHandler.propagateToChildThread(task), result);
		}

		@Override
		public Future<?> submit(Runnable task) {
			return delegate.submit(threadContextHandler.propagateToChildThread(task));
		}

		@Override
		public void close() {
			super.close();
		}

		@Override
		public void shutdown() {
			delegate.shutdown();
		}

		@Override
		public List<Runnable> shutdownNow() {
			return delegate.shutdownNow();
		}

		@Override
		public boolean isShutdown() {
			return delegate.isShutdown();
		}

		@Override
		public boolean isTerminated() {
			return delegate.isTerminated();
		}

		@Override
		public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
			return delegate.awaitTermination(timeout, unit);
		}
	}

}
