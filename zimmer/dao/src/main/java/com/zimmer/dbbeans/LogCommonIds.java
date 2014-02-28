package com.zimmer.dbbeans;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class LogCommonIds implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Comment parentComment;
	private Like parentLike;
	
	public LogCommonIds(Feed parentFeed, Comment parentComment,
			Like parentLike) {
		super();
		this.parentComment = parentComment;
		this.parentLike = parentLike;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="log_comment_id")
	public Comment getParentComment() {
		return parentComment;
	}

	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="log_like_id")
	public Like getParentLike() {
		return parentLike;
	}

	public void setParentLike(Like parentLike) {
		this.parentLike = parentLike;
	}

}
