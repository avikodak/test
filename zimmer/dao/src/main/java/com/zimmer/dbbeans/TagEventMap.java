package com.zimmer.dbbeans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tag_event_map")
public class TagEventMap implements Serializable {

	private static final long serialVersionUID = 1L;

	@Embeddable
	@SuppressWarnings("unused")
	private static class key implements Serializable {
		private static final long serialVersionUID = 1L;
		private String tagId;
		private String eventId;

		public key() {
			super();
		}

		@Basic(optional = false)
		@Column(name = "tag_id")
		public String getTagId() {
			return tagId;
		}

		public void setTagId(String tagId) {
			this.tagId = tagId;
		}

		@Basic(optional = false)
		@Column(name = "event_id")
		public String getEventId() {
			return eventId;
		}

		public void setEventId(String eventId) {
			this.eventId = eventId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((eventId == null) ? 0 : eventId.hashCode());
			result = prime * result + ((tagId == null) ? 0 : tagId.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			key other = (key) obj;
			if (eventId == null) {
				if (other.eventId != null)
					return false;
			} else if (!eventId.equals(other.eventId))
				return false;
			if (tagId == null) {
				if (other.tagId != null)
					return false;
			} else if (!tagId.equals(other.tagId))
				return false;
			return true;
		}

	}

	private Integer sno;
	private key linkId = new key();
	private Date createdAt;
	private Date updatedAt;
	private User createdBy;
	private Boolean isActive;
	private Tag tag;
	private Event event;

	
	public TagEventMap() {
		super();
		this.createdAt = new Date();
	}
	
	public TagEventMap(Date createdAt, Date updatedAt,
			User createdBy, Boolean isActive, Tag tag, Event event) {
		super();
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.createdBy = createdBy;
		this.isActive = isActive;
		this.tag = tag;
		this.event = event;
		this.linkId.tagId = tag.getTagId();
		this.linkId.eventId = event.getEventId();
		event.getEventsTags().add(this);
		tag.getEventsForTag().add(this);
	}


	public TagEventMap(User createdBy, Tag tag, Event event) {
		super();
		this.createdBy = createdBy;
		this.tag = tag;
		this.event = event;
		this.createdAt = new Date();
		this.linkId.tagId = tag.getTagId();
		this.linkId.eventId = event.getEventId();
		event.getEventsTags().add(this);
		tag.getEventsForTag().add(this);
	}

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="sno")
	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	@Embedded
	public key getLinkId() {
		return linkId;
	}

	public void setLinkId(key linkId) {
		this.linkId = linkId;
	}

	@Basic(optional=false)
	@Column(name="tag_event_map_created_at")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name="tag_event_map_updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.ALL},optional=false)
	@JoinColumn(name="tag_event_map_owner_id")
	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	@Basic(optional=false)
	@Column(name="is_tag_event_map_active")
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.ALL},optional=false)
	@JoinColumn(name="tag_id",insertable=false,updatable=false)
	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.ALL},optional=false)
	@JoinColumn(name="event_id",insertable=false,updatable=false)
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}
