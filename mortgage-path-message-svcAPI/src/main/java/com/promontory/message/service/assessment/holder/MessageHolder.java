package com.promontory.message.service.assessment.holder;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@Scope("")
@Getter
public class MessageHolder {

    private final Map<String, String> hashMap = Collections.synchronizedMap(new HashMap<>(10000));

}
