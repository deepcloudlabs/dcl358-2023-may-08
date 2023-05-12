package com.example.crm.service;

import com.example.crm.domain.EventDocument;

public interface MessagingService {
	public void sendMessage(EventDocument event);
}
