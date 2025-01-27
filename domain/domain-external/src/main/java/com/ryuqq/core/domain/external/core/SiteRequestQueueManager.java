package com.ryuqq.core.domain.external.core;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.external.ExternalProductGroup;

@Component
public class SiteRequestQueueManager {
	private static final Logger log = LoggerFactory.getLogger(SiteRequestQueueManager.class);

	private static final int DEFAULT_QUEUE_CAPACITY = 500; // 큐의 최대 크기 설정

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
		log.error("Queue overflow for site ID: {}, Product Group ID: {}", siteId, productGroup.getProductGroupId());
	}

}
