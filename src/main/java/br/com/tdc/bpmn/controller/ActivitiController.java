package br.com.tdc.bpmn.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.ProcessInstanceHistoryLog;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
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

	private static final String TESTER = "tester";
	private static final String ACCOUNTANCY = "accountancy";
	private static final String MANAGEMENT = "management";
	private static final String JERRY = "jerryag";
	private static final String ANDREA = "andreaco";
	private static final String SALGER = "salger";

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private HistoryService historyService;

	@RequestMapping(value = "/beginFinancialReport")
	public String bpmTutorial(@RequestParam(name = "id", required = false) String id, HttpServletRequest request) {

		createGroupsAndUsers();

		if (StringUtils.isBlank(id)) {
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
		if (StringUtils.isBlank(assignee) && StringUtils.isBlank(group)) {
			log.info("Obtendo lista de tasks ativas");
			tasks = taskService.createTaskQuery().active().list();
		} else {
			if (StringUtils.isNotBlank(assignee)) {
				log.info("Obtendo lista de tasks candidatas para o assignee [" + assignee + "]");
				tasks.addAll(taskService.createTaskQuery().taskCandidateUser(assignee).list());
			}
			if (StringUtils.isNotBlank(group)) {
				log.info("Obtendo lista de tasks candidatas para o group [" + group + "]");
				tasks.addAll(taskService.createTaskQuery().taskCandidateGroup(group).list());
			}
		}

		return getTaskDTOs(tasks);
	}

	@RequestMapping(value = "/claimTask/{taskId}")
	public List<TaskDTO> claimTask(@PathVariable("taskId") String taskId,
			@RequestParam(name = "userId", required = false) String userId, HttpServletRequest request) {

		log.info("Request recebido em " + request.getRequestURL() + " / taskId: " + taskId + " / assignee: " + userId);

		log.info("Tast " + taskId + " solicitada para o assignee: " + userId);
		taskService.claim(taskId, userId);

		if (userId == null) {
			return null;
		}

		log.info("Obtendo lista de tasks para o assignee [" + userId + "]");
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).list();

		return getTaskDTOs(tasks);
	}

	@RequestMapping(value = "/taskAssigneeList")
	public List<TaskDTO> taskList(@RequestParam(name = "assignee") String assignee, HttpServletRequest request) {

		log.info("Request recebido em " + request.getRequestURL() + " / assignee: " + assignee);

		List<Task> tasks = new ArrayList<Task>();

		log.info("Obtendo lista de tasks sob associadas ao assignee [" + assignee + "]");
		tasks.addAll(taskService.createTaskQuery().taskAssignee(assignee).list());

		return getTaskDTOs(tasks);
	}

	@RequestMapping(value = "/doneTask/{taskId}")
	public String doneTask(@PathVariable("taskId") String taskId, HttpServletRequest request) {

		log.info("Request recebido em " + request.getRequestURL() + " / taskId: " + taskId);

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		if (task == null) {
			return "Não foi encontrada task com o id " + taskId;
		}

		log.info("Completando task " + taskId);
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

		log.info("Request recebido em " + request.getRequestURL() + " / id: " + id);
		String processKey = "myProcess";

		log.info("Iniciando processo [" + processKey + "]");
		ProcessInstance pi = runtimeService.startProcessInstanceByKey(processKey, id, params.toSingleValueMap());

		log.info("Iniciado processo [" + pi.toString() + "]");

		return "Instância criada de " + processKey + ": " + pi.getProcessInstanceId() + " (businessKey: " + id + ")";

	}

	private void createGroupsAndUsers() {

		// Grupo tester
		long count = identityService.createGroupQuery().groupId(TESTER).count();
		if (count == 0) {
			Group group = new GroupEntity(TESTER);
			group.setName("Grupo " + TESTER);
			identityService.saveGroup(group);
		}

		// Grupo accountancy
		count = identityService.createGroupQuery().groupId(ACCOUNTANCY).count();
		if (count == 0) {
			Group group = new GroupEntity(ACCOUNTANCY);
			group.setName("Grupo " + ACCOUNTANCY);
			identityService.saveGroup(group);
		}

		// Grupo management
		count = identityService.createGroupQuery().groupId(MANAGEMENT).count();
		if (count == 0) {
			Group group = new GroupEntity(MANAGEMENT);
			group.setName("Grupo " + MANAGEMENT);
			identityService.saveGroup(group);
		}

		// Usuário jerryag
		count = identityService.createUserQuery().userId(JERRY).count();
		if (count == 0) {
			User user = new UserEntity(JERRY);
			user.setFirstName("Jerry");
			user.setLastName("Adriane Gonçalves");
			identityService.saveUser(user);
		}

		// Usuário andreaco
		count = identityService.createUserQuery().userId(ANDREA).count();
		if (count == 0) {
			User user = new UserEntity(ANDREA);
			user.setFirstName("Andréa");
			user.setLastName("Cristina de Oliveira");
			identityService.saveUser(user);
		}

		// Usuário salger
		count = identityService.createUserQuery().userId(SALGER).count();
		if (count == 0) {
			User user = new UserEntity(SALGER);
			user.setFirstName("Salger");
			user.setLastName("Murphy");
			identityService.saveUser(user);
		}

		count = identityService.createGroupQuery().groupId(ACCOUNTANCY).groupMember(JERRY).count();
		if (count == 0) {
			identityService.createMembership(JERRY, ACCOUNTANCY);
		}

		count = identityService.createGroupQuery().groupId(MANAGEMENT).groupMember(ANDREA).count();
		if (count == 0) {
			identityService.createMembership(ANDREA, MANAGEMENT);
		}

		count = identityService.createGroupQuery().groupId(TESTER).groupMember(SALGER).count();
		if (count == 0) {
			identityService.createMembership(SALGER, TESTER);
		}

	}

	@RequestMapping(value = "/history/{processId}")
	public ProcessInstanceHistoryLog history(@PathVariable("processId") String processId, HttpServletRequest request) {

		log.info("Request recebido em " + request.getRequestURL() + " / processId: " + processId);

		ProcessInstanceHistoryLog result = historyService.createProcessInstanceHistoryLogQuery(processId)
				.includeActivities().includeTasks().includeVariables().includeVariableUpdates().singleResult();

		return result;
	}

}
