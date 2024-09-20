package com.willis.quiz_service.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
}
