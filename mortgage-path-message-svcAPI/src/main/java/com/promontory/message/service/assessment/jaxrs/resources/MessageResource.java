package com.promontory.message.service.assessment.jaxrs.resources;

import com.promontory.message.service.assessment.exceptions.MessageNotFoundException;
import com.promontory.message.service.assessment.holder.MessageHolder;
import com.promontory.message.service.assessment.model.Message;
import com.promontory.message.service.assessment.model.Digest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Path("/messages")
public class MessageResource {

    private MessageHolder messageHolder;

    @Autowired
    public MessageResource(MessageHolder messageHolder) {
        this.messageHolder = messageHolder;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postMessage(final @Valid Message request) {
        String hexString = DigestUtils.sha256Hex(request.getMessage());
        this.messageHolder.getHashMap().computeIfAbsent(hexString, s -> request.getMessage());
        Digest response = Digest
                .builder()
                .digest(hexString)
                .build();
        return Response.status(Response.Status.CREATED)
                .entity(response).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getMessage(@PathParam("id") String digest) {
        String message = this.messageHolder.getHashMap().getOrDefault(digest, null);
        if (message == null)
            throw new MessageNotFoundException();

       return  Response.ok(Digest.builder().digest(message).build()).build();
    }
}
