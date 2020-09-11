insert into module(id, name) values(2, 'crm');
insert into user(name) values('anonymous');

insert into status(name, module_id, is_system) values('LL_LEAD', 2, 'Y');
insert into status(name, module_id, is_system) values('LL_CUSTOMER', 2, 'Y');
insert into status(name, module_id, is_system) values('LL_INACTIVE', 2, 'Y');

insert into field_category(module_id, name, is_system) values(2, 'LL_COMPANY', 'Y');
insert into field_category(module_id, name, is_system) values(2, 'LL_PERSON', 'Y');
insert into field_category(module_id, name, is_system) values(2, 'LL_OTHER', 'Y');

insert into field(name, field_category_id, is_system) values('LL_SITE', 1, 'Y');
insert into field(name, field_category_id, is_system) values('LL_PHONE', 1, 'Y');
insert into field(name, field_category_id, is_system) values('LL_EMAIL', 1, 'Y');
insert into field(name, field_category_id, is_system) values('LL_ADDRESS', 1, 'Y');
insert into field(name, field_category_id, is_system) values('LL_FAX', 1, 'Y');
insert into field(name, field_category_id, is_system) values('LL_SKYPE_ID', 1, 'Y');
insert into field(name, field_category_id, is_system) values('LL_NOTES', 1, 'Y');

insert into field(name, field_category_id, is_system) values('LL_CELL_PHONE', 2, 'Y');
insert into field(name, field_category_id, is_system) values('LL_HOME_PHONE', 2, 'Y');
insert into field(name, field_category_id, is_system) values('LL_EMAIL', 2, 'Y');
insert into field(name, field_category_id, is_system) values('LL_ADDRESS', 2, 'Y');
insert into field(name, field_category_id, is_system) values('LL_SKYPE_ID', 2, 'Y');
insert into field(name, field_category_id, is_system) values('LL_NOTES', 2, 'Y');

insert into field(name, field_category_id, is_system) values('LL_PHONE', 3, 'Y');
insert into field(name, field_category_id, is_system) values('LL_EMAIL', 3, 'Y');
insert into field(name, field_category_id, is_system) values('LL_SKYPE_ID', 3, 'Y');
insert into field(name, field_category_id, is_system) values('LL_NOTES', 3, 'Y');

-- Temp contact
insert into entity(module_id, name, field_cat_id) values(2, 'Example', 1);
insert into entity_ex(id, entity_id, status_id, user_id) values(1, 1, 1, 1);
insert into entity_field(entity_id, field_id, value) values(1, 1, 'http://www.example.com/');
insert into entity_status_history (status_id, entity_id, user_id, created) values(1, 1, 1, current_timestamp);
insert into entity_user_history(entity_id, owner_id, user_id, created) values(1, 1, 1, current_timestamp);
