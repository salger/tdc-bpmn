package br.com.tdc.bpmn.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricData;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.ProcessInstanceHistoryLog;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.HistoricActivityInstanceEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.tdc.bpmn.dto.TaskDTO;

@RestController
@RequestMapping("/activiti")
public class ActivitiController {

	private static final Logger log = LoggerFactory.getLogger(ActivitiController.class);
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmSS");

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private HistoryService historyService;

	@Autowired
	private RepositoryService repositoryService;

	@RequestMapping(value = "/beginFinancialReport")
	public String beginFinancialReport(@RequestParam(name = "id", required = false) String id,
			HttpServletRequest request) {

		if (StringUtils.isEmpty(id)) {
			id = SDF.format(new Date());
		}

		log.info("Request recebido em " + request.getRequestURL() + " / id: " + id);
		String processKey = "financialReport";

		log.info("Iniciando processo [" + processKey + "]");
		ProcessInstance pi = runtimeService.startProcessInstanceByKey(processKey, id);

		log.info("Iniciado processo [" + pi.toString() + "]");

		return "Instância criada de " + processKey + ": " + pi.getProcessInstanceId() + " (businessKey: " + id + ")";
	}

	@RequestMapping(value = "/taskCandidateList")
	public List<TaskDTO> taskList(@RequestParam(name = "assignee", required = false) String assignee,
			@RequestParam(name = "group", required = false) String group, HttpServletRequest request) {

		log.info("Request recebido em " + request.getRequestURL() + " / assignee: " + assignee + " / group: " + group);

		List<Task> tasks = new ArrayList<Task>();
		if (StringUtils.isEmpty(assignee) && StringUtils.isEmpty(group)) {
			log.info("Obtendo lista de tasks ativas");
			tasks = taskService.createTaskQuery().list();
		} else {
			if (!StringUtils.isEmpty(assignee)) {
				log.info("Obtendo lista de tasks candidatas para o assignee [" + assignee + "]");
				tasks.addAll(taskService.createTaskQuery().taskCandidateUser(assignee).list());
			}
			if (!StringUtils.isEmpty(group)) {
				log.info("Obtendo lista de tasks candidatas para o group [" + group + "]");
				tasks.addAll(taskService.createTaskQuery().taskCandidateGroup(group).list());
			}
		}

		return getTaskDTOs(tasks);
	}

	@RequestMapping(value = "/claimTask/{taskId}")
	public List<TaskDTO> claimTask(@PathVariable("taskId") String taskId,
			@RequestParam(name = "userId", required = false) String userId, HttpServletRequest request) {

		taskService.claim(taskId, userId);

		if (userId == null) {
			return null;
		}

		List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).list();

		return getTaskDTOs(tasks);
	}

	@RequestMapping(value = "/taskAssigneeList")
	public List<TaskDTO> taskList(@RequestParam(name = "assignee") String assignee, HttpServletRequest request) {

		List<Task> tasks = new ArrayList<Task>();

		tasks.addAll(taskService.createTaskQuery().taskAssignee(assignee).list());

		return getTaskDTOs(tasks);
	}

	@RequestMapping(value = "/doneTask/{taskId}")
	public String doneTask(@PathVariable("taskId") String taskId, HttpServletRequest request) {

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		if (task == null) {
			return "Não foi encontrada task com o id " + taskId;
		}

		taskService.complete(taskId);
		task = taskService.createTaskQuery().taskId(taskId).singleResult();

		return "Task " + taskId + " completada com sucesso";
	}

	private List<TaskDTO> getTaskDTOs(List<Task> tasks) {
		List<TaskDTO> taskDTOs = new ArrayList<TaskDTO>();
		for (Task task : tasks) {
			taskDTOs.add(getTaskDTO(task));
		}
		return taskDTOs;
	}

	private TaskDTO getTaskDTO(Task task) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		TaskDTO taskDTO = modelMapper.map(task, TaskDTO.class);
		return taskDTO;
	}

	@RequestMapping(value = "/myProcess")
	public String startMyProcess(@RequestParam MultiValueMap<String, Object> params, HttpServletRequest request) {

		String id = SDF.format(new Date());

		ProcessInstance pi = runtimeService.startProcessInstanceByKey("myProcess", id, params.toSingleValueMap());

		return "Instância criada de myProcess: " + pi.getProcessInstanceId() + " (businessKey: " + id + ")";

	}

	@RequestMapping(value = "/history/{processId}")
	public ProcessInstanceHistoryLog history(@PathVariable("processId") String processId, HttpServletRequest request) {

		log.info("Request recebido em " + request.getRequestURL() + " / processId: " + processId);

		return getHistory(processId);
	}

	private ProcessInstanceHistoryLog getHistory(String processId) {
		ProcessInstanceHistoryLog result = historyService.createProcessInstanceHistoryLogQuery(processId)
				.includeActivities().includeTasks().includeVariables().includeVariableUpdates().singleResult();
		return result;
	}

	@RequestMapping(value = "/getImage/{processId}")
	public String getImage(@PathVariable("processId") String processId, HttpServletRequest request) throws IOException {

		try {

//			ExecutionEntity pi = (ExecutionEntity) runtimeService.createProcessInstanceQuery()
//					.processInstanceId(processId).singleResult();
			
			HistoricProcessInstance hpi = (HistoricProcessInstance) historyService.createHistoricProcessInstanceQuery()
					.processInstanceId(processId).singleResult();

			ProcessDefinitionEntity pde = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
					.getDeployedProcessDefinition(hpi.getProcessDefinitionId());

			BpmnModel bpmnModel = repositoryService.getBpmnModel(hpi.getProcessDefinitionId());

			if (pde != null) {

				ProcessInstanceHistoryLog history = getHistory(processId);
				// List<String> activities = runtimeService.getActiveActivityIds(processId);
				// //pega só a ativa
				List<String> activities = new ArrayList<String>();
				for (HistoricData data : history.getHistoricData()) {
					if (data instanceof HistoricActivityInstanceEntity) {
						activities.add(((HistoricActivityInstanceEntity) data).getActivityId());
					}
				}

				ProcessDiagramGenerator generator = new DefaultProcessDiagramGenerator();
				InputStream resource = generator.generateDiagram(bpmnModel, "png", activities);
				return createFile(resource, processId);
			}
		} catch (Exception e) {
			log.error("Erro ao gerar imagem", e);
		}

		return "Sem imagem";
	}

	private String createFile(InputStream resource, String processId) throws IOException {
		byte[] buffer = new byte[resource.available()];
		resource.read(buffer);

		File targetFile = new File(processId + ".png");
		OutputStream outStream = new FileOutputStream(targetFile);
		outStream.write(buffer);

		IOUtils.closeQuietly(resource);
		IOUtils.closeQuietly(outStream);

		return "<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "\r\n" + "  <head>\r\n"
				+ "  <title>Progresso do fluxo " + processId + "</title>\r\n" + "    \r\n" + "</head>\r\n" + "\r\n"
				+ "\r\n" + "  <body >\r\n" + "\r\n" + "    <img src=\"file://" + targetFile.getAbsolutePath() + "\" />\r\n"
				+ "\r\n" + "\r\n" + "  </body>\r\n" + "\r\n" + "</html>";
	}

}
