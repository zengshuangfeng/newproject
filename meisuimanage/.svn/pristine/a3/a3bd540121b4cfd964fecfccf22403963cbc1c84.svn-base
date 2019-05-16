package com.meisui.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.Message;

@Repository
public interface ImessageDao {

	void InsertMessage(Message message);

	int getMassageCount(@Param("to_manage_id")Integer to_manage_id);

	List<Message> getMessageList(@Param("to_manage_id")Integer to_manage_id, @Param("offset")Integer offset, @Param("rows")Integer rows);

	int imessageCount(@Param("to_manage_id")Integer to_manage_id);

	void updateMessageByisread(@Param("id")Integer id,@Param("read") Integer read);

	int messsageCount(@Param("to_manage_id")Integer to_manage_id);

}
