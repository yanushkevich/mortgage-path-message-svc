package com.promontory.message.service.assessment.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MessageNotFoundException extends RuntimeException implements ExceptionMapper<MessageNotFoundException> {

    public MessageNotFoundException() {
        super();
    }

    @Override
    public Response toResponse(MessageNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new MessageNotFound())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @Getter
    class MessageNotFound {

        @JsonProperty("err_msg")
        private String errorMessage;

        public MessageNotFound() {
            this.errorMessage = "Message not found";
        }
    }
}
