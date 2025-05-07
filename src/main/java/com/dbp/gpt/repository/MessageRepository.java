package com.dbp.gpt.repository;

import com.dbp.gpt.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {

    List<Message> findByChatId(UUID chatId);

    List<Message> findBySender(String sender);

    List<Message> findByChatIdOrderByTimestampAsc(UUID chatId);
}
