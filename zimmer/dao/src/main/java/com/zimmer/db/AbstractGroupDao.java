package com.zimmer.db;
import com.zimmer.dbbeans.Group;
import com.zimmer.dbbeans.GroupMemberLink;

public interface AbstractGroupDao {
	public void createGroup(Group group);
	public void createGroupUserLink(GroupMemberLink groupMemberLink);
}
