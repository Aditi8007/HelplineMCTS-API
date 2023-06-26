package com.iemr.mcts.controller.supervisor;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.mcts.services.supervisor.MctsQAMappingService;
import com.iemr.mcts.services.supervisor.QuestionnaireService;
import com.iemr.mcts.utils.response.OutputResponse;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/questionnaireController")
public class QuestionerController {

	Logger logger = LoggerFactory.getLogger(QuestionerController.class);

	/**
	 * Question Type Repository
	 */
	private QuestionnaireService questionnaireService;

	/**
	 * Inject Question Type Repository
	 */
	@Autowired
	public void setQuestionnaireService(QuestionnaireService questionnaireService) {

		this.questionnaireService = questionnaireService;
	}

	/**
	 * Mcts QA Mapping Service
	 */
	private MctsQAMappingService mctsQAMappingService;

	/**
	 * Inject Mcts QA Mapping Service
	 * 
	 * @param mctsQAMappingService
	 */
	@Autowired
	public void setMctsQAMappingService(MctsQAMappingService mctsQAMappingService) {

		this.mctsQAMappingService = mctsQAMappingService;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@CrossOrigin()
	@RequestMapping(value = "/edit/interaction", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String editInteraction(
			@ApiParam("{\"mctsQAMapID\":\"Integer\", \"interaction\":\"String Value\", \"variableName\":\"String-Name \", \"variableDataType\":\"String- Name\"}") @RequestBody String request) {
		OutputResponse response = new OutputResponse();
		try {
			response.setResponse(mctsQAMappingService.updateInteraction(request));
		} catch (Exception e) {
			logger.error("eidt-interaction failed with error " + e.getMessage(), e);
			response.setError(e);
		}

		return response.toString();
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@CrossOrigin()
	@RequestMapping(value = "/delete/interaction", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String deleteInteraction(@ApiParam("{\"mctsQAMapID\":\"Integer\")}") @RequestBody String request) {
		OutputResponse response = new OutputResponse();
		try {
			response.setResponse(mctsQAMappingService.deleteInteraction(request));
		} catch (Exception e) {
			logger.error("eidt-interaction failed with error " + e.getMessage(), e);
			response.setError(e);
		}

		return response.toString();
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@CrossOrigin()
	@RequestMapping(value = "/get/interaction/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String interactionsList(@ApiParam("{\"questionID\":\"Integer\"}") @RequestBody String request) {
		OutputResponse response = new OutputResponse();
		try {
			response.setResponse(mctsQAMappingService.interactionsList(request));
		} catch (Exception e) {
			logger.error("interaction-list failed with error " + e.getMessage(), e);
			response.setError(e);
		}

		return response.toString();
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@CrossOrigin()
	@RequestMapping(value = "/edit/questionnaire", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String editQuestionnaire(
			@ApiParam("{\"questionID\":\"Integer\", \"question\":\"String Value\", \"answerType\":\"String-Name \", "
					+ "\"triggerFeedback\":\"Boolean- Value\", \"triggerFeedbackFor\":\"String-Value\", \"showText\":\"Boolean- Value\", \"showTextFor\":\"String-Value\"}") @RequestBody String request) {
		OutputResponse response = new OutputResponse();
		try {
//		questionnaireService.updateQuestionnaire(request));
			String res= questionnaireService.updateQuestionnaire(request);
			if(res != null)
				response.setResponse(res);
			else
				response.setError(5000, "Question not updated successfully");
		} catch (Exception e) {
			logger.error("put-questionnaire failed with error " + e.getMessage(), e);
			response.setError(e);
		}

		return response.toString();
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@CrossOrigin()
	@RequestMapping(value = "/put/interactions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String addInteractions(@RequestBody String request) {
		OutputResponse response = new OutputResponse();
		try {
			response.setResponse(mctsQAMappingService.saveInteractions(request));
		} catch (Exception e) {
			logger.error("save-interactions failed with error " + e.getMessage(), e);
			response.setError(e);
		}

		return response.toString();
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@CrossOrigin()
	@RequestMapping(value = "/put/outboundcall/questions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String createOutBoundQuestions(@RequestBody String request) {
		OutputResponse response = new OutputResponse();
		try {

			response.setResponse(mctsQAMappingService.createOutboundQuestions(request));
		} catch (Exception e) {
			logger.error("put-outboundcall-questions failed with error " + e.getMessage(), e);
			response.setError(e);
		}

		return response.toString();
	}

	/**
	 * getting list of questions on outbound call type
	 * 
	 * @param request
	 * @return
	 */
	@CrossOrigin()
	@RequestMapping(value = "/get/questionnaireList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String outboundQuestionList(@RequestBody String request) {
		OutputResponse response = new OutputResponse();
		try {

			response.setResponse(mctsQAMappingService.getOutboundQuestionList(request));
		} catch (Exception e) {
			logger.error("get-outbound question list " + e.getMessage(), e);
			response.setError(e);
		}

		return response.toString();
	}

	/**
	 * getting list of questions on outbound call type
	 * 
	 * @param request
	 * @return
	 */
	@CrossOrigin()
	@RequestMapping(value = "/delete/question", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String updateQuestion(@ApiParam("{\"questionID\":\"Integer\"}") @RequestBody String request) {
		OutputResponse response = new OutputResponse();
		try {			
			String res= mctsQAMappingService.deleteQuestion(request);
			if(res != null)
				response.setResponse(res);
			else
				response.setError(5000, "Question not deleted successfully");
		} catch (Exception e) {
			logger.error("get-outbound question list " + e.getMessage(), e);
			response.setError(e);
		}

		return response.toString();
	}

	/*
	 * Saves the child question ID of parent question ID.
	 */
	@CrossOrigin()
	@RequestMapping(value = "derived/addDeriveQuestion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String deriveQuestion(@ApiParam("{\"questionID\":\"Integer\"}") @RequestBody String request) {
		OutputResponse response = new OutputResponse();
		try {

			response.setResponse(mctsQAMappingService.addDeriveQuestion(request));			String res= mctsQAMappingService.addDeriveQuestion(request);
			if(res != null)
				response.setResponse(res);
			else
				response.setError(5000, "Question not mapped successfully");
		} catch (Exception e) {
			response.setError(e);
		}

		return response.toString();
	}

	@CrossOrigin()
	@RequestMapping(value = "/get/agentQuestionnaireList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String outboundQuestionListAgent(@RequestBody String request) {
		OutputResponse response = new OutputResponse();
		try {

			response.setResponse(mctsQAMappingService.getAgentOutboundQuestionList(request));
		} catch (Exception e) {
			logger.error("get-outbound question list " + e.getMessage(), e);
			response.setError(e);
		}

		return response.toString();
	}
	
	@CrossOrigin()
	@RequestMapping(value = "/delete/multipleQuestion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, headers = "Authorization")
	public String deleteMultipleQuestions(@RequestBody String request) {
		OutputResponse response = new OutputResponse();
		try {

			String res= mctsQAMappingService.deleteMultipleQuestions(request);
			if(res != null)
				response.setResponse(res);
			else
				response.setError(5000, "Questions not deleted successfully");
		} catch (Exception e) {
			logger.error("get-outbound question list " + e.getMessage(), e);
			response.setError(e);
		}

		return response.toString();
	}

}