package com.vku.models;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vku.services.EventService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Guest {

	private int id;
	private String cccd;
	private String name;
	private String email;
	private String phoneNumber;
	private String organization;
	private String purpose;
	private String eventId;
	private String otherEvent;
	private String QRCodeImage;

	private String arrivlDate;
	private boolean status;

	private Timestamp createTime;
	private Timestamp updateTime;

	public Event getEvents() {
//		System.out.println("lớp guest test");
		EventService guestService = new EventService();
		if (eventId != null && !eventId.isEmpty()) {
			return guestService.getEventById(Integer.parseInt(eventId));
		}
		return null;
	}
}
