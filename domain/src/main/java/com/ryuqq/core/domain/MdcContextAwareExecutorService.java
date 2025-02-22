package com.ryuqq.core.domain;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Component
public class MdcContextAwareExecutorService {

	private final ExecutorService delegate;

	public MdcContextAwareExecutorService() {
		this.delegate = Executors.newVirtualThreadPerTaskExecutor();
	}

	private Runnable wrapWithMdc(Runnable task) {
		Map<String, String> contextMap = MDC.getCopyOfContextMap();
		return () -> {
			if (contextMap != null) {
				MDC.setContextMap(contextMap);
			}
			try {
				task.run();
			} finally {
				MDC.clear();
			}
		};
	}

	private <T> Callable<T> wrapWithMdc(Callable<T> task) {
		Map<String, String> contextMap = MDC.getCopyOfContextMap();
		return () -> {
			if (contextMap != null) {
				MDC.setContextMap(contextMap);
			}
			try {
				return task.call();
			} finally {
				MDC.clear();
			}
		};
	}

	// @Override
	// public void execute(Runnable command) {
	// 	delegate.execute(wrapWithMdc(command));
	// }
	//
	// @Override
	// public <T> Future<T> submit(Callable<T> task) {
	// 	return delegate.submit(wrapWithMdc(task));
	// }
	//
	// @Override
	// public <T> Future<T> submit(Runnable task, T result) {
	// 	return delegate.submit(wrapWithMdc(task), result);
	// }
	//
	// @Override
	// public Future<?> submit(Runnable task) {
	// 	return delegate.submit(wrapWithMdc(task));
	// }
	//
	// @Override
	// public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
	// 	return delegate.invokeAll(tasks.stream().map(this::wrapWithMdc).toList());
	// }
	//
	// @Override
	// public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
	// 	throws InterruptedException {
	// 	return delegate.invokeAll(tasks.stream().map(this::wrapWithMdc).toList(), timeout, unit);
	// }
	//
	// @Override
	// public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
	// 	return delegate.invokeAny(tasks.stream().map(this::wrapWithMdc).toList());
	// }
	//
	// @Override
	// public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
	// 	throws InterruptedException, ExecutionException, TimeoutException {
	// 	return delegate.invokeAny(tasks.stream().map(this::wrapWithMdc).toList(), timeout, unit);
	// }
	//
	// @Override
	// public void shutdown() {
	// 	delegate.shutdown();
	// }
	//
	// @Override
	// public List<Runnable> shutdownNow() {
	// 	return delegate.shutdownNow();
	// }
	//
	// @Override
	// public boolean isShutdown() {
	// 	return delegate.isShutdown();
	// }
	//
	// @Override
	// public boolean isTerminated() {
	// 	return delegate.isTerminated();
	// }
	//
	// @Override
	// public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
	// 	return delegate.awaitTermination(timeout, unit);
	// }


}
