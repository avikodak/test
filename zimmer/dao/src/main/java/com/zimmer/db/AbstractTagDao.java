package com.zimmer.db;

import com.zimmer.dbbeans.Tag;
import com.zimmer.dbbeans.TagEventMap;
import com.zimmer.dbbeans.TagFeedMap;
import com.zimmer.dbbeans.TagTaskMap;

public interface AbstractTagDao {
	
	public void createTag(Tag tag);
	
	public void createLinkTagEvent(TagEventMap tagEventMap);
	
	public void createLinkTagTask(TagTaskMap tagTaskMap);
	
	public void createLinkTagFeed(TagFeedMap tagFeedMap);
}
