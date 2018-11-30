create database database;

CREATE TABLE `function` (
  `idt_function` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id do cargo',
  `des_function` varchar(50) NOT NULL COMMENT 'Descrição do cargo',
  PRIMARY KEY (`idt_function`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Cargos';

CREATE TABLE `employee` (
  `idt_employee` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id do funcionário',
  `nam_employee` varchar(100) NOT NULL COMMENT 'Nome do funcionário',
  `idt_function` int(11) NOT NULL,
  PRIMARY KEY (`idt_employee`),
  KEY `fk_employee_function` (`idt_function`),
  CONSTRAINT `fk_employee_function` FOREIGN KEY (`idt_function`) REFERENCES `function` (`idt_function`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Funcionários';

insert into function (idt_function, des_function) values (1, 'Analista de Sistemas');
insert into function (idt_function, des_function) values (2, 'Analista de Testes');
insert into function (idt_function, des_function) values (3, 'Gerente de Projetos');
insert into function (idt_function, des_function) values (4, 'Analista Frontend');

insert into employee (idt_employee, nam_employee, idt_function) values (1, 'Jerry Adriane Gonçalves', 1);
insert into employee (idt_employee, nam_employee, idt_function) values (2, 'Rodrigo Gonçalves de Oliveira', 1);
insert into employee (idt_employee, nam_employee, idt_function) values (3, 'Natália Viegas', 2);
insert into employee (idt_employee, nam_employee, idt_function) values (4, 'Ana Carolini', 2);
insert into employee (idt_employee, nam_employee, idt_function) values (5, 'Cristiano Palazzo', 3);

GRANT USAGE ON *.* TO `database`@`%` IDENTIFIED BY 'database' REQUIRE NONE;
GRANT Select  ON *.* TO `database`@`%`;
GRANT Insert  ON *.* TO `database`@`%`;
GRANT Update  ON *.* TO `database`@`%`;
GRANT Delete  ON *.* TO `database`@`%`;
GRANT Create  ON *.* TO `database`@`%`;
GRANT Drop  ON *.* TO `database`@`%`;
GRANT USAGE  ON *.* TO `database`@`%` WITH GRANT OPTION;
GRANT References  ON *.* TO `database`@`%`;
GRANT Index  ON *.* TO `database`@`%`;
GRANT Alter  ON *.* TO `database`@`%`;
GRANT Create View  ON *.* TO `database`@`%`;
GRANT Trigger  ON *.* TO `database`@`%`;
GRANT Select  ON `database`.* TO `database`@`%`;
GRANT Insert  ON `database`.* TO `database`@`%`;
GRANT Update  ON `database`.* TO `database`@`%`;
GRANT Delete  ON `database`.* TO `database`@`%`;
GRANT Create  ON `database`.* TO `database`@`%`;
GRANT Drop  ON `database`.* TO `database`@`%`;
GRANT USAGE  ON `database`.* TO `database`@`%` WITH GRANT OPTION;
GRANT References  ON `database`.* TO `database`@`%`;
GRANT Index  ON `database`.* TO `database`@`%`;
GRANT Alter  ON `database`.* TO `database`@`%`;
GRANT Create temporary tables  ON `database`.* TO `database`@`%`;
GRANT Lock tables  ON `database`.* TO `database`@`%`;
GRANT Create View  ON `database`.* TO `database`@`%`;
GRANT Show view  ON `database`.* TO `database`@`%`;
GRANT Create routine  ON `database`.* TO `database`@`%`;
GRANT Alter routine  ON `database`.* TO `database`@`%`;
GRANT Execute  ON `database`.* TO `database`@`%`;
GRANT Event  ON `database`.* TO `database`@`%`;
GRANT Trigger  ON `database`.* TO `database`@`%`;


