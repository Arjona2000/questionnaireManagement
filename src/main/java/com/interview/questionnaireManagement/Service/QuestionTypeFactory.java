package com.interview.questionnaireManagement.Service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.questionnaireManagement.DTO.CreateMultiSelectQuestionDTO;
import com.interview.questionnaireManagement.DTO.CreateRatingQuestionDTO;
import com.interview.questionnaireManagement.DTO.CreateSingleSelectQuestionDTO;
import com.interview.questionnaireManagement.DTO.MultiSelectAnswerDTO;
import com.interview.questionnaireManagement.DTO.RatingAnswerDTO;
import com.interview.questionnaireManagement.DTO.SingleSelectAnswerDTO;

// Factory que determina el tipo de pregunta y la procesa adecuadamente

@Service
public class QuestionTypeFactory {
    
    @Autowired
    private RatingQuestionService ratingService;
    
    @Autowired
    private MultiSelectQuestionService multiSelectService;
    
    @Autowired
    private SingleSelectQuestionService singleSelectService;
    
    // Enum que representa los tipos de pregunta disponibles
    public enum QuestionType {
        RATING,
        MULTI_SELECT,
        SINGLE_SELECT
    }
    
    // Crea una pregunta del tipo especificado 
    public boolean createQuestion(QuestionType type, UUID id, Object dto) {
        try {
            switch (type) {
                case RATING:
                    if (dto instanceof CreateRatingQuestionDTO) {
                        ratingService.createQuestion(id, (CreateRatingQuestionDTO) dto);
                        return true;
                    }
                    break;
                case MULTI_SELECT:
                    if (dto instanceof CreateMultiSelectQuestionDTO) {
                        multiSelectService.createQuestion(id, (CreateMultiSelectQuestionDTO) dto);
                        return true;
                    }
                    break;
                case SINGLE_SELECT:
                	if(dto instanceof CreateSingleSelectQuestionDTO) {
                		singleSelectService.createQuestion(id, (CreateSingleSelectQuestionDTO) dto);
                		return true;
                	}
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    // Responde a una pregunta del tipo especificado
    public boolean answerQuestion(QuestionType type, UUID id, Object dto) {
        try {
            switch (type) {
                case RATING:
                    if (dto instanceof RatingAnswerDTO) {
                        return ratingService.answerQuestion(id, ((RatingAnswerDTO) dto).getAnswer());
                    }
                    break;
                case MULTI_SELECT:
                    if (dto instanceof MultiSelectAnswerDTO) {
                        return multiSelectService.answerQuestion(id, ((MultiSelectAnswerDTO) dto).getAnswers());
                    }
                    break;
                case SINGLE_SELECT:
                	if(dto instanceof SingleSelectAnswerDTO) {
                		return singleSelectService.answerQuestion(id, ((SingleSelectAnswerDTO) dto).getAnswer());
                	}
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}