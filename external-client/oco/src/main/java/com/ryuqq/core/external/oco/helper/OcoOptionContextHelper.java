package com.ryuqq.core.external.oco.helper;

import java.util.ArrayList;
import java.util.List;

import com.ryuqq.core.external.oco.OcoOptionContext;

public class OcoOptionContextHelper {

	public static PartitionedOcoOptionContexts partitionContexts(List<OcoOptionContext> optionContexts) {
		List<OcoOptionContext> inserts = new ArrayList<>();
		List<OcoOptionContext> deleted = new ArrayList<>();

		for (OcoOptionContext context : optionContexts) {
			if (context.deleted()) {
				deleted.add(context);
			} else {
				inserts.add(context);
			}
		}

		return new PartitionedOcoOptionContexts(inserts, deleted);
	}


	public static class PartitionedOcoOptionContexts {
		private final List<OcoOptionContext> inserts;
		private final List<OcoOptionContext> deleted;

		public PartitionedOcoOptionContexts(List<OcoOptionContext> inserts, List<OcoOptionContext> deleted) {
			this.inserts = inserts;
			this.deleted = deleted;
		}

		public List<OcoOptionContext> getInserts() {
			return inserts;
		}

		public List<OcoOptionContext> getDeleted() {
			return deleted;
		}
	}

}
