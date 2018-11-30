
create database activiti;

GRANT USAGE ON *.* TO `activiti`@`%` IDENTIFIED BY 'activiti' REQUIRE NONE;
GRANT Select  ON *.* TO `activiti`@`%`;
GRANT Insert  ON *.* TO `activiti`@`%`;
GRANT Update  ON *.* TO `activiti`@`%`;
GRANT Delete  ON *.* TO `activiti`@`%`;
GRANT Create  ON *.* TO `activiti`@`%`;
GRANT Drop  ON *.* TO `activiti`@`%`;
GRANT USAGE  ON *.* TO `activiti`@`%` WITH GRANT OPTION;
GRANT References  ON *.* TO `database`@`%`;
GRANT Index  ON *.* TO `activiti`@`%`;
GRANT Alter  ON *.* TO `activiti`@`%`;
GRANT Execute  ON *.* TO `activiti`@`%`;
GRANT Create View  ON *.* TO `activiti`@`%`;
GRANT Trigger  ON *.* TO `activiti`@`%`;
GRANT Select  ON `activiti`.* TO `activiti`@`%`;
GRANT Insert  ON `activiti`.* TO `activiti`@`%`;
GRANT Update  ON `activiti`.* TO `activiti`@`%`;
GRANT Delete  ON `activiti`.* TO `activiti`@`%`;
GRANT Create  ON `activiti`.* TO `activiti`@`%`;
GRANT Drop  ON `activiti`.* TO `activiti`@`%`;
GRANT USAGE  ON `activiti`.* TO `activiti`@`%` WITH GRANT OPTION;
GRANT References  ON `activiti`.* TO `activiti`@`%`;
GRANT Index  ON `activiti`.* TO `activiti`@`%`;
GRANT Alter  ON `activiti`.* TO `activiti`@`%`;
GRANT Create temporary tables  ON `activiti`.* TO `activiti`@`%`;
GRANT Lock tables  ON `activiti`.* TO `activiti`@`%`;
GRANT Create View  ON `activiti`.* TO `activiti`@`%`;
GRANT Show view  ON `activiti`.* TO `activiti`@`%`;
GRANT Create routine  ON `activiti`.* TO `activiti`@`%`;
GRANT Alter routine  ON `activiti`.* TO `activiti`@`%`;
GRANT Execute  ON `activiti`.* TO `activiti`@`%`;
GRANT Event  ON `activiti`.* TO `activiti`@`%`;
GRANT Trigger  ON `activiti`.* TO `activiti`@`%`;

