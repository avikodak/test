package com.zimmer.utils;

import java.util.UUID;

public class UUIDGenerators {

	public static String generateCompanyId(){
		UUID uniqueKey = UUID.randomUUID();
		return uniqueKey.toString();
	}
	
	public static String generateUserId(){
		UUID uniqueKey = UUID.randomUUID();
		return uniqueKey.toString();
	}
	
	public static String generateGroupId(){
		UUID uniqueKey = UUID.randomUUID();
		return uniqueKey.toString();
	}
	
	public static String generateEventId(){
		UUID uniqueKey = UUID.randomUUID();
		return uniqueKey.toString();
	}
	
	public static String generateTaskId(){
		UUID uniqueKey = UUID.randomUUID();
		return uniqueKey.toString();
	}
	
	public static String generateFeedId(){
		UUID uniqueKey = UUID.randomUUID();
		return uniqueKey.toString();
	}
	
	public static String generateLikeId(){
		UUID uniqueKey = UUID.randomUUID();
		return uniqueKey.toString();
	}
	
	public static String generateFileUploadId(){
		UUID uniqueKey = UUID.randomUUID();
		return uniqueKey.toString();
	}
	
	public static String generateCommentId(){
		UUID uniqueKey = UUID.randomUUID();
		return uniqueKey.toString();
	}
	
	public static String generateTagId(){
		UUID uniqueKey = UUID.randomUUID();
		return uniqueKey.toString();
	}
}
