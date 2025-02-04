package com.ryuqq.core.domain.external.core;

import com.ryuqq.core.domain.external.ExternalProductGroup;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Service;

@Service
public class SiteRequestQueueManager {

	private static final int DEFAULT_QUEUE_CAPACITY = 1000;

	private final Map<Long, BlockingQueue<ExternalProductGroup>> siteQueues = new ConcurrentHashMap<>();

	public void addRequest(long siteId, ExternalProductGroup productGroup) {
		BlockingQueue<ExternalProductGroup> queue = siteQueues.computeIfAbsent(siteId, id -> new LinkedBlockingQueue<>(DEFAULT_QUEUE_CAPACITY));
		if (!queue.offer(productGroup)) {
			handleQueueOverflow(siteId, productGroup);
		}
	}

	public BlockingQueue<ExternalProductGroup> getQueue(long siteId) {
		return siteQueues.get(siteId);
	}

	private void handleQueueOverflow(long siteId, ExternalProductGroup productGroup) {
		throw new RuntimeException(String.format("Queue overflow for site ID: {}, Product Group ID: %d, %d", siteId, productGroup.getProductGroupId()));
	}

}
